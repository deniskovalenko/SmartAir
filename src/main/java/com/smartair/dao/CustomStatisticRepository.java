package com.smartair.dao;

import com.smartair.model.entity.StatisticModel;

import java.util.Date;
import java.util.List;

/**
 * Created by denis on 23.11.15.
 */
public interface CustomStatisticRepository {
    List<StatisticModel> findByDateDescending(int page, int limit);

    List<StatisticModel> findByDevice(String deviceId, int skip, int limit, boolean dateSortDescending);

    List<StatisticModel> findByDevice(String deviceId, Date startDate, Date endDate, boolean dateSortDescending);

}
