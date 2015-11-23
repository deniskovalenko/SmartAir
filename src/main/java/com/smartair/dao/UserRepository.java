package com.smartair.dao;

import com.smartair.model.entity.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by denis on 23.11.15.
 */
@Repository
public interface UserRepository extends MongoRepository<UserModel, String>, CustomUserRepository {
}
