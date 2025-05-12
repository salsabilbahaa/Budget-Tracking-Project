import java.util.Scanner;

public class Main {
    private static User currentUser = null;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("**Welcome to the Budget tracking Application!**");
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
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    currentUser = SignUpPage.run();
                    break;
                case 2:
                    currentUser = LoginPage.run();
                    break;
                case 3:
                    if (currentUser != null) {
                       BudgetingPage.run(currentUser);
                    }
                    else {
                        System.out.println("Please login or sign up first.");
                    }
                    break;
                case 4:
                    if (currentUser != null) {
                        IncomeTrackingPage.run(currentUser);
                    }
                    else {
                        System.out.println("Please login or sign up first.");
                    }
                    break;
                case 5:
                    ReminderPage.handleReminderPage();
                    break;
                case 6:
                    SavingsGoalsPage.handleSavingsPage();
                    break;
                case 7:
                    ExpenseTrackingPage.handleExpenseTrackingPage();
                    break;
                case 8:
                    DebtRepaymentPage.handleDebtRepaymentPage();
                    break;
                case 9:
                    DonationPage.handleDonationPage();
                    break;
                case 10:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}