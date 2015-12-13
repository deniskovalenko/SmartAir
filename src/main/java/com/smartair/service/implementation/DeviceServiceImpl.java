package com.smartair.service.implementation;

import com.smartair.dao.DeviceRepository;
import com.smartair.model.entity.DeviceModel;
import com.smartair.model.entity.user.User;
import com.smartair.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by amira on 03.04.15.
 */

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    public DeviceModel findDevice(String deviceId) {
        return deviceRepository.findOne(deviceId);
    }

    @Override
    public List<DeviceModel> findDevicesByUser(User user) {
        return deviceRepository.findByUserId(user.getUserId());
    }

    public String getDeviceOwnerId(String deviceId) {
        return deviceRepository.getOwnerId(deviceId);
    }

    public boolean ifDeviceExists(String deviceId) {
        return deviceRepository.exists(deviceId);
    }

    public void addDevice(DeviceModel device) {
        //TODO - change to addDevice(DeviceModel)
        //TODO and generate unique device ID
        if (!ifDeviceExists(device.getDeviceId())) {
            deviceRepository.insert(device);
        }
    }

    @Override
    public String getOwnerId(String deviceId) {
        return deviceRepository.getOwnerId(deviceId);
    }
}
