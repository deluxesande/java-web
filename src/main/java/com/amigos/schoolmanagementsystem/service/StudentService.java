package com.amigos.schoolmanagementsystem.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.amigos.schoolmanagementsystem.model.Student;
import com.amigos.schoolmanagementsystem.repository.StudentRepository;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll(); // SELECT * FROM student;
    }

    public Student addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new DataIntegrityViolationException("Email taken");
        }
        return studentRepository.save(student);
    }

    public boolean deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new NoSuchElementException("Student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);

        return exists;
    }

    @Transactional
    public Student updateStudent(Student updatedStudent) {
        Long studentId = updatedStudent.getId();
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student with id " + studentId + " does not exist"));

        Optional.ofNullable(updatedStudent.getFirstName())
                .filter(StringUtils::hasText)
                .filter(f -> !f.equals(student.getFirstName()))
                .ifPresent(student::setFirstName);

        Optional.ofNullable(updatedStudent.getLastName())
                .filter(StringUtils::hasText)
                .filter(l -> !l.equals(student.getLastName()))
                .ifPresent(student::setLastName);

        Optional.ofNullable(updatedStudent.getDob())
                .filter(d -> !d.equals(student.getDob()))
                .ifPresent(student::setDob);

        Optional.ofNullable(updatedStudent.getGrade())
                .filter(StringUtils::hasText)
                .filter(g -> !g.equals(student.getGrade()))
                .ifPresent(student::setGrade);

        Optional.ofNullable(updatedStudent.getEmail())
                .filter(StringUtils::hasText)
                .filter(e -> !e.equals(student.getEmail()))
                .ifPresent(e -> {
                    Optional<Student> studentByEmail = studentRepository.findStudentByEmail(e);
                    if (studentByEmail.isPresent()) {
                        throw new DataIntegrityViolationException("Email taken");
                    }
                    student.setEmail(e);
                });

        return studentRepository.save(student);
    }

}