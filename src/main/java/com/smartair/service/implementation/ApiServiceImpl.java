package com.smartair.service.implementation;

import com.smartair.dao.StatisticRepository;
import com.smartair.dao.UserRepository;
import com.smartair.model.entity.DeviceModel;
import com.smartair.model.entity.StatisticModel;
import com.smartair.model.api.ApiDeviceModel;
import com.smartair.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by denis on 07.04.15.
 */
@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StatisticRepository statisticRepository;

//    public UserServiceImpl() {
//    }

    public List<ApiDeviceModel> getDevices(String userId) {
        List<ApiDeviceModel> apiDevices = new ArrayList<>();

        //get user's devices
        List<DeviceModel> devices = userRepository.findDevices(userId);
        for(DeviceModel device : devices) {
           apiDevices.add(this.convertApiDeviceObject(device));
         }
         //add current co2,humidity and temperature data and their deltas
        List<StatisticModel> lastData;
        for(ApiDeviceModel apiDevice : apiDevices) {
            //get two latest records for this device
            lastData = statisticRepository.findByDevice(apiDevice.getDeviceId(), 0, 2, true);
            if (lastData.size() == 0) {
                apiDevice.setCurrentCO2(0);
                apiDevice.setCurrentTemperature(0);
                apiDevice.setCurrentHumidity(0);
                apiDevice.setDeltaCO2(0);
                apiDevice.setDeltaTemperature(0);
                apiDevice.setDeltaHumidity(0);
            } else if (lastData.size() == 1) {
                StatisticModel current = lastData.get(0);
                apiDevice.setCurrentCO2(current.getCo2());
                apiDevice.setCurrentHumidity(current.getHumidity());
                apiDevice.setCurrentTemperature(current.getTemperature());
                apiDevice.setDeltaCO2(0);
                apiDevice.setDeltaTemperature(0);
                apiDevice.setDeltaHumidity(0);
            } else {
                StatisticModel current = lastData.get(0);
                StatisticModel previous = lastData.get(1);
                //set currents to data from latest record
                apiDevice.setCurrentCO2(current.getCo2());
                apiDevice.setCurrentHumidity(current.getHumidity());
                apiDevice.setCurrentTemperature(current.getTemperature());
                //set deltas to current-previous
                apiDevice.setDeltaCO2(current.getCo2() - previous.getCo2());
                apiDevice.setDeltaHumidity(current.getHumidity() - previous.getHumidity());
                apiDevice.setDeltaTemperature(current.getTemperature() - previous.getTemperature());
            }
        }
        return  apiDevices;
    }

    public List<StatisticModel> findStatisticByDevice(String deviceId, Date startDate, Date endDate, boolean sortDescending) {
       return statisticRepository.findByDevice(deviceId, startDate, endDate, sortDescending);
    }

    private ApiDeviceModel convertApiDeviceObject(DeviceModel device) {
            ApiDeviceModel apiDevice = new ApiDeviceModel();
            apiDevice.setDeviceId(device.getDeviceId());
            apiDevice.setDeviceName(device.getDeviceName());
            apiDevice.setCo2MaxLevel(device.getCo2MaxLevel());
            apiDevice.setDelay(device.getDelay());
            return apiDevice;
    }
}

