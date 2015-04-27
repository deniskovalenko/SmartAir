package com.breathe.dao.implementation;

import com.breathe.dao.DeviceDAO;
import com.breathe.model.DeviceModel;
import com.mongodb.*;
import org.springframework.stereotype.Repository;

import java.net.UnknownHostException;

/**
 * Created by amira on 03.04.15.
 */
@Repository
public class DeviceDAOImpl implements DeviceDAO {
    DBCollection dataCollection;
    DBCollection usersCollection;
    MongoClient mongoClient;
    DB co2Database;

    public DeviceDAOImpl() throws UnknownHostException{
        mongoClient= new MongoClient(new MongoClientURI("mongodb://localhost"));
        co2Database = mongoClient.getDB("co2");
        dataCollection = co2Database.getCollection("data");
        usersCollection = co2Database.getCollection("users");
    }

    public DeviceModel findByDeviceId(String deviceId) {
        DBObject device = usersCollection.findOne(new BasicDBObject("devises", new BasicDBObject("deviceId", deviceId)));
        return  convertDeviceDbObject(device);
    }

    public boolean ifDeviceExists(String deviceId) {
        return (usersCollection.find(new BasicDBObject("devices", new BasicDBObject("deviceId", deviceId))).count() > 0);
    }

    public boolean addDevice(String deviceId, String deviceName, int delay, int co2MinLevel) {
        if (usersCollection.find(new BasicDBObject("devices", new BasicDBObject("deviceId", deviceId))).count() > 0) {
            System.out.println("Device with this device_id already exists: " + deviceId);
            return false;
        }

        BasicDBObject post = new BasicDBObject("deviceId", deviceId)
            .append("deviceName", deviceName)
            .append("delay", delay)
            .append("co2Min", co2MinLevel);

        try {
            //TODO : ????? why data ???
            //TODO : add to special user
            dataCollection.insert(post);
        } catch (Exception e) {
            System.out.println("Error inserting post");
            return false;
        }
                                                                
        return true;
    }

    private DeviceModel convertDeviceDbObject(DBObject deviceDbObject) {
        try {
            DeviceModel device = new DeviceModel();
            device.setDeviceId((String) deviceDbObject.get("deviceId"));
            device.setDeviceName((String) deviceDbObject.get("deviceName"));
            return  device;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
