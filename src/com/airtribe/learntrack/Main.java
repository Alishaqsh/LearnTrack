package com.airtribe.learntrack;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.util.InputValidator;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main application class for LearnTrack
 * Menu-driven console application for Student & Course Management
 * Demonstrates:
 * - Console UI and menu handling
 * - Input validation and error handling
 * - Service layer interaction
 * - Loop and conditional logic
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentService studentService;
    private static CourseService courseService;
    private static EnrollmentService enrollmentService;

    public static void main(String[] args) {
        // Initialize repositories
        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();
        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

        // Initialize services with repositories
        studentService = new StudentService(studentRepository);
        courseService = new CourseService(courseRepository);
        enrollmentService = new EnrollmentService(enrollmentRepository, studentService, courseService);

        // Display welcome message and start application loop
        displayWelcome();
        mainMenu();

        scanner.close();
    }

    /**
     * Display welcome message
     */
    private static void displayWelcome() {
        System.out.println("\n========================================");
        System.out.println("  Welcome to LearnTrack");
        System.out.println("  Student & Course Management System");
        System.out.println("========================================\n");
    }

    /**
     * Main menu - displays options and handles user input
     * Uses do-while loop to keep application running
     */
    private static void mainMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Student Management");
            System.out.println("2. Course Management");
            System.out.println("3. Enrollment Management");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            try {
                String choice = scanner.nextLine().trim();

                // Switch statement for menu handling
                switch (choice) {
                    case "1":
                        studentManagementMenu();
                        break;
                    case "2":
                        courseManagementMenu();
                        break;
                    case "3":
                        enrollmentManagementMenu();
                        break;
                    case "4":
                        running = false;
                        System.out.println("\nThank you for using LearnTrack. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * Student Management submenu
     * Handles student CRUD operations
     */
    private static void studentManagementMenu() {
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

            try {
                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        addNewStudent();
                        break;
                    case "2":
                        viewAllStudents();
                        break;
                    case "3":
                        searchStudentById();
                        break;
                    case "4":
                        updateStudent();
                        break;
                    case "5":
                        deactivateStudent();
                        break;
                    case "6":
                        activateStudent();
                        break;
                    case "7":
                        inMenu = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * Add a new student with input validation
     */
    private static void addNewStudent() {
        try {
            System.out.print("Enter First Name: ");
            String firstName = scanner.nextLine().trim();

            System.out.print("Enter Last Name: ");
            String lastName = scanner.nextLine().trim();

            System.out.print("Enter Email: ");
            String email = scanner.nextLine().trim();

            System.out.print("Enter Batch: ");
            String batch = scanner.nextLine().trim();

            // Service method handles validation
            Student student = studentService.addStudent(firstName, lastName, email, batch);

            System.out.println("\n✓ Student added successfully!");
            System.out.println("Student ID: " + student.getId());
            System.out.println("Name: " + student.getDisplayName());

        } catch (InvalidInputException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * View all students
     * Uses ArrayList iteration
     */
    private static void viewAllStudents() {
        ArrayList<Student> students = studentService.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n--- ALL STUDENTS ---");
        System.out.println(String.format("%-10s %-20s %-25s %-15s %-10s", 
                           "ID", "Name", "Email", "Batch", "Status"));
        System.out.println("--------------------------------------------------------------------");

        for (Student student : students) {
            String status = student.isActive() ? "Active" : "Inactive";
            System.out.println(String.format("%-10d %-20s %-25s %-15s %-10s",
                               student.getId(),
                               student.getDisplayName(),
                               student.getEmail(),
                               student.getBatch(),
                               status));
        }
    }

    /**
     * Search for a student by ID
     */
    private static void searchStudentById() {
        try {
            System.out.print("Enter Student ID: ");
            String input = scanner.nextLine().trim();
            int studentId = InputValidator.parseInteger(input, "Student ID");

            Student student = studentService.getStudentById(studentId);

            System.out.println("\n--- STUDENT DETAILS ---");
            System.out.println(student.toString());

        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Update a student's information
     */
    private static void updateStudent() {
        try {
            System.out.print("Enter Student ID: ");
            String input = scanner.nextLine().trim();
            int studentId = InputValidator.parseInteger(input, "Student ID");

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
            System.out.println(student.toString());

        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Deactivate a student
     */
    private static void deactivateStudent() {
        try {
            System.out.print("Enter Student ID: ");
            String input = scanner.nextLine().trim();
            int studentId = InputValidator.parseInteger(input, "Student ID");

            Student student = studentService.deactivateStudent(studentId);
            System.out.println("\n✓ Student deactivated successfully!");

        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Activate a student
     */
    private static void activateStudent() {
        try {
            System.out.print("Enter Student ID: ");
            String input = scanner.nextLine().trim();
            int studentId = InputValidator.parseInteger(input, "Student ID");

            Student student = studentService.activateStudent(studentId);
            System.out.println("\n✓ Student activated successfully!");

        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Course Management submenu
     */
    private static void courseManagementMenu() {
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

            try {
                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        addNewCourse();
                        break;
                    case "2":
                        viewAllCourses();
                        break;
                    case "3":
                        searchCourseById();
                        break;
                    case "4":
                        updateCourse();
                        break;
                    case "5":
                        deactivateCourse();
                        break;
                    case "6":
                        activateCourse();
                        break;
                    case "7":
                        inMenu = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * Add a new course
     */
    private static void addNewCourse() {
        try {
            System.out.print("Enter Course Name: ");
            String courseName = scanner.nextLine().trim();

            System.out.print("Enter Description: ");
            String description = scanner.nextLine().trim();

            System.out.print("Enter Duration (in weeks): ");
            String input = scanner.nextLine().trim();
            int duration = InputValidator.parseInteger(input, "Duration");

            Course course = courseService.addCourse(courseName, description, duration);

            System.out.println("\n✓ Course added successfully!");
            System.out.println("Course ID: " + course.getId());
            System.out.println("Course Name: " + course.getCourseName());

        } catch (InvalidInputException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * View all courses
     */
    private static void viewAllCourses() {
        ArrayList<Course> courses = courseService.getAllCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        System.out.println("\n--- ALL COURSES ---");
        System.out.println(String.format("%-10s %-25s %-15s %-10s", 
                           "ID", "Course Name", "Duration", "Status"));
        System.out.println("----------------------------------------------------------");

        for (Course course : courses) {
            String status = course.isActive() ? "Active" : "Inactive";
            System.out.println(String.format("%-10d %-25s %-15s %-10s",
                               course.getId(),
                               course.getCourseName(),
                               course.getDurationInWeeks() + " weeks",
                               status));
        }
    }

    /**
     * Search for a course by ID
     */
    private static void searchCourseById() {
        try {
            System.out.print("Enter Course ID: ");
            String input = scanner.nextLine().trim();
            int courseId = InputValidator.parseInteger(input, "Course ID");

            Course course = courseService.getCourseById(courseId);

            System.out.println("\n--- COURSE DETAILS ---");
            System.out.println(course.toString());

        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Update a course's information
     */
    private static void updateCourse() {
        try {
            System.out.print("Enter Course ID: ");
            String input = scanner.nextLine().trim();
            int courseId = InputValidator.parseInteger(input, "Course ID");

            System.out.print("Enter New Course Name: ");
            String courseName = scanner.nextLine().trim();

            System.out.print("Enter New Description: ");
            String description = scanner.nextLine().trim();

            System.out.print("Enter New Duration (in weeks): ");
            String durationInput = scanner.nextLine().trim();
            int duration = InputValidator.parseInteger(durationInput, "Duration");

            Course course = courseService.updateCourse(courseId, courseName, description, duration);

            System.out.println("\n✓ Course updated successfully!");
            System.out.println(course.toString());

        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Deactivate a course
     */
    private static void deactivateCourse() {
        try {
            System.out.print("Enter Course ID: ");
            String input = scanner.nextLine().trim();
            int courseId = InputValidator.parseInteger(input, "Course ID");

            Course course = courseService.deactivateCourse(courseId);
            System.out.println("\n✓ Course deactivated successfully!");

        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Activate a course
     */
    private static void activateCourse() {
        try {
            System.out.print("Enter Course ID: ");
            String input = scanner.nextLine().trim();
            int courseId = InputValidator.parseInteger(input, "Course ID");

            Course course = courseService.activateCourse(courseId);
            System.out.println("\n✓ Course activated successfully!");

        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Enrollment Management submenu
     */
    private static void enrollmentManagementMenu() {
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

            try {
                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        enrollStudent();
                        break;
                    case "2":
                        viewAllEnrollments();
                        break;
                    case "3":
                        viewEnrollmentsByStudent();
                        break;
                    case "4":
                        viewEnrollmentsByCourse();
                        break;
                    case "5":
                        updateEnrollmentStatus();
                        break;
                    case "6":
                        completeEnrollment();
                        break;
                    case "7":
                        cancelEnrollment();
                        break;
                    case "8":
                        inMenu = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * Enroll a student in a course
     */
    private static void enrollStudent() {
        try {
            System.out.print("Enter Student ID: ");
            String studentInput = scanner.nextLine().trim();
            int studentId = InputValidator.parseInteger(studentInput, "Student ID");

            System.out.print("Enter Course ID: ");
            String courseInput = scanner.nextLine().trim();
            int courseId = InputValidator.parseInteger(courseInput, "Course ID");

            Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);

            System.out.println("\n✓ Student enrolled successfully!");
            System.out.println("Enrollment ID: " + enrollment.getId());
            System.out.println(enrollment.toString());

        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * View all enrollments
     */
    private static void viewAllEnrollments() {
        ArrayList<Enrollment> enrollments = enrollmentService.getAllEnrollments();

        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found.");
            return;
        }

        System.out.println("\n--- ALL ENROLLMENTS ---");
        System.out.println(String.format("%-10s %-12s %-12s %-15s", 
                           "ID", "Student ID", "Course ID", "Status"));
        System.out.println("--------------------------------------------------");

        for (Enrollment enrollment : enrollments) {
            System.out.println(String.format("%-10d %-12d %-12d %-15s",
                               enrollment.getId(),
                               enrollment.getStudentId(),
                               enrollment.getCourseId(),
                               enrollment.getStatus()));
        }
    }

    /**
     * View enrollments for a specific student
     */
    private static void viewEnrollmentsByStudent() {
        try {
            System.out.print("Enter Student ID: ");
            String input = scanner.nextLine().trim();
            int studentId = InputValidator.parseInteger(input, "Student ID");

            ArrayList<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudent(studentId);

            if (enrollments.isEmpty()) {
                System.out.println("No enrollments found for this student.");
                return;
            }

            System.out.println("\n--- ENROLLMENTS FOR STUDENT ID: " + studentId + " ---");
            for (Enrollment enrollment : enrollments) {
                System.out.println(enrollment.toString());
            }

        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * View enrollments for a specific course
     */
    private static void viewEnrollmentsByCourse() {
        try {
            System.out.print("Enter Course ID: ");
            String input = scanner.nextLine().trim();
            int courseId = InputValidator.parseInteger(input, "Course ID");

            ArrayList<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourse(courseId);

            if (enrollments.isEmpty()) {
                System.out.println("No enrollments found for this course.");
                return;
            }

            System.out.println("\n--- ENROLLMENTS FOR COURSE ID: " + courseId + " ---");
            for (Enrollment enrollment : enrollments) {
                System.out.println(enrollment.toString());
            }

        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Update enrollment status
     */
    private static void updateEnrollmentStatus() {
        try {
            System.out.print("Enter Enrollment ID: ");
            String input = scanner.nextLine().trim();
            int enrollmentId = InputValidator.parseInteger(input, "Enrollment ID");

            System.out.print("Enter New Status (ACTIVE/COMPLETED/CANCELLED): ");
            String status = scanner.nextLine().trim().toUpperCase();

            Enrollment enrollment = enrollmentService.updateEnrollmentStatus(enrollmentId, status);

            System.out.println("\n✓ Enrollment status updated successfully!");
            System.out.println(enrollment.toString());

        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Mark enrollment as completed
     */
    private static void completeEnrollment() {
        try {
            System.out.print("Enter Enrollment ID: ");
            String input = scanner.nextLine().trim();
            int enrollmentId = InputValidator.parseInteger(input, "Enrollment ID");

            Enrollment enrollment = enrollmentService.completeEnrollment(enrollmentId);
            System.out.println("\n✓ Enrollment marked as completed!");

        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Cancel an enrollment
     */
    private static void cancelEnrollment() {
        try {
            System.out.print("Enter Enrollment ID: ");
            String input = scanner.nextLine().trim();
            int enrollmentId = InputValidator.parseInteger(input, "Enrollment ID");

            Enrollment enrollment = enrollmentService.cancelEnrollment(enrollmentId);
            System.out.println("\n✓ Enrollment cancelled!");

        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
}
