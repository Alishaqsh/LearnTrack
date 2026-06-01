package com.airtribe.learntrack.ui;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleApp app = ConsoleApp.createDefault(scanner);
        app.start();
        scanner.close();
    }
}
