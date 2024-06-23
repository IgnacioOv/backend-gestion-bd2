package com.uade.backendgestionbd2.service;

import com.uade.backendgestionbd2.dto.UserRequestDto;
import com.uade.backendgestionbd2.dto.UserUpdateDto;
import com.uade.backendgestionbd2.exception.UserException;

import com.uade.backendgestionbd2.model.Users;
import com.uade.backendgestionbd2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //Eliminar un usuario
    public void deleteUser(int userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found"));
        userRepository.deleteById(userId);
    }


    // get all users
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    // get user by id
    public Users getUserById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found"));
    }

    // get users by project id
    public List<UserRequestDto> findUsersByProjectId(int projectId) {
        List<Users> users = userRepository.findUsersByProjectId(projectId)
                .orElseThrow(() -> new UserException("Users not found for projectId: " + projectId));

        List<UserRequestDto> dtoUsers = new ArrayList<>();

        for(Users user : users){
            UserRequestDto userDto = new UserRequestDto();
            userDto.setId(user.getUser_id());
            userDto.setFirstname(user.getName());
            userDto.setLastname(user.getLast_name());
            userDto.setEmail(user.getEmail());
            userDto.setWeekyHours(user.getWeekly_hours());
            userDto.setSkillLevel(user.getSkillLevel());
            userDto.setUsername(user.getUsername());
            userDto.setRole(user.getRole());
            dtoUsers.add(userDto);
        }

        return dtoUsers;

    }


    // get users by task id
    public Users findUsersByTaskId(int taskId) {
        return userRepository.findUserByTaskId(taskId)
                .orElseThrow(() -> new UserException("Users not found for taskId: " + taskId));
    }

    public void updateUser(UserUpdateDto user){
        Users updatedUser = new Users();
        updatedUser.setUser_id(user.getUserId());
        updatedUser.setName(user.getFirstname());
        updatedUser.setLast_name(user.getLastname());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        updatedUser.setWeekly_hours(Integer.parseInt(user.getWeeklyHours()));
        updatedUser.setSkillLevel(user.getSkillLevel());
        updatedUser.setUsername(user.getUsername());
        updatedUser.setRole(user.getRole());
        userRepository.updateUser(updatedUser.getUsername(), updatedUser.getUserPassword(), updatedUser.getRole(), updatedUser.getName(), updatedUser.getLast_name(), updatedUser.getEmail(), updatedUser.getWeekly_hours(), updatedUser.getSkillLevel(), updatedUser.getUser_id());
    }




























}
