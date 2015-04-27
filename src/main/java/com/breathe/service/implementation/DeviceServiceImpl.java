package com.breathe.service.implementation;

import com.breathe.dao.DeviceDAO;
import com.breathe.model.DeviceModel;
import com.breathe.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by amira on 03.04.15.
 */

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceDAO deviceDAO;

    public DeviceModel findDevice(String deviceId) {
        return deviceDAO.findByDeviceId(deviceId);
    }

    public boolean ifDeviceExists(String deviceId) {
        return deviceDAO.ifDeviceExists(deviceId);
    }

    public void addDevice(DeviceModel device) {
        //TODO - change to addDevice(DeviceModel)
        //TODO and generate unique device ID
        if (!ifDeviceExists(device.getDeviceId())) {
            deviceDAO.addDevice(device);
        }
    }
}
