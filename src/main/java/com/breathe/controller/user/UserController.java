package com.breathe.controller.user;

import com.breathe.dao.StatisticDAO;
import com.breathe.model.DeviceModel;
import com.breathe.model.StatisticModel;
import com.breathe.service.StatisticService;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class UserController {

    public UserController() throws UnknownHostException {
        final MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost"));
        final DB co2Database = mongoClient.getDB("co2");


        statisticDAO = new StatisticDAO(co2Database);
        this.statisticService = new StatisticService(co2Database);
    }

    private static final String ROOT = "user";
    public final static int PER_PAGE = 30;
    private StatisticDAO statisticDAO;
    private StatisticService statisticService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getStatisticList(ModelMap model) {
        List<DBObject> data = statisticDAO.findByDateDescending(0, PER_PAGE);
        model.put("per_page", PER_PAGE);
        model.put("page", 0);
        model.put("data", data);

        return new ModelAndView(ROOT + "/statisticList", model);
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

    @RequestMapping(value = "/api/statistic", method = RequestMethod.GET)
    public @ResponseBody List<StatisticModel> getStatisticByDevice(@RequestParam final String deviceid, @RequestParam final long st_date,
                                                                   @RequestParam final long end_date) {
        return statisticService.findByDevice(deviceid, new Date(st_date), new Date(end_date), true);
    }

    @RequestMapping(value = "/api/devices", method = RequestMethod.GET)
    public @ResponseBody
    List<com.breathe.model.DeviceModel> getDevicesByUserId(@RequestParam final String userid) {
        return statisticService.findDevicesByUser(userid);
    }

    @RequestMapping(value = "/addData", method = RequestMethod.GET)
    public ModelAndView addData() {
        return new ModelAndView(ROOT + "/   addData");
    }

    @RequestMapping(value = "/addData", method = RequestMethod.POST)
    public String saveData (@ModelAttribute("statistic") StatisticModel stat) {
        statisticService.addEntity(stat);
        return "redirect:/";
    }
//
//    post("/addData", (request, response) -> {
//        String device_name = StringEscapeUtils.escapeHtml4(request.queryParams("device_id"));
//        String temperature = StringEscapeUtils.escapeHtml4(request.queryParams("temperature"));
//        String co2 = StringEscapeUtils.escapeHtml4(request.queryParams("co2"));
//
//        double co2_d = Double.parseDouble(co2);
//        double temperature_d = Double.parseDouble(temperature);
//
//        //TODO: check if device with this name exists
//        statisticService.addEntity(device_name, temperature_d, co2_d);
//
//        response.redirect("/", 302);
//        return "";
//
//    });
}