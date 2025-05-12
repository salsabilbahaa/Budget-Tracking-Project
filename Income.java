import com.google.gson.annotations.Expose;
import java.util.Date;

/**
 * Represents an income record for a user, containing details such as the income source,
 * amount, date, and recurrence status. Supports serialization with Gson annotations
 * for storage and retrieval in a database.
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
     * Constructs a new Income object with the specified source, amount, date, and recurrence status.
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