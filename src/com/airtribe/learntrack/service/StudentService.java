package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;
import java.util.ArrayList;

/**
 * StudentService - Business Logic Layer for Student operations
 * Handles all student-related operations with validation and error handling
 */
public class StudentService {
    private StudentRepository studentRepository;

    // Constructor with dependency injection
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Add a new student with validation
     * @param firstName student's first name
     * @param lastName student's last name
     * @param email student's email
     * @param batch student's batch
     * @return the created student
     * @throws InvalidInputException if input validation fails
     */
    public Student addStudent(String firstName, String lastName, String email, String batch) 
            throws InvalidInputException {
        
        // Validate inputs
        InputValidator.validateNotEmpty(firstName, "First Name");
        InputValidator.validateNotEmpty(lastName, "Last Name");
        InputValidator.validateNotEmpty(email, "Email");
        InputValidator.validateEmail(email);
        InputValidator.validateNotEmpty(batch, "Batch");

        // Create new student with generated ID
        int studentId = IdGenerator.getNextStudentId();
        Student student = new Student(studentId, firstName, lastName, email, batch, true);
        
        // Add to repository
        studentRepository.addStudent(student);
        
        return student;
    }

    /**
     * Get a student by ID
     * @param studentId the student ID
     * @return the student
     * @throws EntityNotFoundException if student not found
     */
    public Student getStudentById(int studentId) throws EntityNotFoundException {
        Student student = studentRepository.findStudentById(studentId);
        if (student == null) {
            throw new EntityNotFoundException("Student with ID " + studentId + " not found.");
        }
        return student;
    }

    /**
     * Get all students
     * @return list of all students
     */
    public ArrayList<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    /**
     * Update student information
     * @param studentId the student ID
     * @param firstName new first name
     * @param lastName new last name
     * @param email new email
     * @param batch new batch
     * @return the updated student
     * @throws EntityNotFoundException if student not found
     * @throws InvalidInputException if input validation fails
     */
    public Student updateStudent(int studentId, String firstName, String lastName, 
                                String email, String batch) 
            throws EntityNotFoundException, InvalidInputException {
        
        // Validate inputs
        InputValidator.validateNotEmpty(firstName, "First Name");
        InputValidator.validateNotEmpty(lastName, "Last Name");
        InputValidator.validateNotEmpty(email, "Email");
        InputValidator.validateEmail(email);
        InputValidator.validateNotEmpty(batch, "Batch");

        // Get existing student
        Student student = getStudentById(studentId);
        
        // Update fields
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setBatch(batch);
        
        // Update in repository
        studentRepository.updateStudent(student);
        
        return student;
    }

    /**
     * Deactivate a student (set active to false)
     * @param studentId the student ID
     * @return the deactivated student
     * @throws EntityNotFoundException if student not found
     */
    public Student deactivateStudent(int studentId) throws EntityNotFoundException {
        Student student = getStudentById(studentId);
        student.setActive(false);
        studentRepository.updateStudent(student);
        return student;
    }

    /**
     * Activate a student (set active to true)
     * @param studentId the student ID
     * @return the activated student
     * @throws EntityNotFoundException if student not found
     */
    public Student activateStudent(int studentId) throws EntityNotFoundException {
        Student student = getStudentById(studentId);
        student.setActive(true);
        studentRepository.updateStudent(student);
        return student;
    }

    /**
     * Delete a student permanently
     * @param studentId the student ID
     * @return true if deleted, false otherwise
     */
    public boolean deleteStudent(int studentId) {
        return studentRepository.removeStudent(studentId);
    }

    /**
     * Get count of all students
     * @return number of students
     */
    public int getStudentCount() {
        return studentRepository.getStudentCount();
    }
}
