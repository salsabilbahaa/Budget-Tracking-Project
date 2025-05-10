import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class ExpenseService 
{
    private static final String fileName = "expenses.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public static void saveExpense(Expense expense) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) 
        {
            writer.write(expense.getAmount() + "|" + expense.getCategory() + "|" + expense.getDate().format(formatter));
            writer.newLine();
        } 
        catch (IOException e) 
        {
            System.err.println("Error saving expense: " + e.getMessage());
        }
    }

    public static List<Expense> getAllExpenses() 
    {
        List<Expense> expenses = new ArrayList<>();
        File file = new File(fileName);
        
        if (!file.exists()) 
        {
            return expenses;
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
                    String category = parts[1];
                    LocalDate date = LocalDate.parse(parts[2], formatter);
                    expenses.add(new Expense(amount, category, date));
                }
            }
        } 
        catch (IOException | DateTimeParseException e) 
        {
            System.err.println("Error loading expenses: " + e.getMessage());
        }
        
        return expenses;
    }
}
