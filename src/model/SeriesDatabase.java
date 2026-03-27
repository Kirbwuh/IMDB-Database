package src.model;
import src.model.Series;

import java.util.HashMap;

/**
 * CPSC 219 W26 Project
 * Stores and manages {@link Series} entries.
 *
 * <p>This class provides series-specific wrapper methods around the generic
 * functionality defined in {@link src,model.Database}.</p>
 *
 * @author Christopher Lassota
 * @email chris.lassota1@ucalgary.ca
 */
public class SeriesDatabase extends src.model.Database<Series> {

    /**
     * Creates an empty series database.
     */
    public SeriesDatabase() {
        super(Series.class);
    }

    /**
     * Adds a series to the database.
     *
     * @param series the series to add
     */
    public void addSeries(Series series) {
        addEntry(series);
    }

    /**
     * Retrieves a series by its database ID.
     *
     * @param id the ID of the series to retrieve
     * @return the matching series, or {@code null} if no series exists with that ID
     */
    public Series getSeries(int id) {
        return getEntry(id);
    }

    /**
     * Retrieves the first series with the given title.
     *
     * @param title the title of the series to retrieve
     * @return the matching series, or {@code null} if no series with that title exists
     */
    public Series getSeries(String title) {
       return getEntry(title);
    }

    /**
     * Returns all series currently stored in the database.
     *
     * @return a map of series IDs to series objects
     */
    public HashMap<Integer, Series> getAllSeries() {
        return getAllEntries();
    }

    /**
     * Updates a series field using the series ID.
     *
     * <p>Fields {@code 1} to {@code 5} are handled by the parent class.
     * Field {@code 6} updates the number of seasons, field {@code 7} updates
     * the number of episodes, and field {@code 8} updates the creator.</p>
     *
     * @param id the ID of the series to update
     * @param field the field number to update
     * @param value the new value for the selected field
     */
    public void updateSeries(int id, int field, String value) {
        if (field < 6) {
            updateEntry(id, field, value);
        } else {
            Series target = getSeries(id);
            switch(field) {
                case 6:
                    target.setNumberOfSeasons(Integer.parseInt(value));
                    break;
                case 7:
                    target.setNumberOfEpisodes(Integer.parseInt(value));
                    break;
                case 8:
                    target.setCreator(value);
                    break;
            }
        }
    }

    /**
     * Updates a series field using the series title.
     *
     * <p>Fields {@code 1} to {@code 5} are handled by the parent class.
     * Field {@code 6} updates the number of seasons, field {@code 7} updates
     * the number of episodes, and field {@code 8} updates the creator.</p>
     *
     * @param title the title of the series to update
     * @param field the field number to update
     * @param value the new value for the selected field
     */
    public void updateSeries(String title, int field, String value) {
        if (field < 6) {
            updateEntry(title, field, value);
        } else {
            Series target = getSeries(title);
            switch(field) {
                case 6:
                    target.setNumberOfSeasons(Integer.parseInt(value));
                    break;
                case 7:
                    target.setNumberOfEpisodes(Integer.parseInt(value));
                    break;
                case 8:
                    target.setCreator(value);
                    break;
            }
        }
    }

    /**
     * Removes a series by its database ID.
     *
     * @param id the ID of the series to remove
     */
    public void removeSeries(int id) {
        removeEntry(id);
    }

    /**
     * Removes the first series with the given title.
     *
     * @param title the title of the series to remove
     */
    public void removeSeries(String title) {
        removeEntry(title);
    }
}
