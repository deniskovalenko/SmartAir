package com.breathe.dao.implementation;

import com.breathe.dao.StatisticDAO;
import com.mongodb.*;
import org.springframework.stereotype.Repository;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

/**
 * Created by denis on 12.03.15.
 */
@Repository
public class StatisticDAOImpl implements StatisticDAO {
    DBCollection dataCollection;
    MongoClient mongoClient;
    DB co2Database;


    public StatisticDAOImpl() throws UnknownHostException{
        mongoClient= new MongoClient(new MongoClientURI("mongodb://localhost"));
        co2Database = mongoClient.getDB("co2");
        dataCollection = co2Database.getCollection("data");
    }


    public List<DBObject> findByDateDescending(int page, int limit) {
        List<DBObject> rows;
        DBCursor cursor = dataCollection.find().sort(new BasicDBObject().append("date", -1)).skip(limit*page).limit(limit);
        try {
            rows = cursor.toArray();
        } finally {
            cursor.close();
        }
        return rows;
    }

    public List<DBObject> findByDevice(String deviceId, Date startDate, Date endDate, boolean dateSortDescending) {
        List<DBObject> result;
        int sortParam = (dateSortDescending) ? -1 : 1; // if sortDescending then sort : -1, if ascending 1
        DBCursor cursor = dataCollection.find(new BasicDBObject("deviceId", deviceId).
                append("date", new BasicDBObject("$gte", startDate).append("$lt", endDate))).sort(new BasicDBObject("date", sortParam));
        try {
            result = cursor.toArray();
        } finally {
            cursor.close();
        }
        return  result;
    }

    public List<DBObject> findByDevice(String deviceId, int skip, int limit, boolean dateSortDescending) {
        List<DBObject> result;
        int sortParam = (dateSortDescending) ? -1 : 1; // if sortDescending then sort : -1, if ascending 1
        DBCursor cursor = dataCollection.find(new BasicDBObject("deviceId", deviceId)).
                sort(new BasicDBObject("date", sortParam)).
                skip(skip).
                limit(limit);
        try {
            result = cursor.toArray();
        } finally {
            cursor.close();
        }
        return  result;
    }

    public boolean addEntity(String deviceId, double temperature, int co2, double humidity) {
        BasicDBObject post = new BasicDBObject("deviceId", deviceId);
        post.append("temperature", temperature);
        post.append("humidity", humidity);
        post.append("co2", co2);
        post.append("date", new Date());

        try {
            dataCollection.insert(post);
        } catch (Exception e) {
            System.out.println("Error inserting post");
            return false;
        }

        return true;
    }


}
