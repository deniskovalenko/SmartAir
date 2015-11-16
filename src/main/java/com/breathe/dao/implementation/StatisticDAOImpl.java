package com.breathe.dao.implementation;

import com.breathe.dao.StatisticDAO;
import com.breathe.model.DeviceModel;
import com.breathe.model.StatisticModel;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.springframework.stereotype.Repository;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by denis on 12.03.15.
 */
@Repository
public class StatisticDAOImpl implements StatisticDAO {
    MongoCollection dataCollection;
    MongoClient mongoClient;
    MongoDatabase co2Database;


    public StatisticDAOImpl() throws UnknownHostException{
        MongoClientURI uri = new MongoClientURI("mongodb://smartair:xnndxdfkoavg@ds053894.mongolab.com:53894/co2");
        mongoClient = new MongoClient(uri);
        co2Database = mongoClient.getDatabase("co2");
        dataCollection = co2Database.getCollection("data");
    }


    public List<StatisticModel> findByDateDescending(int page, int limit) {
        List<DBObject> statistics = new ArrayList<>();
        MongoCursor<DBObject> cursor = dataCollection.find().sort(new BasicDBObject().append("date", -1)).skip(limit*page).limit(limit).iterator();
        try {
            while (cursor.hasNext())
            statistics.add(cursor.next());
        } finally {
            cursor.close();
        }
        return  StatisticMapper.convertStatisticList(statistics);
    }

    public List<StatisticModel> findByDevice(String deviceId, Date startDate, Date endDate, boolean dateSortDescending) {
        List<DBObject> statistics = new ArrayList<>();
        int sortParam = (dateSortDescending) ? -1 : 1; // if sortDescending then sort : -1, if ascending 1
        MongoCursor<DBObject> cursor = dataCollection.find(new BasicDBObject("deviceId", deviceId).
                //TODO - from $lt to $lte, because last record was not included to chart request.
                append("date", new BasicDBObject("$gte", startDate).append("$lt", endDate))).sort(new BasicDBObject("date", sortParam)).iterator();
        try {
            while (cursor.hasNext())
            statistics.add(cursor.next());
        } finally {
            cursor.close();
        }
        return  StatisticMapper.convertStatisticList(statistics);
    }

    public List<StatisticModel> findByDevice(String deviceId, int skip, int limit, boolean dateSortDescending) {
        List<DBObject> statistics = new ArrayList<>();
        int sortParam = (dateSortDescending) ? -1 : 1; // if sortDescending then sort : -1, if ascending 1
        MongoCursor<DBObject> cursor = dataCollection.find(new BasicDBObject("deviceId", deviceId)).
                sort(new BasicDBObject("date", sortParam)).
                skip(skip).
                limit(limit).iterator();
        try {
            while (cursor.hasNext())
                statistics.add(cursor.next());
        } finally {
            cursor.close();
        }
        return   StatisticMapper.convertStatisticList(statistics);
    }

    public void addStatistic(String deviceId, double temperature, int co2, double humidity) {
        BasicDBObject post = new BasicDBObject("deviceId", deviceId);
        post.append("temperature", temperature);
        post.append("humidity", humidity);
        post.append("co2", co2);
        post.append("date", new Date());

        try {
            dataCollection.insertOne(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Created by denis on 14.03.15.
     */
    private static class StatisticMapper {
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
}
