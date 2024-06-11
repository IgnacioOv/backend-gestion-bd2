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
    private int project_id;

    private String name;
    private String description;
    private String start_date;
    private String end_date;
    private String status;


}
