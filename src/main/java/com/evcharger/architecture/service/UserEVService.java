package com.evcharger.architecture.service;

import com.evcharger.architecture.model.ApiResponse;
import com.evcharger.architecture.model.UserEVDTO;

public interface UserEVService {
    UserEVDTO createUser(UserEVDTO user);

    UserEVDTO getUserById(String id);

    UserEVDTO updateUser(String id, UserEVDTO user);

    void deleteUser(String id);

    ApiResponse<UserEVDTO> listUsers(int page, int limit, String sortDir, String sortBy , String username, String email);
}
