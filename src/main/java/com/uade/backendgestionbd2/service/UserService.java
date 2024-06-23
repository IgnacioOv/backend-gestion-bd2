package com.uade.backendgestionbd2.service;

import com.uade.backendgestionbd2.dto.UserRequestDto;
import com.uade.backendgestionbd2.dto.UserUpdateDto;
import com.uade.backendgestionbd2.exception.UserException;

import com.uade.backendgestionbd2.model.Users;
import com.uade.backendgestionbd2.repository.UserRepository;
import org.apache.catalina.User;
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
    public List<UserRequestDto> getAllUsers() {
        List<Users> allUsers = userRepository.findAll();
        List<UserRequestDto> dtoUsers = new ArrayList<>();
        for(Users user : allUsers){
            dtoUsers.add(new UserRequestDto().userToDto(user));
        }
        return dtoUsers;
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
            dtoUsers.add(new UserRequestDto().userToDto(user));
        }

        return dtoUsers;

    }


    // get users by task id
    public UserRequestDto findUsersByTaskId(int taskId) {
        return new UserRequestDto().userToDto(userRepository.findUserByTaskId(taskId)
                .orElseThrow(() -> new UserException("Users not found for taskId: " + taskId)));
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
