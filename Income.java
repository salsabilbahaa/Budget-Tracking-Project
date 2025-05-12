import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents an income record for a user, containing details such as the income source,
 * amount, date, and recurrence status. Supports serialization with Gson annotations
 * for storage and retrieval in a database and notifies observers of changes.
 */
public class Income {
    /**
     * The source of the income (e.g., "Freelance"), exposed for serialization.
     */
    @Expose
    private String source;

    /**
     * The monetary amount of the income, exposed for serialization.
     */
    @Expose
    private double amount;

    /**
     * The date the income was received or recorded, exposed for serialization.
     */
    @Expose
    private Date date;

    /**
     * Indicates whether the income is recurring (e.g., "Yes" or "No"), exposed for serialization.
     */
    @Expose
    private String isRecurring;

    /**
     * List of observers to be notified of income updates.
     */
    private List<FinancialObserver> observers = new ArrayList<>();

    /**
     * Constructs a new Income object with the specified source, amount, date, and recurrence status,
     * and notifies registered observers of the new income.
     *
     * @param source      The source of the income (e.g., "Freelance").
     * @param amount      The monetary amount of the income.
     * @param date        The date the income was received or recorded.
     * @param isRecurring The recurrence status of the income (e.g., "Yes" or "No").
     */
    public Income(String source, double amount, Date date, String isRecurring) {
        this.source = source;
        this.amount = amount;
        this.date = date;
        this.isRecurring = isRecurring;
        notifyObservers();
    }

    /**
     * Registers a new observer to receive updates when the income changes.
     *
     * @param observer The observer to add to the notification list.
     */
    public void addObserver(FinancialObserver observer) {
        observers.add(observer);
    }

    /**
     * Unregisters an observer, stopping it from receiving further income updates.
     *
     * @param observer The observer to remove from the notification list.
     */
    public void removeObserver(FinancialObserver observer) {
        observers.remove(observer);
    }

    /**
     * Alerts all registered observers about changes to this income, triggering their update logic.
     */
    private void notifyObservers() {
        for (FinancialObserver observer : observers) {
            observer.update(this);
        }
    }

    /**
     * Retrieves the source of the income.
     *
     * @return The income source.
     */
    public String getSource() { return source; }

    /**
     * Retrieves the monetary amount of the income.
     *
     * @return The income amount.
     */
    public double getAmount() { return amount; }

    /**
     * Retrieves the date the income was received or recorded.
     *
     * @return The income date.
     */
    public Date getDate() { return date; }

    /**
     * Retrieves the recurrence status of the income.
     *
     * @return The recurrence status (e.g., "Yes" or "No").
     */
    public String isRecurring() { return isRecurring; }

    /**
     * Returns a string representation of the income record, including source, amount,
     * date, and recurrence status.
     *
     * @return A formatted string describing the income record.
     */
    @Override
    public String toString() {
        return source + " | $" + amount + " | " + date + " isRecurring: " + isRecurring;
    }
}