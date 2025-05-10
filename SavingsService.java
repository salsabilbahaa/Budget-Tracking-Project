import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SavingsService 
{
    private static final String fileName = "savings_goals.txt";
    
    public static void saveGoal(SavingsGoal goal) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) 
        {
            writer.write(goal.getGoalName() + "|" + goal.getTargetAmount());
            writer.newLine();
        } 
        catch (IOException e) 
        {
            System.err.println("Error saving goals: " + e.getMessage());
        }
    }

    public static List<SavingsGoal> getAllGoals() 
    {
        List<SavingsGoal> goals = new ArrayList<>();
        File file = new File(fileName);
        
        if (!file.exists()) 
        {
            return goals;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split("\\|");
                if (parts.length == 2) 
                {
                    SavingsGoal goal = new SavingsGoal(parts[0], Double.parseDouble(parts[1]));
                    goals.add(goal);
                }
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Error loading goals: " + e.getMessage());
        }
        
        return goals;
    }
}