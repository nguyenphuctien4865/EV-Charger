package com.evcharger.architecture.repository;

import com.evcharger.architecture.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * SQL query: SELECT * FROM users WHERE userId =
     *
     * @param id as String
     * @return User
     */
    User getUserByUserId(String id);
}