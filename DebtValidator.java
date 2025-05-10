import java.time.LocalDate;

public class DebtValidator 
{
    public static boolean isValid(Debt debt) 
    {
        return debt.getAmount() > 0 && debt.getApr() >= 0 && debt.getDueDate().isAfter(LocalDate.now());
    }
}