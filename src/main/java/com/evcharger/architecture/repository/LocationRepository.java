package com.evcharger.architecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evcharger.architecture.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    
}
