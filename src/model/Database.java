package Model;
import java.util.HashMap;
import java.util.Map;
import src.model.RowEntry;


public abstract class Database<Entry extends RowEntry> {
    private final Class<Entry> entry;  // Java Generic. abstract object to be series or movie
    protected final HashMap<Integer, Entry> database = new HashMap<>();
    private int nextId = 1;

    /**
     * Creates an empty database for the given row entry type.
     *
     * @param entry the concrete entry class stored by this database
     */
    protected Database(Class<Entry> entry) {
        this.entry = entry;
    }

    /**
     * Adds an entry to the database using the next available integer ID.
     *
     * @param entry the entry to add
     */
    public void addEntry(Entry entry) {
        database.put(nextId++, entry);
    }

    /**
     * Retrieves the entry associated with the given ID.
     *
     * @param id the ID of the entry to retrieve
     * @return the matching entry, or {@code null} if no entry exists with that ID
     */
    public Entry getEntry(int id) {
        return database.get(id);
    }

    /**
     * Retrieves the first entry whose title matches the given title.
     *
     * @param title the title of the entry to retrieve
     * @return the matching entry, or {@code null} if no entry with that title is found
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
     * Returns all entries currently stored in the database.
     *
     * @return a map of entry IDs to their corresponding entry objects
     */
    public HashMap<Integer, Entry> getAllEntries() {
        return database;
    }

    /**
     * Updates a field of the entry associated with the given ID.
     *
     * @param id the ID of the entry to update
     * @param field the field number to update
     * @param value the new value for the selected field
     */
    public void updateEntry(int id, int field, String value) {
        Entry target = getEntry(id);
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
     * Updates a field of the first entry whose title matches the given title.
     *
     * @param title the title of the entry to update
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
        }
    }

    /**
     * Removes the entry associated with the given ID.
     *
     * @param id the ID of the entry to remove
     */
    public void removeEntry(int id) {
        database.remove(id);
    }

    /**
     * Removes the first entry whose title matches the given title.
     *
     * @param title the title of the entry to remove
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
