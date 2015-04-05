package com.breathe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by denis on 02.04.15.
 */
@Controller
@RequestMapping("/welcome")
public class WelcomeController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView greetings(ModelMap model) {
        return new ModelAndView("common/index");
    }
}
