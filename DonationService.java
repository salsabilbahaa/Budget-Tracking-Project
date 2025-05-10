import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DonationService 
{

    private static final String fileName = "donations.txt";

    public static void saveDonation(Donation donation) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true)))
        {
            writer.write(donation.getCharity() + "|" + donation.getAmount());
            writer.newLine();
        } 
        catch (IOException e) 
        {
            System.err.println("Error saving donation: " + e.getMessage());
        }
    }

    public static List<Donation> getAllDonations() 
    {
        List<Donation> donations = new ArrayList<>();
        File file = new File(fileName);
        
        if (!file.exists()) 
        {
            return donations;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split("\\|");
                if (parts.length == 2) 
                {
                    donations.add(new Donation(parts[0], Double.parseDouble(parts[1])));
                }
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Error loading donations: " + e.getMessage());
        }
        
        return donations;
    }
}