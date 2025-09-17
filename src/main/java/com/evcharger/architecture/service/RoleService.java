package com.evcharger.architecture.service;


import com.evcharger.architecture.entity.Role;

public interface RoleService {
    void saveRole(String name);
    void deleteRole(Long id);
    Role updateRole(Long id, String name);
    Role getRole(Long id);
    Role getRoleByName(String name);
    
}
