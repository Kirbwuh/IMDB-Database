package src.view;

import src.model.MovieDatabase;

import java.util.ArrayList;
import java.util.Scanner;

public class InputView  {

    public static int nextId = 1;

    //***********************************************************************
    //------------------GET DATA METHODS------------------------------------
    //***********************************************************************

//    /**
//     * CL-3/6/2026-T10
//     * Returns movie object by associated title
//     *
//     * @param title movie title associated with entry.
//     * @return movie object with associated title.
//     */
//    public static String[] getMovieByTitle(String title) {
//        return new String[]{title};
//    }
//
//    /**
//     * CL-3/6/2026-T10
//     * Returns movie object by associated ID
//     *
//     * @param id movie ID in HashMap
//     * @return movie object
//     */
//    public static int getMovieById(int id) {
//        return id;
//    }
//
//    /**
//     * CL-3/6/2026-T10
//     * Returns an ArrayList of specified values present in the HashMap. For example using GENRE will return all the genres.
//     *
//     * @param index Use the constants to index the information you want returned
//     * @return ArrayList of specified values.
//     */
//    public static int getInformation(int index) {
//        return index;
//    }

    /**
     * By: Arraf Hoque 2026/03/06 T10
     * Prints the highest IMDB Rating movie
     */
    public static void highestValue(){
        return MovieDatabase.getHighestRated();
    }

    /**
     * By: Duku Wani 2026/03/06 T10
     * prints out the lowest IMDB RATING
     */
    public static void lowestValue(){
        return MovieDatabase.getLowestRated();
    }

    /**
     * By: Arraf Hoque 2026/03/06 T10
     * get a ArrayList of the top1-5 movies from the dataset depending on how large it is
     * @return top5
     */
    public static ArrayList<String[]> getTop5(){
        return MovieDatabase.getTop5();
    }

    //***********************************************************************
    //------------------UPDATE DATA METHODS----------------------------------
    //***********************************************************************

    //Need to update what movie's are in the update methods
    /**
     * Duku - 20/03/2026 -T10.
     * Allows the user to update a movie by either looking up the Name or ID of the movie
     *
     * @param scanner User's input for searching up the movie
     */
    public static void updateMovie(Scanner scanner) {
        final String searchPrompt = "Enter the ID or Name of the Movie you would like to update.";
        String movieName = getStringInput(scanner, searchPrompt);
        if (!isNumeric(movieName)){//if the user uses only letters
            int categoryNumber = getCategoryInformation(scanner);
            System.out.println("Enter the new value for the category you're changing.");
            String newValue = scanner.nextLine();
            MovieDatabase.updateMovie(movieName,categoryNumber,newValue); //Update Movie with title.
            System.out.println("The value has been updated!");
        } else {
            int movieNameInt = Integer.parseInt(String.valueOf(scanner));
            int categoryNumber = getCategoryInformation(scanner);
            final String valuePrompt = "Enter the new value for the category you're changing.";
            String newValue = getStringInput(scanner,valuePrompt);
            MovieDatabase.updateMovie(movieNameInt,categoryNumber,newValue); //Update Movie with ID number
            System.out.println("The value has been updated!");
            pressEnterToContinue(scanner);
        }
    }


    //***********************************************************************
    //------------------REMOVE DATA METHODS----------------------------------
    //***********************************************************************

    /**
     * Duku - 20/03/2026 -T10.
     * Allows the user to remove a movie by either looking up the Name or ID of the movie
     *
     * @param scanner User's input for searching up the movie
     */
    public static void removeMovie(Scanner scanner) {
        final String searchPrompt = "Enter the ID or Name of the Movie you would like to remove.";
        String movieName = getStringInput(scanner, searchPrompt);
        if (!isNumeric(movieName)){ //if the user uses only letters
            removeMovieByTitle(String.valueOf(scanner));
            System.out.println("The movie has been removed!");
            pressEnterToContinue(scanner);
        } else {
            int scannerInt = Integer.parseInt(String.valueOf(scanner));
            removeMovieById(scannerInt); //if the user uses only numbers
            System.out.println("The movie has been removed!");
            pressEnterToContinue(scanner);
        }
    }

