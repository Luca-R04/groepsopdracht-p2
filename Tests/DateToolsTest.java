import org.junit.Assert;
import org.junit.Test;

public class DateToolsTest {
	/**
	 * @desc Validates is a given date in the form of day, month and year is valid.
	 * 
	 * @subcontract 31 days in month {
	 * @requires (month == 1 || month == 3 || month == 5 || month == 7 ||
	 *           month == 8 || month == 10 || month == 12) && 1 <= day <= 31;
	 * @ensures \result = true;
	 *          }
	 * 
	 * @subcontract 30 days in month {
	 * @requires (month == 4 || month == 6 || month == 9 || month == 11) &&
	 *           1 <= day <= 30;
	 * @ensures \result = true;
	 *          }
	 * 
	 * @subcontract 29 days in month {
	 * @requires month == 2 && 1 <= day <= 29 &&
	 *           (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
	 * @ensures \result = true;
	 *          }
	 * 
	 * @subcontract 28 days in month {
	 * @requires month == 2 && 1 <= day <= 28 &&
	 *           (year % 4 != 0 || (year % 100 == 0 && year % 400 != 0));
	 * @ensures \result = true;
	 *          }
	 * 
	 * @subcontract all other cases {
	 * @requires no other accepting precondition;
	 * @ensures \result = false;
	 *          }
	 * 
	 */

	@Test
	public void testValidateDateRequiresM1D1EnsuresTrue() {
		// Arrange
		int day = 1;
		int month = 1;
		int year = 2000;
		// Act
		boolean result = DateTools.validateDate(day, month, year);
		// Assert
		Assert.assertEquals(true, result);
	}

	@Test
	public void testValidateDateRequiresM3D31EnsuresTrue() {
		// Arrange
		int day = 31;
		int month = 3;
		int year = 2000;
		// Act
		boolean result = DateTools.validateDate(day, month, year);
		// Assert
		Assert.assertEquals(true, result);
	}

	@Test
	public void testValidateDateRequiresM5D1EnsuresTrue() {
		// Arrange
		int day = 1;
		int month = 5;
		int year = 2000;
		// Act
		boolean result = DateTools.validateDate(day, month, year);
		// Assert
		Assert.assertEquals(true, result);
	}

	@Test
	public void testValidateDateRequiresM7D31EnsuresTrue() {
		// Arrange
		int day = 31;
		int month = 7;
		int year = 2000;
		// Act
		boolean result = DateTools.validateDate(day, month, year);
		// Assert
		Assert.assertEquals(true, result);
	}

	@Test
	public void testValidateDateRequiresM8D1EnsuresTrue() {
		// Arrange
		int day = 1;
		int month = 8;
		int year = 2000;
		// Act
		boolean result = DateTools.validateDate(day, month, year);
		// Assert
		Assert.assertEquals(true, result);
	}

	@Test
	public void testValidateDateRequiresM10D1EnsuresTrue() {
		// Arrange
		int day = 1;
		int month = 10;
		int year = 2000;
		// Act
		boolean result = DateTools.validateDate(day, month, year);
		// Assert
		Assert.assertEquals(true, result);
	}

	@Test
	public void testValidateDateRequiresM12D31EnsuresTrue() {
		// Arrange
		int day = 31;
		int month = 12;
		int year = 2000;
		// Act
		boolean result = DateTools.validateDate(day, month, year);
		// Assert
		Assert.assertEquals(true, result);
	}

		/*
	 * @subcontract 30 days in month {
	 * 
	 * @requires (month == 4 || month == 6 || month == 9 || month == 11) &&
	 * 1 <= day <= 30;
	 * 
	 * @ensures \result = true;
	 * }
	 */

