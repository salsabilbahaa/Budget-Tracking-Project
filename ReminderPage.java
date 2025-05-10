import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ReminderPage
{
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) 
    {
        System.out.println();
        System.out.println("------ Reminder System ------");
        handleReminderPage();
    }

    private static void handleReminderPage() 
    {
        while (true) 
        {
            System.out.println();
            System.out.println("1. Create a new reminder");
            System.out.println("2. View all reminders");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            if (choice == 0) 
            {
                System.out.println();
                System.out.println("-----------------------------");
                return;
            } 
            else if (choice == 1) 
            {
                createReminder();
            } 
            else if (choice == 2) 
            {
                viewAllReminders();
            } 
            else 
            {
                System.out.println("Invalid choice");
            }
        }
    }

    private static void createReminder() 
    {
        System.out.println();
        System.out.print("Enter reminder description: ");
        String description = scanner.nextLine();
        
        System.out.print("Enter date/time (yyyy-MM-dd HH:mm): ");
        String dateAndTime = scanner.nextLine();
        System.out.println();
        
        try 
        {
            LocalDateTime reminderDateTime = LocalDateTime.parse(dateAndTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Reminder reminder = new Reminder(description, reminderDateTime);
            
            if (ReminderValidator.isValid(reminder)) 
            {
                ReminderService.saveReminder(reminder);
                System.out.println();
                System.out.println("Reminder set successfully.");
            } 
            else 
            {
                System.out.println("Error: Date must be in the future.");
            }
        } 
        catch (DateTimeParseException e) 
        {
            System.out.println("Invalid date format. Please use yyyy-MM-dd HH:mm");
        }
    }

    private static void viewAllReminders() 
    {
        List<Reminder> reminders = ReminderService.getAllReminders();
        if (reminders.isEmpty()) 
        {
            System.out.println("No reminders found.");
            return;
        }

        System.out.println();
        System.out.println("All Reminders: ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
      
        for (int i = 0; i < reminders.size(); i++) 
        {
            Reminder r = reminders.get(i);
            System.out.println((i + 1) + ". " + r.getDescription() + " - " + r.getDateTime().format(formatter));
        }
    }
}