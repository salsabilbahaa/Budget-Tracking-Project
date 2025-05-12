/**
 * A dashboard that observes financial updates, including budgets and incomes, and displays
 * notifications when new budgets or incomes are added. Implements the FinancialObserver
 * interface to receive and process budget and income changes.
 */
public class DashBoard implements FinancialObserver {

    /**
     * Updates the dashboard when a new budget is added, printing a notification with
     * the budget’s category and amount to the console.
     *
     * @param budget The budget that triggered the update.
     */
    @Override
    public void update(Budget budget) {
        System.out.println("Dashboard updated: New budget added - Category: " + budget.getCategory() + ", Amount: " + budget.getAmount());
    }

    /**
     * Updates the dashboard when a new income is added, printing a notification with
     * the income’s source, amount, and recurrence status to the console.
     *
     * @param income The income that triggered the update.
     */
    @Override
    public void update(Income income) {
        System.out.println("Dashboard updated: New income added - Source: " + income.getSource() + ", Amount: " + income.getAmount() + ", Recurring: " + income.isRecurring());
    }
}