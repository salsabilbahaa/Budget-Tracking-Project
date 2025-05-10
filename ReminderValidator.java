import java.time.LocalDateTime;

public class ReminderValidator 
{
    public static boolean isValid(Reminder reminder) 
    {
        return reminder.getDateTime().isAfter(LocalDateTime.now());
    }
}