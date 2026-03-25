package model;

import org.junit.jupiter.api.Test;
import src.model.Movie;
import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    //*************************************************************************************
    // ----------------------- Constructor / ID Tests -------------------------------------
    // ************************************************************************************

    /**
     * Test to check if two Movie entries never share the same ID
     */
    @Test
    public void constructor_TwoMoviesNeverShareTheSameId() {
        // Arrange
        Movie movieA = new Movie("Inception", 2010, false, "Action", 8.8, "yes", "Nolan", 836);
        Movie movieB = new Movie("Tenet",     2020, false, "Action", 7.3, "no",   "Nolan", 363);
        // Act
        int idA = movieA.getCurrentEntryId();
        int idB = movieB.getCurrentEntryId();
        // Assert
        assertNotEquals(idA, idB, "No two Movies should ever share the same ID");
    }

    /**
     * Test to check if Movie IDs auto-increment with each new entry
     */
    @Test
    public void constructor_IdAutoIncrementsWithEachNewMovie() {
        // Arrange
        Movie first  = new Movie("Inception", 2010, false, "Action", 8.8, "yes", "Nolan", 836);
        Movie second = new Movie("Tenet",     2020, false, "Action", 7.3, "no",   "Nolan", 363);
        // Act
        int firstId  = first.getCurrentEntryId();
        int secondId = second.getCurrentEntryId();
        // Assert
        assertEquals(firstId + 1, secondId, "Second Movie ID should be exactly 1 more than the first");
    }



    //*************************************************************************************
    // ----------------------- Getter Tests -------------------------------------
    // ************************************************************************************

    /**
     * Test to check if getTitle returns the title passed into the constructor

     */
    @Test
    public void getTitle_ReturnsCorrectTitle() {
        // Arrange
        Movie entry = new Movie("Inception", 2010, false, "Action", 8.8, "yes", "Nolan", 836);
        // Act
        String result = entry.getTitle();
        // Assert
        assertEquals("Inception", result);
    }

    /**
     * Test to check if getYear returns the year passed into the constructor

     */
    @Test
    public void getYear_ReturnsCorrectYear() {
        // Arrange
        Movie entry = new Movie("Inception", 2010, false, "Action", 8.8, "yes", "Nolan", 836);
        // Act
        int result = entry.getYear();
        // Assert
        assertEquals(2010, result);
    }

    /**
     * Test to check if getGenre returns the genre passed into the constructor
     */
    @Test
    public void getGenre_ReturnsCorrectGenre() {
        // Arrange
        Movie entry = new Movie("Inception", 2010, false, "Action", 8.8, "yes", "Nolan", 836);
        // Act
        String result = entry.getGenre();
        // Assert
        assertEquals("Action", result);
    }

    /**
     * Test to check if getImdbRating returns the rating passed into the constructor
     */
    @Test
    public void getImdbRating_ReturnsCorrectRating() {
        // Arrange
        Movie entry = new Movie("Inception", 2010, false, "Action", 8.8, "yes", "Nolan", 836);;
        // Act
        double result = entry.getImdbRating();
        // Assert
        assertEquals(8.8, result);
    }

    /**
     * Test to check if getDescription returns the description passed into the constructor
     */
    @Test
    public void getDescription_ReturnsCorrectDescription() {
        // Arrange
        Movie entry = new Movie("Inception", 2010, false, "Action", 8.8, "yes", "Nolan", 836);;
        // Act
        String result = entry.getDescription();
        // Assert
        assertEquals("yes", result);
    }

    //*************************************************************************************
    // ----------------------- Movie-Specific Getter Tests --------------------------------
    // ************************************************************************************

    /**
     * Test to check if isCertification returns false when movie is not PG-13
     */
    @Test
    public void isCertification_ReturnsFalseWhenNotPG13() {
        // Arrange
        Movie entry = new Movie("Inception", 2010, false, "Action", 8.8, "yes", "Nolan", 836);;
        // Act
        boolean result = entry.isCertification();
        // Assert
        assertFalse(result, "Certification should be false as passed into constructor");
    }

    /**
     * Test to check if isCertification returns true when movie is PG-13
     */
    @Test
    public void isCertification_ReturnsTrueWhenPG13() {
        // Arrange
        Movie entry = new Movie("The Dark Knight", 2008, true, "Action", 9.0, "I'm Batman!", "Nolan", 1004558444L);
        // Act
        boolean result = entry.isCertification();
        // Assert
        assertTrue(result, "Certification should be true for a PG-13 movie");
    }

    /**
     * Test to check if getDirector returns the director passed into the constructor
     */
    @Test
    public void getDirector_ReturnsCorrectDirector() {
        // Arrange
        Movie entry = new Movie("Inception", 2010, false, "Action", 8.8, "yes", "Nolan", 836);;
        // Act
        String result = entry.getDirector();
        // Assert
        assertEquals("Nolan", result);
    }

    /**
     * Test to check if getGross returns the gross earnings passed into the constructor
     */
    @Test
    public void getGross_ReturnsCorrectGross() {
        // Arrange
        Movie entry = new Movie("Inception", 2010, false, "Action", 8.8, "yes", "Nolan", 836);
        // Act
        long result = entry.getGross();
        // Assert
        assertEquals(836, result);
    }

    //*************************************************************************************
    // ----------------------- Setters -------------------------------------
    // ************************************************************************************

    /**
     * Test to check if setTitle updates the title correctly
     */
    @Test
    public void setTitle_UpdatesTitleCorrectly() {
        // Arrange
        Movie entry = new Movie("Inception", 2010, false, "Action", 8.8, "yes", "Nolan", 836);;
        // Act
        entry.setTitle("Tenet");
        // Assert
        assertEquals("Tenet", entry.getTitle());
    }

    /**
     * Test to check if setYear updates the year correctly
     */
    @Test
    public void setYear_UpdatesYearCorrectly() {
        // Arrange
        Movie entry = new Movie("Inception", 2010, false, "Action", 8.8, "yes", "Nolan", 836);;
        // Act
        entry.setYear(2020);
        // Assert
        assertEquals(2020, entry.getYear());
    }

    /**
     * Test to check if setGenre updates the genre correctly
     */
    @Test
    public void setGenre_UpdatesGenreCorrectly() {
        // Arrange
        Movie entry = new Movie("Inception", 2010, false, "Action", 8.8, "yes", "Nolan", 836);;
        // Act
        entry.setGenre("Action");
        // Assert
        assertEquals("Action", entry.getGenre());
    }

    /**
     * Test to check if setImdbRating updates the rating correctly
     */
    @Test
    public void setImdbRating_UpdatesRatingCorrectly() {
        // Arrange
        Movie entry = new Movie("Inception", 2010, false, "Sci-Fi", 8.8, "yes", "Nolan", 836);
        // Act
        entry.setImdbRating(9.5);
        // Assert
        assertEquals(9.5, entry.getImdbRating(), 0.001);
    }

    /**
     * Test to check if setDescription updates the description correctly
     */
    @Test
    public void setDescription_UpdatesDescriptionCorrectly() {
        // Arrange
        Movie entry = new Movie("Inception", 2010, false, "Action", 8.8, "yes", "Nolan", 836);;
        // Act
        entry.setDescription("New desc");
        // Assert
        assertEquals("New desc", entry.getDescription());
    }

    //*************************************************************************************
    // ----------------------- Movie-Specific Setter Tests --------------------------------
    // ************************************************************************************

    /**
     * Test to check if setCertification updates the certification correctly
     */
    @Test
    public void setCertification_UpdatesCertificationCorrectly() {
        // Arrange
        Movie entry = new Movie("Inception", 2010, false, "Action", 8.8, "yes", "Nolan", 836);;
        // Act
        entry.setCertification(true);
        // Assert
        assertTrue(entry.isCertification(), "Certification should be updated to true");
    }

    /**
     * Test to check if setDirector updates the director correctly
     */
    @Test
    public void setDirector_UpdatesDirectorCorrectly() {
        // Arrange
        Movie entry = new Movie("Inception", 2010, false, "Action", 8.8, "yes", "Nolan", 836);;
        // Act
        entry.setDirector("Kubrick");
        // Assert
        assertEquals("Kubrick", entry.getDirector());
    }

    /**
     * Test to check if setGross updates the gross earnings correctly
     */
    @Test
    public void setGross_UpdatesGrossCorrectly() {
        // Arrange
        Movie entry = new Movie("Inception", 2010, false, "Action", 8.8, "yes", "Nolan", 836);;
        // Act
        entry.setGross(500000000);
        // Assert
        assertEquals(500000000, entry.getGross());
    }

    //*************************************************************************************
    // ----------------------- toString Tests ---------------------------------------------
    // ************************************************************************************

    /**
     * Test to check if toString contains all Movie fields (inherited + specific)
     */
    @Test
    public void testToString_ContainsAllFields() {
        // Arrange
        Movie entry = new Movie("Inception", 2010, false, "Sci-Fi", 8.8, "yes", "Nolan", 836);;
        // Act
        String result = entry.toString();
        // Assert
        assertAll("toString should contain all Movie fields",
                () -> assertTrue(result.contains("Inception"),  "missing title"),
                () -> assertTrue(result.contains("2010"),       "missing year"),
                () -> assertTrue(result.contains("Action"),     "missing genre"),
                () -> assertTrue(result.contains("8.8"),        "missing IMDB rating"),
                () -> assertTrue(result.contains("yes"), "missing description"),
                () -> assertTrue(result.contains("Nolan"),      "missing director"),
                () -> assertTrue(result.contains("836"),  "missing gross")
        );
    }

    //*************************************************************************************
    // ----------------------- toCSVStringRow Tests ---------------------------------------
    // ************************************************************************************

    /**
     * Test to check if toCSVStringRow contains all Movie fields separated by commas
     */
    @Test
    public void toCSVStringRow_ContainsAllFields() {
        // Arrange
        Movie entry = new Movie("Inception", 2010, false, "Action", 8.8, "yes", "Nolan", 836);
        // Act
        String result = entry.toCSVStringRow();
        // Assert
        assertAll("toCSVStringRow should contain all Movie fields",
                () -> assertTrue(result.contains("Inception"),  "missing title"),
                () -> assertTrue(result.contains("2010"),       "missing year"),
                () -> assertTrue(result.contains("Action"),     "missing genre"),
                () -> assertTrue(result.contains("8.8"),        "missing IMDB rating"),
                () -> assertTrue(result.contains("yes"), "missing description"),
                () -> assertTrue(result.contains("false"),      "missing certification"),
                () -> assertTrue(result.contains("Nolan"),      "missing director"),
                () -> assertTrue(result.contains("836"),  "missing gross")
        );
    }


}