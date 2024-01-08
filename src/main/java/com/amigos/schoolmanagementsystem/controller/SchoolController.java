package com.amigos.schoolmanagementsystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.amigos.schoolmanagementsystem.model.School;
import com.amigos.schoolmanagementsystem.service.SchoolService;

@RestController
@RequestMapping(path = "api/v1/school")
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetExchange
    public List<School> getAllSchools() {
        return schoolService.getAllSchools();
    }

    @PostMapping
    public School addSchool(@RequestBody School school) {
        return schoolService.addSchool(school);
    }

    @PutMapping(path = "{schoolId}")
    public School updateSchool(@PathVariable("schoolId") Long schoolId, @RequestBody School updatedSchool) {
        if (schoolId == null) {
            throw new IllegalArgumentException("School ID cannot be null");
        }

        return schoolService.updateSchool(updatedSchool);
    }

    @DeleteMapping(path = "{schoolId}")
    public ResponseEntity<String> deleteSchool(@PathVariable("schoolId") Long schoolId) {
        boolean exists = schoolService.deleteSchool(schoolId);
        if (exists) {
            return ResponseEntity.status(HttpStatus.OK).body("School deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("School with ID: " + schoolId + " not found");
        }
    }
}
