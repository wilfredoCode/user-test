package com.technicaltest.user.repositories;

import org.springframework.stereotype.Repository;
import com.technicaltest.user.models.entities.PhoneEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, UUID>{
    
}
