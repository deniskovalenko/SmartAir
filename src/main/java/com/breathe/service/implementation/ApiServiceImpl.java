package com.breathe.service.implementation;

import com.breathe.dao.StatisticDAL;
import com.breathe.dao.implementation.StatisticDAO;
import com.breathe.dao.UserDAL;
import com.breathe.dao.implementation.UserDAO;
import com.breathe.model.ApiDeviceModel;
import com.breathe.model.StatisticModel;
import com.breathe.service.ApiService;
import com.breathe.utils.mappers.DeviceMapper;
import com.breathe.utils.mappers.StatisticMapper;
import com.mongodb.DBObject;
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
    private UserDAO userDAO;
    @Autowired
    private StatisticDAO statisticDAO;

//    public UserServiceImpl() {
//    }

    public List<ApiDeviceModel> getDevices(String userId) {
        List<ApiDeviceModel> apiDevices = new ArrayList<>();

        //get user's devices
        List<DBObject> devices = userDAO.findDevices(userId);
        for(DBObject device : devices) {
           apiDevices.add(DeviceMapper.convertDeviceDbObject(device));
         }
         //add current co2,humidity and temperature data and their deltas
        List<DBObject> lastData;
        for(ApiDeviceModel apiDevice : apiDevices) {
            //get two latest records for this device
            lastData = statisticDAO.findByDevice(apiDevice.getDeviceId(), 0, 2, true);
            if (lastData.size() == 0) {
                apiDevice.setCurrentCO2(0);
                apiDevice.setCurrentTemperature(0);
                apiDevice.setCurrentHumidity(0);
                apiDevice.setDeltaCO2(0);
                apiDevice.setDeltaTemperature(0);
                apiDevice.setDeltaHumidity(0);
            } else if (lastData.size() == 1) {
                StatisticModel current = StatisticMapper.convertStatisticDbObject(lastData.get(0));
                apiDevice.setCurrentCO2(current.getCo2());
                apiDevice.setCurrentHumidity(current.getHumidity());
                apiDevice.setCurrentTemperature(current.getTemperature());
                apiDevice.setDeltaCO2(0);
                apiDevice.setDeltaTemperature(0);
                apiDevice.setDeltaHumidity(0);
            } else {
                StatisticModel current = StatisticMapper.convertStatisticDbObject(lastData.get(0));
                StatisticModel previous = StatisticMapper.convertStatisticDbObject(lastData.get(1));
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
        List<DBObject> data = statisticDAO.findByDevice(deviceId, startDate, endDate, sortDescending);
        return StatisticMapper.convertStatisticList(data);
    }
}
