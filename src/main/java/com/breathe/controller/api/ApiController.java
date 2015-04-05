package com.breathe.controller.api;

import com.breathe.model.StatisticModel;
import com.breathe.service.StatisticService;
import com.breathe.service.UserService;
import com.breathe.service.implementation.StatisticServiceImpl;
import com.breathe.service.implementation.UserServiceImpl;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

/**
 * Created by denis on 02.04.15.
 */
@Controller
@RequestMapping("/api")
public class ApiController {
//    @Autowired
//    private StatisticService statisticService;
//    @Autowired
//    private UserService userService;

    private UserService userService;
    private StatisticService statisticService;

    public ApiController() throws UnknownHostException {
        final MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost"));
        final DB co2Database = mongoClient.getDB("co2");
        this.statisticService = new StatisticServiceImpl(co2Database);
        this.userService = new UserServiceImpl(co2Database);
    }

    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public @ResponseBody
    List<StatisticModel> getStatisticByDevice(@RequestParam final String deviceId, @RequestParam final long startDate,
                                              @RequestParam final long endDate) {
        return statisticService.findByDevice(deviceId, new Date(startDate), new Date(endDate), true);
    }

    @RequestMapping(value = "/devices", method = RequestMethod.GET)
    public @ResponseBody
    List<com.breathe.model.DeviceModel> getDevicesByUserId(@RequestParam final String userId) {
        return userService.findDevicesByUser(userId);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login() {
        //RequestBody.;

    }
}
