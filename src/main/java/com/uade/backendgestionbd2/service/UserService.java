package com.uade.backendgestionbd2.service;

import com.uade.backendgestionbd2.exception.UserException;

import com.uade.backendgestionbd2.model.Users;
import com.uade.backendgestionbd2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Crear un usuario
    public Users createUser(Users user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    throw new UserException("User already exists");
                });
        return userRepository.save(user);
    }

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
    public List<Users> findUsersByProjectId(int projectId) {
        return userRepository.findUsersByProjectId(projectId)
                .orElseThrow(() -> new UserException("Users not found for projectId: " + projectId));
    }


    // get users by task id
    public List<Users> findUsersByTaskId(int taskId) {
        return userRepository.findUsersByTaskId(taskId)
                .orElseThrow(() -> new UserException("Users not found for taskId: " + taskId));
    }







}
