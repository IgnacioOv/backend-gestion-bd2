package com.uade.backendgestionbd2.service;

import com.uade.backendgestionbd2.exception.ProjectAssignmentsException;
import com.uade.backendgestionbd2.exception.TaskException;
import com.uade.backendgestionbd2.model.ProjectAssignments;
import com.uade.backendgestionbd2.model.ProjectAssignmentsId;
import com.uade.backendgestionbd2.model.Projects;
import com.uade.backendgestionbd2.repository.ProjectAssignmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectAssignmentsService {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectAssignmentsRepository projectAssignmentsRepository;
    @Autowired
    private UserService userService;

    // Asignar un usuario a un proyecto
    public void assignmentResourceToProject(int projectId, int userId) {
        ProjectAssignmentsId assignmentsId = new ProjectAssignmentsId(projectId, userId);
        // Verificar si ya existe una asignación para evitar duplicados
        if (projectAssignmentsRepository.existsById(assignmentsId)) {
            throw new ProjectAssignmentsException("Assignment already exists");
        }

        // recuperamos las horas del proyecto
        int hours = projectService.getProjectById(projectId).getWeeklyHours();
        // todos los proyectos del usuario
        List<Projects> projects = projectService.getProjectsByUserId(userId);
        // horas del usuario por semana
        int user_hours = userService.getUserById(userId).getWeekly_hours();
        // todas las proyectos del usuario
        //sumas horas
        int sum = projects.stream()
                .map(Projects::getWeeklyHours)
                .reduce(0, Integer::sum);
        if (sum + hours > user_hours) {
            throw new ProjectAssignmentsException("User does not have enough hours");
        }
        ProjectAssignments projectAssignments = new ProjectAssignments(projectId, userId);
        projectAssignmentsRepository.save(projectAssignments);
    }

    // Eliminar una asignación de usuario a proyecto
    public void deleteAssignment(int projectId, int userId) {
        ProjectAssignmentsId assignmentsId = new ProjectAssignmentsId(projectId, userId);
        if (projectAssignmentsRepository.existsById(assignmentsId)) {
            projectAssignmentsRepository.deleteById(assignmentsId);
        } else {
            throw new ResourceNotFoundException("Assignment not found");
        }
    }

    // Modificar la asignación de un usuario a un proyecto
    public void modifyUserAssignment(int projectId, int userId, int newUserId) {
        ProjectAssignmentsId oldAssignmentsId = new ProjectAssignmentsId(projectId, userId);
        ProjectAssignmentsId newAssignmentsId = new ProjectAssignmentsId(projectId, newUserId);

        // Verificar si la asignación original existe
        Optional<ProjectAssignments> existingAssignment = projectAssignmentsRepository.findById(oldAssignmentsId);
        if (existingAssignment.isEmpty()) {
            throw new ResourceNotFoundException("Assignment not found");
        }

        // Verificar si la nueva asignación ya existe para evitar duplicados
        if (projectAssignmentsRepository.existsById(newAssignmentsId)) {
            throw new ProjectAssignmentsException("New assignment already exists");
        }

        // recuperamos las horas del proyecto
        int hours = projectService.getProjectById(projectId).getWeeklyHours();
        // todos los proyectos del usuario
        List<Projects> projects = projectService.getProjectsByUserId(userId);
        // horas del usuario por semana
        int user_hours = userService.getUserById(userId).getWeekly_hours();
        // todas las proyectos del usuario
        //sumas horas
        int sum = projects.stream()
                .map(Projects::getWeeklyHours)
                .reduce(0, Integer::sum);
        if (sum + hours > user_hours) {
            throw new ProjectAssignmentsException("User does not have enough hours");
        }

        // Actualizar la asignación
        ProjectAssignments projectAssignments = existingAssignment.get();
        projectAssignmentsRepository.deleteById(oldAssignmentsId); // Eliminar la asignación antigua
        projectAssignments.setId(newAssignmentsId); // Actualizar la ID de la asignación
        projectAssignmentsRepository.save(projectAssignments); // Guardar la nueva asignación
    }
}

