package com.smartair.dao.implementation;

import com.smartair.dao.CustomDeviceRepository;
import com.smartair.model.entity.DeviceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.net.UnknownHostException;

/**
 * Created by amira on 03.04.15.
 */
@Repository
public class DeviceRepositoryImpl implements CustomDeviceRepository {
    @Autowired
    private MongoOperations mongoOperations;

    public DeviceModel findByDeviceId(String deviceId) {
        return mongoOperations.findOne(Query.query(Criteria.where("deviceId").is(deviceId)), DeviceModel.class);
    }


    public String getOwnerId(String deviceId) {
        return findByDeviceId(deviceId).getUserId();
    }


}
