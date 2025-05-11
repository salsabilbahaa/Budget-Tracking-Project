import java.util.Set;

 class ValidationService {

        private static final  int IncomeLimit=20000;
        private static final Set<String> Categories = Set.of(
                "Food", "Rent", "Utilities", "Transport",
                "Healthcare", "Education", "Entertainment", "Savings"
        );
        public boolean validateBudget(double amount,String category){

            return   (amount<=IncomeLimit && amount>=0 && Categories.contains (category));


        }
    }

