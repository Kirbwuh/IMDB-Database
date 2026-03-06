package tests;
import src.CPSC219W26Project;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
public class getRatingAverageTest {

    @Test
    public void getRatingAverage_normalFunction(){
        // Expected Output
        String expectedOutput = "7.48";
        String actualOutput = CPSC219W26Project.getRatingAverage(29.9,4);
        // Test
        assertEquals(expectedOutput,actualOutput,"The expected value should be 7.48");
    }

    @Test
    public void getRatingAverage_noMoviesGiven(){
        // Expected Output
        String expectedOutput = String.valueOf(Double.NaN);
        String actualOutput = CPSC219W26Project.getRatingAverage(0.0,0);
        // Test
        assertEquals(expectedOutput, actualOutput);//not expecting a number to be returned
    }

    @Test
    public void getRatingAverage_incorrect(){
        // Expected Output
        String expectedOutput = "9.97";
        String actualOutput = CPSC219W26Project.getRatingAverage(4.6,7);
        // Test
        assertNotSame(expectedOutput,actualOutput);
    }
}
