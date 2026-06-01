package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.util.InputValidator;
import java.util.ArrayList;
import java.util.Scanner;

public class EnrollmentMenuHandler {
    private final Scanner scanner;
    private final EnrollmentService enrollmentService;

    public EnrollmentMenuHandler(Scanner scanner, EnrollmentService enrollmentService) {
        this.scanner = scanner;
        this.enrollmentService = enrollmentService;
    }

    public void showMenu() {
        boolean inMenu = true;

        while (inMenu) {
            System.out.println("\n--- ENROLLMENT MANAGEMENT ---");
            System.out.println("1. Enroll Student in Course");
            System.out.println("2. View All Enrollments");
            System.out.println("3. View Enrollments by Student");
            System.out.println("4. View Enrollments by Course");
            System.out.println("5. Update Enrollment Status");
            System.out.println("6. Complete Enrollment");
            System.out.println("7. Cancel Enrollment");
            System.out.println("8. Back to Main Menu");
            System.out.print("Choose an option (1-8): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> MenuHelper.runAction(this::enrollStudent);
                case "2" -> viewAllEnrollments();
                case "3" -> MenuHelper.runAction(this::viewEnrollmentsByStudent);
                case "4" -> MenuHelper.runAction(this::viewEnrollmentsByCourse);
                case "5" -> MenuHelper.runAction(this::updateEnrollmentStatus);
                case "6" -> MenuHelper.runAction(this::completeEnrollment);
                case "7" -> MenuHelper.runAction(this::cancelEnrollment);
                case "8" -> inMenu = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void enrollStudent() throws InvalidInputException, EntityNotFoundException {
        System.out.print("Enter Student ID: ");
        int studentId = InputValidator.parseInteger(scanner.nextLine().trim(), "Student ID");
        System.out.print("Enter Course ID: ");
        int courseId = InputValidator.parseInteger(scanner.nextLine().trim(), "Course ID");

        Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
        System.out.println("\n✓ Student enrolled successfully!");
        System.out.println("Enrollment ID: " + enrollment.getId());
        System.out.println(enrollment);
    }

    private void viewAllEnrollments() {
        ArrayList<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found.");
            return;
        }

        System.out.println("\n--- ALL ENROLLMENTS ---");
        System.out.printf("%-10s %-12s %-12s %-15s%n", "ID", "Student ID", "Course ID", "Status");
        System.out.println("--------------------------------------------------");
        for (Enrollment enrollment : enrollments) {
            System.out.printf("%-10d %-12d %-12d %-15s%n",
                    enrollment.getId(),
                    enrollment.getStudentId(),
                    enrollment.getCourseId(),
                    enrollment.getStatus());
        }
    }

    private void viewEnrollmentsByStudent() throws InvalidInputException, EntityNotFoundException {
        System.out.print("Enter Student ID: ");
        int studentId = InputValidator.parseInteger(scanner.nextLine().trim(), "Student ID");
        ArrayList<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudent(studentId);
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found for this student.");
            return;
        }

        System.out.println("\n--- ENROLLMENTS FOR STUDENT ID: " + studentId + " ---");
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
    }

    private void viewEnrollmentsByCourse() throws InvalidInputException, EntityNotFoundException {
        System.out.print("Enter Course ID: ");
        int courseId = InputValidator.parseInteger(scanner.nextLine().trim(), "Course ID");
        ArrayList<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourse(courseId);
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found for this course.");
            return;
        }

        System.out.println("\n--- ENROLLMENTS FOR COURSE ID: " + courseId + " ---");
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
    }

    private void updateEnrollmentStatus() throws InvalidInputException, EntityNotFoundException {
        System.out.print("Enter Enrollment ID: ");
        int enrollmentId = InputValidator.parseInteger(scanner.nextLine().trim(), "Enrollment ID");
        System.out.print("Enter New Status (ACTIVE/COMPLETED/CANCELLED): ");
        String status = scanner.nextLine().trim().toUpperCase();

        Enrollment enrollment = enrollmentService.updateEnrollmentStatus(enrollmentId, status);
        System.out.println("\n✓ Enrollment status updated successfully!");
        System.out.println(enrollment);
    }

    private void completeEnrollment() throws InvalidInputException, EntityNotFoundException {
        System.out.print("Enter Enrollment ID: ");
        int enrollmentId = InputValidator.parseInteger(scanner.nextLine().trim(), "Enrollment ID");
        enrollmentService.completeEnrollment(enrollmentId);
        System.out.println("\n✓ Enrollment marked as completed!");
    }

    private void cancelEnrollment() throws InvalidInputException, EntityNotFoundException {
        System.out.print("Enter Enrollment ID: ");
        int enrollmentId = InputValidator.parseInteger(scanner.nextLine().trim(), "Enrollment ID");
        enrollmentService.cancelEnrollment(enrollmentId);
        System.out.println("\n✓ Enrollment cancelled!");
    }
}
