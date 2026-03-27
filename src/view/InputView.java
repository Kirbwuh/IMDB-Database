package view;



import java.util.ArrayList;
import java.util.Scanner;
import src.util.HelperMethods;

public class InputView extends src.view.MainMenus {

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

    //***********************************************************************
    //------------------Main Method--------------------------
    //***********************************************************************

    //Constructor with scanner dependency
    public Scanner scanner;
    public InputView(Scanner scanner){
        this.scanner = scanner;
    }

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
    public static Double getDoubleInput(Scanner scanner, String prompt) {
        String DoubleInput;

        do {
            System.out.println(prompt);
            DoubleInput = scanner.nextLine();
            if (!isNumeric(DoubleInput)) {
                System.out.println("Invalid input. Please enter a decimal value.");
            }
        } while (!isNumeric(DoubleInput));

        return Double.parseDouble(DoubleInput);
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
        Integer releasedYear = getIntegerInput(scanner, q2Prompt);
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
        Double rating = getDoubleInput(scanner, q5Prompt);
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
        Double gross = getDoubleInput(scanner, q8Prompt);
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
    public static String[] singlelineEntryProcess(Scanner scanner) {


        final String prompt = """
            Please enter exactly 8 values separated by commas, in this order:
            series_title, released_year, PG-13 (true or false), genre, rating, description, director, gross
            Example: The Matrix,1999,true,Action/Sci-Fi,8.7,A hacker discovers reality,The Wachowskis,463517383""";
        String singleLineEntry = getStringInput(scanner, prompt);
        String[] stripedLineEntry = HelperMethods.separateCommaValues(singleLineEntry);
        return stripedLineEntry;



    }
}