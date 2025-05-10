import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class DebtRepaymentPage 
{
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) 
    {
        System.out.println();
        System.out.println("------ Debt Repayment System ------");

        handleDebtRepaymentPage();
    }

    private static void handleDebtRepaymentPage() 
    {
        while (true) 
        {
            System.out.println();
            System.out.println("1. Add a new debt");
            System.out.println("2. View all existing debts");
            System.out.println("3. View total debts");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 0) 
            {
                System.out.println();
                System.out.println("-----------------------------------");
                return;
            } 
            else if (choice == 1) 
            {
                addNewDebt();
            } 
            else if (choice == 2) 
            {
                viewExistingDebts();
            } 
            else if (choice == 3) 
            {
                viewTotalDebt();
            } 
            else 
            {
                System.out.println("Invalid choice");
            }
        }
    }

    private static void addNewDebt() 
    {
        System.out.println();
        System.out.println("Enter new debt details:");
        System.out.print("Amount: ");
        double amount = scanner.nextDouble();
        
        System.out.print("APR (%): ");
        double apr = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Due Date (yyyy-MM-dd): ");
        String dueDate = scanner.nextLine();

        try
        {
            LocalDate deptDate = LocalDate.parse(dueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Debt debt = new Debt(amount, apr, deptDate);
            
            if (DebtValidator.isValid(debt)) 
            {
                DebtService.saveDebt(debt);
                System.out.println();
                System.out.println("Debt added successfully");
                System.out.println("Updated total debt: " + DebtService.calculateTotalDebt());
                System.out.println();
            }
            else 
            {
                System.out.println("Error: Invalid data (amount must be positive, APR must be positive, due date must be in the in future).");
            }

        }
        catch(DateTimeParseException e)
        {
            System.out.println("Invalid date format. Please use yyyy-MM-dd");
        }
    }

    private static void viewExistingDebts() 
    {
        List<Debt> debts = DebtService.getAllDebts();
        if (debts.isEmpty()) 
        {
            System.out.println("No debts found.");
            return;
        }

        System.out.println();
        System.out.println("All Existing Debts:");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
 
        for (int i = 0; i < debts.size(); i++) 
        {
            Debt debt = debts.get(i);
            System.out.println((i + 1) + ". " + debt.getAmount() + " - " + debt.getApr() + " - " + debt.getDueDate().format(formatter));
        }
    }

    private static void viewTotalDebt() 
    {
        System.out.println();
        System.out.println("Total Debt: " + DebtService.calculateTotalDebt());
    }
}