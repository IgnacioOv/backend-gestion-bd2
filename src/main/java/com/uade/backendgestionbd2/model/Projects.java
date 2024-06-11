package com.uade.backendgestionbd2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//Lombok
@Getter
@Setter
@ToString


@Entity
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projedct_id;

    private String name;

    private String description;

    private String start_date;

    private String end_date;

    private String status;

    public Projects() {
    }

    public Projects( String name, String description, String start_date, String end_date, String status) {
        this.name = name;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
    }




}
