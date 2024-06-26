package com.uade.backendgestionbd2.service;


import com.uade.backendgestionbd2.dto.AuthenticationRequest;
import com.uade.backendgestionbd2.dto.AuthenticationResponse;
import com.uade.backendgestionbd2.dto.RegisterRequest;
import com.uade.backendgestionbd2.config.JwtService;
import com.uade.backendgestionbd2.model.Users;
import com.uade.backendgestionbd2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public Users register(RegisterRequest request) {
        Users user = Users.builder().name(request.getFirstname()).last_name(request.getLastname()).email(request.getEmail()).userPassword(this.passwordEncoder.encode(request.getUserPassword())).role(request.getRole()).username(request.getUsername()).weekly_hours(request.getWeeklyHours()).skillLevel(request.getSkillLevel()).build();
        repository.save(user);
        return user;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getUserPassword()));

        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken((UserDetails) user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}
