package com.uade.backendgestionbd2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


//Lombok
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Entity
public class Roles {
    @Id
    private int role_id;

    private String role_name;

    public Roles() {
    }

    public Roles(int role_id, String roleName) {
        this.role_id = role_id;
        this.role_name = roleName;
    }

}
