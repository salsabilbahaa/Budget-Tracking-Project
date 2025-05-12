import java.util.Set;

/**
 * Provides validation logic for budget and income data, ensuring budgets meet amount 
 * and category criteria and income values are positive. Maintains a set of valid 
 * budget categories and an income limit for validation.
 */
class ValidationService {

    /** Maximum allowable budget amount, set to 20,000. */
    private static final int IncomeLimit = 20000;
    /** Set of valid budget categories, including both capitalized and lowercase variants. */
    private static final Set<String> Categories = Set.of(
            "Food", "Rent", "Utilities", "Transport",
            "Healthcare", "Education", "Entertainment", "Savings",
            "food", "rent", "utilities", "transport",
            "healthcare", "education", "entertainment", "savings"
    );

    /**
     * Validates a budget by checking if the amount is non-negative, within the income 
     * limit, and if the category is in the predefined set of valid categories.
     * 
     * @param amount   The budget amount to validate.
     * @param category The budget category to check.
     * @return True if the budget meets all validation criteria, false otherwise.
     */
    public boolean validateBudget(double amount, String category) {
        // Ensure amount is non-negative, within limit, and category is valid
        return (amount <= IncomeLimit && amount >= 0 && Categories.contains(category));
    }

    /**
     * Checks if an income amount is valid by ensuring it is positive.
     * 
     * @param amount The income amount to validate.
     * @return True if the amount is greater than zero, false otherwise.
     */
    public static boolean validateIncomeData(double amount) {
        // Verify income is positive
        return amount > 0;
    }
}
