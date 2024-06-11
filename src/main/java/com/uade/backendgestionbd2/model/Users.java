package com.uade.backendgestionbd2.model;

import jakarta.persistence.*;


//Lombok
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString


@Table(name = "Users")
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    private String username;

    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles role_id; // es un id de la tabla roles

    @ManyToMany
    @JoinTable(
            name = "taskAssignments",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private Set<Tasks> tasks = new HashSet<>();

    private String nombre;

    private String email;


    public Users() {
    }

    public Users(int user_id, String username, String password, Roles role_id, String nombre, String email) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.role_id = role_id;
        this.nombre = nombre;
        this.email = email;
    }

    public Users(String username, String password, Roles role_id, String nombre, String email) {
        this.username = username;
        this.password = password;
        this.role_id = role_id;
        this.nombre = nombre;
        this.email = email;
    }



}



