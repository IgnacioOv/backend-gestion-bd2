package com.uade.backendgestionbd2.model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

@Document(collection = "comments")
public class Comments {
    @Id
    private String id;

    private String task_id;

    private String user_id;

    private String comment;

    private LocalDate timestamp;

    // Constructores y getters/setters
    public Comments() {
    }

    public Comments(String task_id, String user_id, String comment, LocalDate timestamp) {
        this.task_id = task_id;
        this.user_id = user_id;
        this.comment = comment;
        this.timestamp = timestamp;
    }


}