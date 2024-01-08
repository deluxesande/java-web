package com.amigos.schoolmanagementsystem.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.amigos.schoolmanagementsystem.model.Classroom;
import com.amigos.schoolmanagementsystem.repository.ClassroomRepository;

@Service
public class ClassroomService {
    private final ClassroomRepository classroomRepository;

    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public List<Classroom> getClassrooms() {
        return classroomRepository.findAll(); // SELECT * FROM classroom;
    }

    public Classroom addNewClassroom(Classroom classroom) {
        Optional<Classroom> classroomByName = classroomRepository.findClassroomByName(classroom.getName());
        if (classroomByName.isPresent()) {
            throw new DataIntegrityViolationException("Name taken");
        }
        return classroomRepository.save(classroom);
    }

    public boolean deleteClassroom(Long classroomId) {
        boolean exists = classroomRepository.existsById(classroomId);
        if (!exists) {
            throw new NoSuchElementException("Classroom with id " + classroomId + " does not exist");
        }
        classroomRepository.deleteById(classroomId);

        return exists;
    }

    @Transactional
    public Classroom updateClassroom(Classroom updatedClassroom) {
        Long classroomId = updatedClassroom.getId();
        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new NoSuchElementException("Classroom with id " + classroomId + " does not exist"));

        Optional.ofNullable(updatedClassroom.getName())
                .filter(StringUtils::hasText)
                .filter(f -> !f.equals(classroom.getName()))
                .ifPresent(classroom::setName);

        Optional.ofNullable(updatedClassroom.getDescription())
                .filter(StringUtils::hasText)
                .filter(l -> !l.equals(classroom.getDescription()))
                .ifPresent(classroom::setDescription);

        return classroom;
    }
}
