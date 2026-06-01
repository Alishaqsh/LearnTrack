package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.exception.InvalidInputException;
import java.util.Date;

/**
 * Enrollment entity class
 * Represents a student's enrollment in a course
 */
public class Enrollment {
    private int id;
    private int studentId;
    private int courseId;
    private Date enrollmentDate;
    private EnrollmentStatus status;

    public Enrollment() {
        this.enrollmentDate = new Date();
        this.status = EnrollmentStatus.ACTIVE;
    }

    public Enrollment(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = new Date();
        this.status = EnrollmentStatus.ACTIVE;
    }

    private Enrollment(int id, int studentId, int courseId, Date enrollmentDate, EnrollmentStatus status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        setEnrollmentDate(enrollmentDate != null ? enrollmentDate : new Date());
        setStatus(status);
    }

    public static Enrollment create(int id, int studentId, int courseId, Date enrollmentDate, EnrollmentStatus status) {
        return new Enrollment(id, studentId, courseId, enrollmentDate, status);
    }

    public int getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate != null ? new Date(enrollmentDate.getTime()) : null;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        if (enrollmentDate == null) {
            throw new InvalidInputException("Enrollment date cannot be null.");
        }
        this.enrollmentDate = new Date(enrollmentDate.getTime());
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        if (status == null) {
            throw new InvalidInputException("Status cannot be null.");
        }
        this.status = status;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", enrollmentDate=" + enrollmentDate +
                ", status='" + status + '\'' +
                '}';
    }
}
