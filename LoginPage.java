import java.util.Scanner;

public class LoginPage {
    public static void run() {
        Scanner scanner = new Scanner(System.in);

        String email, password;


            System.out.print("Email: ");
            email = scanner.nextLine();



            System.out.print("Password: ");
            password = scanner.nextLine();


            if (!AuthenticationService.verify(email, password)) {
                System.out.println("Login failed.");
            }
            else{
                System.out.println("Redirecting to dashboard...");
            }

        scanner.close();



    }
}