
import java.util.Scanner;

public class BudgetMain {

    static CreateBudget createbudget = new CreateBudget();
    static AnalysisEngine analysis=new AnalysisEngine(createbudget.getDatabase());
    public static void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter budget category(e.g., Transport): ");
            String category = scanner.nextLine();
            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            createbudget.Create(amount,category);
           analysis.generateSpendingAnalysis();
           break;

        }


        scanner.close();
    }

}

