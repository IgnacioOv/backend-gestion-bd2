package com.uade.backendgestionbd2.service;

import com.uade.backendgestionbd2.exception.UserException;
import com.uade.backendgestionbd2.model.Roles;
import com.uade.backendgestionbd2.model.Users;
import com.uade.backendgestionbd2.repository.RolesRepository;
import com.uade.backendgestionbd2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository roleRepository; // Asumiendo que tienes un repositorio para los roles


    //Crear un usuario
    public void createUser(String username, String password, int roleId, String nombre, String email) {
        String passwordEncrypted = BCrypt.hashpw(password, BCrypt.gensalt());

        Roles role = roleRepository.findById(roleId)
                .orElseThrow(() -> new UserException("Role not found"));

        userRepository.findByEmail(username)
                .ifPresent(u -> {
                    throw new UserException("User already exists");
                });
        Users user = new Users(username, passwordEncrypted, role, nombre, email);
        userRepository.save(user);
    }


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
