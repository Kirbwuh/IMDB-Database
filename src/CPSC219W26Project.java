import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CPSC219W26Project {

    //----------------PROGRAM INPUT---------------------------------
   /*
    Data to be captured:

        series_title   (String) : Name of the movie
        released_year  (double)  : Year the movie was released
        PG-13          (boolean) : true/false
        genre          (String) : Genre(s) of the movie
        rating         (double) : Rating of the movie on the IMDb site
        director       (String) : Name of the director
        gross          (double)   : Total money earned by the movie
    */
    //-----------------Input Methods ----------------------------------------

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

    //---------------------Input process methods ---------------------------------------------
    private static HashMap<String, String> singleEntryProcess(Scanner scanner) {

        HashMap<String, String> entry = new HashMap<>();

        //------- Question 1 --------------------
        String q1Prompt = "Please input the name of the movie:";
        String seriesTitle = getStringInput(scanner, q1Prompt);
        entry.put("series_title", seriesTitle);

        //------- Question 2 --------------------
        String q2Prompt = "Please input the release year of the movie:";
        Double releasedYear = getNumericInput(scanner, q2Prompt);
        entry.put("released_year", String.valueOf(releasedYear));

        //------- Question 3 --------------------
        String q3Prompt = "Is the movie rated PG-13?";
        boolean isPG13 = getBooleanInput(scanner, q3Prompt);
        entry.put("PG-13", String.valueOf(isPG13));

        //------- Question 4 --------------------
        String q4Prompt = "Please input the genre of the movie:";
        String genre = getStringInput(scanner, q4Prompt);
        entry.put("genre", genre);

        //------- Question 5 --------------------
        String q5Prompt = "Please input the IMDb rating of the movie:";
        Double rating = getNumericInput(scanner, q5Prompt);
        entry.put("rating", String.valueOf(rating));

        //------- Question 6 --------------------
        String q6Prompt = "Please input the name of the director:";
        String director = getStringInput(scanner, q6Prompt);
        entry.put("director", director);

        //------- Question 7 --------------------
        String q7Prompt = "Please input the gross earnings of the movie:";
        Double gross = getNumericInput(scanner, q7Prompt);
        entry.put("gross", String.valueOf(gross.longValue()));

        return entry;
    }
    //---------------- Helper Methods-----------------------------------------
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
        String regex = "\\d+";
        return input.matches(regex);
    }



    public static void main(String args[]){
        Scanner inputScannerObject = new Scanner(System.in);





    }

}
