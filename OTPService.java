import java.util.Random;

public class OTPService {
    private static int generatedOTP;

    public static void sendOTP(String phoneNumber) {
        generatedOTP = new Random().nextInt(999999);
        System.out.println("Sending OTP to " + phoneNumber + " : " + generatedOTP);
    }

    public static boolean verifyOTP(int inputOTP) {
        return generatedOTP == inputOTP;
    }
}
