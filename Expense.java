import java.time.LocalDate;

public class Expense 
{
    private double amount;
    private String category;
    private LocalDate date;

    public Expense(double amount, String category, LocalDate date) 
    {
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public double getAmount() 
    { 
        return amount; 
    }
    
    public String getCategory() 
    { 
        return category; 
    }

    public LocalDate getDate() 
    { 
        return date; 
    }
}