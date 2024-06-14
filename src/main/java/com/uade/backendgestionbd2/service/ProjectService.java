package com.uade.backendgestionbd2.service;


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

    public List<Projects> getProjectsByUserId(Long userId) {
        return projectRepository.findProjectsByUserId(userId);
    }

}
