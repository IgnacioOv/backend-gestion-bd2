package com.uade.backendgestionbd2.repository;


import com.uade.backendgestionbd2.model.Activities;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ActivityRepository extends MongoRepository<Activities,Long> {

    @Query(value="{ 'task_id' : ?0 }")
    List<Activities> findByTaskId(int taskId);

    @Query(value="{_id : ?0}")
    Activities findByActivityId(String activityId);
}
