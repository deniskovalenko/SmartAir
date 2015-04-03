package com.breathe.controller.api;

import com.breathe.dao.StatisticDAO;
import com.breathe.model.StatisticModel;
import com.breathe.service.implementation.StatisticService;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

/**
 * Created by denis on 02.04.15.
 */
@Controller
@RequestMapping("/api")
public class ApiController {
    private StatisticDAO statisticDAO;
    private StatisticService statisticService;

    public ApiController() throws UnknownHostException {
        final MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost"));
        final DB co2Database = mongoClient.getDB("co2");


        statisticDAO = new StatisticDAO(co2Database);
        this.statisticService = new StatisticService(co2Database);
    }

    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public @ResponseBody
    List<StatisticModel> getStatisticByDevice(@RequestParam final String deviceid, @RequestParam final long st_date,
                                              @RequestParam final long end_date) {
        return statisticService.findByDevice(deviceid, new Date(st_date), new Date(end_date), true);
    }

    @RequestMapping(value = "/devices", method = RequestMethod.GET)
    public @ResponseBody
    List<com.breathe.model.DeviceModel> getDevicesByUserId(@RequestParam final String userid) {
        return statisticService.findDevicesByUser(userid);
    }
}
