package com.amigos.schoolmanagementsystem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amigos.schoolmanagementsystem.model.Parent;
import com.amigos.schoolmanagementsystem.service.ParentService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(path = "api/v1/parent")
public class ParentController {
    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @GetMapping
    public List<Parent> getParents() {
        return parentService.getParents();
    }

    @PostMapping
    public Parent addNewParent(@RequestBody Parent parent) {
        return parentService.addNewParent(parent);
    }

    @PutMapping(path = "{parentId}")
    public Parent updateParent(@RequestBody Parent updatedParent) {
        return parentService.updateParent(updatedParent);
    }

    @DeleteMapping(path = "{parentId}")
    public boolean deleteParent(@PathParam("parentId") Long parentId, @RequestBody Parent updatedParent) {
        if (parentId == null) {
            throw new IllegalArgumentException("Parent ID cannot be null");
        }
        updatedParent.setId(parentId); // Ensure the ID is set to the one from the path variable
        return parentService.deleteParent(parentId);
    }
}
