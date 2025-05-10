import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class Budget {
    @Expose
    private double amount;
    @Expose
    private String category;
    private List<BudgetObserver> observers = new ArrayList<>();

    public Budget(double amount, String category) {
        this.amount = amount;
        this.category = category;
    }

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
        return category;
    }
}