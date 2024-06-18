package com.uade.backendgestionbd2.model;

import com.uade.backendgestionbd2.util.SkillLevel;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Entity
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int task_id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Projects project;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private String name;

    private String description;

    private int status;

    private LocalDate start_date;

    private LocalDate end_date;

    @Enumerated(EnumType.STRING)
    private SkillLevel skillLevel;

    public Tasks() {
    }

    public Tasks(Projects project, String name, String description,SkillLevel skillLevel, int status, LocalDate start_date, LocalDate end_date) {
        this.project = project;
        this.name = name;
        this.description = description;
        this.skillLevel = skillLevel;
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;
    }

}
