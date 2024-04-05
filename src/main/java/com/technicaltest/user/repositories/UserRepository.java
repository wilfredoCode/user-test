package com.technicaltest.user.repositories;

import org.springframework.stereotype.Repository;

import com.technicaltest.user.models.entities.UserEntity;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID>{
    
}
