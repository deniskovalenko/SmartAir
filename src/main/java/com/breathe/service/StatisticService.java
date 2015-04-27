package com.breathe.service;

import com.breathe.model.StatisticModel;
import com.breathe.model.chart.ChartDataSetModel;
import com.breathe.model.chart.ChartSearchFilterModel;

import java.util.Date;
import java.util.List;

/**
 * Created by denis on 03.04.15.
 */
public interface StatisticService {

    List<StatisticModel> findByDateDescending(int page, int limit);

    List<StatisticModel> findByDevice(String deviceId, int skip, int limit, boolean dateSortDescending);

    List<StatisticModel> findByDevice(String deviceId, Date startDate, Date endDate, boolean sortDescending);

    List<ChartDataSetModel> getChartData(String userId, int page, int limit);

    public List<ChartDataSetModel> getChartData(String userId, ChartSearchFilterModel filter);

    boolean addEntity(StatisticModel stat);
}
