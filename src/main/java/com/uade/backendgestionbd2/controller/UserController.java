package com.uade.backendgestionbd2.controller;

import com.uade.backendgestionbd2.exception.UserException;
import com.uade.backendgestionbd2.model.Users;
import com.uade.backendgestionbd2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // created user
    @PostMapping("/")
    public ResponseEntity<Object> createUser(@RequestBody Users user) {
        try {
            userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (UserException e) {
            // user exists
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    // delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    // get all users
    @GetMapping("/")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // get user by id
    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable int userId) {
        try {
            Users user = userService.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (UserException e) {
            String errorMessage = "User with ID " + userId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<Object> getUsersByProjectId(@PathVariable int projectId) {
        try {
            List<Users> users = userService.findUsersByProjectId(projectId);
            return ResponseEntity.ok(users);
        } catch (UserException e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<Object> getUsersByTaskId(@PathVariable int taskId) {
        try {
            List<Users> users = userService.findUsersByTaskId(taskId);
            return ResponseEntity.ok(users);
        } catch (UserException e) {
            String errorMessage =e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }


}