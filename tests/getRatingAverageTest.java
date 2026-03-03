package tests;

import org.junit.Test;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class getRatingAverageTest {

    @Test
    /**
     * Checking if input's given will be properly calculated and returns a formated double
     * Ex: (8.2 + 9.6 + 8 + 4.1)/4 =  7.48 NOT 7.475
     */
    public void getRatingAverage_normalFunction(){
        // Expected input
        double avg1 = 8.2;
        double avg2 = 9.6;
        double avg3 = 8;
        double avg4 = 4.1;
        int movieCount = 4;
        double avgTotal = avg1+avg2+avg3+avg4;
        double ratingTotal = avgTotal/movieCount;
        // Expected output
        String expectedOutput = "7.48";
        String actualOutput = String.format("%.2f",ratingTotal);
        // Test
        assertEquals(expectedOutput,actualOutput,"The expected value should be 7.48");
    }
    /**
     * Checking if no input is given for calculations
     * specifically if entering only zero
     */
    @Test
    public void getRatingAverage_noMoviesGiven(){
        // Expected input
        double averageNum = 0.0;
        double movieCount = 0.0;
        double ratingTotal = averageNum/movieCount;
        // Expected result
        double expectedResutlt = NaN;
        // Test
        assertEquals(expectedResutlt, ratingTotal);//not expecting a number to be returned
    }
    @Test

    /**
     * Checks if input isn't properly calculated
     */
    public void getRatingAverage_incorrect(){
        double avg1 = 8.2;
        double avg2 = 9.6;
        double avg3 = 8;
        double avg4 = 4.1;
        int movieCount = 3;
        double avgTotal = avg1+avg2+avg3+avg4;
        double ratingTotal = avgTotal/movieCount;
        // Expected output
        String expectedOutput = "9.97";
        String actualOutput = String.format("%.2f",ratingTotal);
        // Test
        assertEquals(expectedOutput,actualOutput);
    }
}
