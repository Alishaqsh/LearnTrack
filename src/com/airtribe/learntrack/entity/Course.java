package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.exception.InvalidInputException;

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

    public Course() {
        this.active = true;
    }

    public Course(String courseName, String description, int durationInWeeks) {
        this.courseName = courseName;
        this.description = description;
        setDurationInWeeks(durationInWeeks);
        this.active = true;
    }

    public Course(String courseName, int durationInWeeks) {
        this.courseName = courseName;
        this.description = "";
        setDurationInWeeks(durationInWeeks);
        this.active = true;
    }

    private Course(int id, String courseName, String description, int durationInWeeks, boolean active) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        setDurationInWeeks(durationInWeeks);
        this.active = active;
    }

    public static Course create(int id, String courseName, String description, int durationInWeeks, boolean active) {
        return new Course(id, courseName, description, durationInWeeks, active);
    }

    public int getId() {
        return id;
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
        if (durationInWeeks <= 0) {
            throw new InvalidInputException("Duration must be a positive number of weeks.");
        }
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
