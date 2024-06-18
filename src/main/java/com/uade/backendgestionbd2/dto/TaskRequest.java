package com.uade.backendgestionbd2.dto;

import com.uade.backendgestionbd2.util.SkillLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TaskRequest {

    private int id;
    private String name;
    private String description;
    private int project;
    private SkillLevel skillLevel;
    private LocalDate startDate;
    private LocalDate endDate;
    private int status;
    private int user;


    public TaskRequest(String name, String description, int project, SkillLevel skillLevel, LocalDate startDate, LocalDate endDate, int status, int user) {
        this.name = name;
        this.description = description;
        this.project = project;
        this.skillLevel = skillLevel;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.user = user;
    }

}
