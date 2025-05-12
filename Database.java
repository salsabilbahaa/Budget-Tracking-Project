import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static Database instance = null;
    private static final String fileName = "users.json";
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    private List<User> users;

    // Private constructor to initialize the users list
    private Database() {
        users = loadUsers();
    }

    // Public method to get the single instance
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
        saveUsers();
    }

    public User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    public void saveUsers() {
        try (Writer writer = new FileWriter(fileName)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    private List<User> loadUsers() {
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

    public void addBudget(User x, BudgetingPage budget) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(x.getEmail())) {
                user.addBudget(budget);
                saveUsers();
                budget.getBudget().notifyObservers();
                return;
            }
        }
        System.out.println("User not found");
    }

    public List<BudgetingPage> retrieveBudgets(User x) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(x.getEmail())) {
                return user.retrieveBudgets();
            }
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

    public void updateUser(User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(updatedUser.getEmail())) {
                users.set(i, updatedUser);
                break;
            }
        }
        saveUsers();
    }
}