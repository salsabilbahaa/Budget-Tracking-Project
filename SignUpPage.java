import java.util.Scanner;

public class SignUpPage {
    public static void run() {
        Scanner scanner = new Scanner(System.in);

        String email, username, password, phone;

        while (true) {
            System.out.print("Email: ");
            email = scanner.nextLine();

            System.out.print("Username: ");
            username = scanner.nextLine();

            System.out.print("Password: ");
            password = scanner.nextLine();

            System.out.print("Phone: ");
            phone = scanner.nextLine();

            if (!AuthenticationService.validateInputs(email, username, password, phone)) {
                System.out.println("Invalid input. Follow these rules:\nThe email should contain @ \nthe password should contain letters and numbers\n" +
                        "The password should be at least 8 characters long\nThe phone number should be at least 10 characters long.");
            }
            else{
                break;
            }
        }

        OTPService.sendOTP(phone);
        while (true) {
            System.out.print("Enter the OTP sent to your phone: ");
            int otp = scanner.nextInt();

            if (!AuthenticationService.signUp(email, username, password, phone, otp)) {
                System.out.println("Invalid OTP. Please try again.");
            } else {
                break;
            }
        }
        System.out.println("Redirecting to dashboard...");
        scanner.close();
    }
}