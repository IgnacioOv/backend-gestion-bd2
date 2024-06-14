package com.uade.backendgestionbd2.repository;

import com.uade.backendgestionbd2.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Projects, Long> {


    @Query(value = "SELECT p.* FROM Projects p " +
            "INNER JOIN ProjectAssignments pa ON p.project_id = pa.project_id " +
            "WHERE pa.user_id = :userId", nativeQuery = true)
    List<Projects> findProjectsByUserId(Long userId);
}

