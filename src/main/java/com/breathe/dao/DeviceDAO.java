package com.breathe.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.springframework.stereotype.Repository;

/**
 * Created by amira on 03.04.15.
 */
//@Repository
public class DeviceDAO {
    DBCollection deviceCollection; //collection of manufactured, but maybe not purchased yet devices

    public DeviceDAO() {
    }

    public DeviceDAO(final DB co2Database) {
        deviceCollection = co2Database.getCollection("data");
        //TODO - change to device collection
    }

    public DBObject findByDeviceId(String deviceId) {
        DBObject result = deviceCollection.findOne(new BasicDBObject("deviceId", deviceId));
        return  result;
    }

    public boolean ifDeviceExists(String deviceId) {
        //T this method search, if there is any record in statistic collection with deviceID
        // would return null, if you've just bought and added device to your account
        //TODO - change to search in device collection, or even in device array of particular user..
        return (deviceCollection.find(new BasicDBObject("deviceId", deviceId)).count() > 0);
    }

    public boolean addDevice(String deviceId, String deviceName, int delay, int co2MinLevel) {
        if (deviceCollection.find(new BasicDBObject("deviceId", deviceId)).count() > 0) {
            System.out.println("Device with this device_id already exists: " + deviceId);
            return false;
        }

        BasicDBObject post = new BasicDBObject("deviceId", deviceId)
            .append("deviceName", deviceName)
            .append("delay", delay)
            .append("co2MinLevel", co2MinLevel);

        try {
            deviceCollection.insert(post);
        } catch (Exception e) {
            System.out.println("Error inserting post");
            return false;
        }

        return true;
    }
}
