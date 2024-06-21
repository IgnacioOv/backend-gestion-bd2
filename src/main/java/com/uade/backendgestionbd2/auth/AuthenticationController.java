package com.uade.backendgestionbd2.auth;


import com.uade.backendgestionbd2.dto.AuthenticationRequest;
import com.uade.backendgestionbd2.dto.AuthenticationResponse;
import com.uade.backendgestionbd2.dto.RegisterRequest;
import com.uade.backendgestionbd2.model.Users;
import com.uade.backendgestionbd2.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest request) {
        Users user =service.register(request);
        return ResponseEntity.ok(user);

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}