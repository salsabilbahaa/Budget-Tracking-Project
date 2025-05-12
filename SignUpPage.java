import java.util.Scanner;

/**
 * Facilitates an interactive user sign-up process, collecting and validating user 
 * details, verifying OTP, and registering the user via authentication services. 
 * Returns the created user or null if sign-up fails.
 */
public class SignUpPage {

    /**
     * Runs an interactive sign-up process, prompting for user details (email, username, 
     * password, phone), validating inputs, verifying OTP, and creating a user account. 
     * Outputs progress and errors to the console and redirects to the dashboard on success.
     * 
     * @return The newly created User object if sign-up succeeds, or null if it fails.
     */
    public static User run() {
        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);

        String email, username, password, phone;
        // Get the singleton authentication service
        AuthenticationService authService = AuthenticationService.getInstance();

        // Loop until valid inputs are provided
        while (true) {
            System.out.print("Email: ");
            email = scanner.nextLine();

            System.out.print("Username: ");
            username = scanner.nextLine();

            System.out.print("Password: ");
            password = scanner.nextLine();

            System.out.print("Phone: ");
            phone = scanner.nextLine();

            // Validate user inputs
            if (!authService.validateInputs(email, username, password, phone)) {
                System.out.println("Invalid input. Follow these rules:\nThe email should contain @ \nthe password should contain letters and numbers\n" +
                        "The password should be at least 8 characters long\nThe phone number should be at least 10 characters long.");
            } else {
                break;
            }
        }

        // Send OTP to the user’s phone
        OTPService.sendOTP(phone);
        // Loop until valid OTP is provided
        while (true) {
            System.out.print("Enter the OTP sent to your phone: ");
            int otp = scanner.nextInt();

            // Verify the OTP
            if (!OTPService.verifyOTP(otp)) {
                System.out.println("Invalid OTP. Please try again.");
            } else {
                break;
            }
        }
        // Attempt to sign up the user
        if (!authService.signUp(email, username, password, phone)) {
            System.out.println("Sign up failed.");
            return null;
        } else {
            System.out.println("Redirecting to dashboard...");
            // Retrieve the newly created user from the database
            Database database = Database.getInstance();
            return database.getUserByEmail(email);
        }
    }
}
