package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
    private final ArrayList<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public Course findCourseById(int courseId) throws EntityNotFoundException {
        for (Course course : courses) {
            if (course.getId() == courseId) {
                return course;
            }
        }
        throw new EntityNotFoundException("Course with ID " + courseId + " not found.");
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    public void updateCourse(Course course) throws EntityNotFoundException {
        Course existing = findCourseById(course.getId());
        existing.setCourseName(course.getCourseName());
        existing.setDescription(course.getDescription());
        existing.setDurationInWeeks(course.getDurationInWeeks());
        existing.setActive(course.isActive());
    }

    public int getCourseCount() {
        return courses.size();
    }

    public void clear() {
        courses.clear();
    }
}
