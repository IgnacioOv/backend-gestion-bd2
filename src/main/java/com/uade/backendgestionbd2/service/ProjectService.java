package com.uade.backendgestionbd2.service;


import com.uade.backendgestionbd2.exception.ProjectException;
import com.uade.backendgestionbd2.model.Projects;
import com.uade.backendgestionbd2.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectService {

    @Autowired
    private final ProjectRepository projectRepository;

    public List<Projects> getProjectsByUserId(int userId) {
        return projectRepository.findProjectsByUserId(userId);
    }

    public Projects getProjectById(int projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectException("Project not found"));
    }

}
