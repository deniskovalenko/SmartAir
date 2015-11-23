package com.smartair.dao;

/**
 * Created by denis on 14.04.15.
 */
public interface CustomDeviceRepository {
//    DeviceModel findByDeviceId(String deviceId);

    //TODO change to void
//    boolean ifDeviceExists(String deviceId);

//    void addDevice(DeviceModel device);

    String getOwnerId(String deviceId);
}
