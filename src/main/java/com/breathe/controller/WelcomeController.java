package com.breathe.controller;

import com.breathe.model.UserModel;
import com.breathe.service.UserService;
import com.breathe.service.implementation.UserServiceImpl;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by denis on 02.04.15.
 */
@Controller
@RequestMapping("/")
public class WelcomeController {

    @Autowired
    private UserServiceImpl userService;


    private static final String REDIRECT = "redirect:/";

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView greetings(ModelMap model) {
        return new ModelAndView("common/index");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        Map<String, Object> model = new HashMap<>();
        model.put("username", "");
        model.put("login_error", "");
        return new ModelAndView("common/login", model);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password) {
        UserModel user = userService.validateLogin(username, password);
        if (user == null) {
            return new ModelAndView(REDIRECT);
        } else {
            Map<String, Object> model = new HashMap<>();
            model.put("username", user.getUsername());
            return new ModelAndView("user/userProfile", model);
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView registerPage() {
        Map<String, Object> model = new HashMap<>();
        model.put("username", "");
        model.put("login_error", "");
        return new ModelAndView("common/signup", model);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") UserModel user) {
       if (user != null) {
           userService.addUser(user);
       }
        return "redirect:/";
    }
}
