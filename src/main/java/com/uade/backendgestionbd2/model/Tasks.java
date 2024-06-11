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

}
