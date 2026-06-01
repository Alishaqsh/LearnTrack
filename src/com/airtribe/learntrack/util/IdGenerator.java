package com.airtribe.learntrack.util;

/**
 * IdGenerator utility class
 * Demonstrates use of static variables and methods
 * Provides unique ID generation for entities
 */
public class IdGenerator {
    // Static variables - shared across all instances
    private static int studentIdCounter = 1000;
    private static int courseIdCounter = 2000;
    private static int enrollmentIdCounter = 3000;

    // Private constructor to prevent instantiation
    private IdGenerator() {
    }

    /**
     * Generates next unique Student ID
     * Static method - can be called without creating instance
     * @return next student ID
     */
    public static int getNextStudentId() {
        return ++studentIdCounter;
    }

    /**
     * Generates next unique Course ID
     * @return next course ID
     */
    public static int getNextCourseId() {
        return ++courseIdCounter;
    }

    /**
     * Generates next unique Enrollment ID
     * @return next enrollment ID
     */
    public static int getNextEnrollmentId() {
        return ++enrollmentIdCounter;
    }

    /**
     * Resets all counters - useful for testing
     */
    public static void resetCounters() {
        studentIdCounter = 1000;
        courseIdCounter = 2000;
        enrollmentIdCounter = 3000;
    }
}
