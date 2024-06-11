package com.uade.backendgestionbd2.repository;

import com.uade.backendgestionbd2.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    // Métodos personalizados si es necesario
    Optional<Users> findByEmail(String email);
}