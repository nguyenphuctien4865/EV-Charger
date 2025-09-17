package com.evcharger.architecture.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.evcharger.architecture.entity.UserEV;

public interface UserEVRepository extends JpaRepository<UserEV, String>, JpaSpecificationExecutor<UserEV> {
    Optional<UserEV> findByUserId(String userId);

    Optional<UserEV> findByEmail(String email);

    Optional<UserEV> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUserId(String userId);

    Boolean existsByPhoneNumber(String phoneNumber);

    Page<UserEV> findAll(Specification<UserEV> spec, Pageable pageable);

}
