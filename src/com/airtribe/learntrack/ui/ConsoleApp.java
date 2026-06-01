package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import java.util.Scanner;

public class ConsoleApp {
    private final Scanner scanner;
    private final StudentMenuHandler studentMenuHandler;
    private final CourseMenuHandler courseMenuHandler;
    private final EnrollmentMenuHandler enrollmentMenuHandler;

    public ConsoleApp(Scanner scanner,
                      StudentService studentService,
                      CourseService courseService,
                      EnrollmentService enrollmentService) {
        this.scanner = scanner;
        this.studentMenuHandler = new StudentMenuHandler(scanner, studentService);
        this.courseMenuHandler = new CourseMenuHandler(scanner, courseService);
        this.enrollmentMenuHandler = new EnrollmentMenuHandler(scanner, enrollmentService);
    }

    public void start() {
        displayWelcome();
        runMainMenu();
    }

    private void displayWelcome() {
        System.out.println("\n========================================");
        System.out.println("  Welcome to LearnTrack");
        System.out.println("  Student & Course Management System");
        System.out.println("========================================\n");
    }

    private void runMainMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Student Management");
            System.out.println("2. Course Management");
            System.out.println("3. Enrollment Management");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> studentMenuHandler.showMenu();
                case "2" -> courseMenuHandler.showMenu();
                case "3" -> enrollmentMenuHandler.showMenu();
                case "4" -> {
                    running = false;
                    System.out.println("\nThank you for using LearnTrack. Goodbye!");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static ConsoleApp createDefault(Scanner scanner) {
        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();
        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

        StudentService studentService = new StudentService(studentRepository);
        CourseService courseService = new CourseService(courseRepository);
        EnrollmentService enrollmentService = new EnrollmentService(
                enrollmentRepository, studentService, courseService);

        return new ConsoleApp(scanner, studentService, courseService, enrollmentService);
    }
}
