import java.util.Scanner;
import java.util.List;

public class DonationPage 
{
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) 
    {
        System.out.println();
        System.out.println("------ Donation System ------");
        handleDonationPage();
    }

    private static void handleDonationPage() 
    {
        while (true) 
        {
            System.out.println();
            System.out.println("1. Make a donation");
            System.out.println("2. View donation history");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            if (choice == 0) 
            {
                System.out.println();
                System.out.println("-----------------------------");
                return;
            } 
            else if (choice == 1) 
            {
                makeDonation();
            } 
            else if (choice == 2) 
            {
                viewDonationHistory();
            } 
            else 
            {
                System.out.println("Invalid choice");
            }
        }
    }

    private static void makeDonation() 
    {
        System.out.println();
        System.out.print("Enter charity name: ");
        String charity = scanner.nextLine();
        
        System.out.print("Enter donation amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); 
        
        Donation donation = new Donation(charity, amount);
        
        if (DonationValidator.isValid(donation)) 
        {
            if (PaymentGateway.processPayment(donation)) 
            {
                DonationService.saveDonation(donation);
                System.out.println();
                
                System.out.println("Thank you for your donation to " + charity);
            } 
            else 
            {
                System.out.println("Payment processing failed");
            }
        } 
        else 
        {
            System.out.println("Error: Amount must be positive and charity name cannot be empty");
        }
    }

    private static void viewDonationHistory() 
    {
        List<Donation> donations = DonationService.getAllDonations();
        if (donations.isEmpty()) 
        {
            System.out.println("No donations found");
            return;
        }

        System.out.println();
        System.out.println("Donation History:");
    
        for (int i = 0; i < donations.size(); i++) 
        {
            Donation d = donations.get(i);
            System.out.println((i + 1) + ". " + d.getCharity() + " - " + d.getAmount());
        }
    }
}