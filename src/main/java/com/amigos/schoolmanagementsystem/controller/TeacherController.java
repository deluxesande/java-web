package com.amigos.schoolmanagementsystem.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.amigos.schoolmanagementsystem.service.TeacherService;
import com.amigos.schoolmanagementsystem.model.Teacher;

@RestController
@RequestMapping(path = "api/v1/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> getTeachers() {
        return teacherService.getTeachers();
    }

    @PostMapping
    public Teacher addNewTeacher(@RequestBody Teacher teacher) {
        return teacherService.addNewTeacher(teacher);
    }

    @DeleteMapping(path = "{teacherId}")
    public ResponseEntity<String> deleteStudent(@PathVariable("teacherId") Long teacherId) {
        boolean exists = teacherService.deleteTeacher(teacherId);
        if (exists) {
            return ResponseEntity.status(HttpStatus.OK).body("Teacher deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher with ID: " + teacherId + " not found");
        }
    }

    @PutMapping(path = "{teacherId}")
    public Teacher updateTeacher(@PathVariable("teacherId") Long teacherId, @RequestBody Teacher updatedTeacher) {
        if (teacherId == null) {
            throw new IllegalArgumentException("Teacher ID cannot be null");
        }
        updatedTeacher.setId(teacherId);
        return teacherService.updateTeacher(updatedTeacher);
    }
}
