package src;

public class NumericRange {
    public static boolean isValidPercentage(int percentage) {
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
