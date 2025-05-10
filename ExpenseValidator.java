import java.time.LocalDate;

public class ExpenseValidator 
{
    public static boolean isValid(Expense expense) 
    {
        return expense.getAmount() > 0 && !expense.getCategory().trim().isEmpty() && !expense.getDate().isAfter(LocalDate.now());
    }
}