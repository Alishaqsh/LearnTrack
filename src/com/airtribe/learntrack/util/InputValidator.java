package com.airtribe.learntrack.util;

import com.airtribe.learntrack.exception.InvalidInputException;

/**
 * InputValidator utility class
 * Provides validation methods for user input
 * Demonstrates helper methods and exception throwing
 */
public class InputValidator {
    
    // Private constructor to prevent instantiation
    private InputValidator() {
    }

    /**
     * Validates if a string is not empty
     * @param input the string to validate
     * @param fieldName name of the field (for error message)
     * @throws InvalidInputException if input is empty or null
     */
    public static void validateNotEmpty(String input, String fieldName) throws InvalidInputException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty.");
        }
    }

    /**
     * Validates if an integer is positive
     * @param value the integer to validate
     * @param fieldName name of the field (for error message)
     * @throws InvalidInputException if value is not positive
     */
    public static void validatePositive(int value, String fieldName) throws InvalidInputException {
        if (value <= 0) {
            throw new InvalidInputException(fieldName + " must be a positive number.");
        }
    }

    /**
     * Validates email format (basic validation)
     * @param email the email to validate
     * @throws InvalidInputException if email format is invalid
     */
    public static void validateEmail(String email) throws InvalidInputException {
        if (email == null || !email.matches("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$")) {
            throw new InvalidInputException("Please enter a valid email address (e.g. user@example.com).");
        }
    }

    /**
     * Converts string to integer with error handling
     * @param input the string to convert
     * @param fieldName name of the field (for error message)
     * @return the integer value
     * @throws InvalidInputException if conversion fails
     */
    public static int parseInteger(String input, String fieldName) throws InvalidInputException {
        try {
            int value = Integer.parseInt(input);
            if (value <= 0) {
                throw new InvalidInputException(fieldName + " must be a positive number.");
            }
            return value;
        } catch (NumberFormatException e) {
            throw new InvalidInputException(fieldName + " must be a valid number.");
        }
    }
}
