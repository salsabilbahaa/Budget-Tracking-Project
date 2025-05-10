class SavingsGoal 
{
    private String goalName;
    private double targetAmount;

    public SavingsGoal(String goalName, double targetAmount) 
    {
        this.goalName = goalName;
        this.targetAmount = targetAmount;
    }

    public String getGoalName() 
    { 
        return goalName; 
    }

    public double getTargetAmount() 
    { 
        return targetAmount; 
    }
}