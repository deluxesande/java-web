package com.amigos.schoolmanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amigos.schoolmanagementsystem.model.Parent;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    @Query("SELECT p FROM Parent p WHERE p.email = ?1")
    Optional<Parent> findParentByEmail(String email);

}
