package com.breathe.service.implementation;

import com.breathe.controller.common.mappers.StatisticMapper;
import com.breathe.dao.DeviceDAO;
import com.breathe.model.DeviceModel;
import com.mongodb.DB;
import com.mongodb.DBObject;

/**
 * Created by amira on 03.04.15.
 */
public class DeviceService {
    private DeviceDAO deviceDAO;

    public DeviceService(final DB co2Database) {
        deviceDAO = new DeviceDAO(co2Database);
    }

    public DeviceModel findDevicesById(String device_id) {
        DBObject device = deviceDAO.findByDeviceId(device_id);
        return  StatisticMapper.convertDeviceDbObject(device);
    }

    public Boolean ifDeviceExists(String device_id) {
        return deviceDAO.ifDeviceExists(device_id);
    }

    public Boolean addDevice(String device_id, String device_name, Integer delay, double co2MinLevel) {
        //TODO - change to addDevice(DeviceModel)
        //TODO and generate unique device ID
        if (ifDeviceExists(device_id)) return false;
        return deviceDAO.addDevice(device_id, device_name, delay, co2MinLevel);
    }
}
