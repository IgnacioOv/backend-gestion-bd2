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

    //Eliminar un usuario
    public void deleteUser(int userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found"));
        userRepository.deleteById(userId);
    }


    // Ver todos los usuarios
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    // Ver un usuario por id
    public Users getUserById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found"));
    }




}
