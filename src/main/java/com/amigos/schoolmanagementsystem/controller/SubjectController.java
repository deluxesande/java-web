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

import com.amigos.schoolmanagementsystem.model.Subject;
import com.amigos.schoolmanagementsystem.service.SubjectService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(path = "api/v1/subject")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public List<Subject> getSubjects() {
        return subjectService.getSubjects();
    }

    @PostMapping
    public Subject addNewSubject(@RequestBody Subject subject) {
        return subjectService.addNewSubject(subject);
    }

    @PutMapping(path = "{subjectId}")
    public Subject updateSubject(@PathParam("subjectId") Long subjectId, @RequestBody Subject updatedSubject) {
        if (subjectId == null) {
            throw new IllegalArgumentException("Subject ID cannot be null");
        }
        updatedSubject.setId(subjectId); // Ensure the ID is set to the one from the path variable
        return subjectService.updateSubject(updatedSubject);
    }

    @DeleteMapping(path = "{subjectId}")
    public ResponseEntity<String> deleteStudent(@PathVariable("subjectId") Long subjectId,
            @RequestBody Subject updatedSubject) {
        boolean exists = subjectService.deleteSubject(subjectId);
        if (exists) {
            return ResponseEntity.status(HttpStatus.OK).body("Student deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with ID: " + subjectId + " not found");
        }
    }

}
