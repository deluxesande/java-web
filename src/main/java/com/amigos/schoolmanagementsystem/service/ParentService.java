package com.amigos.schoolmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.amigos.schoolmanagementsystem.model.Parent;
import com.amigos.schoolmanagementsystem.model.Student;
import com.amigos.schoolmanagementsystem.repository.ParentRepository;

@Service
public class ParentService {
    private final ParentRepository parentRepository;

    public ParentService(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    public List<Parent> getParents() {
        return parentRepository.findAll(); // SELECT * FROM parent;
    }

    public Parent addNewParent(Parent parent) {
        Optional<Parent> parentByEmail = parentRepository.findParentByEmail(parent.getEmail());
        if (parentByEmail.isPresent()) {
            throw new DataIntegrityViolationException("Email taken");
        }
        return parentRepository.save(parent);
    }

    public boolean deleteParent(Long parentId) {
        boolean exists = parentRepository.existsById(parentId);
        if (!exists) {
            throw new IllegalStateException("Parent with id " + parentId + " does not exist");
        }
        parentRepository.deleteById(parentId);

        return exists;
    }

    public Parent updateParent(Parent updatedParent) {
        Long parentId = updatedParent.getId();
        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(() -> new IllegalStateException("Parent with id " + parentId + " does not exist"));

        Optional.ofNullable(updatedParent.getFirstName())
                .filter(f -> !f.equals(parent.getFirstName()))
                .ifPresent(parent::setFirstName);

        Optional.ofNullable(updatedParent.getLastName())
                .filter(l -> !l.equals(parent.getLastName()))
                .ifPresent(parent::setLastName);

        Optional.ofNullable(updatedParent.getDob())
                .filter(d -> !d.equals(parent.getDob()))
                .ifPresent(parent::setDob);

        Optional.ofNullable(updatedParent.getOccupation())
                .filter(o -> !o.equals(parent.getOccupation()))
                .ifPresent(parent::setOccupation);

        Optional.ofNullable(updatedParent.getAddress())
                .filter(a -> !a.equals(parent.getAddress()))
                .ifPresent(parent::setAddress);

        Optional.ofNullable(updatedParent.getPhone())
                .filter(p -> !p.equals(parent.getPhone()))
                .ifPresent(parent::setPhone);

        Optional.ofNullable(updatedParent.getDob())
                .filter(d -> !d.equals(parent.getDob()))
                .ifPresent(parent::setDob);

        Optional.ofNullable(updatedParent.getEmail())
                .filter(StringUtils::hasText)
                .filter(e -> !e.equals(parent.getEmail()))
                .ifPresent(e -> {
                    Optional<Parent> parentByEmail = parentRepository.findParentByEmail(e);
                    if (parentByEmail.isPresent()) {
                        throw new DataIntegrityViolationException("Email taken");
                    }
                    parent.setEmail(e);
                });

        return parentRepository.save(parent);
    }
}
