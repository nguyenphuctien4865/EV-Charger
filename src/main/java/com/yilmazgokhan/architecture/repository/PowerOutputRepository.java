package com.yilmazgokhan.architecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yilmazgokhan.architecture.entity.PowerOutput;

public interface PowerOutputRepository  extends JpaRepository<PowerOutput, Long> {
    
    
}
