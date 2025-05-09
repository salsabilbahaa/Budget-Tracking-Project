import java.util.Scanner;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("**Welcome to the Budget tracking Application!**");
        System.out.println("1. Sign Up");
        System.out.println("2. Log in");
        System.out.println("3. Create budget & analysis");
        System.out.println("4. Exit");

        System.out.println("____________________________");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (true) {
            switch (choice) {
                case 1:
                    SignUpPage.run();
                    break;
                case 2:
                     LoginPage.run();
                    break;
                case 3:
                    BudgetMain.run();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

            scanner.close();
        }
    }
}
