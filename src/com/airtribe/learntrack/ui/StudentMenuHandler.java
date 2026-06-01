package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.InputValidator;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentMenuHandler {
    private final Scanner scanner;
    private final StudentService studentService;

    public StudentMenuHandler(Scanner scanner, StudentService studentService) {
        this.scanner = scanner;
        this.studentService = studentService;
    }

    public void showMenu() {
        boolean inMenu = true;

        while (inMenu) {
            System.out.println("\n--- STUDENT MANAGEMENT ---");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Deactivate Student");
            System.out.println("6. Activate Student");
            System.out.println("7. Back to Main Menu");
            System.out.print("Choose an option (1-7): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> MenuHelper.runAction(this::addNewStudent);
                case "2" -> viewAllStudents();
                case "3" -> MenuHelper.runAction(this::searchStudentById);
                case "4" -> MenuHelper.runAction(this::updateStudent);
                case "5" -> MenuHelper.runAction(this::deactivateStudent);
                case "6" -> MenuHelper.runAction(this::activateStudent);
                case "7" -> inMenu = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addNewStudent() throws InvalidInputException {
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine().trim();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine().trim();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter Batch: ");
        String batch = scanner.nextLine().trim();

        Student student = studentService.addStudent(firstName, lastName, email, batch);
        System.out.println("\n✓ Student added successfully!");
        System.out.println("Student ID: " + student.getId());
        System.out.println("Name: " + student.getDisplayName());
    }

    private void viewAllStudents() {
        ArrayList<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n--- ALL STUDENTS ---");
        System.out.printf("%-10s %-20s %-25s %-15s %-10s%n", "ID", "Name", "Email", "Batch", "Status");
        System.out.println("--------------------------------------------------------------------");
        for (Student student : students) {
            String status = student.isActive() ? "Active" : "Inactive";
            System.out.printf("%-10d %-20s %-25s %-15s %-10s%n",
                    student.getId(),
                    student.getDisplayName(),
                    student.getEmail(),
                    student.getBatch(),
                    status);
        }
    }

    private void searchStudentById() throws InvalidInputException, EntityNotFoundException {
        System.out.print("Enter Student ID: ");
        int studentId = InputValidator.parseInteger(scanner.nextLine().trim(), "Student ID");
        Student student = studentService.getStudentById(studentId);
        System.out.println("\n--- STUDENT DETAILS ---");
        System.out.println(student);
    }

    private void updateStudent() throws InvalidInputException, EntityNotFoundException {
        System.out.print("Enter Student ID: ");
        int studentId = InputValidator.parseInteger(scanner.nextLine().trim(), "Student ID");
        System.out.print("Enter New First Name: ");
        String firstName = scanner.nextLine().trim();
        System.out.print("Enter New Last Name: ");
        String lastName = scanner.nextLine().trim();
        System.out.print("Enter New Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter New Batch: ");
        String batch = scanner.nextLine().trim();

        Student student = studentService.updateStudent(studentId, firstName, lastName, email, batch);
        System.out.println("\n✓ Student updated successfully!");
        System.out.println(student);
    }

    private void deactivateStudent() throws InvalidInputException, EntityNotFoundException {
        System.out.print("Enter Student ID: ");
        int studentId = InputValidator.parseInteger(scanner.nextLine().trim(), "Student ID");
        studentService.deactivateStudent(studentId);
        System.out.println("\n✓ Student deactivated successfully!");
    }

    private void activateStudent() throws InvalidInputException, EntityNotFoundException {
        System.out.print("Enter Student ID: ");
        int studentId = InputValidator.parseInteger(scanner.nextLine().trim(), "Student ID");
        studentService.activateStudent(studentId);
        System.out.println("\n✓ Student activated successfully!");
    }
}
