public class AuthenticationService {
    // Create a private static instance of the class
    private static AuthenticationService instance = null;

    private AuthenticationService() {
    }

    public static AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }

    public boolean validateInputs(String email, String username, String password, String phoneNumber) {
        return email.contains("@") && password.matches(".*\\d.*") && password.length() >= 8 && phoneNumber.length() >= 10;
    }

    public boolean signUp(String email, String username, String password, String phoneNumber) {
        Database database = Database.getInstance();
        User check = database.getUserByEmail(email);
        if (check != null) {
            System.out.println("Email already exists");
            return false;
        }

        User user = new User(email, username, password, phoneNumber);
        database.addUser(user);
        System.out.println("User signed up successfully.");
        return true;
    }

    public boolean verifyCredentials(String email, String password) {
        Database database = Database.getInstance();
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