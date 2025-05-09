import java.time.LocalDateTime;

public class Reminder 
{
    private String description;
    private LocalDateTime dateTime;

    public Reminder(String description, LocalDateTime dateTime) 
    {
        this.description = description;
        this.dateTime = dateTime;
    }

    public String getDescription() 
    {
        return description;
    }

    public LocalDateTime getDateTime() 
    {
        return dateTime;
    }
}