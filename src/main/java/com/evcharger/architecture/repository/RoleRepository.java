package com.evcharger.architecture.repository;

import com.evcharger.architecture.entity.Role;
import com.evcharger.architecture.util.enums.ERole;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
