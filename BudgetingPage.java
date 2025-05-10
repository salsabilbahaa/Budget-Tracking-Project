import java.util.Scanner;

public class BudgetingPage {
    private Budget budget;
    static BudgetService budgetService = new BudgetService();
    static AnalysisEngine analysis = new AnalysisEngine(budgetService.getDatabase());

    public BudgetingPage(Budget budget) {
        this.budget = budget;
    }

    public Budget getBudget() {
        return budget;
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your email: ");
        String userEmail = scanner.nextLine();


        if (Database.getUserByEmail(userEmail) == null) {
            System.out.println("User not found: " + userEmail + ". Please sign up or log in first.");
            return;
        }

        System.out.print("Enter budget category (e.g., Transport): ");
        String category = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Budget budget = new Budget(amount, category);
        BudgetingPage budgetingPage = new BudgetingPage(budget);
        budgetService.createBudget(amount, category, userEmail);
        analysis.generateSpendingAnalysis(userEmail);
    }
}