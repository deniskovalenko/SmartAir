package com.breathe.service;

import com.breathe.model.DeviceModel;

/**
 * Created by denis on 03.04.15.
 */
public interface DeviceService {
    void addDevice(DeviceModel device);

    DeviceModel findDevice(String deviceId);
}
