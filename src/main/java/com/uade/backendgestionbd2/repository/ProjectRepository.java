package com.uade.backendgestionbd2.repository;

import com.uade.backendgestionbd2.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectRepository extends JpaRepository<Projects, Long> {

}
