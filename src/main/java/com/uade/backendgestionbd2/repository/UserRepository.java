package com.uade.backendgestionbd2.repository;

import com.uade.backendgestionbd2.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    // Métodos personalizados si es necesario
    @Query("SELECT u FROM Users u WHERE u.email = :email")
    Optional<Users> findByEmail(String email);

    @Query(value = "SELECT u.*\n" +
            "FROM Users u\n" +
            "JOIN projectassignments pa ON u.user_id = pa.user_id\n" +
            "JOIN Projects p ON pa.project_id = p.project_id\n" +
            "WHERE p.project_id = :projectId;\n", nativeQuery = true)
    Optional<List<Users>> findUsersByProjectId(int projectId);

    @Query(value = "SELECT u.*" +
            "FROM Users u\n" +
            "JOIN taskassignments ta ON u.user_id = ta.user_id\n" +
            "JOIN Tasks t ON ta.task_id = t.task_id\n" +
            "WHERE t.task_id = :taskId;\n", nativeQuery = true)
    Optional<List<Users>> findUsersByTaskId(int taskId);

}