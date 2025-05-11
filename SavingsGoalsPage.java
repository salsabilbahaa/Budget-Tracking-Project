import java.util.List;
import java.util.Scanner;

public class SavingsGoalsPage 
{
    private static Scanner scanner = new Scanner(System.in);

    public static void handleSavingsPage()
    {
        while (true) 
        {
            System.out.println();
            System.out.println("1. Create a new savings goal");
            System.out.println("2. View all goals");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 0) 
            {
                System.out.println();
                System.out.println("------------------------------------");
                return;
            }
            else if(choice == 1)
            {
                createGoal();
            }
            else if(choice == 2)
            {
                viewAllGoals();
            }
            else
            {
                System.out.println("Invalid choice");
            }
        }
    }

    public static void createGoal() 
    {
        System.out.println();
        System.out.print("Enter goal name: ");
        String goalName = scanner.nextLine();
        
        System.out.print("Enter target amount: ");
        double targetAmount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println();

        SavingsGoal goal = new SavingsGoal(goalName, targetAmount);

        if (SavingsValidator.isValid(goal)) 
        {
            SavingsService.saveGoal(goal);
            System.out.println("Goal saved successfully");
        }
        else 
        {
            System.out.println("Invalid data (name cannot be empty, amount must be positive).");
        }
    }

    private static void viewAllGoals() 
    {
        List<SavingsGoal> goals = SavingsService.getAllGoals();
        if (goals.isEmpty()) 
        {
            System.out.println("No goals found.");
            return;
        }

        System.out.println();
        System.out.println("All Savings Goals:");
        
        for (int i = 0; i < goals.size(); i++) 
        {
            SavingsGoal goal = goals.get(i);
            System.out.println((i + 1) + ". " + goal.getGoalName() + " - " + goal.getTargetAmount());
        }
    }
}