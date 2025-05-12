import java.util.Date;
import java.util.Scanner;

public class IncomeTrackingPage {

    public static void run(User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter income source (e.g., Freelance): ");
        String source = scanner.nextLine();

        System.out.print("Enter income amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Is this income Recurring: ");
        String isRecurring = scanner.nextLine();

        Database database = Database.getInstance();
        while (!ValidationService.validateIncomeData(amount)){
            System.out.println("Invalid income amount. Please enter amount > 0.");
            System.out.print("Enter income amount: ");
            amount = scanner.nextDouble();
        }

        Income income = new Income(source, amount, new Date(), isRecurring);
        user.addIncome(income);
        database.updateUser(user);
        System.out.println("Income added successfully.");
    }
}
