package com.amigos.schoolmanagementsystem.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amigos.schoolmanagementsystem.model.Student;
import com.amigos.schoolmanagementsystem.service.StudentService;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public Student addNewStudent(@RequestBody Student student) {
        Student savedStudent = studentService.addNewStudent(student);
        // return ResponseEntity.status(HttpStatus.CREATED).body("Student created with
        // ID: " + savedStudent.getId());
        return savedStudent;
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable("studentId") Long studentId) {
        boolean exists = studentService.deleteStudent(studentId);
        if (exists) {
            return ResponseEntity.status(HttpStatus.OK).body("Student deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with ID: " + studentId + " not found");
        }
    }

    @PutMapping(path = "{studentId}")
    public Student updateStudent(@PathVariable("studentId") Long studentId, @RequestBody Student updatedStudent) {
        if (studentId == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }
        updatedStudent.setId(studentId); // Ensure the ID is set to the one from the path variable
        return studentService.updateStudent(updatedStudent);
    }
}
