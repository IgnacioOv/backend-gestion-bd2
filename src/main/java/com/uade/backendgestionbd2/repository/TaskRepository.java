package com.uade.backendgestionbd2.repository;

import com.uade.backendgestionbd2.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Tasks, Integer> {

    // si es necesario agregar metodos personalizados para la entidad Tasks

    @Query(value = "SELECT * FROM Tasks t WHERE t.name = :name AND t.project_id = :project_id", nativeQuery = true)
    Optional<Tasks> findByNameAndProject(String name, int project_id);

    @Query(value = "SELECT * FROM Tasks t WHERE t.project_id = :project_id", nativeQuery = true)
    Optional<List<Tasks>> findAllByProject(int project_id);



}
