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

    //Setup user context
    @GetMapping("/all")
    public @ResponseBody Iterable<Team> findAllTeams(){
        return teamRepository.findAll();
    }


    @RequestMapping("/find")
    public ModelAndView findTeam(){
        ModelAndView modelAndView = new ModelAndView("findTeam");

        Iterable<Team> teams= teamRepository.findAll();
        List<Team> joinableTeams = new ArrayList<>();
        for (Team team : teams) {
            if(team.getCompetitors().size()<3)
                joinableTeams.add(team);
        }

        modelAndView.addObject("joinableTeams", joinableTeams);

        return modelAndView;
    }

    @RequestMapping(value = "/join/{teamId}")
    public ModelAndView joinTeam(@PathVariable("teamId") Integer teamId, Principal principal){

        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        String userName = (String) details.get("name");
        String userEmail =  (String) details.get("email");
        Integer userId = (Integer) details.get("competitor_id");

        //Add member to team
//        Competitor c = competitorRepository.findByEmail(userEmail);
        if(!(competitorRepository.findById(userId).isPresent())){
            //TODO:
            // Make this return an error page
            System.out.print("\n\n\n Error in TeamController.joinTeam.findById()\n\n\n");
            return new ModelAndView("redirect/home");
        }
        Competitor c = competitorRepository.findById(userId).get();
        //TODO
        //if they are already on a team, redirect them to the home page

        if(!(teamRepository.findByTeamId(teamId).isPresent())){
            //TODO:
            // Make this return an error page
            System.out.print("\n\n\n Error in TeamController.joinTeam.findByTeamId()\n\n\n");
            return new ModelAndView("redirect/home");
        }
        Team t = teamRepository.findByTeamId(teamId).get();
//        t.addTeamMember(c);
        c.setTeamIdFK(t.getTeamId());

        System.out.println("\n\n Competitor:");
        System.out.println(c.toString() + "\n\n");
        System.out.println("\n\n Team:");
        System.out.println(t.toString() + "\n\n");

        competitorRepository.save(c);
        teamRepository.save(t);
        //redirect to home page
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping("/create")
    public ModelAndView createTeam(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("newTeam", new Team());

        //TODO
        //if they are already on a team, redirect them to the home page

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addTeam(@Valid @ModelAttribute("newTeam")Team newTeam, BindingResult result, ModelMap model, Principal principal){

        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        String userEmail =  (String) details.get("email");

        Competitor competitor = competitorRepository.findByEmail(userEmail);

        newTeam.addTeamMember(competitor);
        teamRepository.save(newTeam);
        return new ModelAndView("redirect:/home");
    }
}
