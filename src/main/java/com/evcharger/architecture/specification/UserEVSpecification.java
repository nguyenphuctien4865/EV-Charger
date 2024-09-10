package com.evcharger.architecture.specification;

import org.springframework.data.jpa.domain.Specification;

import com.evcharger.architecture.entity.UserEV;

public class UserEVSpecification {
    
    public static Specification<UserEV> hasUsername(String userName) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("username")),"%" +userName.toLowerCase()+"%");
    }

    public static Specification<UserEV> hasEmail(String email) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("email")), "%"+email.toLowerCase()+"%");
    }


}
