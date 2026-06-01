package com.airtribe.learntrack.exception;

/**
 * Custom exception thrown when input validation fails
 * Demonstrates exception handling for user input
 */
public class InvalidInputException extends RuntimeException {
    
    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
