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

    @Query("SELECT u FROM Users u JOIN ProjectsAssignments pa ON u.user_id = pa.user_id WHERE ta.project_id = :projectId")
    Optional<List<Users>> findUsersByProjectId(int projectId);

    @Query("SELECT u FROM Users u JOIN u.tasks t WHERE t.task_id = :taskId")
    Optional<List<Users>> findUsersByTaskId(int taskId);

}