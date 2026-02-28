package src;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Scanner;

public class CPSC219W26Project {

    //***********************************************************************
    //------------------Class Variables--------------------------
    //***********************************************************************

    // Establish id
    private static int nextId = 1;
    // create movie hashmap
    public static HashMap<Integer, String[]> movies = new HashMap<>();


    // String Array indexes within the HashMap
    public static final int SERIES_TITLE = 0;    // Name of movie (String)
    public static final int RELEASE_YEAR = 1;   // Year movie was released (int)
    public static final int CERTIFICATION = 2;   // Age / Content Rating PG-13 (boolean)
    public static final int GENRE = 3;           // Genre(s) of the movies (String)
    public static final int IMDB_RATING = 4;     // Rating of movie on IMDB (double)
    public static final int OVERVIEW = 5;        // Short description of movie (String)
    public static final int DIRECTOR = 6;      // Name of director (String)
    public static final int GROSS = 7;        // money made by movie (double)

    //***********************************************************************
    //------------------Main Method--------------------------
    //***********************************************************************

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int mainChoice, dbChoice, addChoice;

        do {
            mainChoice = showMainMenu(scanner);

            if (mainChoice == 1) {
                do {
                    dbChoice = showDatabaseMenu(scanner);

                    if (dbChoice == 1) {
                        do {
                            addChoice = showAddMovieMenu(scanner);

                            if (addChoice == 1) {
                                final HashMap<String, String> movieRow = singleEntryProcess(scanner);
                                addMovie(
                                        movieRow.get("series_title"),
                                        (int) Double.parseDouble(movieRow.get("released_year")),
                                        Boolean.parseBoolean(movieRow.get("PG-13")),
                                        movieRow.get("genre"),
                                        Double.parseDouble(movieRow.get("rating")),
                                        movieRow.get("movie"),
                                        movieRow.get("director"),
                                        Double.parseDouble(movieRow.get("gross"))
                                );
                                System.out.println("Movie added successfully!");
                                pressEnterToContinue(scanner);

                            } else if (addChoice == 2) {
                                final HashMap<String, String> movieRow = multilineEntryProcess(scanner);
                                addMovie(
                                        movieRow.get("series_title"),
                                        (int) Double.parseDouble(movieRow.get("released_year")),
                                        Boolean.parseBoolean(movieRow.get("PG-13")),
                                        movieRow.get("genre"),
                                        Double.parseDouble(movieRow.get("rating")),
                                        movieRow.get("description"),
                                        movieRow.get("director"),
                                        Double.parseDouble(movieRow.get("gross"))
                                );
                                System.out.println("Movie added successfully!");
                                pressEnterToContinue(scanner);
                            }

                        } while (addChoice != 3);

                    } else if (dbChoice == 2) {
                        int id = getNumericInput(scanner, "Enter movie ID:").intValue();
                        printMovieById(id);
                        pressEnterToContinue(scanner);

                    } else if (dbChoice == 3) {
                        int id = getNumericInput(scanner, "Enter movie ID to update:").intValue();
                        int field = getNumericInput(scanner, "Enter field (0=Title, 1=Year, 2=PG13, 3=Genre, 4=Rating, 5=Overview, 6=Director, 7=Gross):").intValue();
                        String newValue = getStringInput(scanner, "Enter new value:");
                        updateMovieById(id, field, newValue);
                        System.out.println("Movie updated successfully!");
                        pressEnterToContinue(scanner);

                    } else if (dbChoice == 4) {
                        int id = getNumericInput(scanner, "Enter movie ID to remove:").intValue();
                        removeMovieById(id);
                        System.out.println("Movie removed successfully!");
                        pressEnterToContinue(scanner);

                    } else if (dbChoice == 5) {
                        printAllMovies();
                        System.out.println("--- End of movie list ---");
                        pressEnterToContinue(scanner);
                    }

                } while (dbChoice != 6);
            }

        } while (mainChoice != 2);

