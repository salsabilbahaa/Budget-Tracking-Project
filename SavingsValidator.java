class SavingsValidator 
{
    public static boolean isValid(SavingsGoal goal) 
    {
        return !goal.getGoalName().trim().isEmpty() && goal.getTargetAmount() > 0;
    }
}