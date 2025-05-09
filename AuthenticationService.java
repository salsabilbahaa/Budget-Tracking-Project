public class AuthenticationService {

    public static boolean validateInputs(String email, String username, String password, String phoneNumber) {
        return email.contains("@") && password.matches(".*\\d.*") && password.length() >= 8 && phoneNumber.length() >= 10;
    }

    public static boolean signUp(String email, String username, String password, String phoneNumber, int otp) {
        if (Database.emailExists(email)) {
            System.out.println("Email already exists.");
            return false;
        }

        if (!OTPService.verifyOTP(otp)) {
            System.out.println("Invalid OTP.");
            return false;
        }

        User user = new User(email, username, password, phoneNumber);
        Database.addUser(user);
        System.out.println("User signed up successfully.");
        return true;
    }
}
