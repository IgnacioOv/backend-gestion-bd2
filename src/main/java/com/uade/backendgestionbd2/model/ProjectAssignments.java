package com.uade.backendgestionbd2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
//@Table(name = "project_assignments")
public class ProjectAssignments {
    @Id
    private ProjectAssignmentsId id;

    public ProjectAssignments() {
    }

    public ProjectAssignments(int projectId, int userId) {
        this.id = new ProjectAssignmentsId(projectId, userId);
    }

}
