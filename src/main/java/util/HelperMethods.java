package util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;

public class HelperMethods {

    /** JJ - 2026/09/14 - T10
     * Takes a string in the form:
     *  "<element1>,<element2>...."
     *  and separates them into an Array
     *  if not a valid separated values String returns an empty String
     * @param commaValuesString String with elements separate with comma values
     * @return  String[] and array with the values separated
     */
    public static String[] separateCommaValues (String commaValuesString){
        if (commaValuesString == null) return new String[] {};
        if (commaValuesString.contains(",")){
            return commaValuesString.split(",");

        }else{
            return new String[] {};
        }
    }



    /** Duku - 17/03/2026 - T10
     * Normalizes a word by converting it to lower case and trimming spaces
     * @param word String a string
     * @return returns a normalized string (lower case and spaces trimmed)
     */
    public static String normalizeWord(String word){
        if (word == null) return "";
        return word.toLowerCase().trim();
    }

    /** Duku - 17/03/2026 - T10
     * Checks if an input only contains numeric values
     * @param input A String
     * @return a boolean that checks if the string only contains numbers
     */
    public static boolean isNumeric(String input) {
        if (input == null) return false;
        String regex = "^[+-]?(\\d*|\\d{1,3}(,\\d{3})*)(\\.\\d+)?\\b$";
        return input.matches(regex);
    }

    /**
     * This method receives a scanner object and captures
     * the user input and normalize it, whilst printing in console the data to be
     * capture
     * @param scanner scanner object from java.util.Scanner
     * @param prompt the prompt that specifies the user which data to input
     * @return the normalized input of the user as a String
     */
    public static String getStringInput(java.util.Scanner scanner, String prompt){
        System.out.println(prompt);
        return normalizeWord(scanner.nextLine());
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
    public static Double getNumericInput(java.util.Scanner scanner, String prompt) {
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
    public static Integer getIntegerInput(java.util.Scanner scanner, String prompt) {
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

    /**
     * This method receives a scanner object and returns the user input
     * as a boolean
     * @param scanner scanner object from java.util.Scanner
     * @param prompt the prompt specifying if the input is yes or no
     * @return the boolean if the movie is rated PG-13 or not
     */
    public static boolean getBooleanInput(java.util.Scanner scanner, String prompt) {
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

   

/**
     * Safely parses a long from a string like "$2,932,193.0", "£1,000", "€500".
     * Based on:
     *  - https://mkyong.com/java/convert-string-with-commas-to-long-java/
     *  - https://www.baeldung.com/java-currency-symbols-match
     * Returns 0 on null, blank, or unparseable input.
     */
    public static long parseLongSafe(String input) {
        if (input == null || input.isBlank()) return 0L;

        String s = input.trim();

        // Try currency-aware parsing first (handles $, £, €, commas, decimals)
        
        for (Locale locale : new Locale[]{Locale.US, Locale.UK, Locale.GERMANY}) {
            try {
                Number number = NumberFormat.getCurrencyInstance(locale).parse(s);
                return number.longValue();
            } catch (ParseException ignored) {}
        }

        // Fallback
        try {
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            return format.parse(s).longValue();
        } catch (ParseException e) {
            return 0L;
        }
    }
}
