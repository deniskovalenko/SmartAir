package com.breathe.controller.user;

import com.breathe.model.ChartDataSetModel;
import com.breathe.model.StatisticModel;
import com.breathe.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    @RequestMapping(value = "/chart", method = RequestMethod.GET)
    public ModelAndView getStatisticChart() {
      //  List<StatisticModel> data = statisticService.findByDateDescending(page, PER_PAGE);
      //  Map<String, Object> model = new HashMap<>();
       // model.put("per_page", PER_PAGE);
       // model.put("page", page);
       // model.put("data", data);
        return new ModelAndView(ROOT + "/chart");
    }

    @RequestMapping(value = "/chart/{page}", method = RequestMethod.GET)
    public @ResponseBody List<ChartDataSetModel> getChartData(@PathVariable("page") int page) {
        return statisticService.getChartData("someId", page, PER_PAGE);
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