package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CPSC 219 W26 Project
 * <p>
 * SeriesDatabaseTest
 * <p>
 * Test class for all seriesDatabase methods
 *
 * @author Christopher Lassota
 * @email chris.lassota1@ucalgary.ca
 */
public class SeriesDatabaseTest {

    @Test
    void test_SeriesDatabaseConstructor() {
        SeriesDatabase database = new SeriesDatabase();

        assertAll("new series database should start empty",
                () -> assertTrue(database.getAllSeries().isEmpty()),
                () -> assertNull(database.getSeries(1)),
                () -> assertNull(database.getSeries("Any Title"))
        );
    }

    @Test
    void test_addSeries_success() {
        SeriesDatabase database = new SeriesDatabase();
        Series series = new Series("Breaking Bad", 2008, "Drama", 9.5, "Chemistry teacher turns to crime", 5, 62, "Gilligan");

        database.addSeries(series);

        assertAll("series should be added and retrievable",
                () -> assertEquals(1, database.getAllSeries().size()),
                () -> assertSame(series, database.getSeries(1)),
                () -> assertSame(series, database.getSeries("Breaking Bad"))
        );
    }

    @Test
    void test_addSeries_fail() {
        SeriesDatabase database = new SeriesDatabase();
        Series series = new Series("The Wire", 2002, "Drama", 9.3, "Baltimore crime drama", 5, 60, "Simon");

        database.addSeries(series);

        assertAll("non-existent series should not be returned",
                () -> assertNull(database.getSeries(2)),
                () -> assertNull(database.getSeries("Lost"))
        );
    }

    @Test
    void test_addSeries_nullSeries() {
        SeriesDatabase database = new SeriesDatabase();

        database.addSeries(null);

        assertAll("adding null should still create a slot in the current implementation",
                () -> assertEquals(1, database.getAllSeries().size()),
                () -> assertNull(database.getSeries(1))
        );
    }

    @Test
    void test_getSeries_success() {
        SeriesDatabase database = new SeriesDatabase();
        Series series = new Series("Breaking Bad", 2008, "Drama", 9.5, "Chemistry teacher turns to crime", 5, 62, "Gilligan");

        database.addSeries(series);

        assertAll("existing series should be retrievable",
                () -> assertSame(series, database.getSeries(1)),
                () -> assertSame(series, database.getSeries("Breaking Bad"))
        );
    }

    @Test
    void test_getSeries_fail() {
        SeriesDatabase database = new SeriesDatabase();
        Series series = new Series("The Wire", 2002, "Drama", 9.3, "Baltimore crime drama", 5, 60, "Simon");

        database.addSeries(series);

        assertAll("non-existent getSeries lookups should return null",
                () -> assertNull(database.getSeries(2)),
                () -> assertNull(database.getSeries("Lost"))
        );
    }

    @Test
    void test_getSeries_invalidId() {
        SeriesDatabase database = new SeriesDatabase();
        Series series = new Series("The Wire", 2002, "Drama", 9.3, "Baltimore crime drama", 5, 60, "Simon");

        database.addSeries(series);

        assertAll("invalid ids should return null",
                () -> assertNull(database.getSeries(0)),
                () -> assertNull(database.getSeries(-1)),
                () -> assertNull(database.getSeries(99))
        );
    }

    @Test
    void test_getSeries_title() {
        SeriesDatabase database = new SeriesDatabase();
        Series series = new Series("Breaking Bad", 2008, "Drama", 9.5, "Chemistry teacher turns to crime", 5, 62, "Gilligan");

        database.addSeries(series);

        assertAll("title lookups are exact-match in the current implementation",
                () -> assertNull(database.getSeries(null)),
                () -> assertNull(database.getSeries("")),
                () -> assertNull(database.getSeries(" breaking bad ")),
                () -> assertNull(database.getSeries("breaking bad"))
        );
    }

