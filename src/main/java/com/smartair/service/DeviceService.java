package com.smartair.service;

import com.smartair.model.entity.DeviceModel;

/**
 * Created by denis on 03.04.15.
 */
public interface DeviceService {

    void addDevice(DeviceModel device);

    DeviceModel findDevice(String deviceId);

    boolean ifDeviceExists(String deviceId);

    String getOwnerId(String deviceId);
}
