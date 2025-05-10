import com.google.gson.annotations.Expose;

import java.util.Date;

public class Income {
    @Expose
    private String source;
    @Expose
    private double amount;
    @Expose
    private Date date;
    @Expose
    private String isRecurring;

    public Income(String source, double amount, Date date, String isRecurring) {
        this.source = source;
        this.amount = amount;
        this.date = date;
        this.isRecurring = isRecurring;
    }

    public String getSource() { return source; }
    public double getAmount() { return amount; }
    public Date getDate() { return date; }
    public String isRecurring() { return isRecurring; }

    @Override
    public String toString() {
        return source + " | $" + amount + " | " + date + " isRecurring: " + isRecurring;
    }
}