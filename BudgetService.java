import java.util.*;

public class BudgetService {
    private ValidationService validateBudget = new ValidationService();
    private Database database = new Database();
    private DashBoard dashboard = new DashBoard();


    public Database getDatabase() {
        return database;
    }

    public void createBudget(double amount, String category, String userEmail) {
        if (validateBudget.validateBudget(amount, category)) {
            Budget budget = new Budget(amount, category);
            BudgetingPage budgetingPage = new BudgetingPage(budget);
            budget.addObserver(dashboard);
            database.addBudget(userEmail, budgetingPage);
            System.out.println("Successfully created budget");
        } else {
            System.out.println("Invalid budget");
        }
    }

    public void retrieveBudgets(String userEmail) {
        List<BudgetingPage> budgets = database.retrieveBudgets(userEmail);
        for (BudgetingPage budgetingPage : budgets) {
            Budget budget = budgetingPage.getBudget(); // Use Budget from BudgetingPage
            System.out.println("Budget: " + budget.getCategory() + " " + budget.getAmount());
        }
    }
}