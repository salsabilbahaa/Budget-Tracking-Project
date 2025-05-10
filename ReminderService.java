import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ReminderService 
{
    private static final String fileName = "reminders.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void saveReminder(Reminder reminder) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) 
        {
            writer.write(reminder.getDescription() + "|" + reminder.getDateTime().format(formatter));
            writer.newLine();
            NotificationService.schedule(reminder);
        } 
        catch (IOException e) 
        {
            System.err.println("Error saving reminder: " + e.getMessage());
        }
    }

    public static List<Reminder> getAllReminders() 
    {
        List<Reminder> reminders = new ArrayList<>();
        File file = new File(fileName);
        
        if (!file.exists()) 
        {
            return reminders;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split("\\|");
                if (parts.length == 2) 
                {
                    LocalDateTime dateTime = LocalDateTime.parse(parts[1], formatter);
                    reminders.add(new Reminder(parts[0], dateTime));
                }
            }
        } 
        catch (IOException | DateTimeParseException e) 
        {
            System.err.println("Error loading reminders: " + e.getMessage());
        }
        
        return reminders;
    }
}