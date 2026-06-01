package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;
import java.util.ArrayList;

/**
 * CourseRepository - Data Access Layer for Course entity
 * Manages in-memory storage of Course objects using ArrayList
 */
public class CourseRepository {
    private ArrayList<Course> courses;

    // Constructor
    public CourseRepository() {
        this.courses = new ArrayList<>();
    }

    /**
     * Add a new course to the repository
     * @param course the course to add
     */
    public void addCourse(Course course) {
        courses.add(course);
    }

    /**
     * Remove a course by ID
     * @param courseId the ID of course to remove
     * @return true if removed, false if not found
     */
    public boolean removeCourse(int courseId) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == courseId) {
                courses.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Find a course by ID
     * @param courseId the ID to search for
     * @return Course if found, null otherwise
     */
    public Course findCourseById(int courseId) {
        for (Course course : courses) {
            if (course.getId() == courseId) {
                return course;
            }
        }
        return null;
    }

    /**
     * Get all courses
     * @return ArrayList of all courses
     */
    public ArrayList<Course> getAllCourses() {
        return courses;
    }

    /**
     * Update an existing course
     * @param course the updated course object
     * @return true if updated, false if not found
     */
    public boolean updateCourse(Course course) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == course.getId()) {
                courses.set(i, course);
                return true;
            }
        }
        return false;
    }

    /**
     * Get count of all courses
     * @return number of courses
     */
    public int getCourseCount() {
        return courses.size();
    }

    /**
     * Clear all courses (useful for testing)
     */
    public void clear() {
        courses.clear();
    }
}
