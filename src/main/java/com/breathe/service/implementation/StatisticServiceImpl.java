package com.breathe.service.implementation;

import com.breathe.dao.StatisticDAO;
import com.breathe.model.DeviceModel;
import com.breathe.model.StatisticModel;
import com.breathe.model.chart.ChartDataSetModel;
import com.breathe.model.chart.ChartPoint;
import com.breathe.model.chart.ChartSearchFilterModel;
import com.breathe.service.StatisticService;
import com.breathe.service.UserService;
import com.breathe.utils.ColorGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private ColorGenerator colorGenerator;

    public List<StatisticModel> findByDateDescending(int page, int limit) {
        List<StatisticModel> data = statisticDAO.findByDateDescending(page, limit);
        return data;
    }

    public List<StatisticModel> findByDevice(String deviceId, Date startDate, Date endDate, boolean sortDescending) {
        List<StatisticModel> data = statisticDAO.findByDevice(deviceId, startDate, endDate, sortDescending);
        return data;
    }

    public List<StatisticModel> findByDevice(String deviceId, int skip, int limit, boolean dateSortDescending) {
        List<StatisticModel> data = statisticDAO.findByDevice(deviceId, skip, limit, dateSortDescending);
        return data;
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
            dataSet.setColor(colorGenerator.getRandomColorString());
            List<StatisticModel> stats = this.findByDevice(device.getDeviceId(), page, limit, true);
            List<ChartPoint> values = new ArrayList<>();
            for(StatisticModel stat : stats) {
                try {
                    values.add(new ChartPoint(stat.getDate(), stat.getCo2()));
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
            dataSet.setValues(values);
            dataSets.add(dataSet);
        }
        return dataSets;
    }

    public List<ChartDataSetModel> getChartData(String userId, ChartSearchFilterModel filter) {
        List<DeviceModel> devices = userService.findDevicesByUser(userId);
        List<ChartDataSetModel> dataSets = new ArrayList<>();
        //logic to get "latest date for all devices"
        Date latestDate = getLatestStatisticDate(devices);
        //get offset time
        Date searchStartDate = filter.getStartDate(latestDate);
        Date searchEndDate = filter.getEndDate(latestDate);

        for (DeviceModel device :devices) {
            ChartDataSetModel dataSet = new ChartDataSetModel();
            dataSet.setKey(device.getDeviceName());
            //TODO make different colors?
            dataSet.setColor(colorGenerator.getRandomColorString());
            //then search from "DATE" - mode*count*page -> "DATE - page"
            List<StatisticModel> stats = this.findByDevice(device.getDeviceId(),searchStartDate, searchEndDate, true );

            List<ChartPoint> values = new ArrayList<>();
            for(StatisticModel stat : stats) {
                try {
                    values.add(new ChartPoint(stat.getDate(), stat.getCo2()));
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
            dataSet.setValues(values);
            dataSets.add(dataSet);
        }
        return dataSets;
    }

    private Date getLatestStatisticDate(List<DeviceModel> devices) {
        StatisticModel lastData = new StatisticModel();
        Date latestStatisticDate = findByDevice(devices.get(0).getDeviceId(), 0, 1, true).get(0).getDate();
        //TODO provide better way to select init date
        for (int i = 1; i < devices.size(); i++) {
            List<StatisticModel> statistic = findByDevice(devices.get(i).getDeviceId(), 0, 1, true);
            if (statistic.size()!=0) {
                lastData = statistic.get(0);
                //if current device's last statistic record comes later ...
                if (lastData.getDate().after(latestStatisticDate)) {
                    latestStatisticDate = lastData.getDate();
                }
            }
        }
        return latestStatisticDate;
    }
}
