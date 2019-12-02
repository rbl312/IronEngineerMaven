package com.COEN174.IronEngineer.controllers;

import com.COEN174.IronEngineer.entities.Competitor;
import com.COEN174.IronEngineer.repositories.CompetitorRepository;
import com.COEN174.IronEngineer.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

import com.COEN174.IronEngineer.entities.Team;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private CompetitorRepository competitorRepository;

    @Autowired
    private TeamRepository teamRepository;

    // Function Name: homeView
    // Parameters: principal (Principal), used to handle user context.
    // Expected Result: Returns the home view for a user.
    // Description: returns the home view for a user.
    // Notes: the home view includes items such as the user's name, team, distances completed, as well as links to functions like adding distances and joining teams.
    @RequestMapping("/home")
    public ModelAndView homeView(Principal principal) {

        //Get User information from Google Sign on

        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        String userName = (String) details.get("name");
        String userEmail =  (String) details.get("email");


        Competitor user = competitorRepository.findByEmail(userEmail);

        //User is not in the database redirect them to the registration
        if(user == null){
            return new ModelAndView("redirect:/register");
        }
        //by checking if it is true we also check to make sure it is not null
        if(user.getIsAdmin() == true){
            return new ModelAndView("redirect:/admin/home");
        }

        Team userTeam = teamRepository.findByTeamId(user.getTeamIdFK());
//        teamApproved = userTeam.getApproved();

        //Check to make sure user is on a team, if not, have them register on one
        if(userTeam == null){
            return new ModelAndView("redirect:/register");
        }

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("name", userName);
        modelAndView.addObject("userTeam", userTeam);
        modelAndView.addObject("isApproved", userTeam.getApproved());
        return modelAndView;
    }


}