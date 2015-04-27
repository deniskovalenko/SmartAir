package com.breathe.service;

import com.breathe.model.DeviceModel;

/**
 * Created by denis on 03.04.15.
 */
public interface DeviceService {
    boolean addDevice(String deviceId, String deviceName, int delay, int co2MinLevel);

    DeviceModel findDevice(String deviceId);
}
