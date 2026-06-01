package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentRepository {
    private final ArrayList<Enrollment> enrollments = new ArrayList<>();

    public void addEnrollment(Enrollment enrollment) throws InvalidInputException {
        for (Enrollment existing : enrollments) {
            if (existing.getStudentId() == enrollment.getStudentId()
                    && existing.getCourseId() == enrollment.getCourseId()
                    && "ACTIVE".equals(existing.getStatus())) {
                throw new InvalidInputException("Student is already actively enrolled in this course.");
            }
        }
        enrollments.add(enrollment);
    }

    public Enrollment findEnrollmentById(int enrollmentId) throws EntityNotFoundException {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == enrollmentId) {
                return enrollment;
            }
        }
        throw new EntityNotFoundException("Enrollment with ID " + enrollmentId + " not found.");
    }

    public List<Enrollment> getAllEnrollments() {
        return new ArrayList<>(enrollments);
    }

    public List<Enrollment> getEnrollmentsByStudent(int studentId) {
        ArrayList<Enrollment> studentEnrollments = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId) {
                studentEnrollments.add(enrollment);
            }
        }
        return studentEnrollments;
    }

    public List<Enrollment> getEnrollmentsByCourse(int courseId) {
        ArrayList<Enrollment> courseEnrollments = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourseId() == courseId) {
                courseEnrollments.add(enrollment);
            }
        }
        return courseEnrollments;
    }

    public void updateEnrollment(Enrollment enrollment) throws EntityNotFoundException {
        Enrollment existing = findEnrollmentById(enrollment.getId());
        existing.setStudentId(enrollment.getStudentId());
        existing.setCourseId(enrollment.getCourseId());
        existing.setEnrollmentDate(enrollment.getEnrollmentDate());
        existing.setStatus(enrollment.getStatus());
    }

    public int getEnrollmentCount() {
        return enrollments.size();
    }

    public void clear() {
        enrollments.clear();
    }
}
