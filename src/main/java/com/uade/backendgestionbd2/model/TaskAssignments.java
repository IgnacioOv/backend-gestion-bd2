package com.uade.backendgestionbd2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TaskAssignments")
public class TaskAssignments {
    @Id
    private String task_id;
    @Id
    private String user_id;

    public TaskAssignments() {
    }

    public TaskAssignments(String task_id, String user_id) {
        this.task_id = task_id;
        this.user_id = user_id;
    }
}
