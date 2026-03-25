package tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import src.util.CsvFileHandler;

public class CSVFileHandlingTest {
    @Test
    public void errorHandlingFilePresent() {
        // Arrange
        // Act
        CsvFileHandler test = new CsvFileHandler("The Matrix", 1999, false, "Action",
                8.7, "Hacker discovers life is a lie.", "Lana Wachowski", 465900000,
                "src/util/", "Movies.csv");

        // Assert

    }
}
