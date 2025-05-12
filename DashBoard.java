/**
 * A dashboard that observes budget updates and displays notifications when new budgets 
 * are added. Implements the BudgetObserver interface to receive and process budget changes.
 */
public class DashBoard implements BudgetObserver {

    /**
     * Updates the dashboard when a new budget is added, printing a notification with 
     * the budget’s category and amount to the console.
     * 
     * @param budget The budget that triggered the update.
     */
    @Override
    public void update(Budget budget) {
        // Display the new budget’s details
        System.out.println("Dashboard updated: New budget added - Category: " + budget.getCategory() + ", Amount: " + budget.getAmount());
    }
}
