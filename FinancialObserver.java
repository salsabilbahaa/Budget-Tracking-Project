/**
 * Defines the contract for objects that observe changes to financial data, including budgets and incomes.
 * Implementations of this interface are notified when a Budget or Income is added or updated,
 * allowing them to react to changes, such as updating a dashboard display.
 */
public interface FinancialObserver {
    /**
     * Called when a Budget is added or updated, allowing the observer to react to the change.
     *
     * @param budget The Budget object that has been added or updated.
     */
    void update(Budget budget);

    /**
     * Called when an Income is added or updated, allowing the observer to react to the change.
     *
     * @param income The Income object that has been added or updated.
     */
    void update(Income income);
}