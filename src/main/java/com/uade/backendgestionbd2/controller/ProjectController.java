package com.uade.backendgestionbd2.controller;


import com.uade.backendgestionbd2.dto.ProjectRequest;
import com.uade.backendgestionbd2.exception.ProjectException;
import com.uade.backendgestionbd2.model.Projects;
import com.uade.backendgestionbd2.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjectController {

    @Autowired
    private final ProjectService projectService;

    @GetMapping("/all")
    public List<Projects> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/user/{userId}")
    public List<Projects> getProjectsByUserId(@PathVariable int userId) {
        return projectService.getProjectsByUserId(userId);
    }

    @GetMapping("/{projectId}")
    public Projects getProjectById(@PathVariable int projectId) {
        return projectService.getProjectById(projectId);
    }

    @PostMapping("/add")
    public Projects addProject(@RequestBody ProjectRequest project) {
        return projectService.addProject(project);
    }

    @SuppressWarnings("rawtypes")
    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity deleteProject(@PathVariable("projectId") int projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.ok(Collections.singletonMap("response", "project deleted successfully"));
    }

    @SuppressWarnings("rawtypes")
    @PutMapping("/update/{projectId}")
    public ResponseEntity updateProject(@PathVariable("projectId") int projectId, @RequestBody ProjectRequest project) {
        projectService.updateProject(projectId, project);
        return ResponseEntity.ok(Collections.singletonMap("response", "project updated successfully"));
    }
}
