import java.util.*;
public class Budget {
private double amount; 
    private String category;
public Budget(double amount, String category) {
    this.amount = amount;
    this.catogry = category;
}

 List<BudgetObserver>observers=new ArrayList<>();
 public void addObserver(BudgetObserver observer) {
     observers.add(observer);
 }
 public void removeObserver(BudgetObserver observer) {
     observers.remove(observer);
 }
 public void notifyObservers() {
     for (BudgetObserver observer : observers) {
         observer.update(this);

     }
 }
    public double getAmount() {
        return amount;
    }
 public String getCategory() {
     return catogry;
 }
}
class DashBoard implements BudgetObserver {
@Override
public void update(Budget budget) {
    System.out.println("Dashboard updated: New budget added - Category: " + budget.getCategory() + ", Amount: " + budget.getAmount());
}
}
 class ValidateBudget{
    private static final  int IncomeLimit=20000;
     private static final Set<String> Categories = Set.of(
             "Food", "Rent", "Utilities", "Transport",
             "Healthcare", "Education", "Entertainment", "Savings"
     );
     public boolean validate(double amount,String category){

         return   (amount<=IncomeLimit && amount>=0 && Categories.contains (category));


     }
}
class Database{
    private List<Budget>  budgets= new ArrayList<>();
    public  void addBudget(Budget budget){
        budgets.add(budget);
        budget.notifyObservers();
    }
    public void retriveBudgets(){
        for(Budget budget:budgets){
            System.out.println("Budget: "+budget.catogry+" "+budget.amount);


        }
    }
    public List<Budget> getBudgets(){
        return budgets;
    }
}


class CreateBudget{

    Budget budget;
    ValidateBudget validate_budget=new ValidateBudget();
    Database database=new Database();
    DashBoard dashboard=new DashBoard();
    public Database getDatabase() {
        return database;
    }

    public void Create(double amount,String Category) {

       if(validate_budget.validate(amount,Category)){
           budget = new Budget(amount,Category);
           budget.addObserver(dashboard);
           database.addBudget(budget);
           System.out.println("Successfully created budget");


       }
       else{
           System.out.println("Invalid budget");

       }
    }
    public void retriveBudgets(){
        database.retriveBudgets();
    }
}
class AnalysisEngine{
    Database database ;
    public AnalysisEngine(Database database) {
        this.database = database;
    }
    public void generateSpendingAnalysis(){
       List<Budget>budgets=database.getBudgets();
       double totalSpending=0;
       for(Budget budget:budgets){
           totalSpending+=budget.amount;
       }
       System.out.println("Total Spending: "+totalSpending);
       if(totalSpending>15000){
           System.out.println("Spending is very high this month");
       }
       else{
           System.out.println("Spending is nice in this month");
       }
        System.out.println("Spending Analysis is done successfully");
    }
}



