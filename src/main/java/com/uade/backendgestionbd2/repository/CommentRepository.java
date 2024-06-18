package com.uade.backendgestionbd2.repository;

import com.uade.backendgestionbd2.model.Comments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends MongoRepository<Comments, String> {

    // Método personalizado
    @Query(value = "{'task_id': ?0}")
    Optional<List<Comments>> findAllByTask(String taskId);


}
