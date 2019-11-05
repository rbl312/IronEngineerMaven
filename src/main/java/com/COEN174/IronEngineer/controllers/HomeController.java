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

    @RequestMapping("/home")
    public ModelAndView homeView(Principal principal) {

        //This variable should come from the logged in user
        //i.e. userContext from Google Sign on

        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        String userName = (String) details.get("name");
        String userEmail =  (String) details.get("email");

        Competitor user = competitorRepository.findByEmail(userEmail);

        //User is not in the database redirect them to the registration
        if(user == null){
            return new ModelAndView("redirect:/register");
        }



        //This should be a value of User / Participant
        //Would probably look more like if(user.getTeamId() == null) etc..
        boolean isOnTeam;
        Team userTeam;
        if(teamRepository.findByTeamId(user.getTeamIdFK()).isPresent()) {
            isOnTeam = true;
            userTeam = teamRepository.findByTeamId(user.getTeamIdFK()).get();
        }
        else{
            isOnTeam = false;
            userTeam = teamRepository.findByTeamId(user.getTeamIdFK()).get();
        }
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("name", userName);
        modelAndView.addObject("isOnTeam", isOnTeam);
        modelAndView.addObject("userTeam", userTeam);
        return modelAndView;
    }
}