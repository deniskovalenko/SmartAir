package com.breathe.service;

import com.breathe.model.DeviceModel;

/**
 * Created by denis on 03.04.15.
 */
public interface DeviceService {
    public boolean addDevice(String deviceId, String deviceName, Integer delay, double co2MinLevel);

    DeviceModel findDevicesById(String deviceId);
}
