package com.uade.backendgestionbd2.dto;


import com.uade.backendgestionbd2.util.Roles;
import com.uade.backendgestionbd2.util.SkillLevel;
import lombok.Data;

@Data
public class UserUpdateDto {
    private int userId;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String userPassword;
    private String weeklyHours;
    private SkillLevel skillLevel;
    private Roles role;
}
