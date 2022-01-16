import org.junit.Assert;
import org.junit.Test;

public class PostalCodeTest {
	/**
	 * @desc Formats the input postal code to a uniform output in the form
	 *       nnnn<space>MM, where nnnn is numeric and > 999 and MM are 2 capital
	 *       letters.
	 *       Spaces before and after the input string are trimmed.
	 * 
	 * @subcontract null postalCode {
	 * @requires postalCode == null;
	 * @signals (NullPointerException) postalCode == null;
	 *          }
	 * 
     * @subcontract valid postalCode {
     *   @requires Integer.valueOf(postalCode.trim().substring(0, 4)) > 999 &&
     *             Integer.valueOf(postalCode.trim().substring(0, 4)) <= 9999 &&
     *             postalCode.trim().substring(4).trim().length == 2 &&
     *             'A' <= postalCode.trim().substring(4).trim().toUpperCase().charAt(0) <= 'Z';
     *   @ensures \result = postalCode.trim().substring(0, 4) + " " +
     *                  postalCode.trim().substring(4).trim().toUpperCase()
     * }
	 * 
	 * @subcontract invalid postalCode {
	 * @requires no other valid precondition;
	 * @signals (IllegalArgumentException);
	 *          }
	 * 
	*/

	@Test(expected = NullPointerException.class)
	public void testFormatPostalCodeRequiresnullSignalsNullPointerException() {
		// Arrange
		String input = null;
		// Act
		String result = PostalCode.formatPostalCode(input);
	}

    @Test
    public void testFormatPostalCodeRequires1000_AKEnsures1000_AK() {
		//Arrange
        String input = "1000 AK";
        //Act
        String result = PostalCode.formatPostalCode(input);
        //Assert
        Assert.assertEquals("1000 AK", result);
	}

	@Test 
	public void testFormatPostalCodeRequires9999_AKEnsures9999_AK() {
		// Arrange
		String input = "9999 AK";
		// Act
		String result = PostalCode.formatPostalCode(input);
		// Assert
		Assert.assertEquals("9999 AK", result);
	}

	@Test 
	public void testFormatPostalCodeRequires6723_CKEnsuresCK() {
		// Arrange
		String input = "6723 CK"; 
		// Act
		String result = PostalCode.formatPostalCode(input);
		// Assert
		Assert.assertEquals("6723 CK", result);
	}

    @Test
    public void testFormatPostalCodeRequires1000_AKEnsuresTrue() {
        //Arrange
        String input = "4321 AK";
        //Act
        String result = PostalCode.formatPostalCode(input);
        //Assert
        Assert.assertEquals("4321 AK", result);
    }

	@Test(expected = IllegalArgumentException.class)
	public void testFormatPostalCodeRequires12345_AKKEnsuresIlligalArgumentException() {
		//Arrange
        String input = "12345 AKK";
        //Act
        String result = PostalCode.formatPostalCode(input);
	}
}