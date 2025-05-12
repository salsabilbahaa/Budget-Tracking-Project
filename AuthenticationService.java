/**
 * Handles user authentication processes, including account creation, input validation, 
 * and login verification. It uses a singleton pattern to ensure a single instance and 
 * communicates with a database to manage user records.
 */
public class AuthenticationService {
    // Create a private static instance of the class
    /** Singleton instance of the AuthenticationService for global access. */
    private static AuthenticationService instance = null;

    /**
     * Private constructor to restrict instantiation, supporting the singleton design.
     */
    private AuthenticationService() {
    }

    /**
     * Returns the single instance of this authentication service, initializing it if 
     * it hasn't been created yet.
     * 
     * @return The sole AuthenticationService instance.
     */
    public static AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }

    /**
     * Evaluates user registration inputs to ensure they meet basic requirements: 
     * email must include an '@' symbol, password must have at least one number and 
     * be 8+ characters, and phone number must be 10+ digits. Username is accepted 
     * but not validated.
     * 
     * @param email The email address to verify.
     * @param username The user's chosen username (not checked in this method).
     * @param password The password to validate.
     * @param phoneNumber The phone number to check.
     * @return True if inputs satisfy all conditions, false otherwise.
     */
    public boolean validateInputs(String email, String username, String password, String phoneNumber) {
        // Confirm email format, password strength, and phone number length
        return email.contains("@") && password.matches(".*\\d.*") && password.length() >= 8 && phoneNumber.length() >= 10;
    }

    /**
     * Creates a new user account if the email is unique. Stores the user’s details 
     * in the database and outputs the result (success or failure) to the console.
     * 
     * @param email The email for the new account.
     * @param username The username for the new account.
     * @param password The password for the new account.
     * @param phoneNumber The phone number for the new account.
     * @return True if the account is created, false if the email is already registered.
     */
    public boolean signUp(String email, String username, String password, String phoneNumber) {
        // Access the singleton database
        Database database = Database.getInstance();
        // Check for existing user with the same email
        User check = database.getUserByEmail(email);
        if (check != null) {
            System.out.println("Email already exists");
            return false;
        }

        // Register the new user
        User user = new User(email, username, password, phoneNumber);
        database.addUser(user);
        System.out.println("User signed up successfully.");
        return true;
    }

    /**
     * Checks if a user’s email and password match a stored account. Outputs whether 
     * the login succeeded, the email wasn’t found, or the password was wrong.
     * 
     * @param email The email address to look up.
     * @param password The password to compare.
     * @return True if the credentials are correct, false if the email or password is invalid.
     */
    public boolean verifyCredentials(String email, String password) {
        // Retrieve the database instance
        Database database = Database.getInstance();
        // Search for the user by email
        User user = database.getUserByEmail(email);

        if (user == null) {
            System.out.println("Email not found.");
            return false;
        } else if (user.getPassword().equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Password is invalid.");
            return false;
        }
    }
}
