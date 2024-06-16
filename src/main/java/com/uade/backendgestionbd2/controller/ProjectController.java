package com.uade.backendgestionbd2.controller;


import com.uade.backendgestionbd2.model.Projects;
import com.uade.backendgestionbd2.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjectController {


    @Autowired
    private final ProjectService projectService;


    @GetMapping("/{userId}")
    public List<Projects> getProjectsByUserId(@PathVariable int userId) {
        return projectService.getProjectsByUserId(userId);
    }


}
