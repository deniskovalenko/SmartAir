package com.breathe.dao.implementation;

import com.breathe.dao.DeviceDAO;
import com.breathe.model.DeviceModel;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.net.UnknownHostException;

/**
 * Created by amira on 03.04.15.
 */
@Repository
public class DeviceDAOImpl implements DeviceDAO {
    MongoCollection dataCollection;
    MongoCollection usersCollection;
    MongoClient mongoClient;
    MongoDatabase co2Database;

    public DeviceDAOImpl() throws UnknownHostException{
        MongoClientURI uri = new MongoClientURI("mongodb://smartair:xnndxdfkoavg@ds053894.mongolab.com:53894/co2"); 
        mongoClient = new MongoClient(uri);
        co2Database = mongoClient.getDatabase("co2");
        dataCollection = co2Database.getCollection("data");
        usersCollection = co2Database.getCollection("users");
    }

    public DeviceModel findByDeviceId(String deviceId) {
        Document device = ((Document) usersCollection.find(new BasicDBObject("devises", new BasicDBObject("deviceId", deviceId))).first());
        return  convertDeviceDbObject(device);
    }

    public boolean ifDeviceExists(String deviceId) {
        return (usersCollection.count(new BasicDBObject("devices", new BasicDBObject("deviceId", deviceId))) > 0);
    }

    public void addDevice(DeviceModel device) {
        if (usersCollection.count(new BasicDBObject("devices", new BasicDBObject("deviceId", device.getDeviceId()))) > 0) {
            System.out.println("Device with this device_id already exists: " + device.getDeviceId());
        //TODO move check to service level
        }

        BasicDBObject insertDevice = new BasicDBObject("deviceId", device.getDeviceId())
            .append("deviceName", device.getDeviceName())
            .append("delay", device.getDelay())
            .append("co2Min", device.getCo2MinLevel());

        try {
            //TODO : ????? why data ???
            //TODO : add to special user
            dataCollection.insertOne(insertDevice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DeviceModel convertDeviceDbObject(Document deviceDbObject) {
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
