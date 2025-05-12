import java.util.Date;
import java.util.Scanner;

/**
 * Manages the process of adding income records for a user through a command-line interface.
 * Collects income details such as source, amount, and recurrence, validates the data,
 * and stores the income in the user's record via the database.
 */
public class IncomeTrackingPage {

    /**
     * Prompts the user to input income details, including source, amount, and recurrence status.
     * Validates the income amount, creates an Income object, adds it to the user's income list,
     * and updates the database. Displays success or error messages based on the input validation.
     *
     * @param user The User object to which the income record will be added.
     */
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