package Model;
import src.model.Series;

import java.util.HashMap;

public class SeriesDatabase extends Database<Series> {

    public SeriesDatabase() {
        super(Series.class);
    }

    public void addSeries(Series movie) {
        addEntry(movie);
    }

    public Series getSeries(int id) {
        getEntry(id);
    }

    public Series getSeries(String title) {
        getEntry(title);
    }

    public HashMap<Integer, Series> getAllSeries() {
        getAllEntries();
    }

    public void updateSeries(int id, int field, String value) {
        updateEntry(id, field, value);
    }

    public void updateSeries(String title, int field, String value) {
        updateEntry(title, field, value);
    }

    public void removeSeries(int id) {
        removeEntry(id);
    }

    public void removeSeries(String title) {
        removeEntry(title);
    }
}
