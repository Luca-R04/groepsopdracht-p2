package src;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class NumericRangeTest {
    /**
     * @desc Validates if the input is within range of 0-100%
     * 
     * @subcontract value within valid range {
     *   @requires 0 <= percentage <= 100;
     *   @ensures \result = true;
     * }
     * 
     * @subcontract value out of range low {
     *   @requires percentage < 0;
     *   @ensures \result = false;
     * }
     * 
     * @subcontract value out of range high {
     *   @requires percentage > 100;
     *   @signals \result = false;
     * }
     * 
     */
    public static boolean isValidPercentage(int percentage) {

        return false;
    }    



    /* 
    /**
     * @desc Validates if the input is within range of 0-100%
     * 
     * @subcontract value within valid range {
     *   @requires 0 <= percentage <= 100;
     *   @ensures \result = true;
     * }
     * 
     */

    @Test
    public void isValidPercentageTestInclusive0(){
        // Arrange 
        int percentage = 0;
        // Act
        boolean result = NumericRange.isValidPercentage(percentage);
        // Assert
        assertEquals(true, result);
    }

    @Test
    public void isValidPercentageTestInclusive100(){
        // Arrange 
        int percentage = 100;
        // Act
        boolean result = NumericRange.isValidPercentage(percentage);
        // Assert
        assertEquals(true, result);
    }

    @Test
    public void isValidPercentageTestSuccess30(){
        // Arrange 
        int percentage = 30;
        // Act
        boolean result = NumericRange.isValidPercentage(percentage);
        // Assert
        assertEquals(true, result);
    }

   /**
     * 
     * @subcontract value out of range low {
     *   @requires percentage < 0;
     *   @ensures \result = false;
     * }
     * 
     */
    
    @Test
    public void isValidPercentageTestMinus1OutOfRange(){
        // Arrange 
        int percentage = -1;
        // Act
        boolean result = NumericRange.isValidPercentage(percentage);
        // Assert
        assertEquals(false, result);
    }
    
    /**
     * 
     * @subcontract value out of range high {
     *   @requires percentage > 100;
     *   @signals \result = false;
     * }
     * 
     */
    @Test
    public void isValidPercentageTest101OutOfRange(){
        // Arrange 
        int percentage = 101;
        // Act
        boolean result = NumericRange.isValidPercentage(percentage);
        // Assert
        assertEquals(false, result);
    }
}   

