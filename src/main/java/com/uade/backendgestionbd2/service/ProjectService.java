package com.uade.backendgestionbd2.service;


import com.uade.backendgestionbd2.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

}
