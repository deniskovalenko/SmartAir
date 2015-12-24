package com.smartair.controller.user;

import com.smartair.model.DeviceCreateModel;
import com.smartair.model.UserException;
import com.smartair.model.entity.DeviceModel;
import com.smartair.model.entity.StatisticModel;
import com.smartair.model.entity.user.User;
import com.smartair.model.chart.ChartDataSetModel;
import com.smartair.model.chart.ChartSearchFilterModel;
import com.smartair.service.DeviceService;
import com.smartair.service.StatisticService;
import com.smartair.service.UserService;
import com.smartair.service.security.AuthorizedUserProvider;
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

    @RequestMapping(value = "/chartData", method = RequestMethod.GET)
    public @ResponseBody List<ChartDataSetModel> getStatisticData(@Validated @ModelAttribute ChartSearchFilterModel filter) {
        final User user = AuthorizedUserProvider.getAuthorizedUser();
        return statisticService.getChartData(user, filter);
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
    public ModelAndView profile() {
        final User user = AuthorizedUserProvider.getAuthorizedUser();
        Map<String, Object> model = new HashMap<>();
            model.put("username", user.getUsername());
            if (user.getDevices() != null) {
                model.put("devices_count", user.getDevices().size());
            } else {
                model.put("devices_count", 0);
            }
            List<DeviceModel> devices = deviceService.findDevicesByUser(user);
            model.put("devices", devices);
            return new ModelAndView(ROOT + "/profile", model);
    }

    @RequestMapping(value = "/addDevice", method = RequestMethod.GET)
    public ModelAndView addDevice() {
        Map<String, Object> model = new HashMap<>();
        return new ModelAndView(ROOT + "/addDevice", model);
    }

    @RequestMapping(value = "/addDevice", method = RequestMethod.POST)
    public ModelAndView addDevice (@ModelAttribute("device") DeviceCreateModel device) {
        final User user = AuthorizedUserProvider.getAuthorizedUser();
        userService.addDevice(user.getUserId(), device);
        Map<String, Object> model = new HashMap<>();
        return new ModelAndView("redirect:/" + ROOT + "/profile", model);
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public ModelAndView profileSettings() {
        Map<String, Object> model = new HashMap<>();
        return new ModelAndView(ROOT + "/settings", model);
    }

    @RequestMapping(value = "/settings", method = RequestMethod.POST)
    public ModelAndView profileSettings (@ModelAttribute("user") User user) {
        final User loggedInUser = AuthorizedUserProvider.getAuthorizedUser();
        Map<String, Object> model = new HashMap<>();
        try {
            userService.setNewPassword(loggedInUser.getUserId(), user.getPassword());
            return new ModelAndView("redirect:/" + ROOT + "/profile", model);
        } catch (UserException e) {
            model.put("error", e.getMessage());
            return new ModelAndView(ROOT + "/settings", model);
        }
    }
}