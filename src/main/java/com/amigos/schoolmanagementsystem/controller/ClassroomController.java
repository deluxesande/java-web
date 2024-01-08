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

import com.amigos.schoolmanagementsystem.model.Classroom;
import com.amigos.schoolmanagementsystem.service.ClassroomService;

@RestController
@RequestMapping(path = "api/v1/classroom")
public class ClassroomController {
    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping
    public List<Classroom> getClassrooms() {
        return classroomService.getClassrooms();
    }

    @PostMapping
    public Classroom addNewClassroom(@RequestBody Classroom classroom) {
        return classroomService.addNewClassroom(classroom);
    }

    @PutMapping(path = "{classroomId}")
    public Classroom updateClassroom(@PathVariable("classroomId") Long classroomId,
            @RequestBody Classroom updatedClassroom) {
        if (classroomId == null) {
            throw new IllegalArgumentException("Classroom ID cannot be null");
        }
        updatedClassroom.setId(classroomId); // Ensure the ID is set to the one from the path variable
        return classroomService.updateClassroom(updatedClassroom);
    }

    @DeleteMapping(path = "{classroomId}")
    public ResponseEntity<String> deleteStudent(@PathVariable("classroomId") Long classroomId) {
        boolean exists = classroomService.deleteClassroom(classroomId);
        if (exists) {
            return ResponseEntity.status(HttpStatus.OK).body("Student deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with ID: " + classroomId + " not found");
        }
    }
}
