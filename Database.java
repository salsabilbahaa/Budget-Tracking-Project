import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a singleton database for storing and retrieving user data, including user profiles
 * and their associated budgets and incomes. Uses Gson for JSON serialization to persist
 * data in a file named "users.json". Provides methods to add, retrieve, and update users
 * and their budgets, ensuring thread-safe singleton access.
 */
public class Database {
    /**
     * The single instance of the Database class, initialized lazily.
     */
    private static Database instance = null;

    /**
     * The name of the file used to store user data in JSON format.
     */
    private static final String fileName = "users.json";

    /**
     * Gson instance configured for pretty printing and excluding fields without @Expose annotation.
     */
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    /**
     * The list of users managed by the database.
     */
    private List<User> users;

    /**
     * Private constructor to initialize the users list by loading data from the JSON file.
     * Prevents direct instantiation to enforce the singleton pattern.
     */
    private Database() {
        users = loadUsers();
    }

    /**
     * Retrieves the singleton instance of the Database, creating it if it does not exist.
     *
     * @return The single Database instance.
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * Retrieves the list of all users in the database.
     *
     * @return The list of users.
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Adds a new user to the database and saves the updated user list to the JSON file.
     *
     * @param user The User object to add.
     */
    public void addUser(User user) {
        users.add(user);
        saveUsers();
    }

    /**
     * Retrieves a user by their email address, ignoring case.
     *
     * @param email The email address of the user to find.
     * @return The User object with the matching email, or null if no user is found.
     */
    public User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Saves the current list of users to the JSON file.
     */
    public void saveUsers() {
        try (Writer writer = new FileWriter(fileName)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    /**
     * Loads the list of users from the JSON file. If the file does not exist or an error occurs,
     * returns an empty list.
     *
     * @return The list of users loaded from the file, or an empty list if loading fails.
     */
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

    /**
     * Adds a budget to the specified user’s budget list, saves the updated user list,
     * and notifies observers of the budget change.
     *
     * @param x     The User to whom the budget will be added.
     * @param budget The BudgetingPage to add to the user’s budgets.
     */
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

    /**
     * Retrieves the list of budgets for the specified user.
     *
     * @param x The User whose budgets are to be retrieved.
     * @return The list of BudgetingPage objects for the user, or an empty list if the user is not found.
     */
    public List<BudgetingPage> retrieveBudgets(User x) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(x.getEmail())) {
                return user.retrieveBudgets();
            }
        }
        return new ArrayList<>();
    }

    /**
     * Retrieves all budgets across all users in the database.
     *
     * @return A list of all BudgetingPage objects from all users.
     */
    public List<BudgetingPage> getAllBudgets() {
        List<BudgetingPage> allBudgets = new ArrayList<>();
        for (User user : users) {
            allBudgets.addAll(user.retrieveBudgets());
        }
        return allBudgets;
    }

    /**
     * Updates the specified user’s data in the database and saves the updated user list.
     *
     * @param updatedUser The User object with updated data to replace the existing user.
     */
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