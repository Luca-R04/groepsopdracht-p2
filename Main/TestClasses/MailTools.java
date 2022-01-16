package Main.TestClasses;

// Checks if the given email is valid.

public class MailTools {
    
    public static boolean validateMailAddress(String mailAddress) {
        // Has to include @
        if (!mailAddress.contains("@")) {
            return false;
        } else if (mailAddress.split("@")[0].length() < 1) {
            return false;
        } else if (mailAddress.split("@")[1].split("\\.").length > 2) { 
            return false;
        } else if (mailAddress.split("@")[1].split("\\.")[0].length() < 1) {  // Email@gmail.com -> gmail.com -> gmail
            return false;
        } else if (mailAddress.split("@")[1].split("\\.")[1].trim().length() < 1) { 
            return false;
        }

        return true;
    }
}
