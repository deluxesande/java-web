package com.amigos.schoolmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.amigos.schoolmanagementsystem.model.Teacher;
import com.amigos.schoolmanagementsystem.repository.TeacherRepository;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getTeachers() {
        return teacherRepository.findAll(); // SELECT * FROM teacher;
    }

    public Teacher addNewTeacher(Teacher teacher) {
        Optional<Teacher> teacherByEmail = teacherRepository.findTeacherByEmail(teacher.getEmail());
        if (teacherByEmail.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        return teacherRepository.save(teacher);
    }

    public boolean deleteTeacher(Long teacherId) {
        boolean exists = teacherRepository.existsById(teacherId);
        if (!exists) {
            throw new IllegalStateException("Teacher with id " + teacherId + " does not exist");
        }
        teacherRepository.deleteById(teacherId);

        return exists;
    }

    public Teacher updateTeacher(Teacher updatedTeacher) {
        Long teacherId = updatedTeacher.getId();
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalStateException("Teacher with id " + teacherId + " does not exist"));

        Optional.ofNullable(updatedTeacher.getFirstName())
                .filter(f -> !f.equals(teacher.getFirstName()))
                .ifPresent(teacher::setFirstName);

        Optional.ofNullable(updatedTeacher.getLastName())
                .filter(l -> !l.equals(teacher.getLastName()))
                .ifPresent(teacher::setLastName);

        Optional.ofNullable(updatedTeacher.getDob())
                .filter(d -> !d.equals(teacher.getDob()))
                .ifPresent(teacher::setDob);

        Optional.ofNullable(updatedTeacher.getSalary())
                .filter(d -> !d.equals(teacher.getSalary()))
                .ifPresent(teacher::setSalary);

        Optional.ofNullable(updatedTeacher.getCode())
                .filter(c -> !c.equals(teacher.getCode()))
                .ifPresent(teacher::setCode);

        Optional.ofNullable(updatedTeacher.getEmail())
                .filter(StringUtils::hasText)
                .filter(e -> !e.equals(teacher.getEmail()))
                .ifPresent(e -> {
                    Optional<Teacher> teacherByEmail = teacherRepository.findTeacherByEmail(e);
                    if (teacherByEmail.isPresent()) {
                        throw new DataIntegrityViolationException("Email taken");
                    }
                    teacher.setEmail(e);
                });

        return teacherRepository.save(teacher);
    }
}
