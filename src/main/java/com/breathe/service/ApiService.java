package com.breathe.service;

import com.breathe.model.api.ApiDeviceModel;
import com.breathe.model.StatisticModel;

import java.util.Date;
import java.util.List;

/**
 * Created by denis on 07.04.15.
 */
public interface ApiService {
    List<ApiDeviceModel> getDevices(String userId);

    List<StatisticModel> findStatisticByDevice(String deviceId, Date startDate,Date endDate, boolean sortDescending);
}
