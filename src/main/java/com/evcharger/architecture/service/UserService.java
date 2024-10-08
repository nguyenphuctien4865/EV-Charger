package com.evcharger.architecture.service;

import com.evcharger.architecture.entity.User;
import com.evcharger.architecture.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    //region
    private final UserRepository repository;
    //endregion

    /**
     * Get user by id
     *
     * @param userId as String
     * @return User
     */
    public User getUserById(String userId) {
        return repository.getUserByUserId(userId);
    }

    /**
     * Save user
     *
     * @param user as User
     * @return User
     */
    public User saveUser(User user) {
        return repository.save(user);
    }
}
