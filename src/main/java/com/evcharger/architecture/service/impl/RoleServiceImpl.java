package com.evcharger.architecture.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evcharger.architecture.entity.Role;
import com.evcharger.architecture.exception.common.ResourceNotFoundException;
import com.evcharger.architecture.repository.RoleRepository;
import com.evcharger.architecture.service.RoleService;
import com.evcharger.architecture.util.enums.ERole;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleJpaRepository;

    @Override
    public void saveRole(String name) {
        Role role = new Role();
        role.setName(name);
        roleJpaRepository.save(role);
    }

    @Override
    public void deleteRole(Long id) {
        // TODO Auto-generated method stub
        roleJpaRepository.deleteById(id);
    }

    @Override
    public Role updateRole(Long id, String name) {
        // TODO Auto-generated method stub
        Role role = roleJpaRepository.findByName(ERole.valueOf(name).toString())
                .orElseThrow(() -> new ResourceNotFoundException("Role", "Name", name));
        role.setName(name);
        return roleJpaRepository.save(role);

    }

    @Override
    public Role getRole(Long id) {
        // TODO Auto-generated method stub
        return roleJpaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role", "Id", id));
    }

    @Override
    public Role getRoleByName(String name) {
        // TODO Auto-generated method stub
        return roleJpaRepository.findByName(ERole.valueOf(name).toString())
                .orElseThrow(() -> new ResourceNotFoundException("Role", "Name", name));
    }

}
