public class DashBoard implements  BudgetObserver{
    @Override
    public void update(Budget budget) {
        System.out.println("Dashboard updated: New budget added - Category: " + budget.getCategory() + ", Amount: " + budget.getAmount());
    }
}
