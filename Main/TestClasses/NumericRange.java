package Main.TestClasses;

// Checks if the given percentage is between 0 and 100. 

public class NumericRange {
    public static boolean isValidPercentage(Double percentage) {
        if  (0 <= percentage && percentage <= 100) {
            return true;
        } else if (percentage < 0) {
            return false;
        } else if (percentage > 100) {
            return false;
        }
        return true;
    } 
}
