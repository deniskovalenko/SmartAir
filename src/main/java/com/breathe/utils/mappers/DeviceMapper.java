package com.breathe.utils.mappers;

import com.breathe.model.api.ApiDeviceModel;
import com.breathe.model.DeviceModel;
import com.mongodb.DBObject;

/**
 * Created by denis on 07.04.15.
 */
public class DeviceMapper {
    static public ApiDeviceModel convertApiDeviceDbObject(DeviceModel device) {
        ApiDeviceModel apiDevice = new ApiDeviceModel();
        apiDevice.setDeviceId(device.getDeviceId());
        apiDevice.setDeviceName(device.getDeviceName());
        apiDevice.setCo2MinLevel(device.getCo2MinLevel());
        apiDevice.setDelay(device.getDelay());
        return apiDevice;
    }

    static public ApiDeviceModel convertDeviceDbObject(DBObject deviceDbObject) {
        try {
            ApiDeviceModel device = new ApiDeviceModel();
            device.setDeviceId((String) deviceDbObject.get("deviceId"));
            device.setDeviceName((String) deviceDbObject.get("deviceName"));
            device.setCo2MinLevel((Integer) deviceDbObject.get("co2Min"));
            device.setDelay((Integer) deviceDbObject.get("delay"));
            return  device;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
