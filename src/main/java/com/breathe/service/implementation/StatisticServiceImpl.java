package com.breathe.service.implementation;

import com.breathe.dao.DeviceDAO;
import com.breathe.dao.StatisticDAO;
import com.breathe.model.ChartDataSetModel;
import com.breathe.model.ChartPoint;
import com.breathe.model.DeviceModel;
import com.breathe.service.ApiService;
import com.breathe.service.DeviceService;
import com.breathe.service.UserService;
import com.breathe.utils.mappers.StatisticMapper;
import com.breathe.model.StatisticModel;
import com.breathe.service.StatisticService;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by denis on 14.03.15.
 */
@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private StatisticDAO statisticDAO;;
    @Autowired
    private UserService userService;


//
//    public StatisticServiceImpl() throws UnknownHostException {
//                final MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost"));
//        final DB co2Database = mongoClient.getDB("co2");
//    }

    public List<StatisticModel> findByDateDescending(int page, int limit) {
        List<DBObject> data = statisticDAO.findByDateDescending(page, limit);
        return StatisticMapper.convertStatisticList(data);
    }

    public List<StatisticModel> findByDevice(String deviceId, Date startDate, Date endDate, boolean sortDescending) {
        List<DBObject> data = statisticDAO.findByDevice(deviceId, startDate, endDate, sortDescending);
        return StatisticMapper.convertStatisticList(data);
    }

    public List<StatisticModel> findByDevice(String deviceId, int skip, int limit, boolean dateSortDescending) {
        List<DBObject> data = statisticDAO.findByDevice(deviceId, skip, limit, dateSortDescending);
        return StatisticMapper.convertStatisticList(data);
    }

    public boolean addEntity(StatisticModel stat) {
        //TODO provide check in devices colleciton
       // if (!deviceDAO.ifDeviceExists(stat.getDeviceId())) return false;
        return statisticDAO.addEntity(stat.getDeviceId(), stat.getTemperature(), stat.getCo2(), stat.getHumidity());
        //TODO: check if data is valid
//        return statisticDAO.addEntity(stat.getDeviceId(), stat.getTemperature(), stat.getCo2());
    }

    public List<ChartDataSetModel> getChartData(String userId, int page, int limit) {
        List<DeviceModel> devices = userService.findDevicesByUser(userId);
        List<ChartDataSetModel> dataSets = new ArrayList<>();
        for (DeviceModel device :devices) {
            ChartDataSetModel dataSet = new ChartDataSetModel();
            dataSet.setKey(device.getDeviceName());
            dataSet.setColor("#ff7f0e");
            List<StatisticModel> stats = this.findByDevice(device.getDeviceId(), page, limit, true);
            List<ChartPoint> values = new ArrayList<>();
            for(StatisticModel stat : stats) {
                values.add(new ChartPoint(stat.getDate(), stat.getCo2()));
            }
            dataSet.setValues(values);
            dataSets.add(dataSet);
        }
        return dataSets;
    }

}
