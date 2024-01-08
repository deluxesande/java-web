package com.amigos.schoolmanagementsystem.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.amigos.schoolmanagementsystem.model.School;
import com.amigos.schoolmanagementsystem.repository.SchoolRepository;

import jakarta.transaction.Transactional;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    public School addSchool(School school) {
        Optional<School> schoolByName = schoolRepository.findSchoolByName(school.getName());
        if (schoolByName.isPresent()) {
            throw new NoSuchElementException("School name taken");
        }

        return schoolRepository.save(school);
    }

    @Transactional
    public School updateSchool(School updatedSchool) {
        Long schoolId = updatedSchool.getId();
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new NoSuchElementException("School with id " + schoolId + " does not exist"));

        Optional.ofNullable(updatedSchool.getName())
                .filter(name -> !name.equals(school.getName()))
                .ifPresent(school::setName);

        Optional.ofNullable(updatedSchool.getEmail())
                .filter(email -> !email.equals(school.getEmail()))
                .ifPresent(school::setEmail);

        Optional.ofNullable(updatedSchool.getDescription())
                .filter(description -> !description.equals(school.getDescription()))
                .ifPresent(school::setDescription);

        Optional.ofNullable(updatedSchool.getAddress())
                .filter(address -> !address.equals(school.getAddress()))
                .ifPresent(school::setAddress);

        return school;
    }

    public boolean deleteSchool(Long schoolId) {
        boolean exists = schoolRepository.existsById(schoolId);
        if (!exists) {
            throw new NoSuchElementException("School with id " + schoolId + " does not exist");
        }
        schoolRepository.deleteById(schoolId);
        return exists;
    }
}