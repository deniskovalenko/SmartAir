package com.breathe.dao;

import com.breathe.model.DeviceModel;

/**
 * Created by denis on 14.04.15.
 */
public interface DeviceDAO {
    DeviceModel findByDeviceId(String deviceId);

    //TODO change to void
    boolean ifDeviceExists(String deviceId);

    void addDevice(DeviceModel device);
}
