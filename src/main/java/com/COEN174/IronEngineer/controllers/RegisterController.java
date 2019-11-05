package com.COEN174.IronEngineer.controllers;


import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

import com.COEN174.IronEngineer.entities.Team;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class RegisterController {
    @RequestMapping("/register")
    public ModelAndView regview(Principal principal) {
        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", (String) details.get("name"));
        map.put("email", (String) details.get("email"));
        String name = map.get("name");

        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("name" , name);
        return modelAndView;
    }


}
