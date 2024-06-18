package com.uade.backendgestionbd2.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
@Getter
@Setter

@Embeddable
public class ProjectAssignmentsId implements Serializable {

    private int projectId;
    private int userId;

    public ProjectAssignmentsId() {}

    public ProjectAssignmentsId(int projectId, int userId) {
        this.projectId = projectId;
        this.userId = userId;
    }

    // Getters and setters, equals() and hashCode() methods
    // It's important to override equals and hashCode properly for composite keys

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectAssignmentsId that = (ProjectAssignmentsId) o;
        return Objects.equals(projectId, that.projectId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, userId);
    }
}
