package com.uade.backendgestionbd2.config;
import com.uade.backendgestionbd2.util.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/auth/register").hasAnyAuthority(Roles.Admin.name())
                        .requestMatchers("/projects/add", "/projects/delete/**", "/projects/update/**").hasAnyAuthority(Roles.Admin.name())
                        .requestMatchers("/tasks/add", "/tasks/delete/**", "/tasks/update/**").hasAnyAuthority(Roles.Admin.name())
                        .requestMatchers("/project-assignment/assign", "/project-assignment/delete", "/project-assignment/update").hasAnyAuthority(Roles.Admin.name())
                        .requestMatchers("/tasks/**").hasAnyAuthority(Roles.Admin.name(), Roles.Employee.name())
                        .requestMatchers("/auth/authenticate").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