	@Test
	public void testValidateDateRequiresM4D1EnsuresTrue() {
		// Arrange
		int day = 30;
		int month = 4;
		int year = 2000;
		// Act
		boolean result = DateTools.validateDate(day, month, year);
		// Assert
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void testValidateDateRequiresM6D1EnsuresTrue() {
		// Arrange
		int day = 30;
		int month = 6;
		int year = 2000;
		// Act
		boolean result = DateTools.validateDate(day, month, year);
		// Assert
		Assert.assertEquals(true, result);
	}

	@Test
	public void testValidateDateRequiresM9D1EnsuresTrue() {
		// Arrange
		int day = 30;
		int month = 9;
		int year = 2000;
		// Act
		boolean result = DateTools.validateDate(day, month, year);
		// Assert
		Assert.assertEquals(true, result);
	}

	@Test
	public void testValidateDateRequiresM11D1EnsuresTrue() {
		// Arrange
		int day = 30;
		int month = 11;
		int year = 2000;
		// Act
		boolean result = DateTools.validateDate(day, month, year);
		// Assert
		Assert.assertEquals(true, result);
	}


	// @subcontract 29 days in month {
	// @requires month == 2 && 1 <= day <= 29 &&
	// (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
	// @ensures \result = true;
	// }

	@Test 
	public void testValidateDateRequiresM2D29Y2024EnsuresTrue() {
		// Arrange
		int day = 29;
		int month = 2;
		int year = 2024;
		// Act
		boolean result = DateTools.validateDate(day, month, year);
		// Assert
		Assert.assertEquals(true, result);
	}

	@Test 
	public void testValidateDateRequiresM2D1Y2000EnsuresTrue() {
		// Arrange
		int day = 29;
		int month = 2;
		int year = 2000;
		// Act
		boolean result = DateTools.validateDate(day, month, year);
		// Assert
		Assert.assertEquals(true, result);
	}

	@Test 
	public void testValidateDateRequiresM2D1Y2024EnsuresTrue() {
		// Arrange
		int day = 1;
		int month = 2;
		int year = 2024;
		// Act
		boolean result = DateTools.validateDate(day, month, year);
		// Assert
		Assert.assertEquals(true, result);
	}

	@Test 
	public void testValidateDateRequiresM2D29Y2000EnsuresTrue() {
		// Arrange
		int day = 29;
		int month = 2;
		int year = 2000;
		// Act
		boolean result = DateTools.validateDate(day, month, year);
		// Assert
		Assert.assertEquals(true, result);
	}

	//@subcontract 28 days in month {
	//@requires month == 2 && 1 <= day <= 28 &&
	//          (year % 4 != 0 || (year % 100 == 0 && year % 400 != 0));
	//@ensures \result = true;
	//         }

	@Test 
	public void testValidateDateRequiresM2D28Y2023EnsuresTrue() {
		// Arrange
		int day = 28;
		int month = 2;
		int year = 2023;
		// Act
		boolean result = DateTools.validateDate(day, month, year);
		// Assert
		Assert.assertEquals(true, result);
	}
	
	
	@Test 
	public void testValidateDateRequiresM2D28Y2100EnsuresTrue() {
		// Arrange
		int day = 28;
		int month = 2;
		int year = 2100;
		// Act
		boolean result = DateTools.validateDate(day, month, year);
		// Assert
		Assert.assertEquals(true, result);
	}

	@Test 
	public void testValidateDateRequiresM2D1Y2023EnsuresTrue() {
		// Arrange
		int day = 1;
		int month = 2;
		int year = 2023;
		// Act
		boolean result = DateTools.validateDate(day, month, year);
		// Assert
		Assert.assertEquals(true, result);
	}
	
	
	@Test 
	public void testValidateDateRequiresM2D1Y2100EnsuresTrue() {
		// Arrange
		int day = 1;
		int month = 2;
		int year = 2100;
		// Act
		boolean result = DateTools.validateDate(day, month, year);
		// Assert
		Assert.assertEquals(true, result);
	}

	//@subcontract all other cases {
	//@requires no other accepting precondition;
	//@ensures \result = false;
	//         }
	
	@Test
	public void testValidateDateRequiresM15D65Y21000EnsuresFalse() {
		// Arrange
		int day = 65;
		int month = 15;
		int year = 21000;
		// Act
		boolean result = DateTools.validateDate(day, month, year);
		// Assert
		Assert.assertEquals(false, result);
	}
}