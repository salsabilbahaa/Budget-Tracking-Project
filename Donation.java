public class Donation 
{
    private String charity;
    private double amount;

    public Donation(String charity, double amount) 
    {
        this.charity = charity;
        this.amount = amount;
    }

    public String getCharity() 
    {
        return charity;
    }

    public double getAmount() 
    {
        return amount;
    }
}