package com.uade.backendgestionbd2.model;

import com.uade.backendgestionbd2.util.Roles;
import jakarta.persistence.*;


//Lombok
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Check;

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

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles role; // es un enum Roles

    @ManyToMany
    @JoinTable(
            name = "taskAssignments",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private Set<Tasks> tasks = new HashSet<>();

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "email")
    @Check(constraints = "email ~* '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$'")
    private String email;

    @Column(name = "weekly_hours")
    private int weekly_hours;

    public Users() {
    }

    public Users(int user_id, String username, String password, Roles role, String nombre, String email) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = nombre;
        this.email = email;
    }

    public Users(String username, String password, Roles role, String nombre, String email) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = nombre;
        this.email = email;
    }



}



