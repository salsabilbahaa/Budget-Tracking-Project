import java.util.Scanner;

public class LoginPage {
    public static User run() {
        Scanner scanner = new Scanner(System.in);
        String email, password;

        System.out.print("Email: ");
        email = scanner.nextLine();

        System.out.print("Password: ");
        password = scanner.nextLine();
        AuthenticationService authService = AuthenticationService.getInstance();
        Database database = Database.getInstance();

        if (!authService.verifyCredentials(email, password)) {
            System.out.println("Login failed.");
            return null;
        }
        else{
            System.out.println("Redirecting to dashboard...");
            return (database.getUserByEmail(email));
        }
    }
}