package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(String firstName, String lastName, String email, String batch)
            throws InvalidInputException {
        InputValidator.validateNotEmpty(firstName, "First Name");
        InputValidator.validateNotEmpty(lastName, "Last Name");
        InputValidator.validateNotEmpty(email, "Email");
        InputValidator.validateEmail(email);
        InputValidator.validateNotEmpty(batch, "Batch");

        int studentId = IdGenerator.getNextStudentId();
        Student student = Student.create(studentId, firstName, lastName, email, batch, true);
        studentRepository.addStudent(student);
        return student;
    }

    public Student getStudentById(int studentId) throws EntityNotFoundException {
        return studentRepository.findStudentById(studentId);
    }

    public ArrayList<Student> getAllStudents() {
        return new ArrayList<>(studentRepository.getAllStudents());
    }

    public Student updateStudent(int studentId, String firstName, String lastName,
                                 String email, String batch)
            throws EntityNotFoundException, InvalidInputException {
        InputValidator.validateNotEmpty(firstName, "First Name");
        InputValidator.validateNotEmpty(lastName, "Last Name");
        InputValidator.validateNotEmpty(email, "Email");
        InputValidator.validateEmail(email);
        InputValidator.validateNotEmpty(batch, "Batch");

        Student student = getStudentById(studentId);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setBatch(batch);
        studentRepository.updateStudent(student);
        return student;
    }

    public Student deactivateStudent(int studentId) throws EntityNotFoundException {
        Student student = getStudentById(studentId);
        student.setActive(false);
        studentRepository.updateStudent(student);
        return student;
    }

    public Student activateStudent(int studentId) throws EntityNotFoundException {
        Student student = getStudentById(studentId);
        student.setActive(true);
        studentRepository.updateStudent(student);
        return student;
    }

    public int getStudentCount() {
        return studentRepository.getStudentCount();
    }
}
