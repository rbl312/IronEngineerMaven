package com.COEN174.IronEngineer.controllers;


import com.COEN174.IronEngineer.repositories.CompetitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

import com.COEN174.IronEngineer.entities.Competitor;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class RegisterController {

    @Autowired
    private CompetitorRepository competitorRepository;

    // Function Name:
    // Parameters:
    // Description:
    // Notes:
    @RequestMapping("/register")
    public ModelAndView regview(Principal principal) {
        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        String userName =  (String) details.get("name");
        String userEmail = (String) details.get("email");

        Competitor newUser = new Competitor(userName, userEmail);
        competitorRepository.save(newUser);

        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("name" , userName);
        return modelAndView;
    }


}
