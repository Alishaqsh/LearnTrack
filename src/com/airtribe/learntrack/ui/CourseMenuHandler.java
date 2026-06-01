package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.util.InputValidator;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseMenuHandler {
    private final Scanner scanner;
    private final CourseService courseService;

    public CourseMenuHandler(Scanner scanner, CourseService courseService) {
        this.scanner = scanner;
        this.courseService = courseService;
    }

    public void showMenu() {
        boolean inMenu = true;

        while (inMenu) {
            System.out.println("\n--- COURSE MANAGEMENT ---");
            System.out.println("1. Add New Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Search Course by ID");
            System.out.println("4. Update Course");
            System.out.println("5. Deactivate Course");
            System.out.println("6. Activate Course");
            System.out.println("7. Back to Main Menu");
            System.out.print("Choose an option (1-7): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> MenuHelper.runAction(this::addNewCourse);
                case "2" -> viewAllCourses();
                case "3" -> MenuHelper.runAction(this::searchCourseById);
                case "4" -> MenuHelper.runAction(this::updateCourse);
                case "5" -> MenuHelper.runAction(this::deactivateCourse);
                case "6" -> MenuHelper.runAction(this::activateCourse);
                case "7" -> inMenu = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addNewCourse() throws InvalidInputException {
        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine().trim();
        System.out.print("Enter Description: ");
        String description = scanner.nextLine().trim();
        System.out.print("Enter Duration (in weeks): ");
        int duration = InputValidator.parseInteger(scanner.nextLine().trim(), "Duration");

        Course course = courseService.addCourse(courseName, description, duration);
        System.out.println("\n✓ Course added successfully!");
        System.out.println("Course ID: " + course.getId());
        System.out.println("Course Name: " + course.getCourseName());
    }

    private void viewAllCourses() {
        ArrayList<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        System.out.println("\n--- ALL COURSES ---");
        System.out.printf("%-10s %-25s %-15s %-10s%n", "ID", "Course Name", "Duration", "Status");
        System.out.println("----------------------------------------------------------");
        for (Course course : courses) {
            String status = course.isActive() ? "Active" : "Inactive";
            System.out.printf("%-10d %-25s %-15s %-10s%n",
                    course.getId(),
                    course.getCourseName(),
                    course.getDurationInWeeks() + " weeks",
                    status);
        }
    }

    private void searchCourseById() throws InvalidInputException, EntityNotFoundException {
        System.out.print("Enter Course ID: ");
        int courseId = InputValidator.parseInteger(scanner.nextLine().trim(), "Course ID");
        Course course = courseService.getCourseById(courseId);
        System.out.println("\n--- COURSE DETAILS ---");
        System.out.println(course);
    }

    private void updateCourse() throws InvalidInputException, EntityNotFoundException {
        System.out.print("Enter Course ID: ");
        int courseId = InputValidator.parseInteger(scanner.nextLine().trim(), "Course ID");
        System.out.print("Enter New Course Name: ");
        String courseName = scanner.nextLine().trim();
        System.out.print("Enter New Description: ");
        String description = scanner.nextLine().trim();
        System.out.print("Enter New Duration (in weeks): ");
        int duration = InputValidator.parseInteger(scanner.nextLine().trim(), "Duration");

        Course course = courseService.updateCourse(courseId, courseName, description, duration);
        System.out.println("\n✓ Course updated successfully!");
        System.out.println(course);
    }

    private void deactivateCourse() throws InvalidInputException, EntityNotFoundException {
        System.out.print("Enter Course ID: ");
        int courseId = InputValidator.parseInteger(scanner.nextLine().trim(), "Course ID");
        courseService.deactivateCourse(courseId);
        System.out.println("\n✓ Course deactivated successfully!");
    }

    private void activateCourse() throws InvalidInputException, EntityNotFoundException {
        System.out.print("Enter Course ID: ");
        int courseId = InputValidator.parseInteger(scanner.nextLine().trim(), "Course ID");
        courseService.activateCourse(courseId);
        System.out.println("\n✓ Course activated successfully!");
    }
}
