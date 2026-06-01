package com.airtribe.learntrack.entity;

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
    private String status; // ACTIVE, COMPLETED, CANCELLED

    // Default constructor
    public Enrollment() {
        this.enrollmentDate = new Date();
        this.status = "ACTIVE";
    }

    // Parameterized constructor
    public Enrollment(int id, int studentId, int courseId, Date enrollmentDate, String status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    // Constructor overloading - without status (defaults to ACTIVE)
    public Enrollment(int id, int studentId, int courseId, Date enrollmentDate) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = "ACTIVE";
    }

    // Constructor overloading - basic info only
    public Enrollment(int id, int studentId, int courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = new Date();
        this.status = "ACTIVE";
    }

    // Getters and Setters (Encapsulation)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.equals("ACTIVE") || status.equals("COMPLETED") || status.equals("CANCELLED")) {
            this.status = status;
        }
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
