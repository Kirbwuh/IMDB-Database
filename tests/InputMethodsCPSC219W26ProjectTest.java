import src.CPSC219W26Project;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class InputMethodsCPSC219W26ProjectTest {
    //*************************************************************************************
    // ----------------------- Input Methods Tests --------------------------------------------
    // ************************************************************************************
    // Source:
    // https://stackoverflow.com/questions/31635698/junit-testing-for-user-input-using-scanner

    /**
     * Test to check if getStringInput returns a normalized (lowercase + trimmed) string
     * e.g. user types " MaTriX " -> "matrix"
     */
    @Test
    public void getStringInput_NormalizesInput() {
        // Expected input
        InputStream in = new ByteArrayInputStream(" MaTriX ".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        // Expected output
        String expectedResult = "matrix";
        // test
        assertEquals(expectedResult, CPSC219W26Project.getStringInput(scanner, "Enter movie name:"));
    }

    /**
     * Test to check if getStringInput handles plain lowercase input with no changes needed
     * e.g. user types "action" -> "action"
     */
    @Test
    public void getStringInput_PlainLowercaseInput() {
        // Expected input
        InputStream in = new ByteArrayInputStream("action".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        // Expected output
        String expectedResult = "action";
        // test
        assertEquals(expectedResult, CPSC219W26Project.getStringInput(scanner, "Enter genre:"));
    }

    /**
     * Test to check if getStringInput handles an empty input
     * e.g. user just presses enter -> ""
     */
    @Test
    public void getStringInput_EmptyInput() {
        // Expected input
        InputStream in = new ByteArrayInputStream("\n".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        // Expected output
        String expectedResult = "";
        // test
        assertEquals(expectedResult, CPSC219W26Project.getStringInput(scanner, "Enter movie name:"));
    }

    /**
     * Test to check if getNumericInput returns a valid integer as a Double
     * e.g. user types "1999" -> 1999.0
     */
    @Test
    public void getNumericInput_ValidInteger() {
        // Expected input
        InputStream in = new ByteArrayInputStream("1999".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        // Expected output
        Double expectedResult = 1999.0;
        // test
        assertEquals(expectedResult, CPSC219W26Project.getNumericInput(scanner, "Enter year:"));
    }

    /**
     * Test to check if getNumericInput returns a valid decimal as a Double
     * e.g. user types "8.7" -> 8.7
     */
    @Test
    public void getNumericInput_ValidDecimal() {
        // Expected input
        InputStream in = new ByteArrayInputStream("8.7".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        // Expected output
        Double expectedResult = 8.7;
        // test
        assertEquals(expectedResult, CPSC219W26Project.getNumericInput(scanner, "Enter rating:"));
    }

    /**
     * Test to check if getBooleanInput returns true when user types "1"
     * e.g. user types "1" -> true
     */
    @Test
    public void getBooleanInput_ReturnsTrue() {
        // Expected input
        InputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        // Expected output: true
        // test
        assertTrue(CPSC219W26Project.getBooleanInput(scanner, "Is the movie PG-13?"));
    }

    /**
     * Test to check if getBooleanInput returns false when user types "0"
     * e.g. user types "0" -> false
     */
    @Test
    public void getBooleanInput_ReturnsFalse() {
        // Expected input
        InputStream in = new ByteArrayInputStream("0".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        // Expected output: false
        // test
        assertFalse(CPSC219W26Project.getBooleanInput(scanner, "Is the movie PG-13?"));
    }


}