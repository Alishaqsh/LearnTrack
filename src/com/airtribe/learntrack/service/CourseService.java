package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;
import java.util.ArrayList;

/**
 * CourseService - Business Logic Layer for Course operations
 * Handles all course-related operations with validation and error handling
 */
public class CourseService {
    private CourseRepository courseRepository;

    // Constructor with dependency injection
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * Add a new course with validation
     * @param courseName the course name
     * @param description the course description
     * @param durationInWeeks the course duration
     * @return the created course
     * @throws InvalidInputException if input validation fails
     */
    public Course addCourse(String courseName, String description, int durationInWeeks) 
            throws InvalidInputException {
        
        // Validate inputs
        InputValidator.validateNotEmpty(courseName, "Course Name");
        InputValidator.validatePositive(durationInWeeks, "Duration");

        // Create new course with generated ID
        int courseId = IdGenerator.getNextCourseId();
        Course course = new Course(courseId, courseName, description, durationInWeeks, true);
        
        // Add to repository
        courseRepository.addCourse(course);
        
        return course;
    }

    /**
     * Get a course by ID
     * @param courseId the course ID
     * @return the course
     * @throws EntityNotFoundException if course not found
     */
    public Course getCourseById(int courseId) throws EntityNotFoundException {
        Course course = courseRepository.findCourseById(courseId);
        if (course == null) {
            throw new EntityNotFoundException("Course with ID " + courseId + " not found.");
        }
        return course;
    }

    /**
     * Get all courses
     * @return list of all courses
     */
    public ArrayList<Course> getAllCourses() {
        return courseRepository.getAllCourses();
    }

    /**
     * Update course information
     * @param courseId the course ID
     * @param courseName new course name
     * @param description new description
     * @param durationInWeeks new duration
     * @return the updated course
     * @throws EntityNotFoundException if course not found
     * @throws InvalidInputException if input validation fails
     */
    public Course updateCourse(int courseId, String courseName, String description, int durationInWeeks) 
            throws EntityNotFoundException, InvalidInputException {
        
        // Validate inputs
        InputValidator.validateNotEmpty(courseName, "Course Name");
        InputValidator.validatePositive(durationInWeeks, "Duration");

        // Get existing course
        Course course = getCourseById(courseId);
        
        // Update fields
        course.setCourseName(courseName);
        course.setDescription(description);
        course.setDurationInWeeks(durationInWeeks);
        
        // Update in repository
        courseRepository.updateCourse(course);
        
        return course;
    }

    /**
     * Deactivate a course
     * @param courseId the course ID
     * @return the deactivated course
     * @throws EntityNotFoundException if course not found
     */
    public Course deactivateCourse(int courseId) throws EntityNotFoundException {
        Course course = getCourseById(courseId);
        course.setActive(false);
        courseRepository.updateCourse(course);
        return course;
    }

    /**
     * Activate a course
     * @param courseId the course ID
     * @return the activated course
     * @throws EntityNotFoundException if course not found
     */
    public Course activateCourse(int courseId) throws EntityNotFoundException {
        Course course = getCourseById(courseId);
        course.setActive(true);
        courseRepository.updateCourse(course);
        return course;
    }

    /**
     * Delete a course permanently
     * @param courseId the course ID
     * @return true if deleted, false otherwise
     */
    public boolean deleteCourse(int courseId) {
        return courseRepository.removeCourse(courseId);
    }

    /**
     * Get count of all courses
     * @return number of courses
     */
    public int getCourseCount() {
        return courseRepository.getCourseCount();
    }
}
