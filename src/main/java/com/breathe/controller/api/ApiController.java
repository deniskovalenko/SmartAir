package com.breathe.controller.api;

import com.breathe.model.StatisticModel;
import com.breathe.model.api.ApiDeviceModel;
import com.breathe.model.api.ApiResponseModel;
import com.breathe.service.ApiService;
import com.breathe.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by denis on 02.04.15.
 */
@Controller
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private StatisticService statisticService;
    @Autowired
    private ApiService apiService;

    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public @ResponseBody
    List<StatisticModel> getStatisticByDevice(@RequestParam final String deviceId, @RequestParam final long startDate,
                                              @RequestParam final long endDate) {
        return apiService.findStatisticByDevice(deviceId, new Date(startDate), new Date(endDate), true);
    }

    @RequestMapping(value = "/devices", method = RequestMethod.GET)
    public @ResponseBody
    List<ApiDeviceModel> getDevicesByUserId(@RequestParam final String userId) {
        return apiService.getDevices(userId);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login() {
        //RequestBody.;

    }

    @RequestMapping(value = "/addData", method = RequestMethod.POST)
    public @ResponseBody
    ApiResponseModel saveData (@ModelAttribute("statistic") StatisticModel stat) {
        statisticService.addStatistic(stat);
        ApiResponseModel response = new ApiResponseModel();
        //find changes,
        response.setChanged((byte)0);
        response.setType("success");
        response.setCode(200);
        return response;
    }

}
