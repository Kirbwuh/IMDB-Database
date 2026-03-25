package model;

import org.junit.jupiter.api.Test;
import src.model.Series;
import static org.junit.jupiter.api.Assertions.*;

class SeriesTest {

    //*************************************************************************************
    // ----------------------- Constructor / ID Tests -------------------------------------
    // ************************************************************************************

    /**
     * Test to check if two Series entries never share the same ID
     */
    @Test
    public void constructor_TwoSeriesNeverShareTheSameId() {
        // Arrange
        Series seriesA = new Series("Breaking Bad", 2008, "Crime", 9.5, "yes", 5, 62, "Gilligan");
        Series seriesB = new Series("The Wire",     2002, "Crime", 9.3, "no",  5, 60, "Simon");
        // Act
        int idA = seriesA.getCurrentEntryId();
        int idB = seriesB.getCurrentEntryId();
        // Assert
        assertNotEquals(idA, idB, "No two Series should ever share the same ID");
    }

    /**
     * Test to check if Series IDs auto-increment with each new entry
     */
    @Test
    public void constructor_IdAutoIncrementsWithEachNewSeries() {
        // Arrange
        Series first  = new Series("Breaking Bad", 2008, "Crime", 9.5, "yes", 5, 62, "Gilligan");
        Series second = new Series("The Wire",     2002, "Crime", 9.3, "no",  5, 60, "Simon");
        // Act
        int firstId  = first.getCurrentEntryId();
        int secondId = second.getCurrentEntryId();
        // Assert
        assertEquals(firstId + 1, secondId, "Second Series ID should be exactly 1 more than the first");
    }

    //*************************************************************************************
    // ----------------------- Getter Tests -----------------------------------------------
    // ************************************************************************************

    /**
     * Test to check if getTitle returns the title passed into the constructor
     */
    @Test
    public void getTitle_ReturnsCorrectTitle() {
        Series entry = new Series("Breaking Bad", 2008, "Crime", 9.5, "yes", 5, 62, "Gilligan");
        String result = entry.getTitle();
        assertEquals("Breaking Bad", result);
    }

    /**
     * Test to check if getYear returns the year passed into the constructor
     */
    @Test
    public void getYear_ReturnsCorrectYear() {
        Series entry = new Series("Breaking Bad", 2008, "Crime", 9.5, "yes", 5, 62, "Gilligan");
        int result = entry.getYear();
        assertEquals(2008, result);
    }

    /**
     * Test to check if getGenre returns the genre passed into the constructor
     */
    @Test
    public void getGenre_ReturnsCorrectGenre() {
        Series entry = new Series("Breaking Bad", 2008, "Crime", 9.5, "yes", 5, 62, "Gilligan");
        String result = entry.getGenre();
        assertEquals("Crime", result);
    }

    /**
     * Test to check if getImdbRating returns the rating passed into the constructor
     */
    @Test
    public void getImdbRating_ReturnsCorrectRating() {
        Series entry = new Series("Breaking Bad", 2008, "Crime", 9.5, "yes", 5, 62, "Gilligan");
        double result = entry.getImdbRating();
        assertEquals(9.5, result);
    }

    /**
     * Test to check if getDescription returns the description passed into the constructor
     */
    @Test
    public void getDescription_ReturnsCorrectDescription() {
        Series entry = new Series("Breaking Bad", 2008, "Crime", 9.5, "yes", 5, 62, "Gilligan");
        String result = entry.getDescription();
        assertEquals("yes", result);
    }

    //*************************************************************************************
    // ----------------------- Series-Specific Getter Tests -------------------------------
    // ************************************************************************************

    /**
     * Test to check if getNumberOfSeasons returns the value passed into the constructor
     */
    @Test
    public void getNumberOfSeasons_ReturnsCorrectValue() {
        Series entry = new Series("Breaking Bad", 2008, "Crime", 9.5, "yes", 5, 62, "Gilligan");
        int result = entry.getNumberOfSeasons();
        assertEquals(5, result);
    }

    /**
     * Test to check if getNumberOfEpisodes returns the value passed into the constructor
     */
    @Test
    public void getNumberOfEpisodes_ReturnsCorrectValue() {
        Series entry = new Series("Breaking Bad", 2008, "Crime", 9.5, "yes", 5, 62, "Gilligan");
        int result = entry.getNumberOfEpisodes();
        assertEquals(62, result);
    }

    /**
     * Test to check if getCreator returns the creator passed into the constructor
     */
    @Test
    public void getCreator_ReturnsCorrectCreator() {
        Series entry = new Series("Breaking Bad", 2008, "Crime", 9.5, "yes", 5, 62, "Gilligan");
        String result = entry.getCreator();
        assertEquals("Gilligan", result);
    }

    //*************************************************************************************
    // ----------------------- Setters ----------------------------------------------------
    // ************************************************************************************

    /**
     * Test to check if setTitle updates the title correctly
     */
    @Test
    public void setTitle_UpdatesTitleCorrectly() {
        Series entry = new Series("Breaking Bad", 2008, "Crime", 9.5, "yes", 5, 62, "Gilligan");
        entry.setTitle("The Wire");
        assertEquals("The Wire", entry.getTitle());
    }

