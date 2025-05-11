import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class User {
    @Expose
    private String email;
    @Expose
    private String username;
    @Expose
    private String password;
    @Expose
    private String phoneNumber;
    @Expose
    private List<BudgetingPage> budgets=new ArrayList<>();
    @Expose
    private List<Income> incomes = new ArrayList<>();

    public User(String email, String username, String password, String phoneNumber) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.incomes = new ArrayList<>();
    }

    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getPhoneNumber() { return phoneNumber; }

 
    public List<BudgetingPage> retrieveBudgets() {
        if (budgets == null) {
            budgets = new ArrayList<>();
        }
        return budgets;
    }

    public void setBudgets(List<BudgetingPage> budgets) {
        this.budgets = budgets;
    }

    public void addBudget(BudgetingPage budget) {
        budgets.add(budget);
    }

    public void addIncome(Income income) {
        if (incomes == null) {
            incomes = new ArrayList<>();
        }
        incomes.add(income);
    }
    public List<Income> getIncomes() { return incomes; }
}
