package com.uade.backendgestionbd2.model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "activities")
public class Activities {

    @Id
    private String progress_id;

    private String task_id;

    @DBRef
    private Set<String> updates_ids = new HashSet<>();

    public Activities() {
    }

    public Activities(String task_id) {
        this.task_id = task_id;
    }

}