        System.out.println("Goodbye!");
        scanner.close();
        System.exit(0);
    }

        // money made by movie (double)

    //***********************************************************************
    //----------------PROGRAM INPUT-----------------------------------------
    //***********************************************************************

   /*
    Data to be captured:

        series_title   (String) : Name of the movie
        released_year  (double)  : Year the movie was released
        PG-13          (boolean) : true/false
        genre          (String) : Genre(s) of the movie
        rating         (double) : Rating of the movie on the IMDb site
        description    (String) : Description of the movie
        director       (String) : Name of the director
        gross          (double)   : Total money earned by the movie
    */
    //***********************************************************************
    //-----------------Input Methods ----------------------------------------
    //***********************************************************************
    /**
     * This method receives a scanner object and captures
     * the user input and normalize it, whilst printing in console the data to be
     * capture
     * @param scanner scanner object from java.util.Scanner
     * @param prompt the prompt that specifies the user which data to input
     * @return the normalized input of the user as a String
     */
    public static String getStringInput (Scanner scanner, String prompt ){
        System.out.println(prompt);
        return (normalizeWord(scanner.nextLine()));
    }

    /**
     * This method receives a scanner object and captures
     * the user input, validating that it is numeric, whilst printing in console
     * the data to be captured.
     *
     * @param scanner scanner object from java.util.Scanner
     * @param prompt the prompt that specifies the user which numeric data to input
     * @return the numeric input of the user as a Double
     */
    public static Double getNumericInput(Scanner scanner, String prompt) {
        String numericInput;

        do {
            System.out.println(prompt);
            numericInput = scanner.nextLine();
            if (!isNumeric(numericInput)) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        } while (!isNumeric(numericInput));

        return Double.parseDouble(numericInput);
    }

    /**
     * This method receives a scanner object and captures
     * the user input, validating that it is numeric, whilst printing in console
     * the data to be captured.
     *
     * @param scanner scanner object from java.util.Scanner
     * @param prompt the prompt that specifies the user which numeric data to input
     * @return the numeric input of the user as an Integer
     */
    public static Integer getIntegerInput(Scanner scanner, String prompt) {
        String numericInput;

        do {
            System.out.println(prompt);
            numericInput = scanner.nextLine();
            if (!isNumeric(numericInput)) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        } while (!isNumeric(numericInput));

        return Integer.parseInt(numericInput);
    }

    public static boolean getBooleanInput(Scanner scanner, String prompt) {
        String input;


        do {
            System.out.println(prompt + " (0 = No, 1 = Yes)");
            input = scanner.nextLine();

            if (!input.equals("0") && !input.equals("1")) {
                System.out.println("Invalid input. Please enter 0 or 1.");
            }

        } while (!input.equals("0") && !input.equals("1"));

        return input.equals("1");

    }

    /** prompts user to press enter
     * @param scanner scanner object from java.util.Scanner
     */
    private static void pressEnterToContinue(Scanner scanner) {
        // source: https://stackoverflow.com/questions/26184409/java-console-prompt-for-enter-input-before-moving-on
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    /** Prompts the user line by line to add a new movie
     * @param scanner  scanner object from java.util.Scanner
     * @return Hashmap<String,String> of the collected values
     */
    //---------------------Input process methods ---------------------------------------------
    private static HashMap<Integer, String> singleEntryProcess(Scanner scanner) {

        HashMap<Integer, String> entry = new HashMap<>();

        //------- Question 1 --------------------
        final String q1Prompt = "Please input the name of the movie:";
        String seriesTitle = getStringInput(scanner, q1Prompt);
        entry.put(SERIES_TITLE, seriesTitle);

        //------- Question 2 --------------------
        final String q2Prompt = "Please input the release year of the movie:";
        Double releasedYear = getNumericInput(scanner, q2Prompt);
        entry.put(RELEASE_YEAR, String.valueOf(releasedYear));

        //------- Question 3 --------------------
        final String q3Prompt = "Is the movie rated PG-13?";
        boolean isPG13 = getBooleanInput(scanner, q3Prompt);
        entry.put(CERTIFICATION, String.valueOf(isPG13));

        //------- Question 4 --------------------
        final String q4Prompt = "Please input the genre of the movie:";
        String genre = getStringInput(scanner, q4Prompt);
        entry.put(GENRE, genre);

        //------- Question 5 --------------------
        final String q5Prompt = "Please input the IMDb rating of the movie:";
        Double rating = getNumericInput(scanner, q5Prompt);
        entry.put(IMDB_RATING, String.valueOf(rating));

        //------- Question 6 --------------------
        final String q6Prompt = "Please input the description of the movie:";
        String movieDesc = getStringInput(scanner, q6Prompt);
        entry.put(OVERVIEW, movieDesc);

        //------- Question 7 --------------------
        final String q7Prompt = "Please input the name of the director:";
        String director = getStringInput(scanner, q7Prompt);
        entry.put(DIRECTOR, director);

        //------- Question 8 --------------------
        final String q8Prompt = "Please input the gross earnings of the movie:";
        Double gross = getNumericInput(scanner, q8Prompt);
        entry.put(GROSS, String.valueOf(gross.longValue()));

        return entry;
    }

    /**
     * Prompts the user to enter all movie data in a single line,
     * using comma-separated values in a predefined order.
     * The method validates that the correct number of values is provided
     * and that each value matches its expected data type
     * (String, numeric, or boolean).
     * The user is repeatedly prompted until valid input is entered.
     *
     * @param scanner scanner object from java.util.Scanner
     * @return HashMap<String,String> containing the validated movie data
     */
    private static HashMap<Integer, String> multilineEntryProcess(Scanner scanner) {
        String[] separatedValuesList = null;
        boolean validInput;
        HashMap<Integer, String> entry = new HashMap<>();

        final String prompt = """
            Please enter exactly 8 values separated by commas, in this order:
            series_title, released_year, PG-13 (true or false), genre, rating, description, director, gross
            Example: The Matrix,1999,true,Action/Sci-Fi,8.7,A hacker discovers reality,The Wachowskis,463517383""";

        do {

            validInput = true;
            separatedValuesList = null;

            String inputString = getStringInput(scanner, prompt);

            if (inputString.trim().isEmpty()) {
                System.out.println("Invalid input: input cannot be empty. Please enter all 8 values.");
                validInput = false;
                continue;
            }

            separatedValuesList = separateCommaValues(inputString);

            if (separatedValuesList.length != 8) {
                System.out.println("Invalid input: you must enter exactly 8 comma-separated values.");
                validInput = false;
                continue;
            }

            for (int i = 0; i < separatedValuesList.length; i++) {
                String value = separatedValuesList[i].trim();

                switch (i) {
                    //-------- String values
                    case SERIES_TITLE:
                    case GENRE:
                    case OVERVIEW:
                    case DIRECTOR:
                        if (value.isEmpty()) {
                            System.out.println("Invalid input: text fields cannot be empty.");
                            validInput = false;
                        }
                        break;

                    //---------Numeric values
                    case RELEASE_YEAR:
                    case IMDB_RATING:
                    case GROSS:
                        if (!isNumeric(value)) {
                            System.out.println("Invalid input: numeric value expected at position " + (i + 1));
                            validInput = false;
                        }
                        break;

                    //-----------Boolean value
                    case CERTIFICATION: // PG-13 (true / false)
                        if (!(value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false"))) {
                            System.out.println("Invalid input: PG-13 must be true or false.");
                            validInput = false;
                        }
                        break;
                }

                if (!validInput) break;
            }

        } while (!validInput);

        if (separatedValuesList == null || separatedValuesList.length != 8) {
            return new HashMap<>();
        }else{
            entry.put(SERIES_TITLE, separatedValuesList[0].trim());
            entry.put(RELEASE_YEAR, separatedValuesList[1].trim());
            entry.put(CERTIFICATION, String.valueOf(Boolean.parseBoolean(separatedValuesList[2].trim())));
            entry.put(GENRE, separatedValuesList[3].trim());
            entry.put(IMDB_RATING, separatedValuesList[4].trim());
            entry.put(OVERVIEW, separatedValuesList[5].trim());
            entry.put(DIRECTOR, separatedValuesList[6].trim());
            entry.put(GROSS, separatedValuesList[7].trim());

            return entry;
        }

        entry.put("series_title",  separatedValuesList[0].trim());
        entry.put("released_year", separatedValuesList[1].trim());
        entry.put("PG-13",         String.valueOf(Boolean.parseBoolean(separatedValuesList[2].trim())));
        entry.put("genre",         separatedValuesList[3].trim());
        entry.put("rating",        separatedValuesList[4].trim());
        entry.put("description",   separatedValuesList[5].trim());
        entry.put("director",      separatedValuesList[6].trim());
        entry.put("gross",         separatedValuesList[7].trim());

        return entry;
    }

    /**
     * Prompts the user to search for a movie by its title
     *
     * @param scanner scanner object from java.util.Scanner
     * @return movie String[] or null
     */
    private static String[] searchMovieByTitle(Scanner scanner) {
        final String searchPrompt = "Enter the TITLE of the Movie you would like to Search.";
        String searchInput = getStringInput(scanner, searchPrompt);
        return getMovieByTitle(searchInput);
    }

    /**
     * Prompts the user to search for a movie by its ID
     *
     * @param scanner scanner object from java.util.Scanner
     * @return movie String[] or null
     */
    private static String[] searchMovieByID(Scanner scanner) {
        final String searchPrompt = "Enter the ID of the Movie you would like to Search.";
        int searchInput = getIntegerInput(scanner, searchPrompt);
        return getMovieById(searchInput);
    }

    private static ArrayList<String> getCategoryInformation(Scanner scanner) {
        final String prompt = ("""
                Please enter a number corresponding to the data you want displayed:
                1. Series Titles
                2. Release Years
                3. PG- 13
                4. Genres
                5. IMDB Ratings
                6. Overviews
                7. Gross Earnings
                """);
        int searchInput = getIntegerInput(scanner, prompt);
        return getInformation(searchInput);
    }

    //***********************************************************************
    //------------------Menu Methods------------------------------------
    //***********************************************************************
    // Menu order
    /*
     *  showMainMenu()
     *  |
     *  +-- 1. Manage Database --> showDatabaseMenu()
     *  |         |
     *  |         +-- 1. Add Movie --> showAddMovieMenu()
     *  |         |         |
     *  |         |         +-- 1. Step by step
     *  |         |         +-- 2. Single line
     *  |         |         +-- 3. Back
     *  |         |
     *  |         +-- 2. Search by ID
     *  |         +-- 3. Update movie
     *  |         +-- 4. Remove movie
     *  |         +-- 5. Print all movies
     *  |         +-- 6. Back
     *  |
     *  +-- 2. Exit
     */

    /**
     * Displays the main menu and returns the user's choice
     * @param scanner scanner object from java.util.Scanner
     * @return the menu option selected by the user as an int
     */
    private static int showMainMenu(Scanner scanner) {
        String choice;

        System.out.println("********************* IMDb Movie Database - CPSC219 W26  *********************");
        System.out.println("Track and store your favourite movies with ratings, directors, genres and more.");
        System.out.println();

        System.out.println("""
        +--------+---------------------------+--------------------------------+
        | Option | Action                    | Description                    |
        +--------+---------------------------+--------------------------------+
        |   1    | Manage database           | Add, search, update or remove  |
        |   2    | Exit                      | Close the program              |
        +--------+---------------------------+--------------------------------+""");

        do {
            System.out.println("Please enter an option (1 or 2):");
            choice = scanner.nextLine().trim();

            if (!choice.equals("1") && !choice.equals("2")) {
                System.out.println("Invalid input. Please enter 1 or 2.");
            }

        } while (!choice.equals("1") && !choice.equals("2"));

        return Integer.parseInt(choice);
    }
    /**
     * Displays the database management menu and returns the user's choice
     * @param scanner scanner object from java.util.Scanner
     * @return the menu option selected by the user as an int
     */
    private static int showDatabaseMenu(Scanner scanner) {
        String choice;

        System.out.println("\n==================== Database Management ====================");
        System.out.println("""
        +--------+---------------------------+--------------------------------+
        | Option | Action                    | Description                    |
        +--------+---------------------------+--------------------------------+
        |   1    | Add movie                 | Choose an add method           |
        |   2    | Search movie by ID        | Find a movie using its ID      |
        |   3    | Update movie              | Modify an existing movie       |
        |   4    | Remove movie              | Delete a movie from database   |
        |   5    | Print all movies          | Display all stored movies      |
        |   6    | Back                      | Return to main menu            |
        +--------+---------------------------+--------------------------------+""");

        do {
            System.out.println("Please enter an option (1-6):");
            choice = scanner.nextLine().trim();

            if (!choice.matches("[1-6]")) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
            }

        } while (!choice.matches("[1-6]"));

        return Integer.parseInt(choice);
    }

    /**
     * Displays the add movie submenu and returns the user's choice
     * @param scanner scanner object from java.util.Scanner
     * @return the menu option selected by the user as an int
     */
    private static int showAddMovieMenu(Scanner scanner) {
        String choice;

        System.out.println("\n==================== Add Movie ====================");
        System.out.println("""
        +--------+---------------------------+--------------------------------+
        | Option | Action                    | Description                    |
        +--------+---------------------------+--------------------------------+
        |   1    | Add movie (step by step)  | Answer one value at a time     |
        |   2    | Add movie (single line)   | Enter all 8 values with commas |
        |   3    | Back                      | Return to database menu        |
        +--------+---------------------------+--------------------------------+""");

        do {
            System.out.println("Please enter an option (1-3):");
            choice = scanner.nextLine().trim();

            if (!choice.matches("[1-3]")) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
            }

        } while (!choice.matches("[1-3]"));

        return Integer.parseInt(choice);
    }

    //***********************************************************************
    //---------------- Helper Methods ---------------------------------------
    //***********************************************************************

    /**
     * Normalizes a word by converting it to lower case and trimming spaces
     * @param word String a string
     * @return returns a normalized string (lower case and spaces trimmed)
     */
    public static String normalizeWord(String word){
        return word.toLowerCase().trim();
    }

    /**
     * Checks if an input ony contains numeric values
     * @param input A String
     * @return a boolean that checks if the string only contains numbers
     */
    public static boolean isNumeric(String input) {
        // source:
        // https://stackoverflow.com/questions/15111420/how-to-check-if-a-string-contains-only-digits-in-java
        // https://stackoverflow.com/questions/10921058/regex-matching-numbers-and-decimals
        String regex = "^[+-]?(\\d*|\\d{1,3}(,\\d{3})*)(\\.\\d+)?\\b$";
        return input.matches(regex);
    }

    /** Takes a string in the form:
     *  "<element1>,<element2>...."
     *  and separates them into an Array
     *  if not a valid separated values String returns an empty String
     * @param commaValuesString String with elements separate with comma values
     * @return  String[] and array with the values separated
     */
    public static String[] separateCommaValues (String commaValuesString){
        if (commaValuesString.contains(",")){
            return commaValuesString.split(",");

        }else{
            return new String[] {};
        }
    }




    //***********************************************************************
    //------------------ADD DATA METHODS-------------------------------------
    //***********************************************************************
    /**
     * Adds a movie to the map and assigns the next available id.
     *
     * @param entry Hashmap of movie data.
     */
    public static void addMovie(HashMap<Integer, String> entry) {
        int id = nextId++;
        movies.put(id,new String[] {
            entry.get(SERIES_TITLE), entry.get(RELEASE_YEAR),entry.get(CERTIFICATION),entry.get(GENRE),entry.get(IMDB_RATING),entry.get(OVERVIEW),entry.get(DIRECTOR),entry.get(GROSS)
        });
    }

    //***********************************************************************
    //------------------GET DATA METHODS------------------------------------
    //***********************************************************************

    /**
     * Returns movie object by associated title
     *
     * @param title movie title associated with entry.
     * @return movie object with associated title.
     */
    public static String[] getMovieByTitle(String title) {
        for (Map.Entry<Integer, String[]> entry: movies.entrySet()) {
            String[] movie = entry.getValue();
            if (movie[SERIES_TITLE].equals(title)) {
                return movie;
            }
        }
        return null;
    }

    /**
     * Returns movie object by associated ID
     *
     * @param id movie ID in HashMap
     * @return movie object
     */
    public static String[] getMovieById(int id) {
        for (Map.Entry<Integer, String[]> entry: movies.entrySet()) {
            int key = entry.getKey();
            String[] movie = entry.getValue();
            if (key == id) {
                return movie;
            }
        }
        return null;
    }

    /**
     *Returns an ArrayList of specified values present in the HashMap. For example using GENRE will return all the genres.
     *
     * @param index Use the constants to index the information you want returned
     * @return ArrayList of specified values.
     */
    public static ArrayList<String> getInformation(int index) {
        ArrayList<String> values = new ArrayList<String>();
        for (Map.Entry<Integer, String[]> entry : movies.entrySet()) {
            String[] movie = entry.getValue();
            values.add(movie[index]);
        }
        return values;
    }

    //***********************************************************************
    //------------------UPDATE DATA METHODS----------------------------------
    //***********************************************************************

    /**
     *Updates specified movie data by ID matching.
     *
     * @param id id (key) of movie
     * @param index The index of the data you want to update based on constants.
     * @param update The update/change you want to make
     */
    public static void updateMovieById(int id,int index,String update) {
        String[] movie = movies.get(id);
        if (movie != null) {
            movie[index] = update;
        }
    }

    /**
     *Updates specified movie data by title matching.
     *
     * @param title title of movie
     * @param index The index of the data you want to update based on constants.
     * @param update the update/change you want to make.
     */
    public static void updateMovieByTitle(String title, int index, String update) {
        for (Map.Entry<Integer, String[]> entry: movies.entrySet()) {
            String[] movie = entry.getValue();
            if (movie[SERIES_TITLE].equals(title)) {
                movie[index] = update;
            }
        }
    }

    //***********************************************************************
    //------------------REMOVE DATA METHODS----------------------------------
    //***********************************************************************

    /**
     * Deletes a movie entry by its title.
     *
     * @param title movie title associated with entry.
     */
    public static void removeMovieByTitle(String title) {
        for (Map.Entry<Integer, String[]> entry : movies.entrySet()) {
            int id = entry.getKey();
            String[] movie = entry.getValue();
            if (movie[SERIES_TITLE].equals(title)) {
                movies.remove(id);
            }
        }
    }

    /**
     * Deletes a movie entry by its ID.
     *
     * @param id movie id associated with entry.
     */
    public static void removeMovieById(int id) {
        if (movies.containsKey(id)) {
            movies.remove(id);
        } else {
            System.out.println("No movie found with ID: " + id);
        }
    }

    //***********************************************************************
    //------------------QUICK PRINT METHODS FOR TESTING----------------------
    //***********************************************************************

    /**
     * Builds a formatted string for a single movie record.
     *
     * @param movie record values stored in the fixed index order
     * @return formatted movie details
     */
    public static String movieToString(String[] movie) {
        return "Movie {" +
                "\n Series Title: " + movie[SERIES_TITLE] +
                "\n Release Year: " + movie[RELEASE_YEAR] +
                "\n Certification(PG-13): " + movie[CERTIFICATION] +
                "\n Genre: " + movie[GENRE] +
                "\n IMDB Rating: " + movie[IMDB_RATING] +
                "\n Overview: " + movie[OVERVIEW] +
                "\n Director: " + movie[DIRECTOR] +
                "\n Gross: " + movie[GROSS] +
                "\n }";
    }

    /**
     * Print's a single movie by associated title.
     *
     * @param title movie title associated with entry.
     */
    public static void printMovieByTitle(String title) {
        System.out.println(movieToString(getMovieByTitle(title)));
    }

    /**
     * Print's a single movie by associated ID
     *
     * @param id movie id associated with entry.
     */
    public static void printMovieById(int id) {
        System.out.println(movieToString(getMovieById(id)));
    }

    /**
     * Prints every movie currently stored in the map.
     */
    public static void printAllMovies() {
        for (Map.Entry<Integer, String[]> entry: movies.entrySet()) {
            String[] movie = entry.getValue();
            System.out.println(movieToString(movie));
        }
    }
}

