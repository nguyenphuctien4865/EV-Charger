package com.evcharger.architecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evcharger.architecture.entity.PowerOutput;

public interface PowerOutputRepository  extends JpaRepository<PowerOutput, Long> {
    
    
}
