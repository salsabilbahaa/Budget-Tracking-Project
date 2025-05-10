import java.util.List;

public class AnalysisEngine {
    private Database database;

    public AnalysisEngine(Database database) {
        this.database = database;
    }

    public void generateSpendingAnalysis(String userEmail) {
        List<BudgetingPage> budgets = database.retrieveBudgets(userEmail);
        double totalSpending = 0;
        for (BudgetingPage budgetingPage : budgets) {
            Budget budget = budgetingPage.getBudget();
            if (budget != null) {
                totalSpending += budget.getAmount();
            }
        }
        System.out.println("Total Spending: " + totalSpending);
        if (totalSpending > 15000) {
            System.out.println("Spending is very high this month");
        } else {
            System.out.println("Spending is nice in this month");
        }
        System.out.println("Spending Analysis is done successfully");
    }
}