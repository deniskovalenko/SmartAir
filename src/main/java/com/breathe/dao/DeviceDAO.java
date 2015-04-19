package com.breathe.dao;

import com.breathe.model.DeviceModel;
import com.mongodb.DBObject;

/**
 * Created by denis on 14.04.15.
 */
public interface DeviceDAO {
    DBObject findByDeviceId(String deviceId);

    //TODO change to void
    boolean ifDeviceExists(String deviceId);

    boolean addDevice(String deviceId, String deviceName, int delay, int co2MinLevel);

    boolean addDevice(String userId, DeviceModel device);
}
