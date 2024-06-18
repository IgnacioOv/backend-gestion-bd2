package com.uade.backendgestionbd2.service;

import com.uade.backendgestionbd2.exception.ProjectAssignmentsException;
import com.uade.backendgestionbd2.model.ProjectAssignments;
import com.uade.backendgestionbd2.model.ProjectAssignmentsId;
import com.uade.backendgestionbd2.repository.ProjectAssignmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectAssignmentsService {

    @Autowired
    private ProjectAssignmentsRepository projectAssignmentsRepository;

    // Asignar un usuario a un proyecto
    public void assignmentResourceToProject(int projectId, int userId) {
        ProjectAssignmentsId assignmentsId = new ProjectAssignmentsId(projectId, userId);
        // Verificar si ya existe una asignación para evitar duplicados
        if (projectAssignmentsRepository.existsById(assignmentsId)) {
            throw new ProjectAssignmentsException("Assignment already exists");
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

        // Actualizar la asignación
        ProjectAssignments projectAssignments = existingAssignment.get();
        projectAssignmentsRepository.deleteById(oldAssignmentsId); // Eliminar la asignación antigua
        projectAssignments.setId(newAssignmentsId); // Actualizar la ID de la asignación
        projectAssignmentsRepository.save(projectAssignments); // Guardar la nueva asignación
    }
}

