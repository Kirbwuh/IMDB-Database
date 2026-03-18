package src.view;

import java.util.HashMap;
import java.util.Scanner;

public class InputView extends view.ConsoleView {

    //***********************************************************************
    //------------------Class Variables--------------------------
    //***********************************************************************

    // Establish id
    private static int nextId = 1;

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
    //***********************************************************************
    //------------------Main Method--------------------------
    //***********************************************************************

    //Constructor with scanner dependency
    public Scanner scanner;
    public InputView(Scanner scanner){
        this.scanner = scanner;
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
    private static void pressEnterToContinue(Scanner scanner) {
        // source: https://stackoverflow.com/questions/26184409/java-console-prompt-for-enter-input-before-moving-on
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    // Single line Entry ( Should be named MultiLine Entry )

    /** JJ - 2026/09/14 - T10
     * Prompts the user line by line to add a new movie
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
    public static HashMap<Integer, String> multilineEntryProcess(Scanner scanner) {
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


    }
}
