import java.util.Random;

/**
 * Manages one-time password (OTP) generation and verification for user authentication.
 * Generates a random OTP, simulates sending it to a phone number, and checks user input
 * against the generated OTP.
 */
public class OTPService {
    /** Stores the most recently generated OTP for verification. */
    private static int generatedOTP;

    /**
     * Generates a random 6-digit OTP and simulates sending it to the specified phone number.
     * Prints the OTP to the console for debugging or simulation purposes.
     * 
     * @param phoneNumber The phone number to which the OTP is "sent".
     */
    public static void sendOTP(String phoneNumber) {
        // Generate a random 6-digit OTP (0 to 999999)
        generatedOTP = new Random().nextInt(999999);
        // Simulate sending OTP by printing to console
        System.out.println("Sending OTP to " + phoneNumber + " : " + generatedOTP);
    }

    /**
     * Verifies if the provided OTP matches the most recently generated OTP.
     * 
     * @param inputOTP The OTP entered by the user.
     * @return True if the input OTP matches the generated OTP, false otherwise.
     */
    public static boolean verifyOTP(int inputOTP) {
        // Compare input OTP with stored OTP
        return generatedOTP == inputOTP;
    }
}
