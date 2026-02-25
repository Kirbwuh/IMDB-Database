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
        description    (String) : Description of the movie
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
    private static HashMap<String, String> multiLIneEntryProcess(Scanner scanner) {
        String[] separatedValuesList;
        boolean validInput;
        HashMap<String, String> entry = new HashMap<>();


        final String prompt = "Please enter exactly 8 values separated by commas, in this order:\n"
                + "series_title, released_year, PG-13 (true or false), genre, rating, description, director, gross\n"
                + "Example: \"The Matrix,1999,true,Action/Sci-Fi,8.7,A hacker discovers reality,The Wachowskis,463517383\"";

        do {
            validInput = true;

            String inputString = getStringInput(scanner, prompt);
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

                    //--------- Numeric values
                    case 1: // released_year
                    case 4: // rating
                    case 7: // gross
                        if (!isNumeric(value)) {
                            System.out.println("Invalid input: numeric value expected at position " + (i + 1));
                            validInput = false;
                        }
                        break;

                    //----------- Boolean value
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



    public static void main(String args[]){
        Scanner inputScannerObject = new Scanner(System.in);






    }

}
