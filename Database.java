import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String fileName = "users.json";
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    private static List<User> users = loadUsers();

    public static List<User> getUsers() {
        return users;
    }

    public static void addUser(User user) {
        users.add(user);
        saveUsers();
    }

    public static User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    public static void saveUsers() {
        try (Writer writer = new FileWriter(fileName)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    private static List<User> loadUsers() {
        File file = new File(fileName);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (Reader reader = new FileReader(file)) {
            Type usersType = new TypeToken<List<User>>(){}.getType();
            return gson.fromJson(reader, usersType);
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void addBudget(String email, BudgetingPage budget) {
        User user = getUserByEmail(email);
        if (user != null) {
            user.addBudget(budget);
            saveUsers();
            budget.getBudget().notifyObservers();
        } else {
            System.out.println("User not found: " + email);
        }
    }

    public List<BudgetingPage> retrieveBudgets(String email) {
        User user = getUserByEmail(email);
        if (user != null) {
            return user.retrieveBudgets();
        }
        return new ArrayList<>();
    }

    public List<BudgetingPage> getAllBudgets() {
        List<BudgetingPage> allBudgets = new ArrayList<>();
        for (User user : users) {
            allBudgets.addAll(user.retrieveBudgets());
        }
        return allBudgets;
    }

    public static void updateUser(User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(updatedUser.getEmail())) {
                users.set(i, updatedUser);
                break;
            }
        }
        saveUsers();
    }

}