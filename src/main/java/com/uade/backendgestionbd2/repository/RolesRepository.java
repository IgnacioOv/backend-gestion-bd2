package com.uade.backendgestionbd2.repository;

import com.uade.backendgestionbd2.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
    // Métodos personalizados si es necesario
}
