public class DonationValidator 
{
    public static boolean isValid(Donation donation) 
    {
        return donation.getAmount() > 0 && !donation.getCharity().trim().isEmpty();
    }
}