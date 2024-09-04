package com.evcharger.architecture.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.evcharger.architecture.entity.EVCharger;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EVChargerRepository extends JpaRepository<EVCharger, String>,  JpaSpecificationExecutor<EVCharger> {
        Page<EVCharger> findAll(Specification<EVCharger> spec, Pageable pageable);
        Optional<EVCharger> findByChargerId(String Id);
}
