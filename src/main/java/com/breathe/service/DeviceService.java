package com.breathe.service;

import com.breathe.model.DeviceModel;

/**
 * Created by denis on 03.04.15.
 */
public interface DeviceService {
    public Boolean addDevice(String device_id, String device_name, Integer delay, double co2MinLevel);

    DeviceModel findDevicesById(String device_id);
}
