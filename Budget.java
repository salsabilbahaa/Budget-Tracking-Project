import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a budget with an amount and category, supporting observer notifications 
 * for changes. Uses Gson annotations for serialization and maintains a list of observers 
 * to implement the observer pattern.
 */
public class Budget {
    /** The monetary amount allocated to this budget, exposed for serialization. */
    @Expose
    private double amount;
    /** The category of this budget (e.g., food, travel), exposed for serialization. */
    @Expose
    private String category;
    /** List of observers to be notified of budget updates. */
    private List<BudgetObserver> observers = new ArrayList<>();

    /**
     * Initializes a new budget with a specified amount and category.
     * 
     * @param amount The budget’s monetary value.
     * @param category The type or purpose of the budget.
     */
    public Budget(double amount, String category) {
        this.amount = amount;
        this.category = category;
    }

    /**
     * Registers a new observer to receive updates when the budget changes.
     * 
     * @param observer The observer to add to the notification list.
     */
    public void addObserver(BudgetObserver observer) {
        // Append the observer to the list
        observers.add(observer);
    }

    /**
     * Unregisters an observer, stopping it from receiving further budget updates.
     * 
     * @param observer The observer to remove from the notification list.
     */
    public void removeObserver(BudgetObserver observer) {
        // Remove the specified observer
        observers.remove(observer);
    }

    /**
     * Alerts all registered observers about changes to this budget, triggering their 
     * update logic.
     */
    public void notifyObservers() {
        // Loop through observers and call their update method
        for (BudgetObserver observer : observers) {
            observer.update(this);
        }
    }

    /**
     * Retrieves the monetary amount of this budget.
     * 
     * @return The budget’s amount.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Retrieves the category of this budget.
     * 
     * @return The budget’s category.
     */
    public String getCategory() {
        return category;
    }
}
