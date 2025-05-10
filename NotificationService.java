public class NotificationService 
{
    public static void schedule(Reminder reminder) 
    {
        System.out.println("Notification scheduled for: " + reminder.getDateTime());
    }
}