    /**
     * CL-3/6/2026-T10
     * Deletes a movie entry by its title.
     *
     * @param title movie title associated with entry.
     */
    public static void removeMovieByTitle(String title) {
        return MovieDatabase.removeMovie(title);
    }

    /**
     * CL-3/6/2026-T10
     * Deletes a movie entry by its ID.
     *
     * @param id movie id associated with entry.
     */
    public static void removeMovieById(int id) {
        return MovieDatabase.removeMovie(id);
        //TODO add proper implementation of Movie.Java & MovieDatabase.Java
    }


    //***********************************************************************
    //---------------------------- PRINT METHODS ----------------------------
    //***********************************************************************


    /**
     * CL-3/6/2026-T10
     * Prints every movie currently stored in the map.
     */
    public static void printAllMovies() {
        return MovieDatabase.getAllMovies;
    }


    public static void reviews(model.Movie){
        //TODO rewrite how reviews will be displayed.
        if (movies.isEmpty()){
            System.out.println("No movies in the database yet. Add some movies first!");
            pressEnterToContinue(scanner);
        } else {
            System.out.println("The movies you watch are rated " + getRatingAverage(0, 0) + " on average.");
            System.out.println("\nHere's the rating of every movie you've watched:");
            System.out.println("----------------------------------------------------");
            // Same as Print all movies but only show movie names and ratings of said movies
            System.out.println("----------------------------------------------------");
            pressEnterToContinue(scanner);
        }
    }

    //***********************************************************************
    //---------------- Helper Methods ---------------------------------------
    //***********************************************************************

    /**
     * Duku - 17/03/2026 - T10
     * JJ - 2026/09/14 - T10
     * Normalizes a word by converting it to lower case and trimming spaces
     * @param word String a string
     * @return returns a normalized string (lower case and spaces trimmed)
     */
    public static String normalizeWord(String word){
        return word.toLowerCase().trim();
    }

    /**
     * Duku - 17/03/2026 - T10
     * JJ - 2026/09/14 - T10
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

    /** JJ - 2026/09/14 - T10
     * Takes a string in the form:
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
//    /**
//     * CL-3/6/2026-T10
//     * Prompts the user to search for a movie by its title
//     *
//     * @param scanner scanner object from java.util.Scanner
//     * @return movie String[] or null
//     */
//    private static String searchMovieByTitle(Scanner scanner) {
//        return getMovie(scanner); // From MovieDatabase.Java
//    }

    /**
     * CL-3/6/2026-T10
     * Prompts the user to search for a movie by its ID
     *
     * @param scanner scanner object from java.util.Scanner
     * @return movie String[] or null
     */
    public static void searchMovie(Scanner scanner) {
        final String searchPrompt = "Enter the ID or Name of the Movie you would like to Search.";
        String movieName = getStringInput(scanner, searchPrompt);
        if (!isNumeric(movieName)){ //if the user uses only letters
            String movieInfo = MovieDatabase.getMovie(String.valueOf(scanner));
            System.out.println(movieInfo);
            pressEnterToContinue(scanner);
        } else {
            int scannerInt = Integer.parseInt(String.valueOf(scanner)); //returns the movie assigned to the id given
            String movieInfo = MovieDatabase.getMovie(scannerInt); //if the user uses only numbers
            System.out.println(movieInfo);
            pressEnterToContinue(scanner);
        }
    }

    /**
     * CL-3/6/2026-T10
     * Prompts the user to select a category of data they want displayed,
     *
     * @param scanner scanner object from java.util.scanner
     * @return an ArrayList<String> containing all category data.
     */
    private static int getCategoryInformation(Scanner scanner) {
        final String prompt = ("""
                Please enter a number corresponding to the data you want to change:
                1. Series Titles
                2. Release Years
                3. PG-13
                4. Genres
                5. IMDB Ratings
                6. Overviews
                7. Gross Earnings
                """);
        return getIntegerInput(scanner, prompt);
    }
    //***********************************************************************
    //------------------Main Method--------------------------
    //***********************************************************************

