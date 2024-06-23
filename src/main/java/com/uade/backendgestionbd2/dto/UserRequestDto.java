package com.uade.backendgestionbd2.dto;


import com.uade.backendgestionbd2.model.Users;
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
    private int weeklyHours;
    private SkillLevel skillLevel;


    public UserRequestDto userToDto(Users user) {
        UserRequestDto userDto = new UserRequestDto();
        userDto.setId(user.getUser_id());
        userDto.setFirstname(user.getName());
        userDto.setLastname(user.getLast_name());
        userDto.setEmail(user.getEmail());
        userDto.setWeeklyHours(user.getWeekly_hours());
        userDto.setSkillLevel(user.getSkillLevel());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole());
        return userDto;
    }

}


