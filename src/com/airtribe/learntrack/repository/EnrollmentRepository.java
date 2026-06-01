package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;
import java.util.ArrayList;

/**
 * EnrollmentRepository - Data Access Layer for Enrollment entity
 * Manages in-memory storage of Enrollment objects using ArrayList
 */
public class EnrollmentRepository {
    private ArrayList<Enrollment> enrollments;

    // Constructor
    public EnrollmentRepository() {
        this.enrollments = new ArrayList<>();
    }

    /**
     * Add a new enrollment to the repository
     * @param enrollment the enrollment to add
     */
    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    /**
     * Remove an enrollment by ID
     * @param enrollmentId the ID of enrollment to remove
     * @return true if removed, false if not found
     */
    public boolean removeEnrollment(int enrollmentId) {
        for (int i = 0; i < enrollments.size(); i++) {
            if (enrollments.get(i).getId() == enrollmentId) {
                enrollments.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Find an enrollment by ID
     * @param enrollmentId the ID to search for
     * @return Enrollment if found, null otherwise
     */
    public Enrollment findEnrollmentById(int enrollmentId) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == enrollmentId) {
                return enrollment;
            }
        }
        return null;
    }

    /**
     * Get all enrollments
     * @return ArrayList of all enrollments
     */
    public ArrayList<Enrollment> getAllEnrollments() {
        return enrollments;
    }

    /**
     * Get all enrollments for a specific student
     * @param studentId the student ID
     * @return ArrayList of enrollments for that student
     */
    public ArrayList<Enrollment> getEnrollmentsByStudent(int studentId) {
        ArrayList<Enrollment> studentEnrollments = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId) {
                studentEnrollments.add(enrollment);
            }
        }
        return studentEnrollments;
    }

    /**
     * Get all enrollments for a specific course
     * @param courseId the course ID
     * @return ArrayList of enrollments for that course
     */
    public ArrayList<Enrollment> getEnrollmentsByCourse(int courseId) {
        ArrayList<Enrollment> courseEnrollments = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourseId() == courseId) {
                courseEnrollments.add(enrollment);
            }
        }
        return courseEnrollments;
    }

    /**
     * Update an existing enrollment
     * @param enrollment the updated enrollment object
     * @return true if updated, false if not found
     */
    public boolean updateEnrollment(Enrollment enrollment) {
        for (int i = 0; i < enrollments.size(); i++) {
            if (enrollments.get(i).getId() == enrollment.getId()) {
                enrollments.set(i, enrollment);
                return true;
            }
        }
        return false;
    }

    /**
     * Get count of all enrollments
     * @return number of enrollments
     */
    public int getEnrollmentCount() {
        return enrollments.size();
    }

    /**
     * Clear all enrollments (useful for testing)
     */
    public void clear() {
        enrollments.clear();
    }
}
