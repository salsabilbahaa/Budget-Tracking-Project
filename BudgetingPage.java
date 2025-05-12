import com.google.gson.annotations.Expose;
import java.util.Scanner;

/**
 * Represents a single budgeting page that holds a budget and facilitates user interaction
 * for creating budgets. Integrates with budget services and analysis engines to manage and
 * analyze budget data, with Gson support for serialization.
 */
public class BudgetingPage {
    /** The budget associated with this page, exposed for serialization. */
    @Expose
    private Budget budget;
    /** Shared budget service instance for creating and managing budgets. */
    static BudgetService budgetService = new BudgetService();
    /** Shared analysis engine for generating spending reports. */
    static AnalysisEngine analysis = new AnalysisEngine(budgetService.getDatabase());

    /**
     * Initializes a budgeting page with a specified budget.
     * 
     * @param budget The budget to associate with this page.
     */
    public BudgetingPage(Budget budget) {
        this.budget = budget;
    }

    /**
     * Retrieves the budget linked to this budgeting page.
     * 
     * @return The associated budget.
     */
    public Budget getBudget() {
        return budget;
    }

    /**
     * Runs an interactive session to create a new budget for a user. Prompts for category
     * and amount, creates a budget, and triggers spending analysis if creation succeeds.
     * 
     * @param user The user for whom the budget is being created.
     */
    public static void run(User user) {
        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Prompt for budget details
        System.out.print("Enter budget category (e.g., Transport): ");
        String category = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        // Create a new budget and budgeting page
        Budget budget = new Budget(amount, category);
        BudgetingPage budgetingPage = new BudgetingPage(budget);
        // Attempt to create the budget and run analysis if successful
        if (budgetService.createBudget(amount, category, user)) {
            analysis.generateSpendingAnalysis(user); // Validate and analyze
        }
    }
}
