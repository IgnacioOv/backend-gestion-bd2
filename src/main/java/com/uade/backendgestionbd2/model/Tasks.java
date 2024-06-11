package com.uade.backendgestionbd2.model;

import jakarta.persistence.*;

import java.util.Date;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private String status;

    private Date start_date;

    private Date end_date;

    public Tasks() {
    }

    public Tasks(Projects project, String name, String description, Users user, String status, Date start_date, Date end_date) {
        this.project = project;
        this.name = name;
        this.description = description;
        this.user = user;
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;
    }

}
