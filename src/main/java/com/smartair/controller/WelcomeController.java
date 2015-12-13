package com.smartair.controller;

import com.smartair.model.entity.user.User;
import com.smartair.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by denis on 02.04.15.
 */
@Controller
@RequestMapping("/")
public class WelcomeController {

    @Autowired
    private UserService userService;


    private static final String REDIRECT = "redirect:/";
    private static final String LOGIN = "login";
    private static final String SIGN_UP = "signup";
    private static final String COMMON = "common/";
    private static final String INDEX = "index";
    private static final String PROFILE = "user/profile";

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView greetings(ModelMap model) {
        return new ModelAndView(COMMON + INDEX);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        Map<String, Object> model = new HashMap<>();
        model.put("username", "");
        model.put("login_error", "");
        return new ModelAndView(COMMON + LOGIN, model);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView registerPage() {
        Map<String, Object> model = new HashMap<>();
        model.put("username", "");
        model.put("login_error", "");
        return new ModelAndView(COMMON + SIGN_UP, model);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user) {
       if (user != null) {
           userService.addUser(user);
           return REDIRECT + LOGIN;
       }
       return REDIRECT;
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public ModelAndView contacts() {
        return new ModelAndView(COMMON + "contacts");
    }
}
