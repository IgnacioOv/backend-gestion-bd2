package com.uade.backendgestionbd2.dto;


import com.uade.backendgestionbd2.util.Roles;
import com.uade.backendgestionbd2.util.SkillLevel;
import lombok.Data;

@Data
public class UserRequestDto {
    private int id;
    private String username;
    private Roles role;
    private String firstname;
    private String lastname;
    private String email;
    private int weekyHours;
    private SkillLevel skillLevel;
}

