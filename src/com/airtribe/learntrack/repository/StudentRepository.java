package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;
import java.util.ArrayList;

/**
 * StudentRepository - Data Access Layer for Student entity
 * Manages in-memory storage of Student objects using ArrayList
 * Demonstrates Collections (ArrayList) usage
 */
public class StudentRepository {
    private ArrayList<Student> students;

    // Constructor
    public StudentRepository() {
        this.students = new ArrayList<>();
    }

    /**
     * Add a new student to the repository
     * @param student the student to add
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Remove a student by ID
     * @param studentId the ID of student to remove
     * @return true if removed, false if not found
     */
    public boolean removeStudent(int studentId) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == studentId) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Find a student by ID
     * @param studentId the ID to search for
     * @return Student if found, null otherwise
     */
    public Student findStudentById(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }

    /**
     * Get all students
     * @return ArrayList of all students
     */
    public ArrayList<Student> getAllStudents() {
        return students;
    }

    /**
     * Update an existing student
     * @param student the updated student object
     * @return true if updated, false if not found
     */
    public boolean updateStudent(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == student.getId()) {
                students.set(i, student);
                return true;
            }
        }
        return false;
    }

    /**
     * Get count of all students
     * @return number of students
     */
    public int getStudentCount() {
        return students.size();
    }

    /**
     * Clear all students (useful for testing)
     */
    public void clear() {
        students.clear();
    }
}
