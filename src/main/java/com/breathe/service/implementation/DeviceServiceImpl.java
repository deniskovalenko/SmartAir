package com.breathe.service.implementation;

import com.breathe.dao.DeviceDAO;
import com.breathe.utils.mappers.StatisticMapper;
import com.breathe.model.DeviceModel;
import com.breathe.service.DeviceService;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by amira on 03.04.15.
 */
@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceDAO deviceDAO;

//    public DeviceServiceImpl() {}

    public DeviceModel findDevicesById(String deviceId) {
        DBObject device = deviceDAO.findByDeviceId(deviceId);
        return  StatisticMapper.convertDeviceDbObject(device);
    }

    public boolean ifDeviceExists(String deviceId) {
        return deviceDAO.ifDeviceExists(deviceId);
    }

    public boolean addDevice(String deviceId, String deviceName, int delay, int co2MinLevel) {
        //TODO - change to addDevice(DeviceModel)
        //TODO and generate unique device ID
        if (ifDeviceExists(deviceId)) return false;
        return deviceDAO.addDevice(deviceId, deviceName, delay, co2MinLevel);
    }
}
