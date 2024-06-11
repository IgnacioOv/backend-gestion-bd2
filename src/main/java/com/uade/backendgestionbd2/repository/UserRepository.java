package com.uade.backendgestionbd2.repository;

import com.uade.backendgestionbd2.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepository extends JpaRepository<Users, Integer> {
    //Methods
}