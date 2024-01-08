package com.amigos.schoolmanagementsystem.service;

import java.util.Optional;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.amigos.schoolmanagementsystem.model.Subject;
import com.amigos.schoolmanagementsystem.repository.SubjectRepository;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getSubjects() {
        return subjectRepository.findAll(); // SELECT * FROM subject;
    }

    public Subject addNewSubject(Subject subject) {
        Optional<Subject> subjectOptional = subjectRepository.findSubjectByName(subject.getName());
        if (subjectOptional.isPresent()) {
            throw new DataIntegrityViolationException("Subject already exists");
        }
        return subjectRepository.save(subject);
    }

    public boolean deleteSubject(Long subjectId) {
        boolean exists = subjectRepository.existsById(subjectId);
        if (!exists) {
            throw new IllegalStateException("Subject with id " + subjectId + " does not exist");
        }
        subjectRepository.deleteById(subjectId);

        return exists;
    }

    public Subject updateSubject(Subject updatedSubject) {
        Long subjectId = updatedSubject.getId();
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalStateException("Subject with id " + subjectId + " does not exist"));

        Optional.ofNullable(updatedSubject.getName())
                .filter(n -> !n.equals(subject.getName()))
                .ifPresent(subject::setName);

        Optional.ofNullable(updatedSubject.getDescription())
                .filter(d -> !d.equals(subject.getDescription()))
                .ifPresent(subject::setDescription);

        return subjectRepository.save(subject);
    }
}
