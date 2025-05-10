public class User {
    private String email;
    private String username;
    private String password;
    private String phoneNumber;
    private List<BudgetingPage> budgets;

    public User(String email, String username, String password, String phoneNumber) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
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
       retrieveBudgets().add(budget);
    }
    
}

