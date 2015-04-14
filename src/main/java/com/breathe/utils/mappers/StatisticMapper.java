package com.breathe.utils.mappers;


import com.breathe.model.DeviceModel;
import com.breathe.model.StatisticModel;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by denis on 14.03.15.
 */
public class StatisticMapper {
    static public StatisticModel convertStatisticDbObject(DBObject statisticDbObject) {
        try {
            StatisticModel stat = new StatisticModel();
            stat.setCo2((Integer) statisticDbObject.get("co2"));
            stat.setDeviceId((String) statisticDbObject.get("deviceId"));
            stat.setDate((Date) statisticDbObject.get("date"));
            stat.setTemperature((Double) statisticDbObject.get("temperature"));
            stat.setHumidity((Double) statisticDbObject.get("humidity"));
            return  stat;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    static public List<StatisticModel> convertStatisticList(List<DBObject> statisticList) {
        List<StatisticModel> result = new ArrayList<StatisticModel>();
        if (!statisticList.isEmpty()) {
           for (DBObject row : statisticList) {
               result.add(StatisticMapper.convertStatisticDbObject(row));
           }
        }
        return result;
    }

    static public DeviceModel convertDeviceDbObject(DBObject deviceDbObject) {
        try {
            DeviceModel device = new DeviceModel();
            device.setDeviceId((String) deviceDbObject.get("deviceId"));
            device.setDeviceName((String) deviceDbObject.get("deviceName"));
            return  device;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    static public List<DeviceModel> convertDevicesList(List<DBObject> devicesList) {
        List<DeviceModel> result = new ArrayList<>();
        if (!devicesList.isEmpty()) {
            for (DBObject device : devicesList) {
                result.add(StatisticMapper.convertDeviceDbObject(device));
            }
        }
        return result;
    }
}
