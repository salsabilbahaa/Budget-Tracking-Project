import com.google.gson.annotations.Expose;

import java.util.Scanner;

public class BudgetingPage {
    @Expose
    private Budget budget;
    static BudgetService budgetService = new BudgetService();
    static AnalysisEngine analysis = new AnalysisEngine(budgetService.getDatabase());

    public BudgetingPage(Budget budget) {
        this.budget = budget;
    }

    public Budget getBudget() {
        return budget;
    }

    public static void run(User user) {
        Scanner scanner = new Scanner(System.in);



        System.out.print("Enter budget category (e.g., Transport): ");
        String category = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Budget budget = new Budget(amount, category);
        BudgetingPage budgetingPage = new BudgetingPage(budget);
        if(budgetService.createBudget(amount, category, user)) {
            analysis.generateSpendingAnalysis(user);//validate
        }
    }
}