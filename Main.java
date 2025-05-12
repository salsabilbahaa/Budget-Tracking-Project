import java.util.Scanner;

/**
 * Author :Ola Ghoneim , Salsabil Bahaa Eldin ,Mariem Refaey
 * Serves as the entry point for the Budget Tracking Application, providing a menu-driven 
 * interface for users to sign up, log in, manage budgets, track finances, and access various 
 * features. Maintains the current user session and directs to appropriate pages based on 
 * user choices.
 */
public class Main {
    /** Tracks the currently logged-in user, or null if no user is logged in. */
    private static User currentUser = null;

    /**
     * Runs the main application loop, displaying a menu of options and handling user input 
     * to navigate through sign-up, login, budget creation, and other financial tracking features.
     * Continues until the user chooses to exit.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Main application loop
        while (true) {
            // Display menu options
            System.out.println("**Welcome to the Budget Tracking Application!**");
            System.out.println("1. Sign Up");
            System.out.println("2. Log in");
            System.out.println("3. Create budget & analysis");
            System.out.println("4. Income Tracking Page");
            System.out.println("5. Reminders Page");
            System.out.println("6. Saving and Goals Page");
            System.out.println("7. Expense Tracking Page");
            System.out.println("8. Debt Repayment Page");
            System.out.println("9. Donation Page");
            System.out.println("10. Exit");
            System.out.println("____________________________");
            System.out.print("Enter your choice: ");

            int choice;
            // Handle invalid input for menu choice
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            // Process user choice
            switch (choice) {
                case 1:
                    // Run sign-up process
                    currentUser = SignUpPage.run();
                    break;
                case 2:
                    // Run login process
                    currentUser = LoginPage.run();
                    break;
                case 3:
                    // Run budget creation and analysis if user is logged in
                    if (currentUser != null) {
                        BudgetingPage.run(currentUser);
                    } else {
                        System.out.println("Please login or sign up first.");
                    }
                    break;
                case 4:
                    // Run income tracking if user is logged in
                    if (currentUser != null) {
                        IncomeTrackingPage.run(currentUser);
                    } else {
                        System.out.println("Please login or sign up first.");
                    }
                    break;
                case 5:
                    // Access reminders page
                    ReminderPage.handleReminderPage();
                    break;
                case 6:
                    // Access savings and goals page
                    SavingsGoalsPage.handleSavingsPage();
                    break;
                case 7:
                    // Access expense tracking page
                    ExpenseTrackingPage.handleExpenseTrackingPage();
                    break;
                case 8:
                    // Access debt repayment page
                    DebtRepaymentPage.handleDebtRepaymentPage();
                    break;
                case 9:
                    // Access donation page
                    DonationPage.handleDonationPage();
                    break;
                case 10:
                    // Exit the application
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    // Handle invalid menu choice
                    System.out.println("Invalid choice.");
            }
        }
    }
}
