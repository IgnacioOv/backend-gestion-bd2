package com.uade.backendgestionbd2.service;


import com.uade.backendgestionbd2.dto.ProjectRequest;
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

    public Projects addProject(ProjectRequest project) {

        Projects newProject = new Projects();
        newProject.setName(project.getName());
        newProject.setDescription(project.getDescription());
        newProject.setStartDate(project.getStartDate());
        newProject.setEndDate(project.getEndDate());
        newProject.setStatus(project.getStatus());
        newProject.setWeeklyHours(project.getWeeklyHours());

        return projectRepository.save(newProject);


    }

    public void deleteProject(int projectId) {
        projectRepository.deleteById(projectId);
    }

    public List<Projects> getAllProjects() {
        return projectRepository.findAll();
    }

    public Projects updateProject(int projectId, ProjectRequest project) {

        Projects projectToUpdate = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectException("Project not found"));

        projectToUpdate.setName(project.getName());
        projectToUpdate.setDescription(project.getDescription());
        projectToUpdate.setStartDate(project.getStartDate());
        projectToUpdate.setEndDate(project.getEndDate());
        projectToUpdate.setStatus(project.getStatus());
        projectToUpdate.setWeeklyHours(project.getWeeklyHours());

        return projectRepository.save(projectToUpdate);

    }
}
