import java.util.List;

/**
 * A class responsible for generating spending analysis for a user based on their budget data.
 * It interacts with a database to retrieve budget information and performs analysis on spending.
 */
public class AnalysisEngine {
    /** The database instance used to retrieve budget data. */
    private Database database;

    /**
     * Constructs an AnalysisEngine with a specified database.
     *
     * @param database The database instance to be used for retrieving budget data.
     */
    public AnalysisEngine(Database database) {
        this.database = database;
    }

    /**
     * Generates a spending analysis for the specified user by retrieving their budgets from the database,
     * calculating total spending, and providing feedback based on the spending amount.
     * The analysis result is printed to the console, including total spending, a spending assessment,
     * and a completion message.
     *
     * @param user The user for whom the spending analysis is to be generated.
     */
    public void generateSpendingAnalysis(User user) {
        // Retrieve all budgets associated with the user from the database
        List<BudgetingPage> budgets = database.retrieveBudgets(user);
        double totalSpending = 0;
        // Iterate through each budgeting page to sum up the budget amounts
        for (BudgetingPage budgetingPage : budgets) {
            Budget budget = budgetingPage.getBudget();
            if (budget != null) {
                totalSpending += budget.getAmount();
            }
        }
        // Print the total spending amount
        System.out.println("Total Spending: " + totalSpending);
        // Assess spending level based on a threshold of 15000
        if (totalSpending > 15000) {
            System.out.println("Spending is very high this month");
        } else {
            System.out.println("Spending is nice in this month");
        }
        // Confirm successful completion of the analysis
        System.out.println("Spending Analysis is done successfully");
    }
}
