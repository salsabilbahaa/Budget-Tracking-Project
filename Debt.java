import java.time.LocalDate;

public class Debt 
{
    private double amount;
    private double apr;
    private LocalDate dueDate;

    public Debt(double amount, double apr, LocalDate dueDate) 
    {
        this.amount = amount;
        this.apr = apr;
        this.dueDate = dueDate;
    }

    public double getAmount() 
    { 
        return amount; 
    }

    public double getApr() 
    { 
        return apr; 
    }

    public LocalDate getDueDate() 
    { 
        return dueDate; 
    }

}