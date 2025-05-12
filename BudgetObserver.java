/**
 * Defines the contract for objects that observe changes to a Budget.
 * Implementations of this interface are notified when a Budget is updated,
 * allowing them to react to changes in budget data.
 */
public interface BudgetObserver {
    /**
     * Called when a Budget is updated, allowing the observer to react to the change.
     *
     * @param budget The Budget object that has been updated.
     */
    void update(Budget budget);
}
