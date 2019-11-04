package com.COEN174.IronEngineer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

import com.COEN174.IronEngineer.entities.Team;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public ModelAndView homeView() {

        //This variable should come from the logged in user
        //i.e. userContext from Google Sign on
        String name = "Ryan";

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