package com.uade.backendgestionbd2.model;

import com.uade.backendgestionbd2.util.Status;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Date start_date;

    private Date end_date;

    public Tasks() {
    }

    public Tasks(Projects project, String name, String description, Status status, Date start_date, Date end_date) {
        this.project = project;
        this.name = name;
        this.description = description;
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;
    }

}
