package com.smartair.dao;

import com.smartair.model.entity.DeviceModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by denis on 23.11.15.
 */
@Repository
public interface DeviceRepository extends MongoRepository<DeviceModel, String>, CustomDeviceRepository {
    List<DeviceModel> findByUserId(String userId);
}
