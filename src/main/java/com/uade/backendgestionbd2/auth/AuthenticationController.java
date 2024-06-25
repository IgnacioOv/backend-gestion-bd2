package com.uade.backendgestionbd2.auth;


import com.uade.backendgestionbd2.dto.AuthenticationRequest;
import com.uade.backendgestionbd2.dto.AuthenticationResponse;
import com.uade.backendgestionbd2.dto.RegisterRequest;
import com.uade.backendgestionbd2.model.Users;
import com.uade.backendgestionbd2.service.AuthenticationService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody RegisterRequest request) {
        Users user = service.register(request);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequest request) {
        Map<String, String> badResponse = new HashMap<>();
        try {
            return ResponseEntity.ok(service.authenticate(request));
        }
        catch (Exception e) {
            badResponse.put("message", "Error: " + e.getMessage());
            return ResponseEntity.badRequest().body(badResponse);
        }
    }
}