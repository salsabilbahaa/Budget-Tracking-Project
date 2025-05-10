public class PaymentGateway 
{
    public static boolean processPayment(Donation donation) 
    {
        System.out.println("Processing payment of " + donation.getAmount() +  " to " + donation.getCharity());
        return true; 
    }
}