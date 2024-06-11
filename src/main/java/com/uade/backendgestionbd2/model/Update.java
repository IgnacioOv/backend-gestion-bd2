package com.uade.backendgestionbd2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Document(collection = "updates")
public class Update {

    @Id
    private String update_id;

    @DBRef
    private Progress progress;

    private String user_id;
    private String description;
    private Date timestamp;
    private int progress_percentage;
    private int time_worked;

    public Update() {
    }

    public Update(Progress progress, String user_id, String description, Date timestamp, int progress_percentage, int time_worked) {
        this.progress = progress;
        this.user_id = user_id;
        this.description = description;
        this.timestamp = timestamp;
        this.progress_percentage = progress_percentage;
        this.time_worked = time_worked;
    }
}