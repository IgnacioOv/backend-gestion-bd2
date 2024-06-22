package com.uade.backendgestionbd2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivitiesRequest {
    private int task_id;
    private String user_id;
    private String description;
    private Date timestamp;
    private int progress_percentage;
    private int time_worked;

    public ActivitiesRequest(int task_id, String user_id, String description, int progress_percentage, int time_worked) {
        this.task_id = task_id;
        this.user_id = user_id;
        this.description = description;
        this.timestamp = new Date();
        this.progress_percentage = progress_percentage;
        this.time_worked = time_worked;
    }



}
