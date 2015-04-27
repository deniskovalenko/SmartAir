package com.breathe.dao;

import com.breathe.model.StatisticModel;

import java.util.Date;
import java.util.List;

/**
 * Created by denis on 14.04.15.
 */
public interface StatisticDAO {
    List<StatisticModel> findByDateDescending(int page, int limit);

    List<StatisticModel> findByDevice(String deviceId, int skip, int limit, boolean dateSortDescending);

    List<StatisticModel> findByDevice(String deviceId, Date startDate, Date endDate, boolean dateSortDescending);

    void addStatistic(String deviceId, double temperature, int co2, double humidity);
}
