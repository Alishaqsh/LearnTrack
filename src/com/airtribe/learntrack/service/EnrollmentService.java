package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;
import java.util.ArrayList;
import java.util.Date;

/**
 * EnrollmentService - Business Logic Layer for Enrollment operations
 * Handles all enrollment-related operations with validation and error handling
 */
public class EnrollmentService {
    private EnrollmentRepository enrollmentRepository;
    private StudentService studentService;
    private CourseService courseService;

    // Constructor with dependency injection
    public EnrollmentService(EnrollmentRepository enrollmentRepository, 
                           StudentService studentService, 
                           CourseService courseService) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    /**
     * Enroll a student in a course with validation
     * @param studentId the student ID
     * @param courseId the course ID
     * @return the created enrollment
     * @throws EntityNotFoundException if student or course not found
     * @throws InvalidInputException if input validation fails
     */
    public Enrollment enrollStudent(int studentId, int courseId) 
            throws EntityNotFoundException, InvalidInputException {
        
        // Validate inputs
        InputValidator.validatePositive(studentId, "Student ID");
        InputValidator.validatePositive(courseId, "Course ID");

        // Check if student exists
        Student student = studentService.getStudentById(studentId);
        
        // Check if course exists
        Course course = courseService.getCourseById(courseId);

        // Create new enrollment
        int enrollmentId = IdGenerator.getNextEnrollmentId();
        Enrollment enrollment = new Enrollment(enrollmentId, studentId, courseId, new Date(), "ACTIVE");
        
        // Add to repository
        enrollmentRepository.addEnrollment(enrollment);
        
        return enrollment;
    }

    /**
     * Get an enrollment by ID
     * @param enrollmentId the enrollment ID
     * @return the enrollment
     * @throws EntityNotFoundException if enrollment not found
     */
    public Enrollment getEnrollmentById(int enrollmentId) throws EntityNotFoundException {
        Enrollment enrollment = enrollmentRepository.findEnrollmentById(enrollmentId);
        if (enrollment == null) {
            throw new EntityNotFoundException("Enrollment with ID " + enrollmentId + " not found.");
        }
        return enrollment;
    }

    /**
     * Get all enrollments
     * @return list of all enrollments
     */
    public ArrayList<Enrollment> getAllEnrollments() {
        return enrollmentRepository.getAllEnrollments();
    }

    /**
     * Get enrollments for a specific student
     * @param studentId the student ID
     * @return list of enrollments for that student
     * @throws EntityNotFoundException if student not found
     */
    public ArrayList<Enrollment> getEnrollmentsByStudent(int studentId) throws EntityNotFoundException {
        // Verify student exists
        studentService.getStudentById(studentId);
        return enrollmentRepository.getEnrollmentsByStudent(studentId);
    }

    /**
     * Get enrollments for a specific course
     * @param courseId the course ID
     * @return list of enrollments for that course
     * @throws EntityNotFoundException if course not found
     */
    public ArrayList<Enrollment> getEnrollmentsByCourse(int courseId) throws EntityNotFoundException {
        // Verify course exists
        courseService.getCourseById(courseId);
        return enrollmentRepository.getEnrollmentsByCourse(courseId);
    }

    /**
     * Update enrollment status
     * @param enrollmentId the enrollment ID
     * @param newStatus new status (ACTIVE, COMPLETED, CANCELLED)
     * @return the updated enrollment
     * @throws EntityNotFoundException if enrollment not found
     * @throws InvalidInputException if status is invalid
     */
    public Enrollment updateEnrollmentStatus(int enrollmentId, String newStatus) 
            throws EntityNotFoundException, InvalidInputException {
        
        // Validate status
        InputValidator.validateNotEmpty(newStatus, "Status");
        if (!newStatus.equals("ACTIVE") && !newStatus.equals("COMPLETED") && !newStatus.equals("CANCELLED")) {
            throw new InvalidInputException("Status must be ACTIVE, COMPLETED, or CANCELLED.");
        }

        // Get existing enrollment
        Enrollment enrollment = getEnrollmentById(enrollmentId);
        
        // Update status
        enrollment.setStatus(newStatus);
        
        // Update in repository
        enrollmentRepository.updateEnrollment(enrollment);
        
        return enrollment;
    }

    /**
     * Mark an enrollment as completed
     * @param enrollmentId the enrollment ID
     * @return the updated enrollment
     * @throws EntityNotFoundException if enrollment not found
     */
    public Enrollment completeEnrollment(int enrollmentId) throws EntityNotFoundException {
        try {
            return updateEnrollmentStatus(enrollmentId, "COMPLETED");
        } catch (InvalidInputException e) {
            // This shouldn't happen as COMPLETED is valid
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    /**
     * Cancel an enrollment
     * @param enrollmentId the enrollment ID
     * @return the updated enrollment
     * @throws EntityNotFoundException if enrollment not found
     */
    public Enrollment cancelEnrollment(int enrollmentId) throws EntityNotFoundException {
        try {
            return updateEnrollmentStatus(enrollmentId, "CANCELLED");
        } catch (InvalidInputException e) {
            // This shouldn't happen as CANCELLED is valid
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    /**
     * Delete an enrollment permanently
     * @param enrollmentId the enrollment ID
     * @return true if deleted, false otherwise
     */
    public boolean deleteEnrollment(int enrollmentId) {
        return enrollmentRepository.removeEnrollment(enrollmentId);
    }

    /**
     * Get count of all enrollments
     * @return number of enrollments
     */
    public int getEnrollmentCount() {
        return enrollmentRepository.getEnrollmentCount();
    }
}