    @Test
    void test_getAllSeries_success() {
        SeriesDatabase database = new SeriesDatabase();
        Series firstSeries = new Series("Breaking Bad", 2008, "Drama", 9.5, "Chemistry teacher turns to crime", 5, 62, "Gilligan");
        Series secondSeries = new Series("The Wire", 2002, "Drama", 9.3, "Baltimore crime drama", 5, 60, "Simon");

        database.addSeries(firstSeries);
        database.addSeries(secondSeries);

        assertAll("getAllSeries should return every stored series",
                () -> assertEquals(2, database.getAllSeries().size()),
                () -> assertSame(firstSeries, database.getAllSeries().get(1)),
                () -> assertSame(secondSeries, database.getAllSeries().get(2))
        );
    }

    @Test
    void test_getAllSeries_fail() {
        SeriesDatabase database = new SeriesDatabase();
        Series series = new Series("Breaking Bad", 2008, "Drama", 9.5, "Chemistry teacher turns to crime", 5, 62, "Gilligan");

        database.addSeries(series);

        assertAll("getAllSeries should not contain missing entries",
                () -> assertNotEquals(2, database.getAllSeries().size()),
                () -> assertNull(database.getAllSeries().get(2))
        );
    }

    @Test
    void test_getAllSeries_emptyDatabase() {
        SeriesDatabase database = new SeriesDatabase();

        assertAll("getAllSeries should be empty for a new database",
                () -> assertTrue(database.getAllSeries().isEmpty()),
                () -> assertEquals(0, database.getAllSeries().size())
        );
    }

    @Test
    void test_updateSeriesById_success() {
        SeriesDatabase database = new SeriesDatabase();
        Series series = new Series("Breaking Bad", 2008, "Drama", 9.5, "Chemistry teacher turns to crime", 5, 62, "Gilligan");

        database.addSeries(series);
        database.updateSeries(1, 1, "Better Call Saul");
        database.updateSeries(1, 6, "6");
        database.updateSeries(1, 8, "Vince Gilligan");

        assertAll("updateSeries by id should update stored series fields",
                () -> assertEquals("Better Call Saul", database.getSeries(1).getTitle()),
                () -> assertEquals(6, database.getSeries(1).getNumberOfSeasons()),
                () -> assertEquals("Vince Gilligan", database.getSeries(1).getCreator())
        );
    }

    @Test
    void test_updateSeriesByTitle_success() {
        SeriesDatabase database = new SeriesDatabase();
        Series series = new Series("The Wire", 2002, "Drama", 9.3, "Baltimore crime drama", 5, 60, "Simon");

        database.addSeries(series);
        database.updateSeries("The Wire", 2, "2003");
        database.updateSeries("The Wire", 7, "61");
        database.updateSeries("The Wire", 8, "David Simon");

        assertAll("updateSeries by title should update stored series fields",
                () -> assertEquals(2003, database.getSeries("The Wire").getYear()),
                () -> assertEquals(61, database.getSeries("The Wire").getNumberOfEpisodes()),
                () -> assertEquals("David Simon", database.getSeries("The Wire").getCreator())
        );
    }

    @Test
    void test_updateSeries_fail() {
        SeriesDatabase database = new SeriesDatabase();
        Series series = new Series("Breaking Bad", 2008, "Drama", 9.5, "Chemistry teacher turns to crime", 5, 62, "Gilligan");

        database.addSeries(series);

        assertAll("updateSeries should not affect missing targets",
                () -> assertThrows(NullPointerException.class, () -> database.updateSeries(2, 1, "Better Call Saul")),
                () -> assertThrows(NullPointerException.class, () -> database.updateSeries("Missing Title", 8, "Someone Else"))
        );
    }

