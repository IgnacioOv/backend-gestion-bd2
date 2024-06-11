package com.uade.backendgestionbd2.model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "progress")
public class Progress {

    @Id
    private String progress_id;

    private String task_id;

    @DBRef
    private Set<Update> updates = new HashSet<>();

    public Progress() {
    }

    public Progress(String task_id) {
        this.task_id = task_id;
    }

}
