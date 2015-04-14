package com.breathe.dao;

import com.mongodb.DBObject;

import java.util.Date;
import java.util.List;

/**
 * Created by denis on 14.04.15.
 */
public interface StatisticDAL {
    List<DBObject> findByDateDescending(int page, int limit);

    List<DBObject> findByDevice(String deviceId, int skip, int limit, boolean dateSortDescending);

    List<DBObject> findByDevice(String deviceId, Date startDate, Date endDate, boolean dateSortDescending);

    boolean addEntity(String deviceId, double temperature, int co2, double humidity);
}