    /**
     * Test to check if setYear updates the year correctly
     */
    @Test
    public void setYear_UpdatesYearCorrectly() {
        Series entry = new Series("Breaking Bad", 2008, "Crime", 9.5, "yes", 5, 62, "Gilligan");
        entry.setYear(2002);
        assertEquals(2002, entry.getYear());
    }

    /**
     * Test to check if setGenre updates the genre correctly
     */
    @Test
    public void setGenre_UpdatesGenreCorrectly() {
        Series entry = new Series("Breaking Bad", 2008, "Crime", 9.5, "yes", 5, 62, "Gilligan");
        entry.setGenre("Drama");
        assertEquals("Drama", entry.getGenre());
    }

    /**
     * Test to check if setImdbRating updates the rating correctly
     */
    @Test
    public void setImdbRating_UpdatesRatingCorrectly() {
        Series entry = new Series("Breaking Bad", 2008, "Crime", 9.5, "yes", 5, 62, "Gilligan");
        entry.setImdbRating(9.9);
        assertEquals(9.9, entry.getImdbRating(), 0.001);
    }

    /**
     * Test to check if setDescription updates the description correctly
     */
    @Test
    public void setDescription_UpdatesDescriptionCorrectly() {
        Series entry = new Series("Breaking Bad", 2008, "Crime", 9.5, "yes", 5, 62, "Gilligan");
        entry.setDescription("You are goddamn right!");
        assertEquals("You are goddamn right!", entry.getDescription());
    }

    //*************************************************************************************
    // ----------------------- Series-Specific Setter Tests -------------------------------
    // ************************************************************************************

    /**
     * Test to check if setNumberOfSeasons updates the value correctly
     */
    @Test
    public void setNumberOfSeasons_UpdatesValueCorrectly() {
        Series entry = new Series("Breaking Bad", 2008, "Crime", 9.5, "yes", 5, 62, "Gilligan");
        entry.setNumberOfSeasons(6);
        assertEquals(6, entry.getNumberOfSeasons());
    }

    /**
     * Test to check if setNumberOfEpisodes updates the value correctly
     */
    @Test
    public void setNumberOfEpisodes_UpdatesValueCorrectly() {
        Series entry = new Series("Breaking Bad", 2008, "Crime", 9.5, "yes", 5, 62, "Gilligan");
        entry.setNumberOfEpisodes(70);
        assertEquals(70, entry.getNumberOfEpisodes());
    }

    /**
     * Test to check if setCreator updates the creator correctly
     */
    @Test
    public void setCreator_UpdatesCreatorCorrectly() {
        Series entry = new Series("Breaking Bad", 2008, "Crime", 9.5, "yes", 5, 62, "Gilligan");
        entry.setCreator("Vince");
        assertEquals("Vince", entry.getCreator());
    }

    //*************************************************************************************
    // ----------------------- toString Tests ---------------------------------------------
    // ************************************************************************************

    /**
     * Test to check if toString contains all Series fields (inherited + specific)
     */
    @Test
    public void testToString_ContainsAllFields() {
        Series entry = new Series("Breaking Bad", 2008, "Crime", 9.5, "yes", 5, 62, "Gilligan");
        String result = entry.toString();
        assertAll("toString should contain all Series fields",
                () -> assertTrue(result.contains("Breaking Bad"), "missing title"),
                () -> assertTrue(result.contains("2008"),         "missing year"),
                () -> assertTrue(result.contains("Crime"),        "missing genre"),
                () -> assertTrue(result.contains("9.5"),          "missing IMDB rating"),
                () -> assertTrue(result.contains("yes"),          "missing description"),
                () -> assertTrue(result.contains("5"),            "missing number of seasons"),
                () -> assertTrue(result.contains("62"),           "missing number of episodes"),
                () -> assertTrue(result.contains("Gilligan"),     "missing creator")
        );
    }

    //*************************************************************************************
    // ----------------------- toCSVStringRow Tests ---------------------------------------
    // ************************************************************************************

    /**
     * Test to check if toCSVStringRow contains all Series fields separated by commas
     */
    @Test
    public void toCSVStringRow_ContainsAllFields() {
        Series entry = new Series("Breaking Bad", 2008, "Crime", 9.5, "yes", 5, 62, "Gilligan");
        String result = entry.toCSVStringRow();
        assertAll("toCSVStringRow should contain all Series fields",
                () -> assertTrue(result.contains("Breaking Bad"), "missing title"),
                () -> assertTrue(result.contains("2008"),         "missing year"),
                () -> assertTrue(result.contains("Crime"),        "missing genre"),
                () -> assertTrue(result.contains("9.5"),          "missing IMDB rating"),
                () -> assertTrue(result.contains("yes"),          "missing description"),
                () -> assertTrue(result.contains("5"),            "missing number of seasons"),
                () -> assertTrue(result.contains("62"),           "missing number of episodes"),
                () -> assertTrue(result.contains("Gilligan"),     "missing creator")
        );
    }

}