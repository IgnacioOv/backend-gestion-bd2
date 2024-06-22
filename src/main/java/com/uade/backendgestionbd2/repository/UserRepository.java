package com.uade.backendgestionbd2.repository;

import com.uade.backendgestionbd2.model.Users;
import com.uade.backendgestionbd2.util.Roles;
import com.uade.backendgestionbd2.util.SkillLevel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    // Métodos personalizados si es necesario
    @Query("SELECT u FROM Users u WHERE u.email = :email")
    Optional<Users> findByEmail(@Param("email")String email);

    @Query(value = "SELECT u.*\n" +
            "FROM Users u\n" +
            "JOIN project_assignments pa ON u.user_id = pa.user_id\n" +
            "JOIN Projects p ON pa.project_id = p.project_id\n" +
            "WHERE p.project_id = :projectId;\n", nativeQuery = true)
    Optional<List<Users>> findUsersByProjectId(int projectId);

    @Query(value = "SELECT u.*" +
            "FROM Users u\n" +
            "JOIN Tasks t ON u.user_id = t.user_id\n" +
            "WHERE t.task_id = :taskId;\n", nativeQuery = true)
    Optional<Users> findUserByTaskId(int taskId);

    Optional<Users> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE Users u SET u.username = :username, u.userPassword = :userPassword, u.role = :role, u.name = :name, u.last_name = :lastName, u.email = :email, u.weekly_hours = :weeklyHours, u.skillLevel = :skillLevel WHERE u.user_id = :userId")
    void updateUser(@Param("username") String username, @Param("userPassword") String userPassword, @Param("role") Roles role, @Param("name") String name, @Param("lastName") String lastName, @Param("email") String email, @Param("weeklyHours") int weeklyHours, @Param("skillLevel") SkillLevel skillLevel, @Param("userId") int userId);
}

