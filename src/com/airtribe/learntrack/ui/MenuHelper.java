package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;

public final class MenuHelper {
    private MenuHelper() {
    }

    public static void printError(Exception e) {
        System.out.println("✗ Error: " + e.getMessage());
    }

    public static void runAction(MenuAction action) {
        try {
            action.run();
        } catch (InvalidInputException | EntityNotFoundException e) {
            printError(e);
        }
    }

    @FunctionalInterface
    public interface MenuAction {
        void run() throws InvalidInputException, EntityNotFoundException;
    }
}
