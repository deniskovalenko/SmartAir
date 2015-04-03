package com.breathe.controller.user;

import com.breathe.dao.StatisticDAO;
import com.breathe.model.StatisticModel;
import com.breathe.service.StatisticService;
import com.breathe.service.UserService;
import com.breathe.service.implementation.StatisticServiceImpl;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class UserController {
//    @Autowired
//    private StatisticService statisticService;

    private static final String ROOT = "user";
    public final static int PER_PAGE = 30;
    private StatisticDAO statisticDAO;
   private StatisticServiceImpl statisticService;

    public UserController() throws UnknownHostException {
        final MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost"));
        final DB co2Database = mongoClient.getDB("co2");

        statisticDAO = new StatisticDAO(co2Database);
        this.statisticService = new StatisticServiceImpl(co2Database);
    }

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



    @RequestMapping(value = "/addData", method = RequestMethod.GET)
    public ModelAndView addData() {
        return new ModelAndView(ROOT + "/addData");
    }

    @RequestMapping(value = "/addData", method = RequestMethod.POST)
    public String saveData (@ModelAttribute("statistic") StatisticModel stat) {
        statisticService.addEntity(stat);
        return "redirect:/";
    }
}