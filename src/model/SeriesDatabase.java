package Model;
import src.model.Series;

import java.util.HashMap;

public class SeriesDatabase extends Database<Series> {

    public SeriesDatabase() {
        super(Series.class);
    }

    public void addSeries(Series series) {
        addEntry(series);
    }

    public Series getSeries(int id) {
        return getEntry(id);
    }

    public Series getSeries(String title) {
       return getEntry(title);
    }

    public HashMap<Integer, Series> getAllSeries() {
        return getAllEntries();
    }

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

    public void removeSeries(int id) {
        removeEntry(id);
    }

    public void removeSeries(String title) {
        removeEntry(title);
    }
}