package com.breathe.controller.user;

import com.breathe.model.DeviceModel;
import com.breathe.model.StatisticModel;
import com.breathe.model.UserModel;
import com.breathe.model.chart.ChartDataSetModel;
import com.breathe.model.chart.ChartSearchFilterModel;
import com.breathe.service.DeviceService;
import com.breathe.service.StatisticService;
import com.breathe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private StatisticService statisticService;
    @Autowired
    private UserService userService;
    @Autowired
    private DeviceService deviceService;

    private static final String ROOT = "user";
    public final static int PER_PAGE = 30;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getStatisticList(ModelMap model) {
        return getStatisticPage(0);
	}

    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
    public ModelAndView getStatisticPage(@PathVariable final int page) {
        List<StatisticModel> data = statisticService.findByDateDescending(page, PER_PAGE);
        Map<String, Object> model = new HashMap<>();
        model.put("per_page", PER_PAGE);
        model.put("page", page);
        model.put("data", data);
        return new ModelAndView(ROOT + "/statisticList", model);
    }
    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public ModelAndView getStatisticChart() {
        Map<String, Object> model = new HashMap<>();
        model.put("currentPage", 0);
        return new ModelAndView(ROOT + "/chart", model);
    }

//    @RequestMapping(value = "/chart", method = RequestMethod.GET)
//    public @ResponseBody List<ChartDataSetModel> getDefaultChartData() {
//        return getChartData(0);
//    }
//
//    @RequestMapping(value = "/chart/{page}", method = RequestMethod.GET)
//    public @ResponseBody List<ChartDataSetModel> getChartData(@PathVariable("page") int page) {
//        //hardcoded userId
//        return statisticService.getChartData("700caba5-9d40-4d34-9d6c-b15e40c5425f", page, 5);
//    }

    @RequestMapping(value = "/chartData", method = RequestMethod.GET)
    public @ResponseBody List<ChartDataSetModel> getStatisticData(@Validated @ModelAttribute ChartSearchFilterModel filter) {
        //hardcoded userId
        return statisticService.getChartData("40665d90-268d-4857-90fa-21b4016a52d3", filter);
    }

    @RequestMapping(value = "/addData", method = RequestMethod.GET)
    public ModelAndView addData() {
        return new ModelAndView(ROOT + "/addData");
    }

    @RequestMapping(value = "/addData", method = RequestMethod.POST)
    public String saveData (@ModelAttribute("statistic") StatisticModel stat) {
        statisticService.addStatistic(stat);
        return "redirect:/user";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile(@RequestParam("user_id") String userId) {
        UserModel user = userService.getUserById(userId);
        if (user != null) {
            Map<String, Object> model = new HashMap<>();
            model.put("username", user.getUsername());
            model.put("devices_count", user.getDevices().size());
            model.put("devices", user.getDevices());
            model.put("user_id", userId);
            return new ModelAndView(ROOT + "/profile", model);
        }
        else {
            return new ModelAndView("redirect:/");
        }
    }

    @RequestMapping(value = "/addDevice", method = RequestMethod.GET)
    public ModelAndView addDevice(@RequestParam("user_id") String userId) {
        Map<String, Object> model = new HashMap<>();
        model.put("user_id", userId);
        return new ModelAndView(ROOT + "/addDevice", model);
    }

    @RequestMapping(value = "/addDevice", method = RequestMethod.POST)
    public ModelAndView addDevice (@ModelAttribute("device") DeviceModel device, @RequestParam("user_id") String userId) {
        userService.addDevice(userId, device);
        Map<String, Object> model = new HashMap<>();
        model.put("user_id", userId);
        return new ModelAndView("redirect:/" + ROOT + "/profile", model);
    }

}