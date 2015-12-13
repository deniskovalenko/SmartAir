package com.smartair.dao;

import com.smartair.model.entity.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by denis on 23.11.15.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String>, CustomUserRepository {
    User findByUsername(String username);
}
