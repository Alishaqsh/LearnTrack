package com.airtribe.learntrack.entity;

/**
 * Student class extending Person
 * Demonstrates inheritance and polymorphism
 */
public class Student extends Person {
    private String batch;
    private boolean active;

    public Student() {
        super();
        this.active = true;
    }

    public Student(String firstName, String lastName, String email, String batch) {
        super(firstName, lastName, email);
        this.batch = batch;
        this.active = true;
    }

    public Student(String firstName, String lastName, String batch) {
        super(firstName, lastName);
        this.batch = batch;
        this.active = true;
    }

    private Student(int id, String firstName, String lastName, String email, String batch, boolean active) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = active;
    }

    public static Student create(int id, String firstName, String lastName, String email, String batch, boolean active) {
        return new Student(id, firstName, lastName, email, batch, active);
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getDisplayName() {
        String baseName = super.getDisplayName();
        if (batch != null && !batch.isEmpty()) {
            return baseName + " [Batch: " + batch + "]";
        }
        return baseName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", batch='" + batch + '\'' +
                ", active=" + active +
                '}';
    }
}
