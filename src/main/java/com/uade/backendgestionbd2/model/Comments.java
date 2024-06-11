package com.uade.backendgestionbd2.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

@Document(collection = "comments")
public class Comments {
    @Id
    private String comment_id; // Utiliza String para almacenar el ObjectId de MongoDB

    private String task_id;

    private String user_id;

    private String comment;

    private Date timestamp;

    // Constructores y getters/setters
    public Comments() {
    }

    public Comments(String task_id, String user_id, String comment, Date timestamp) {
        this.task_id = task_id;
        this.user_id = user_id;
        this.comment = comment;
        this.timestamp = timestamp;
    }


}