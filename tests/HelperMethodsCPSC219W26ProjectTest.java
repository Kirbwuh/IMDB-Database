import src.CPSC219W26Project;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HelperMethodsCPSC219W26ProjectTest {
    //*************************************************************************************
    // ----------------------- Helper Functions --------------------------------------------
    // ************************************************************************************
    /**
     * Test to check if words with different cases gets normalized to lower case
     * e.g. tRiCk -> trick
     */
    @Test
    public void normalizeWord_NormalizeCase() {
        // Expected input
        String variableCaseWord = "RiCk";
        // excpected output
        String excpectedCaseNormalizedWord = "rick";
        // output
        String caseNormalizedWord = CPSC219W26Project.normalizeWord(variableCaseWord);
        // test
        assertEquals(excpectedCaseNormalizedWord, caseNormalizedWord, "Should return true as the String would be normalized to lower case");

    }

    /**
     * Test to check if words with spaces get normalized (trimmed)
     * e.g. "  trick  " -> "trick"
     */
    @Test
    public void normalizeWord_NormalizeSpaces() {
        // Expected input
        String spacedWord = " rick ";
        // excpected output
        String excpectedSpaceNormalizedWord = "rick";
        // output
        String spaceNormalizedWord = CPSC219W26Project.normalizeWord(spacedWord);
        // test
        assertEquals(excpectedSpaceNormalizedWord, spaceNormalizedWord, "Should return true as the String would be normalized to spaces");

    }

    /**
     * Test to verify that words with leading/trailing spaces and mixed case
     * are normalized to lowercase with spaces trimmed.
     * Example: " tRicK " -> "trick"
     */
    @Test
    public void normalizeWord_NormalizeSpacesAndCase() {
        // Input with leading/trailing spaces and mixed case
        String spacedMixedCaseWord = " MaTriX ";

        // Expected output
        String expectedNormalizedWord = "matrix";

        // output
        String actualNormalizedWord = CPSC219W26Project.normalizeWord(spacedMixedCaseWord);

        // Test
        assertEquals(expectedNormalizedWord, actualNormalizedWord,
                "Should return true as the string should be trimmed and converted to lowercase");
    }


    /**
     * Test to check if a plain integer is recognized as numeric
     * e.g. "1999" -> true
     */
    @Test
    public void isNumeric_ValidInteger() {
        // Expected input
        String integerValue = "1999";
        // Expected output
        boolean expectedResult = true;
        // output
        boolean actualResult = CPSC219W26Project.isNumeric(integerValue);
        // test
        assertEquals(expectedResult, actualResult, "Should return true as the String only contains digits");
    }

    /**
     * Test to check if a decimal number is recognized as numeric
     * e.g. "8.7" -> true
     */
    @Test
    public void isNumeric_ValidDecimal() {
        // Expected input
        String decimalValue = "8.7";
        // Expected output
        boolean expectedResult = true;
        // output
        boolean actualResult = CPSC219W26Project.isNumeric(decimalValue);
        // test
        assertEquals(expectedResult, actualResult, "Should return true as the String contains a valid decimal number");
    }

    /**
     * Test to check if a non-numeric string is rejected
     * e.g. "hello" -> false
     */
    @Test
    public void isNumeric_InvalidNonNumeric() {
        // Expected input
        String nonNumericValue = "hello";
        // Expected output
        boolean expectedResult = false;
        // output
        boolean actualResult = CPSC219W26Project.isNumeric(nonNumericValue);
        // test
        assertEquals(expectedResult, actualResult, "Should return false as the String contains non-numeric characters");
    }

    /**
     * Test to check if a comma separated string gets split into an array correctly
     * e.g. "The Matrix,1999,Action" -> ["The Matrix", "1999", "Action"]
     */
    @Test
    public void separateCommaValues_ValidCommaSeparatedString() {
        // Expected input
        String commaSeparatedString = "The Matrix,1999,Action";
        // Expected output
        String[] expectedResult = {"The Matrix", "1999", "Action"};
        // output
        String[] actualResult = CPSC219W26Project.separateCommaValues(commaSeparatedString);
        // test
        assertArrayEquals(expectedResult, actualResult, "Should return an array with the values split by comma");
    }

    /**
     * Test to check if a string with no commas returns an empty array
     * e.g. "The Matrix" -> []
     */
    @Test
    public void separateCommaValues_NoCommaReturnsEmptyArray() {
        // Expected input
        String noCommaString = "The Matrix";
        // Expected output
        String[] expectedResult = {};
        // output
        String[] actualResult = CPSC219W26Project.separateCommaValues(noCommaString);
        // test
        assertArrayEquals(expectedResult, actualResult, "Should return an empty array as the String has no commas");
    }

    /**
     * Test to check if an empty string returns an empty array
     * e.g. "" -> []
     */
    @Test
    public void separateCommaValues_EmptyStringReturnsEmptyArray() {
        // Expected input
        String emptyString = "";
        // Expected output
        String[] expectedResult = {};
        // output
        String[] actualResult = CPSC219W26Project.separateCommaValues(emptyString);
        // test
        assertArrayEquals(expectedResult, actualResult, "Should return an empty array as the String is empty");
    }
}