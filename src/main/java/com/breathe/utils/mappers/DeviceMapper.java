package com.breathe.utils.mappers;

import com.breathe.model.ApiDeviceModel;
import com.breathe.model.DeviceModel;
import com.mongodb.DBObject;

/**
 * Created by denis on 07.04.15.
 */
public class DeviceMapper {
    static public ApiDeviceModel convertApiDeviceDbObject(DeviceModel device) {
        ApiDeviceModel apiDevice = new ApiDeviceModel();
        apiDevice.setDevice_id(device.getDevice_id());
        apiDevice.setDevice_name(device.getDevice_name());
        apiDevice.setCo2MinLevel(device.getCo2MinLevel());
        apiDevice.setDelay(device.getDelay());
        return apiDevice;
    }

    static public ApiDeviceModel convertDeviceDbObject(DBObject deviceDbObject) {
        try {
            ApiDeviceModel device = new ApiDeviceModel();
            device.setDevice_id((String)deviceDbObject.get("device_id"));
            device.setDevice_name((String)deviceDbObject.get("device_name"));
            device.setCo2MinLevel((Double) deviceDbObject.get("co2Min"));
            device.setDelay((Double) deviceDbObject.get("delay"));
            return  device;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
