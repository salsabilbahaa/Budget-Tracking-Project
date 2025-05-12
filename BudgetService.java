import java.util.*;

/**
 * Manages budget-related operations, such as creating and retrieving budgets for users.
 * Integrates with validation, database, and dashboard components to ensure valid budgets
 * are stored and displayed correctly.
 */
public class BudgetService {
    /** Service for validating budget inputs. */
    private ValidationService validateBudget = new ValidationService();
    /** Singleton database instance for storing and retrieving budget data. */
    private Database database = Database.getInstance();
    /** Dashboard for observing budget updates. */
    private DashBoard dashboard = new DashBoard();

    /**
     * Retrieves the database instance used by this service.
     * 
     * @return The singleton Database instance.
     */
    public Database getDatabase() {
        return database;
    }

    /**
     * Creates a new budget for a user if the provided amount and category are valid.
     * Stores the budget in the database, links it to a budgeting page, and notifies
     * the dashboard observer. Outputs the result to the console.
     * 
     * @param amount   The monetary value of the budget.
     * @param category The type or purpose of the budget (e.g., food, travel).
     * @param user     The user associated with this budget.
     * @return True if the budget is created successfully, false if validation fails.
     */
    public boolean createBudget(double amount, String category, User user) {
        // Check if the budget inputs are valid
        if (validateBudget.validateBudget(amount, category)) {
            // Create a new budget and wrap it in a budgeting page
            Budget budget = new Budget(amount, category);
            BudgetingPage budgetingPage = new BudgetingPage(budget);
            // Register the dashboard as an observer for updates
            budget.addObserver(dashboard);
            // Save the budget to the database
            database.addBudget(user, budgetingPage);
            System.out.println("Successfully created budget");
            return true;
        } else {
            System.out.println("Invalid budget");
            return false;
        }
    }

    /**
     * Fetches and displays all budgets associated with a user, printing each budget’s
     * category and amount to the console.
     * 
     * @param user The user whose budgets are to be retrieved.
     */
    public void retrieveBudgets(User user) {
        // Get the user’s budgets from the database
        List<BudgetingPage> budgets = database.retrieveBudgets(user);
        // Iterate through budgets and print details
        for (BudgetingPage budgetingPage : budgets) {
            Budget budget = budgetingPage.getBudget(); // Use Budget from BudgetingPage
            System.out.println("Budget: " + budget.getCategory() + " " + budget.getAmount());
        }
    }
}