    //Constructor with scanner dependency
    public Scanner scanner;
    public InputView(Scanner scanner){
        this.scanner = scanner;
    }

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
     *
     * int mainMenuSelectionInt = null
     * switch(mainMenuSelect):
     * case 1
     * return mainMenuSelect
     *
     * case 2
     * case 3
     */
    /**
     * JJ - 2026/09/14 - T10
     * Displays the main menu and returns the user's choice
     *
     * @param scanner scanner object from java.util.Scanner
     * @return the menu option selected by the user as an int
     */
    public static int showMainMenu(Scanner scanner) {
        String choice;
        int mainMenuSelect;

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
            switch (choice){
                case "1": showDatabaseMenu(scanner);
                    mainMenuSelect = 1;
                    return mainMenuSelect;
                case "2": System.exit(0);
            }


        } while (!choice.equals("1") && !choice.equals("2"));

        return Integer.parseInt(choice);
    }

    /** JJ - 2026/09/14 - T10
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
                        |   6    | Reviews                   | Average & individual ratings   |
                        |   7    | Highest rated movie       | Display the highest rated movie|
                        |   8    | Lowest rated movie        | Display the lowest rated movie |
                        |   9    | Print Top 5 Movies        | Display the top 5 rated movies |
                        |   10   | Back                      | Return to main menu            |
                        +--------+---------------------------+--------------------------------+""");

        do {
            System.out.println("Please enter an option (1-10):");
            choice = scanner.nextLine().trim();

            if (!choice.matches("([1-9]|10)")) {
                System.out.println("Invalid input. Please enter a number between 1 and 10.");
            }
            switch (choice){
                case "1": showAddMovieMenu(scanner);
                    break;
                case "2": searchMovie(scanner);
                    break;
                case "3": updateMovie(scanner);
                    break;
                case "4": removeMovie(scanner);
                    break;
                case "5": printAllMovies();
                    break;
                case "6": reviews();
                    break;
                case "7": highestValue();
                    break;
                case "8": lowestValue();
                    break;
                case "9": getTop5();
                    break;
                case "10": pressEnterToContinue(scanner);
                    break;
            }

        } while (!choice.matches("([1-9]|10)"));

        return Integer.parseInt(choice);
    }

    /** JJ - 2026/09/14 - T10
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
            switch (choice){
                case "1": multilineEntryProcess(scanner);
                    break;
                case "2": singlelineEntryProcess(scanner);
                    break;
                case "3": showMainMenu(scanner);
                    break;
            }

        } while (!choice.matches("[1-3]"));

        return Integer.parseInt(choice);
    }
    // String Input
    /**
     * Duku - 18/03/2026 - T10
     * JJ - 2026/09/14 - T10
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

    // Numeric Input
    /**
     * Duku - 17/03/2026 - T10
     * JJ - 2026/09/14 - T10
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

    // Integer Input
    // Can be used to find the ID Number of a movie
    /**
     * Duku - 17/03/2026 - T10
     * CL-3/6/2026-T10
     * JJ - 2026/09/14 - T10
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

    //Boolean Input

    /**
     * Duku - 17/03/2026 - T10
     * This method receives a scanner object and returns the user input
     * as a boolean
     * @param scanner scanner object from java.util.Scanner
     * @param prompt the prompt specifying if the input is yes or no
     * @return the boolean if the movie is rated PG-13 or not
     */
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

    // Pressing enter to continue

    /**
     * Duku - 18/03/2026 - T10
     * JJ - 2026/09/14 - T10
     * prompts user to press enter
     * @param scanner scanner object from java.util.Scanner
     */
    public static void pressEnterToContinue(Scanner scanner) {
        // source: https://stackoverflow.com/questions/26184409/java-console-prompt-for-enter-input-before-moving-on
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    // Single line Entry ( Should be named MultiLine Entry )

    /**
     * Duku - 20/03/2026 - T10
     * JJ - 2026/09/14 - T10
     * Prompts the user line by line to add a new movie
     * @param scanner  scanner object from java.util.Scanner
     * @return ArrayList of the collected values
     */
    //---------------------Input process methods ---------------------------------------------
    public static ArrayList<String> multilineEntryProcess(Scanner scanner) {
        //
        ArrayList<String> movieEntriesMulti = new ArrayList<>();

        //------- Question 1 --------------------
        final String q1Prompt = "Please input the name of the movie:";
        String seriesTitle = getStringInput(scanner, q1Prompt);
        movieEntriesMulti.add(seriesTitle);

        //------- Question 2 --------------------
        final String q2Prompt = "Please input the release year of the movie:";
        Double releasedYear = getNumericInput(scanner, q2Prompt);
        movieEntriesMulti.add(String.valueOf(releasedYear));

        //------- Question 3 --------------------
        final String q3Prompt = "Is the movie rated PG-13?";
        boolean isPG13 = getBooleanInput(scanner, q3Prompt);
        movieEntriesMulti.add(String.valueOf(isPG13));

        //------- Question 4 --------------------
        final String q4Prompt = "Please input the genre of the movie:";
        String genre = getStringInput(scanner, q4Prompt);
        movieEntriesMulti.add(genre);

        //------- Question 5 --------------------
        final String q5Prompt = "Please input the IMDb rating of the movie:";
        Double rating = getNumericInput(scanner, q5Prompt);
        movieEntriesMulti.add(String.valueOf(rating));

        //------- Question 6 --------------------
        final String q6Prompt = "Please input the description of the movie:";
        String movieDesc = getStringInput(scanner, q6Prompt);
        movieEntriesMulti.add(movieDesc);

        //------- Question 7 --------------------
        final String q7Prompt = "Please input the name of the director:";
        String director = getStringInput(scanner, q7Prompt);
        movieEntriesMulti.add(director);

        //------- Question 8 --------------------
        final String q8Prompt = "Please input the gross earnings of the movie:";
        Double gross = getNumericInput(scanner, q8Prompt);
        movieEntriesMulti.add(String.valueOf(gross));

        return movieEntriesMulti;
    }

    // Multi Line Entry ( Should be named Single Line Entry)

    /** JJ - 2026/09/14 - T10
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
    public static ArrayList<String[]> singlelineEntryProcess(Scanner scanner) {
        String[] separatedValuesList = null;
        boolean validInput;
        ArrayList<String[]> movieEntriesSingle = new ArrayList<>();

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
                    case SERIES_TITLE: //Todo Change Variables reffering to movie aspects
                    case GENRE:
                    case OVERVIEW:
                    case DIRECTOR:
                        if (value.isEmpty()) {
                            System.out.println("Invalid input: text fields cannot be empty.");
                            validInput = false;
                        }
                        break;

                    //---------Numeric values
                    case RELEASE_YEAR://Todo Change Variables reffering to movie aspects
                    case IMDB_RATING:
                    case GROSS:
                        if (!isNumeric(value)) {
                            System.out.println("Invalid input: numeric value expected at position " + (i + 1));
                            validInput = false;
                        }
                        break;

                    //-----------Boolean value
                    case CERTIFICATION: // PG-13 (true / false) //Todo Change Variables reffering to movie aspects
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
            return new ArrayList<>();
        }else{
            movieEntriesSingle.add(SERIES_TITLE, separatedValuesList[0].trim());
            movieEntriesSingle.add(RELEASE_YEAR, separatedValuesList[1].trim());
            movieEntriesSingle.add(CERTIFICATION, String.valueOf(Boolean.parseBoolean(separatedValuesList[2].trim())));
            movieEntriesSingle.add(GENRE, separatedValuesList[3].trim());
            movieEntriesSingle.add(IMDB_RATING, separatedValuesList[4].trim());
            movieEntriesSingle.add(OVERVIEW, separatedValuesList[5].trim());
            movieEntriesSingle.add(DIRECTOR, separatedValuesList[6].trim());
            movieEntriesSingle.add(GROSS, separatedValuesList[7].trim());

            return movieEntriesSingle;
        }


    }
}
