package com.smartair.service.implementation;

import com.smartair.dao.StatisticRepository;
import com.smartair.model.entity.DeviceModel;
import com.smartair.model.entity.StatisticModel;
import com.smartair.model.chart.ChartDataSetModel;
import com.smartair.model.chart.ChartPoint;
import com.smartair.model.chart.ChartSearchFilterModel;
import com.smartair.service.DeviceService;
import com.smartair.service.NotificationService;
import com.smartair.service.StatisticService;
import com.smartair.service.UserService;
import com.smartair.utils.ColorGenerator;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
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
    private StatisticRepository statisticRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ColorGenerator colorGenerator;

    public List<StatisticModel> findByDateDescending(int page, int limit) {
        List<StatisticModel> data = statisticRepository.findByDateDescending(page, limit);
        return data;
    }

    public List<StatisticModel> findByDevice(String deviceId, Date startDate, Date endDate, boolean sortDescending) {
        List<StatisticModel> data = statisticRepository.findByDevice(deviceId, startDate, endDate, sortDescending);
        return data;
    }

    public List<StatisticModel> findByDevice(String deviceId, int skip, int limit, boolean dateSortDescending) {
        List<StatisticModel> data = statisticRepository.findByDevice(deviceId, skip, limit, dateSortDescending);
        return data;
    }

    public void addStatistic(StatisticModel stat) {
        //TODO provide check in devices colleciton
        if (!deviceService.ifDeviceExists(stat.getDeviceId())) return;

        if (stat.getDate() == null) {
            stat.setDate(new DateTime(DateTimeZone.forOffsetHours(2)).toDate());
        }
        statisticRepository.insert(stat);

        DeviceModel device = deviceService.findDevice(stat.getDeviceId());

        if (device.getUserId() != null && !device.getUserId().equals("")) {
            if (device.getCo2MaxLevel() < stat.getCo2()) {
                notificationService.notifyUser(device.getUserId(), " co2 в " + device.getDeviceName() + " = " +
                stat.getCo2() + "! \n Проветрите " + device.getDeviceName());
            }
        }
        //TODO: check if data is valid
//        return statisticRepository.addStatistic(stat.getDeviceId(), stat.getTemperature(), stat.getCo2());
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

        if (devices == null || devices.size() == 0) {
            return dataSets;
        }
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
        if (devices == null || devices.size() == 0) {
            return new Date();
        }
        StatisticModel lastData;
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
