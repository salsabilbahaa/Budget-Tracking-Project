import java.util.Scanner;

/**
 * Handles user login functionality by collecting email and password input,
 * verifying credentials, and retrieving the corresponding user from the database.
 * Provides a simple command-line interface for user authentication.
 */
public class LoginPage {
    /**
     * Prompts the user for email and password, verifies the credentials using the
     * AuthenticationService, and retrieves the authenticated user from the Database.
     * If authentication fails, returns null and displays a failure message.
     * If successful, redirects to the dashboard and returns the User object.
     *
     * @return The authenticated User object if login is successful, or null if login fails.
     */
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