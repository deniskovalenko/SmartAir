package com.smartair.controller.api;

import com.smartair.model.entity.StatisticModel;
import com.smartair.model.api.ApiDeviceModel;
import com.smartair.model.api.ApiResponseModel;
import com.smartair.service.ApiService;
import com.smartair.service.StatisticService;
import com.smartair.service.UserService;
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
    @Autowired
    private UserService userService;

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

    /**
     * This request is called to set new Google Cloud Messaging token for mobile user
     */
    @RequestMapping(value = "/set_gcm_token", method = RequestMethod.POST)
    public @ResponseBody
    ApiResponseModel setGcmToken(@RequestParam final String userId, @RequestParam final String gcmToken) {
        userService.setGcmToken(userId, gcmToken);
        ApiResponseModel response = new ApiResponseModel();
        //find changes,
        response.setChanged((byte)0);
        response.setType("success");
        response.setCode(200);
        return response;
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
