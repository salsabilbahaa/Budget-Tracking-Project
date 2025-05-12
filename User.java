import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user with personal details and associated financial data, including
 * budgets and incomes. Supports serialization with Gson annotations and manages
 * lists of budgeting pages and income records.
 */
public class User {
    /**
     * User’s email address, exposed for serialization.
     */
    @Expose
    private String email;
    /**
     * User’s chosen username, exposed for serialization.
     */
    @Expose
    private String username;
    /**
     * User’s password, exposed for serialization.
     */
    @Expose
    private String password;
    /**
     * User’s phone number, exposed for serialization.
     */
    @Expose
    private String phoneNumber;
    /**
     * List of user’s budgeting pages, exposed for serialization.
     */
    @Expose
    private List<BudgetingPage> budgets = new ArrayList<>();
    /**
     * List of user’s income records, exposed for serialization.
     */
    @Expose
    private List<Income> incomes = new ArrayList<>();

    /**
     * Initializes a user with their personal details and an empty income list.
     *
     * @param email       The user’s email address.
     * @param username    The user’s chosen username.
     * @param password    The user’s password.
     * @param phoneNumber The user’s phone number.
     */
    public User(String email, String username, String password, String phoneNumber) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.budgets = new ArrayList<>();
        this.incomes = new ArrayList<>();
    }

    /**
     * Retrieves the user’s email address.
     *
     * @return The user’s email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the user’s username.
     *
     * @return The user’s username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the user’s password.
     *
     * @return The user’s password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Retrieves the user’s phone number.
     *
     * @return The user’s phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns the user’s list of budgeting pages, initializing it if null.
     *
     * @return The list of budgeting pages.
     */
    public List<BudgetingPage> retrieveBudgets() {
        if (budgets == null) {
            budgets = new ArrayList<>();
        }
        return budgets;
    }

    /**
     * Adds a budgeting page to the user’s list of budgets.
     *
     * @param budget The budgeting page to add.
     */
    public void addBudget(BudgetingPage budget) {
        if (budgets == null) {
            budgets = new ArrayList<>();
        }
        budgets.add(budget);
    }

    /**
     * Sets the user’s list of budgeting pages.
     *
     * @param budgets The new list of budgeting pages to set.
     */
    public void setBudgets(List<BudgetingPage> budgets) {
        this.budgets = budgets;
    }

    /**
     * Adds an income record to the user’s list of incomes.
     *
     * @param income The income record to add.
     */
    public void addIncome(Income income) {
        if (incomes == null) {
            incomes = new ArrayList<>();
        }
        incomes.add(income);
    }

    /**
     * Returns the user’s list of income records, initializing it if null.
     *
     * @return The list of income records.
     */
    public List<Income> getIncomes() {
        if (incomes == null) {
            incomes = new ArrayList<>();
        }
        return incomes;
    }

    /**
     * Sets the user’s list of income records.
     *
     * @param incomes The new list of income records to set.
     */
    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }
}