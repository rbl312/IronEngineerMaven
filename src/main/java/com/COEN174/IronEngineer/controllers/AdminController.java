package com.COEN174.IronEngineer.controllers;

import com.COEN174.IronEngineer.entities.Competitor;
import com.COEN174.IronEngineer.entities.Team;
import com.COEN174.IronEngineer.repositories.CompetitorRepository;
import com.COEN174.IronEngineer.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CompetitorRepository competitorRepository;

    @Autowired
    private TeamRepository teamRepository;

    @RequestMapping("/home")
    public ModelAndView adminView(Principal principal) {
        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        String userName = (String) details.get("name");
        String userEmail =  (String) details.get("email");

        Competitor user = competitorRepository.findByEmail(userEmail);
        if(user.getAdmin() == 0){
            return new ModelAndView("redirect:/home");
        }
        ModelAndView modelAndView = new ModelAndView("admin");
        modelAndView.addObject("name", userName);
        modelAndView.addObject("isAdmin", user.getAdmin());
        return modelAndView;

    }
    @RequestMapping("/approve")
    public ModelAndView approveTeam(){
        ModelAndView modelAndView = new ModelAndView("approveTeam");

        Iterable<Team> teams= teamRepository.findAll();
        List<Team> approveTeams = new ArrayList<>();
        for (Team team : teams) {
            if(team.getApproved() == 0)
                approveTeams.add(team);
        }

        modelAndView.addObject("approveTeams", approveTeams);

        return modelAndView;
    }
    @RequestMapping(value = "/approve/{teamId}")
    public ModelAndView joinTeam(@PathVariable("teamId") Integer teamId, Principal principal){

        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        String userName = (String) details.get("name");
        String userEmail =  (String) details.get("email");
        Competitor c = competitorRepository.findByEmail(userEmail);
        if(c.getAdmin() == 0){
            return new ModelAndView("redirect:/home");
        }

        Team t = teamRepository.findByTeamId(teamId);
        t.setApproved(1);
        teamRepository.save(t);

        //redirect to home page
        return new ModelAndView("redirect:/admin/approve");
    }
}
