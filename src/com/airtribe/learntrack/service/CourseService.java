package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;
import java.util.ArrayList;

public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(String courseName, String description, int durationInWeeks)
            throws InvalidInputException {
        InputValidator.validateNotEmpty(courseName, "Course Name");
        InputValidator.validatePositive(durationInWeeks, "Duration");

        int courseId = IdGenerator.getNextCourseId();
        Course course = Course.create(courseId, courseName, description, durationInWeeks, true);
        courseRepository.addCourse(course);
        return course;
    }

    public Course getCourseById(int courseId) throws EntityNotFoundException {
        return courseRepository.findCourseById(courseId);
    }

    public ArrayList<Course> getAllCourses() {
        return new ArrayList<>(courseRepository.getAllCourses());
    }

    public Course updateCourse(int courseId, String courseName, String description, int durationInWeeks)
            throws EntityNotFoundException, InvalidInputException {
        InputValidator.validateNotEmpty(courseName, "Course Name");
        InputValidator.validatePositive(durationInWeeks, "Duration");

        Course course = getCourseById(courseId);
        course.setCourseName(courseName);
        course.setDescription(description);
        course.setDurationInWeeks(durationInWeeks);
        courseRepository.updateCourse(course);
        return course;
    }

    public Course deactivateCourse(int courseId) throws EntityNotFoundException {
        Course course = getCourseById(courseId);
        course.setActive(false);
        courseRepository.updateCourse(course);
        return course;
    }

    public Course activateCourse(int courseId) throws EntityNotFoundException {
        Course course = getCourseById(courseId);
        course.setActive(true);
        courseRepository.updateCourse(course);
        return course;
    }

    public int getCourseCount() {
        return courseRepository.getCourseCount();
    }
}
