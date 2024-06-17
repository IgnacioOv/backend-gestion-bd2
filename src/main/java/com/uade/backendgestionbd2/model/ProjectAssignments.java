package com.uade.backendgestionbd2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "ProjectAssignments")
public class ProjectAssignments {
    @Id
    private String project_id;
    @Id
    private String user_id;

    public ProjectAssignments() {
    }

    public ProjectAssignments(String project_id, String user_id) {
        this.project_id = project_id;
        this.user_id = user_id;
    }

}
