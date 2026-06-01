package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private final ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student findStudentById(int studentId) throws EntityNotFoundException {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        throw new EntityNotFoundException("Student with ID " + studentId + " not found.");
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public void updateStudent(Student student) throws EntityNotFoundException {
        Student existing = findStudentById(student.getId());
        existing.setFirstName(student.getFirstName());
        existing.setLastName(student.getLastName());
        existing.setEmail(student.getEmail());
        existing.setBatch(student.getBatch());
        existing.setActive(student.isActive());
    }

    public int getStudentCount() {
        return students.size();
    }

    public void clear() {
        students.clear();
    }
}
