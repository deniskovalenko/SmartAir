package com.breathe.service.implementation;

import com.breathe.dao.StatisticDAO;
import com.breathe.dao.UserDAO;
import com.breathe.model.ApiDeviceModel;
import com.breathe.model.StatisticModel;
import com.breathe.service.ApiService;
import com.breathe.utils.mappers.DeviceMapper;
import com.breathe.utils.mappers.StatisticMapper;
import com.mongodb.DB;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by denis on 07.04.15.
 */
public class ApiServiceImpl implements ApiService {

    private UserDAO userDAO;
    private StatisticDAO statisticDAO;

//    public UserServiceImpl() {
//    }

    public ApiServiceImpl(final DB co2Database) {
        userDAO = new UserDAO(co2Database);
        statisticDAO = new StatisticDAO(co2Database);
    }

    public List<ApiDeviceModel> getDevices(String userId) {
        List<ApiDeviceModel> apiDevices = new ArrayList<>();

        //get user's devices
        List<DBObject> devices = userDAO.findDevicesByUser(userId);
        for(DBObject device : devices) {
           apiDevices.add(DeviceMapper.convertDeviceDbObject(device));
         }
         //add current co2,humidity and temperature data and their deltas
        List<DBObject> lastData = new ArrayList<>(2);
        for(ApiDeviceModel apiDevice : apiDevices) {
            //get two latest records for this device
            lastData = statisticDAO.findByDevice(apiDevice.getDeviceId(), 0, 2, true);
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
        return  apiDevices;
    }

    public List<StatisticModel> findStatisticByDevice(String deviceId, Date startDate, Date endDate, boolean sortDescending) {
        List<DBObject> data = statisticDAO.findByDevice(deviceId, startDate, endDate, sortDescending);
        return StatisticMapper.convertStatisticList(data);
    }
}
