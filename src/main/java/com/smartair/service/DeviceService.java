package com.smartair.service;

import com.smartair.model.entity.DeviceModel;
import com.smartair.model.entity.user.User;

import java.util.List;

/**
 * Created by denis on 03.04.15.
 */
public interface DeviceService {

    void addDevice(DeviceModel device);

    List<DeviceModel> findDevicesByUser(User user);

    DeviceModel findDevice(String deviceId);

    boolean ifDeviceExists(String deviceId);

    String getOwnerId(String deviceId);
}
