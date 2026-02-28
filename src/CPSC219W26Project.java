package src;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Scanner;

public class CPSC219W26Project {

    static void main(String[] args) {
        Scanner inputScannerObject = new Scanner(System.in);
        int choice = showMainMenu(inputScannerObject);
        HashMap<String, String> movieRow;
        switch (choice) {
            case 1:
                System.out.println("You chose option 1.");
                movieRow = singleEntryProcess(inputScannerObject);
                break;

            case 2:
                System.out.println("You chose option 2.");
                movieRow = multilineEntryProcess(inputScannerObject);
                break;

            case 3:
                // source
                // https://stackoverflow.com/questions/22452930/terminating-a-java-program
                System.out.println("You chose option 3.");
                System.out.println("Goodbye!");
                System.exit(0);
                break;
        }
    }

    //***********************************************************************
    //----------------PROGRAM INPUT---------------------------------
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

    /** Prompts the user line by line to add a new movie
     * @param scanner  scanner object from java.util.Scanner
     * @return Hashmap<String,String> of the collected values
     */
    //---------------------Input process methods ---------------------------------------------
    private static HashMap<String, String> singleEntryProcess(Scanner scanner) {

        HashMap<String, String> entry = new HashMap<>();

        //------- Question 1 --------------------
        final String q1Prompt = "Please input the name of the movie:";
        String seriesTitle = getStringInput(scanner, q1Prompt);
        entry.put("series_title", seriesTitle);

        //------- Question 2 --------------------
        final String q2Prompt = "Please input the release year of the movie:";
        Double releasedYear = getNumericInput(scanner, q2Prompt);
        entry.put("released_year", String.valueOf(releasedYear));

        //------- Question 3 --------------------
        final String q3Prompt = "Is the movie rated PG-13?";
        boolean isPG13 = getBooleanInput(scanner, q3Prompt);
        entry.put("PG-13", String.valueOf(isPG13));

        //------- Question 4 --------------------
        final String q4Prompt = "Please input the genre of the movie:";
        String genre = getStringInput(scanner, q4Prompt);
        entry.put("genre", genre);

        //------- Question 5 --------------------
        final String q5Prompt = "Please input the IMDb rating of the movie:";
        Double rating = getNumericInput(scanner, q5Prompt);
        entry.put("rating", String.valueOf(rating));

        //------- Question 6 --------------------
        final String q6Prompt = "Please input the desription of the movie:";
        String movieDesc = getStringInput(scanner, q6Prompt);
        entry.put("movie", movieDesc);

        //------- Question 7 --------------------
        final String q7Prompt = "Please input the name of the director:";
        String director = getStringInput(scanner, q7Prompt);
        entry.put("director", director);

        //------- Question 8 --------------------
        final String q8Prompt = "Please input the gross earnings of the movie:";
        Double gross = getNumericInput(scanner, q8Prompt);
        entry.put("gross", String.valueOf(gross.longValue()));

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
    private static HashMap<String, String> multilineEntryProcess(Scanner scanner) {
        String[] separatedValuesList = null;
        boolean validInput;
        HashMap<String, String> entry = new HashMap<>();


        final String prompt = """
                Please enter exactly 8 values separated by commas, in this order:
                series_title, released_year, PG-13 (true or false), genre, rating, description, director, gross
                Example: "The Matrix,1999,true,Action/Sci-Fi,8.7,A hacker discovers reality,The Wachowskis,463517383\"""";

        do {
            validInput = true;

            String inputString = getStringInput(scanner, prompt);
            if (inputString.trim().isEmpty()) {

                System.out.println("Invalid input: input cannot be empty. Please enter all 8 values.");
                continue;
            }

            separatedValuesList = separateCommaValues(inputString);



            if (separatedValuesList.length != 8) {
                System.out.println("Invalid input: you must enter exactly 8 comma-separated values.");
                continue;
            }


            for (int i = 0; i < separatedValuesList.length; i++) {
                String value = separatedValuesList[i].trim();

                switch (i) {
                    //-------- String values
                    case 0: // series_title
                    case 3: // genre
                    case 5: // description
                    case 6: // director
                        if (value.isEmpty()) {
                            System.out.println("Invalid input: text fields cannot be empty.");
                            validInput = false;
                        }
                        break;

                    //---------Numeric values
                    case 1: // released_year
                    case 4: // rating
                    case 7: // gross
                        if (!isNumeric(value)) {
                            System.out.println("Invalid input: numeric value expected at position " + (i + 1));
                            validInput = false;
                        }
                        break;

                    //-----------Boolean value
                    case 2: // PG-13 (true / false)
                        if (!(value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false"))) {
                            System.out.println("Invalid input: PG-13 must be true or false.");
                            validInput = false;
                        }
                        break;
                }

                if (!validInput) {
                    break;
                }
            }

        } while (!validInput);

        if (separatedValuesList == null || separatedValuesList.length != 8) {
            return new HashMap<>();
        }else{
            entry.put("series_title", separatedValuesList[0].trim());
            entry.put("released_year", separatedValuesList[1].trim());
            entry.put("PG-13", String.valueOf(Boolean.parseBoolean(separatedValuesList[2].trim())));
            entry.put("genre", separatedValuesList[3].trim());
            entry.put("rating", separatedValuesList[4].trim());
            entry.put("description", separatedValuesList[5].trim());
            entry.put("director", separatedValuesList[6].trim());
            entry.put("gross", separatedValuesList[7].trim());

            return entry;
        }


    }
    //***********************************************************************
    //------------------Main Menu Methods------------------------------------
    //***********************************************************************
    /**
     * Displays the main menu and returns the user's choice
     * @param scanner scanner object from java.util.Scanner
     * @return the menu option selected by the user as a String
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
        |   1    | Add movie (step by step)  | Answer one value at a time     |
        |   2    | Add movie (single line)   | Enter all 8 values with commas |
        |   3    | Exit                      | Close the program              |
        |   4    | Search movie by ID        | Find a movie using its ID      |
        |   5    | Print all movies          | Display all stored movies      |
        +--------+---------------------------+--------------------------------+""");

        do {
            System.out.println("Please enter an option (1, 2 or 3):");
            choice = scanner.nextLine().trim();

            if (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")) {
                System.out.println("Invalid input. Please enter 1, 2 or 3.");
            }

        } while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3"));

        return Integer.parseInt(choice);
    }
    //***********************************************************************
    //---------------- Helper Methods-----------------------------------------
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
    //------------------DATA STORAGE AND MANAGEMENT--------------------------
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
    //------------------ADD DATA METHODS-------------------------------------
    //***********************************************************************
    /**
     * Adds a movie to the map and assigns the next available id.
     *
     * @param seriesTitle movie title (non-null)
     * @param releaseYear year the movie was released (e.g., 1999)
     * @param certification true if PG-13, false otherwise
     * @param genre genre or genres (non-null)
     * @param imdbRating rating on a 0.0–10.0 scale
     * @param overview short description (non-null)
     * @param director director's name (non-null)
     * @param gross gross revenue (same units used throughout your program)
     */
    public static void addMovie(String seriesTitle, int releaseYear, boolean certification, String genre, double imdbRating, String overview, String director, double gross) {
        int id = nextId++;
        movies.put(id, new String[] {
                seriesTitle, String.valueOf(releaseYear), String.valueOf(certification), genre, String.valueOf(imdbRating), overview, director, String.valueOf(gross)
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
            int key = entry.getKey();
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
            int key = entry.getKey();
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
        for (Map.Entry<Integer, String[]> entry: movies.entrySet()) {
            int key = entry.getKey();
            if (key == id) {
                movies.remove(id);
            }
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

