import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("**Welcome to the Budget tracking Application!**");
        System.out.println("1. Sign Up");
        System.out.println("2. Log in");
        System.out.println("____________________________");

        System.out.println("Enter your choice:");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                SignUpPage.run();
                break;
            case 2:
                // login
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}