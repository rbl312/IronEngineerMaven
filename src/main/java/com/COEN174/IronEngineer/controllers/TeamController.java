package com.COEN174.IronEngineer.controllers;

import com.COEN174.IronEngineer.entities.Competitor;
import com.COEN174.IronEngineer.entities.Team;
import com.COEN174.IronEngineer.repositories.CompetitorRepository;
import com.COEN174.IronEngineer.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private CompetitorRepository competitorRepository;

    // Function Name:
    // Parameters:
    // Description:
    // Notes:
    //Setup user context
    @GetMapping("/all")
    public @ResponseBody Iterable<Team> findAllTeams(){
        return teamRepository.findAll();
    }

    // Function Name:
    // Parameters:
    // Description:
    // Notes:
    @RequestMapping("/find")
    public ModelAndView findTeam(){
        ModelAndView modelAndView = new ModelAndView("findTeam");

        Iterable<Team> teams= teamRepository.findAll();
        List<Team> joinableTeams = new ArrayList<>();
        for (Team team : teams) {
            if(team.getApproved() == 1) {
                if (team.getCompetitors().size() < 3)
                    joinableTeams.add(team);
            }
        }

        modelAndView.addObject("joinableTeams", joinableTeams);

        return modelAndView;
    }

    // Function Name:
    // Parameters:
    // Description:
    // Notes:
    @RequestMapping(value = "/join/{teamId}")
    public ModelAndView joinTeam(@PathVariable("teamId") Integer teamId, Principal principal){

        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        String userName = (String) details.get("name");
        String userEmail =  (String) details.get("email");

        //Add member to team
        Competitor c = competitorRepository.findByEmail(userEmail);

        //TODO
        //if they are already on a team, redirect them to the home page

        Team t = teamRepository.findByTeamId(teamId);

        if(t.getCompetitors().size()>=3){
            return new ModelAndView("redirect:/team/join");
        }
        
        t.addTeamMember(c);
        teamRepository.save(t);

        c.setTeamIdFK(t.getTeamId());
        competitorRepository.save(c);
        //redirect to home page
        return new ModelAndView("redirect:/home");
    }

    // Function Name:
    // Parameters:
    // Description:
    // Notes:
    @RequestMapping("/create")
    public ModelAndView createTeam(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("newTeam", new Team());

        //TODO
        //if they are already on a team, redirect them to the home page

        return modelAndView;
    }

    // Function Name:
    // Parameters:
    // Description:
    // Notes:
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addTeam(@Valid @ModelAttribute("newTeam")Team newTeam, BindingResult result, ModelMap model, Principal principal){

        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        String userEmail =  (String) details.get("email");

        Competitor competitor = competitorRepository.findByEmail(userEmail);

        newTeam.addTeamMember(competitor);
        newTeam.setApproved(0);
        teamRepository.save(newTeam);
        System.out.println(newTeam.getTeamId());
        competitor.setTeamIdFK(newTeam.getTeamId());
        competitorRepository.save(competitor);
        return new ModelAndView("redirect:/home");
    }

}
