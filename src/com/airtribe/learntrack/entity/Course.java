package com.airtribe.learntrack.entity;

/**
 * Course entity class
 * Represents a course with basic information
 */
public class Course {
    private int id;
    private String courseName;
    private String description;
    private int durationInWeeks;
    private boolean active;

    // Default constructor
    public Course() {
        this.active = true;
    }

    // Parameterized constructor
    public Course(int id, String courseName, String description, int durationInWeeks, boolean active) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.durationInWeeks = durationInWeeks;
        this.active = active;
    }

    // Constructor overloading - without description
    public Course(int id, String courseName, int durationInWeeks) {
        this.id = id;
        this.courseName = courseName;
        this.description = "";
        this.durationInWeeks = durationInWeeks;
        this.active = true;
    }

    // Getters and Setters (Encapsulation)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDurationInWeeks() {
        return durationInWeeks;
    }

    public void setDurationInWeeks(int durationInWeeks) {
        this.durationInWeeks = durationInWeeks;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", description='" + description + '\'' +
                ", durationInWeeks=" + durationInWeeks +
                ", active=" + active +
                '}';
    }
}
