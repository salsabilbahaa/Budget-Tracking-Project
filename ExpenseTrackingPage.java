import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ExpenseTrackingPage 
{
    private static final Scanner scanner = new Scanner(System.in);

    public static void handleExpenseTrackingPage()
    {
        while (true) 
        {
            System.out.println();
            System.out.println("1. Add a new Expense");
            System.out.println("2. View expense history");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 0) 
            {
                System.out.println();
                System.out.println("-------------------------------------");
                return;
            } 
            else if (choice == 1) 
            {
                addNewExpense();
            } 
            else if (choice == 2) 
            {
                viewExpenseHistory();
            } 
            else 
            {
                System.out.println("Invalid choice");
            }
        }
    }

    private static void addNewExpense() 
    {
        viewExpenseHistory();

        System.out.println();
        System.out.println("Enter new expense details:");
        System.out.print("Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Category: ");
        String category = scanner.nextLine();
        
        System.out.print("Date (yyyy-MM-dd): ");
        String date = scanner.nextLine();

        try
        {
            LocalDate expenseDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Expense expense = new Expense(amount, category, expenseDate);
            
            if (ExpenseValidator.isValid(expense)) 
            {
                ExpenseService.saveExpense(expense);
                System.out.println();
                System.out.println("Expense added successfully!");
            }
            else
            {
                System.out.println("Invalid expense data. Please check amount, category, and date.");
            }
        
        }
        catch (DateTimeParseException e)
        {
            System.out.println("Invalid date format. Please use yyyy-MM-dd");
        }
    }

    private static void viewExpenseHistory() 
    {
        List<Expense> expenses = ExpenseService.getAllExpenses();
        if (expenses.isEmpty()) 
        {
            System.out.println();
            System.out.println("No expenses found.");
            return;
        }

        System.out.println();
        System.out.println("All Expenses: ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < expenses.size(); i++) 
        {
            Expense expense = expenses.get(i);
            System.out.println((i + 1) + ". " + expense.getCategory() + " - " + expense.getAmount() + " - " + expense.getDate().format(formatter));
        }
    }
}