package Model;
import java.util.HashMap;
import java.util.Map;
import src.model.RowEntry;


public abstract class Database<Entry extends RowEntry> {
    private final Class<Entry> entry;  // abstract object to be series or movie
    protected HashMap<Integer, Entry> database = new HashMap<>();
    private int nextId = 1;

    /**
     * Creates an empty movie database and initializes its internal storage.
     */
    protected Database(Class<Entry> entry) {
        this.entry = entry;
    }

    /**
     * Adds a movie to the database using the next available integer ID.
     *
     * @param movie the movie to add
     */
    public void addEntry(Entry entry) {
        database.put(nextId++, entry);
    }

    /**
     * Removes the movie associated with the given ID.
     *
     * @param id the ID of the movie to remove
     */
    public void removeEntry(int id) {
        database.remove(id);
    }

    /**
     * Retrieves the movie associated with the given ID.
     *
     * @param id the ID of the movie to retrieve
     * @return the matching movie, or {@code null} if no movie exists with that ID
     */
    public Entry getEntry(int id) {
        return database.get(id);
    }

    /**
     * Retrieves the first movie whose title matches the given title.
     *
     * @param title the title of the movie to retrieve
     * @return the matching movie, or {@code null} if no movie with that title is found
     */
    public Entry getEntry(String title) {
        for (Map.Entry<Integer, Entry> entry: database.entrySet()) {
            Entry value = entry.getValue();
            if (value.getTitle().equals(title)) {
                return value;
            }
        }
        return null;
    }

    /**
     * Returns all movies currently stored in the database.
     *
     * @return a map of movie IDs to movie objects
     */
    public HashMap<Integer, Entry> getAllEntries() {
        return database;
    }

    /**
     * Updates a field of the movie associated with the given ID.
     *
     * @param id the ID of the movie to update
     * @param field the field number to update
     * @param value the new value for the selected field
     */
    public void updateEntry(int id, int field, String value) {
        Entry target = getMovie(id);
        switch(field) {
            case 1:
                target.setTitle(value);
                break;
            case 2:
                target.setYear(Integer.parseInt(value));
                break;
            case 3:
                target.setGenre(value);
                break;
            case 4:
                target.setImdbRating(Double.parseDouble(value));
                break;
            case 5:
                target.setDescription(value);
                break;
// Add in actual class
//            case 6:
//                target.setDirector(value);
//                break;
//            case 7:
//                target.setGross(Long.parseLong(value));
//                break;
        }
    }

    /**
     * Updates a field of the first movie whose title matches the given title.
     *
     * @param title the title of the movie to update
     * @param field the field number to update
     * @param value the new value for the selected field
     */
    public void updateEntry(String title, int field, String value) {
        Entry target = getEntry(title);
        switch(field) {
            case 1:
                target.setTitle(value);
                break;
            case 2:
                target.setYear(Integer.parseInt(value));
                break;
            case 3:
                target.setGenre(value);
                break;
            case 4:
                target.setImdbRating(Double.parseDouble(value));
                break;
            case 5:
                target.setDescription(value);
                break;
//            case 6:
//                target.setDirector(value);
//                break;
//            case 7:
//                target.setGross(Long.parseLong(value));
//                break;
        }
    }

    /**
     * Removes the first movie whose title matches the given title.
     *
     * @param title the title of the movie to remove
     */
    public void removeEntry(String title) {
        for (Map.Entry<Integer, Entry> entry : database.entrySet()) {
            int id = entry.getKey();
            Entry value = entry.getValue();
            if (value.getTitle().equals(title)) {
                database.remove(id);
            }
        }
    }
}
