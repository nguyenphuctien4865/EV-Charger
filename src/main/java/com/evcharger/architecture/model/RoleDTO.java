package com.evcharger.architecture.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RoleDTO {
    private Long id;
    private String name;
    private Set<UserEVDTO> userIds; // Assuming you want to include user IDs associated with the role

    // You can also include a constructor if needed
    public RoleDTO(Long id, String name, Set<UserEVDTO> users) {
        this.id = id;
        this.name = name;
        this.userIds = users;
    }
}
