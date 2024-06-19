package com.uade.backendgestionbd2.repository;


import com.uade.backendgestionbd2.model.Activities;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ActivityRepository extends MongoRepository<Activities,Long> {

}