    @Test
    void test_updateSeries_invalidFieldDoesNothing() {
        SeriesDatabase database = new SeriesDatabase();
        Series series = new Series("Breaking Bad", 2008, "Drama", 9.5, "Chemistry teacher turns to crime", 5, 62, "Gilligan");

        database.addSeries(series);
        database.updateSeries(1, 99, "Ignored");

        assertAll("unsupported field numbers should leave the series unchanged",
                () -> assertEquals("Breaking Bad", database.getSeries(1).getTitle()),
                () -> assertEquals(5, database.getSeries(1).getNumberOfSeasons()),
                () -> assertEquals("Gilligan", database.getSeries(1).getCreator())
        );
    }

    @Test
    void test_updateSeries_duplicateTitleUpdatesFirstMatch() {
        SeriesDatabase database = new SeriesDatabase();
        Series firstSeries = new Series("Duplicate", 2008, "Drama", 9.5, "First", 5, 62, "Gilligan");
        Series secondSeries = new Series("Duplicate", 2004, "Drama", 8.7, "Second", 6, 121, "Lindelof");

        database.addSeries(firstSeries);
        database.addSeries(secondSeries);
        database.updateSeries("Duplicate", 8, "Updated Creator");

        assertAll("title-based updates should affect only the first matching series",
                () -> assertEquals("Updated Creator", database.getSeries(1).getCreator()),
                () -> assertEquals("Lindelof", database.getSeries(2).getCreator())
        );
    }

    @Test
    void test_removeSeriesById_success() {
        SeriesDatabase database = new SeriesDatabase();
        Series series = new Series("Breaking Bad", 2008, "Drama", 9.5, "Chemistry teacher turns to crime", 5, 62, "Gilligan");

        database.addSeries(series);
        database.removeSeries(1);

        assertAll("removeSeries by id should remove the stored series",
                () -> assertTrue(database.getAllSeries().isEmpty()),
                () -> assertNull(database.getSeries(1)),
                () -> assertNull(database.getSeries("Breaking Bad"))
        );
    }

    @Test
    void test_removeSeriesByTitle_success() {
        SeriesDatabase database = new SeriesDatabase();
        Series series = new Series("The Wire", 2002, "Drama", 9.3, "Baltimore crime drama", 5, 60, "Simon");

        database.addSeries(series);
        database.removeSeries("The Wire");

        assertAll("removeSeries by title should remove the stored series",
                () -> assertTrue(database.getAllSeries().isEmpty()),
                () -> assertNull(database.getSeries(1)),
                () -> assertNull(database.getSeries("The Wire"))
        );
    }

    @Test
    void test_removeSeries_fail() {
        SeriesDatabase database = new SeriesDatabase();
        Series series = new Series("Breaking Bad", 2008, "Drama", 9.5, "Chemistry teacher turns to crime", 5, 62, "Gilligan");

        database.addSeries(series);

        assertDoesNotThrow(() -> {
            database.removeSeries(2);
            database.removeSeries("Missing Title");
        });
        assertAll("removing missing series should leave existing entries untouched",
                () -> assertEquals(1, database.getAllSeries().size()),
                () -> assertSame(series, database.getSeries(1)),
                () -> assertSame(series, database.getSeries("Breaking Bad"))
        );
    }

    @Test
    void test_removeSeries_emptyDatabase() {
        SeriesDatabase database = new SeriesDatabase();

        assertDoesNotThrow(() -> {
            database.removeSeries(1);
            database.removeSeries("Missing Title");
        });
        assertTrue(database.getAllSeries().isEmpty());
    }

    @Test
    void test_removeSeries_sameSeriesTwice() {
        SeriesDatabase database = new SeriesDatabase();
        Series series = new Series("Breaking Bad", 2008, "Drama", 9.5, "Chemistry teacher turns to crime", 5, 62, "Gilligan");

        database.addSeries(series);
        database.removeSeries(1);

        assertDoesNotThrow(() -> database.removeSeries(1));
        assertAll("removing the same series twice should keep the database empty",
                () -> assertTrue(database.getAllSeries().isEmpty()),
                () -> assertNull(database.getSeries(1))
        );
    }
}
