import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("**Welcome to the Budget tracking Application!**");
            System.out.println("1. Sign Up");
            System.out.println("2. Log in");
            System.out.println("3. Create budget & analysis");
            System.out.println("4. Exit");
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
                    SignUpPage.run();
                    break;
                case 2:
                    LoginPage.run();
                    break;
                case 3:
                    BudgetingPage.run();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}