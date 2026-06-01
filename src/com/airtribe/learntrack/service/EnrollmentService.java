package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;
import java.util.ArrayList;
import java.util.Date;

public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             StudentService studentService,
                             CourseService courseService) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enrollStudent(int studentId, int courseId)
            throws EntityNotFoundException, InvalidInputException {
        InputValidator.validatePositive(studentId, "Student ID");
        InputValidator.validatePositive(courseId, "Course ID");

        studentService.getStudentById(studentId);
        courseService.getCourseById(courseId);

        int enrollmentId = IdGenerator.getNextEnrollmentId();
        Enrollment enrollment = Enrollment.create(enrollmentId, studentId, courseId, new Date(), "ACTIVE");
        enrollmentRepository.addEnrollment(enrollment);
        return enrollment;
    }

    public Enrollment getEnrollmentById(int enrollmentId) throws EntityNotFoundException {
        return enrollmentRepository.findEnrollmentById(enrollmentId);
    }

    public ArrayList<Enrollment> getAllEnrollments() {
        return new ArrayList<>(enrollmentRepository.getAllEnrollments());
    }

    public ArrayList<Enrollment> getEnrollmentsByStudent(int studentId)
            throws EntityNotFoundException, InvalidInputException {
        Student student = studentService.getStudentById(studentId);
        if (!student.isActive()) {
            throw new InvalidInputException("Student with ID " + studentId + " is not active.");
        }
        return new ArrayList<>(enrollmentRepository.getEnrollmentsByStudent(studentId));
    }

    public ArrayList<Enrollment> getEnrollmentsByCourse(int courseId)
            throws EntityNotFoundException, InvalidInputException {
        Course course = courseService.getCourseById(courseId);
        if (!course.isActive()) {
            throw new InvalidInputException("Course with ID " + courseId + " is not active.");
        }
        return new ArrayList<>(enrollmentRepository.getEnrollmentsByCourse(courseId));
    }

    public Enrollment updateEnrollmentStatus(int enrollmentId, String newStatus)
            throws EntityNotFoundException, InvalidInputException {
        InputValidator.validateNotEmpty(newStatus, "Status");
        Enrollment enrollment = getEnrollmentById(enrollmentId);
        enrollment.setStatus(newStatus);
        enrollmentRepository.updateEnrollment(enrollment);
        return enrollment;
    }

    public Enrollment completeEnrollment(int enrollmentId) throws EntityNotFoundException, InvalidInputException {
        return updateEnrollmentStatus(enrollmentId, "COMPLETED");
    }

    public Enrollment cancelEnrollment(int enrollmentId) throws EntityNotFoundException, InvalidInputException {
        return updateEnrollmentStatus(enrollmentId, "CANCELLED");
    }

    public int getEnrollmentCount() {
        return enrollmentRepository.getEnrollmentCount();
    }
}
