package com.smartair.dao.implementation;

import com.smartair.dao.CustomStatisticRepository;
import com.smartair.dao.StatisticRepository;
import com.smartair.model.entity.DeviceModel;
import com.smartair.model.entity.StatisticModel;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by denis on 12.03.15.
 */
@Repository
public class StatisticRepositoryImpl implements CustomStatisticRepository {
    @Autowired
    MongoOperations mongoOperations;

    public StatisticRepositoryImpl() throws UnknownHostException{
//        MongoClientURI uri = new MongoClientURI("mongodb://smartair:xnndxdfkoavg@ds053894.mongolab.com:53894/co2");
//        mongoClient = new MongoClient(uri);
//        co2Database = mongoClient.getDatabase("co2");
//        dataCollection = co2Database.getCollection("new_data", StatisticModel.class);
    }

    @Override
    public List<StatisticModel> findByDateDescending(int page, int limit) {
        Query query = new Query();
        query.limit(limit);
        query.skip(limit*page);
        query.with(new Sort(Sort.Direction.DESC, "date"));

        return mongoOperations.find(query, StatisticModel.class);
//        List<Document> statistics = new ArrayList<>();
//        MongoCursor<StatisticModel> cursor = dataCollection.find().sort(new BasicDBObject().append("date", -1)).skip(limit*page).limit(limit).iterator();
//        try {
//            while (cursor.hasNext())
//            statistics.add(cursor.next());
//        } finally {
//            cursor.close();
//        }
//        return  StatisticMapper.convertStatisticList(statistics);
    }
    @Override
    public List<StatisticModel> findByDevice(String deviceId, Date startDate, Date endDate, boolean dateSortDescending) {
        Query query = new Query();
        query.addCriteria(Criteria.where("deviceId").is(deviceId));
        query.addCriteria(Criteria.where("date").gte(startDate).and("date").lt(endDate));
        if (dateSortDescending) {
            query.with(new Sort(Sort.Direction.DESC, "date"));
        } else {
            query.with(new Sort(Sort.Direction.ASC, "date"));
        }
        return mongoOperations.find(query, StatisticModel.class);
//        List<Document> statistics = new ArrayList<>();
//        int sortParam = (dateSortDescending) ? -1 : 1; // if sortDescending then sort : -1, if ascending 1
//        MongoCursor<StatisticModel> cursor = dataCollection.find(new BasicDBObject("deviceId", deviceId).
//                //TODO - from $lt to $lte, because last record was not included to chart request.
//                append("date", new BasicDBObject("$gte", startDate).append("$lt", endDate))).sort(new BasicDBObject("date", sortParam)).iterator();
//        try {
//            while (cursor.hasNext())
//            statistics.add(cursor.next());
//        } finally {
//            cursor.close();
//        }
//        return  StatisticMapper.convertStatisticList(statistics);
    }
    @Override
    public List<StatisticModel> findByDevice(String deviceId, int skip, int limit, boolean dateSortDescending) {
        Query query = new Query();
        query.addCriteria(Criteria.where("deviceId").is(deviceId));
        if (dateSortDescending) {
            query.with(new Sort(Sort.Direction.DESC, "date"));
        } else {
            query.with(new Sort(Sort.Direction.ASC, "date"));
        }
        query.skip(skip);
        query.limit(limit);
        return mongoOperations.find(query, StatisticModel.class);
//        List<Document> statistics = new ArrayList<>();
//        int sortParam = (dateSortDescending) ? -1 : 1; // if sortDescending then sort : -1, if ascending 1
//        MongoCursor<StatisticModel> cursor = dataCollection.find(new BasicDBObject("deviceId", deviceId)).
//                sort(new BasicDBObject("date", sortParam)).
//                skip(skip).
//                limit(limit).iterator();
//        try {
//            while (cursor.hasNext())
//                statistics.add(cursor.next());
//        } finally {
//            cursor.close();
//        }
//        return   StatisticMapper.convertStatisticList(statistics);
    }

//    public void addStatistic(String deviceId, double temperature, int co2, double humidity) {
//        StatisticModel statictic = new StatisticModel();
//        statictic.setCo2(co2);
//        statictic.setDate(new Date());
//        statictic.setDeviceId(deviceId);
//        statictic.setHumidity(humidity);
//        statictic.setTemperature(temperature);
//
//        dataCollection.insertOne(statictic);
//
//    }

//    /**
//     * Created by denis on 14.03.15.
//     */
//    private static class StatisticMapper {
//        static public StatisticModel convertStatisticDbObject(Document statisticDbObject) {
//            try {
//                StatisticModel stat = new StatisticModel();
//                stat.setCo2((Integer) statisticDbObject.get("co2"));
//                stat.setDeviceId((String) statisticDbObject.get("deviceId"));
//                stat.setDate((Date) statisticDbObject.get("date"));
//                stat.setTemperature((Double) statisticDbObject.get("temperature"));
//                stat.setHumidity((Double) statisticDbObject.get("humidity"));
//                return  stat;
//            } catch (Exception e)
//            {
//                e.printStackTrace();
//                return null;
//            }
//        }
//        static public List<StatisticModel> convertStatisticList(List<Document> statisticList) {
//            List<StatisticModel> result = new ArrayList<>();
//            if (!statisticList.isEmpty()) {
//               for (Document row : statisticList) {
//                   result.add(StatisticMapper.convertStatisticDbObject(row));
//               }
//            }
//            return result;
//        }
//
//        static public DeviceModel convertDeviceDbObject(Document deviceDbObject) {
//            try {
//                DeviceModel device = new DeviceModel();
//                device.setDeviceId((String) deviceDbObject.get("deviceId"));
//                device.setDeviceName((String) deviceDbObject.get("deviceName"));
//                return  device;
//            } catch (Exception e)
//            {
//                e.printStackTrace();
//                return null;
//            }
//        }
//        static public List<DeviceModel> convertDevicesList(List<Document> devicesList) {
//            List<DeviceModel> result = new ArrayList<>();
//            if (!devicesList.isEmpty()) {
//                for (Document device : devicesList) {
//                    result.add(StatisticMapper.convertDeviceDbObject(device));
//                }
//            }
//            return result;
//        }
//    }
}
