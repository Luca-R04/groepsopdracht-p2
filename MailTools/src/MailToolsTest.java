package src;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class MailToolsTest {
    
    /**
     * @desc Validates if mailAddress is valid. It should be in the form of:
     *       <at least 1 character>@<at least 1 character>.<at least 1 character>
     * 
     * @subcontract no mailbox part {
     *   @requires !mailAddress.contains("@") ||
     *             mailAddress.split("@")[0].length < 1;
     *   @ensures \result = false;
     * }
     * 
     * @subcontract subdomain-tld delimiter {
     *   @requires !mailAddress.contains("@") ||
     *             mailAddress.split("@")[1].split(".").length > 2;
     *   @ensures \result = false;
     * }
     * 
     * @subcontract no subdomain part {
     *   @requires !mailAddress.contains("@") ||
     *             mailAddress.split("@")[1].split(".")[0].length < 1;
     *   @ensures \result = false;
     * }
     * 
     * @subcontract no tld part {
     *   @requires !mailAddress.contains("@") ||
     *             mailAddress.split("@")[1].split(".")[1].length < 1;
     *   @ensures \result = false;
     * }
     * 
     * @subcontract valid email {
     *   @requires no other precondition
     *   @ensures \result = true;
     * }
    **/

    public static boolean validateMailAddress() {
        return false;
    }
        
    @Test
    public void validateMailAddressNoAtSign() {
        // Arrange
        String mail = "mailgmail.com";

        // Act
        Boolean result = MailTools.validateMailAddress(mail);

        // Assert
        assertEquals(false, result);
    }

    /** 
     * @subcontract no mailbox part {
     *   @requires !mailAddress.contains("@") ||
     *             mailAddress.split("@")[0].length < 1;
     *   @ensures \result = false;
     * }
    */
    
    @Test
    public void validateMailAddressNoMailboxPart() {
        // Arrange
        String mail = "";

        // Act
        Boolean result = MailTools.validateMailAddress(mail);

        // Assert
        assertEquals(false, result);
    }

    /** 
    * @subcontract subdomain-tld delimiter {
     *   @requires !mailAddress.contains("@") ||
     *             mailAddress.split("@")[1].split(".").length > 2;
     *   @ensures \result = false;
     * }
     * 
    */
    
    @Test
    public void validateMailAddressSubdomainGreaterLengthThan1() {
        // Arrange
        String mail = "mail@.com";

        // Act
        Boolean result = MailTools.validateMailAddress(mail);

        // Assert
        assertEquals(false, result);
    }
    
    /** 
     * @subcontract subdomain-tld delimiter {
     *   @requires !mailAddress.contains("@") ||
     *             mailAddress.split("@")[1].split(".").length > 2; mail -> gmail . com
     *   @ensures \result = false;
     * }
    */
    
    @Test
    public void validateMailAddressTwoManyTLD() {
        // Arrange
        String mail = "mail@gmail.com.com";

        // Act
        Boolean result = MailTools.validateMailAddress(mail);

        // Assert
        assertEquals(false, result);
    }

    /** 
     * @subcontract no tld part {
     *   @requires !mailAddress.contains("@") ||
     *             mailAddress.split("@")[1].split(".")[1].length < 1; email@gmail.com -> gmail.com -> com
     *   @ensures \result = false;
     * }
    */

    @Test
    public void validateMailAddressNoTLD() {
        // Arrange
        String mail = "mail@gmail. ";

        // Act
        Boolean result = MailTools.validateMailAddress(mail);

        // Assert
        assertEquals(false, result);
    }

    /** 
     * @subcontract valid email {
     *   @requires no other precondition
     *   @ensures \result = true;
     * }
    */
    
    @Test
    public void validateMailAddressCorrectEmail() {
        // Arrange
        String mail = "mail@gmail.com";

        // Act
        Boolean result = MailTools.validateMailAddress(mail);

        // Assert
        assertEquals(true, result);
    }
}
