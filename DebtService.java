import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DebtService 
{
    private static final String fileName = "debts.txt";
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void saveDebt(Debt debt) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) 
        {
            writer.write(debt.getAmount() + "|" + debt.getApr() + "|" + debt.getDueDate().format(dateFormatter));
            writer.newLine();
        } 
        catch (IOException e) 
        {
            System.err.println("Error saving debt: " + e.getMessage());
        }
    }

    public static List<Debt> getAllDebts() 
    {
        List<Debt> debts = new ArrayList<>();
        File file = new File(fileName);
        
        if (!file.exists()) 
        {
            return debts;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split("\\|");
                if (parts.length == 3) 
                {
                    double amount = Double.parseDouble(parts[0]);
                    double apr = Double.parseDouble(parts[1]);
                    LocalDate dueDate = LocalDate.parse(parts[2], dateFormatter);
                    debts.add(new Debt(amount, apr, dueDate));
                }
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Error loading debts: " + e.getMessage());
        }
        
        return debts;
    }

    public static double calculateTotalDebt() 
    {
        List<Debt> debts = getAllDebts(); 
        double total = 0.0;
        
        for (Debt debt : debts) 
        {
            total += debt.getAmount();
        }
        
        return total;
    }
}
