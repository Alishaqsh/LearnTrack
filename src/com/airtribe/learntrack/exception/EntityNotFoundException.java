package com.airtribe.learntrack.exception;

/**
 * Custom exception thrown when an entity is not found
 * Demonstrates basic exception handling
 */
public class EntityNotFoundException extends Exception {
    
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
