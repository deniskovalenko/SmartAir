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

//
//    MongoCollection<DeviceModel> devicesCollection;
//    MongoClient mongoClient;
//    MongoDatabase co2Database;

    public DeviceRepositoryImpl() throws UnknownHostException{
//        MongoClientURI uri = new MongoClientURI("mongodb://smartair:xnndxdfkoavg@ds053894.mongolab.com:53894/co2");
//        mongoClient = new MongoClient(uri);
//        co2Database = mongoClient.getDatabase("co2");
//        devicesCollection = co2Database.getCollection("new_devices", DeviceModel.class);
    }

    public DeviceModel findByDeviceId(String deviceId) {
        return mongoOperations.findOne(Query.query(Criteria.where("deviceId").is(deviceId)), DeviceModel.class);
      //  return devicesCollection.find(new BasicDBObject("deviceId", deviceId)).first();
    }
//
//    public boolean ifDeviceExists(String deviceId) {
//
//        return (devicesCollection.count(new BasicDBObject("deviceId", deviceId)) > 0);
//    }

//    public void addDevice(DeviceModel device) {
//        devicesCollection.insertOne(device);
//    }

    public String getOwnerId(String deviceId) {
        return findByDeviceId(deviceId).getUserId();
    }


}
