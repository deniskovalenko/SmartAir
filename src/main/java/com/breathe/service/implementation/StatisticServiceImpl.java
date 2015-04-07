package com.breathe.service.implementation;

import com.breathe.utils.mappers.StatisticMapper;
import com.breathe.dao.DeviceDAO;
import com.breathe.dao.StatisticDAO;
import com.breathe.model.StatisticModel;
import com.breathe.service.StatisticService;
import com.mongodb.DB;
import com.mongodb.DBObject;

import java.util.Date;
import java.util.List;

/**
 * Created by denis on 14.03.15.
 */

//@Component
public class StatisticServiceImpl implements StatisticService {
    private StatisticDAO statisticDAO;
    private DeviceDAO deviceDAO;

    public StatisticServiceImpl(final DB co2Database) {
        statisticDAO = new StatisticDAO(co2Database);
        deviceDAO = new DeviceDAO(co2Database);
    }
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

}
