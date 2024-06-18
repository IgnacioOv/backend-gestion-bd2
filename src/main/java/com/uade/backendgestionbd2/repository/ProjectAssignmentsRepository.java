package com.uade.backendgestionbd2.repository;

import com.uade.backendgestionbd2.model.ProjectAssignments;
import com.uade.backendgestionbd2.model.ProjectAssignmentsId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectAssignmentsRepository extends JpaRepository<ProjectAssignments, ProjectAssignmentsId> {
    // metodos personalizados

}
