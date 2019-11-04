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
public class HomeController {

    @RequestMapping("/home")
    public ModelAndView homeView(Principal principal) {

        //This variable should come from the logged in user
        //i.e. userContext from Google Sign on

        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", (String) details.get("name"));
        map.put("email", (String) details.get("email"));

        String name = map.get("name");

        //If not logged in, redirect to login page
        boolean is_logged_in = true;

        //This should be a value of User / Participant
        //Would probably look more like if(user.getTeamId() == null) etc..
        boolean is_on_a_team = true;

        //TODO
        //There should be a getter function to get the user's team
        Team userTeam = new Team(11, "Team1", null);

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("name", name);
        modelAndView.addObject("is_on_a_team", is_on_a_team);
        modelAndView.addObject("userTeam", userTeam);
        return modelAndView;
    }
}