import java.util.Scanner;

public class CPSC219W26Project {

    //----------------PROGRAM INPUT---------------------------------
   /*
    Data to be captured:

        series_title   (String) : Name of the movie
        released_year  (double)    : Year the movie was released
        certificate    (String) : Age / content rating (e.g., PG-13, R)
        runtime        (Duration)    : Total runtime of the movie in minutes
        genre          (String) : Genre(s) of the movie
        imdb_rating    (double) : Rating of the movie on the IMDb site
        overview       (String) : Short story or summary of the movie
        meta_score     (double)    : Score earned by the movie (0–100)
        director       (String) : Name of the director
        star1          (String) : Name of the first main star
        star2          (String) : Name of the second main star
        star3          (String) : Name of the third main star
        star4          (String) : Name of the fourth main star
        no_of_votes    (double)    : Total number of votes received
        gross          (long)   : Total money earned by the movie
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
        Scanner input = new Scanner(System.in);



    }

}
