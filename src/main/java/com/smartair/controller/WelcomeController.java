package com.smartair.controller;

import com.smartair.model.UserException;
import com.smartair.model.entity.user.User;
import com.smartair.service.UserService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.SecureRandom;
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

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public ModelAndView loginFailurePage(HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("username", "");
        model.put("login_error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        return new ModelAndView(COMMON + LOGIN, model);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView registerPage() {
        Map<String, Object> model = new HashMap<>();
        model.put("username", "");
        model.put("login_error", "");
        return new ModelAndView(COMMON + SIGN_UP, model);
    }

    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public ModelAndView readArticle() {
        return new ModelAndView(COMMON + "article", null);
    }


    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
       if (!bindingResult.hasErrors()) {
           try {
               userService.add(user);
           } catch (UserException e) {
               //TODO display error
           }
           return loginPage();
       } else {
           Map<String, Object> model = new HashMap<>();
           model.put("username", user.getUsername());
           model.put("email", user.getEmail());
           model.put("password_error", "weak password");
           return new ModelAndView(COMMON + SIGN_UP, model);
       }
    }

    @RequestMapping(value = "/signup_lab", method = RequestMethod.GET)
    public ModelAndView generatePasswordPage() {
        Map<String, Object> model = new HashMap<>();
        model.put("username", "");
        //userService.makeTestData();
        return new ModelAndView(COMMON + SIGN_UP + "_lab", model);
    }

    @RequestMapping(value = "/signup_lab", method = RequestMethod.POST)
    public ModelAndView generatePassword(@ModelAttribute("user") User user, BindingResult bindingResult) {
        String generatedPassword = RandomStringUtils.random(10, 0, 0, true, true, null, new SecureRandom());
            user.setPassword(generatedPassword);
        try {
            userService.add(user);
        } catch (UserException e) {
           //TODO display error
        }
        Map<String, Object> model = new HashMap<>();
            model.put("username", user.getUsername());
            model.put("password", user.getPassword());
            return new ModelAndView(COMMON + SIGN_UP + "_lab", model);

    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public ModelAndView contacts() {
        return new ModelAndView(COMMON + "contacts");
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ModelAndView order() {
        return new ModelAndView(COMMON + "order");
    }

    //customize the error message
    private String getErrorMessage(HttpServletRequest request, String key){

        Exception exception =
                (Exception) request.getSession().getAttribute(key);

        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = exception.getMessage();
        }else if(exception instanceof LockedException) {
            error = exception.getMessage();
        }else{
            error = "Invalid username and password!";
        }

        return error;
    }
}
