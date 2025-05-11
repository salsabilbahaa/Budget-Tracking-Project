import java.util.*;

public class BudgetService {
    private ValidationService validateBudget = new ValidationService();
    private Database database = new Database();
    private DashBoard dashboard = new DashBoard();


    public Database getDatabase() {
        return database;
    }

    public boolean createBudget(double amount, String category, User user) {
        if (validateBudget.validateBudget(amount, category)) {
            Budget budget = new Budget(amount, category);
            BudgetingPage budgetingPage = new BudgetingPage(budget);
            budget.addObserver(dashboard);
            database.addBudget(user, budgetingPage);
            System.out.println("Successfully created budget");
            return true;
        } else {
            System.out.println("Invalid budget");
            return false;
        }
    }

    public void retrieveBudgets(User user) {
        List<BudgetingPage> budgets = database.retrieveBudgets(user);
        for (BudgetingPage budgetingPage : budgets) {
            Budget budget = budgetingPage.getBudget(); // Use Budget from BudgetingPage
            System.out.println("Budget: " + budget.getCategory() + " " + budget.getAmount());
        }
    }
}