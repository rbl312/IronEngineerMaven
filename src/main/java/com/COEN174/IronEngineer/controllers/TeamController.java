package com.COEN174.IronEngineer.controllers;

import com.COEN174.IronEngineer.entities.Team;
import com.COEN174.IronEngineer.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;

    //Setup user context
    @GetMapping("/all")
    public @ResponseBody Iterable<Team> findAllTeams(){
        return teamRepository.findAll();
    }


    @RequestMapping("/find")
    public ModelAndView findTeam(){
        ModelAndView modelAndView = new ModelAndView("findTeam");

        //TODO
        //Replace this with a function which gets all joinable teams
        //For the purposes of this demo, that means any team with less than 3 people

        List<Team> joinableTeams = new ArrayList<>();
        Team team1 = new Team(11, "Team1", null);
        Team team2 = new Team(12, "Team2", null);
        joinableTeams.add(team1);
        joinableTeams.add(team2);

        modelAndView.addObject("joinableTeams", joinableTeams);

        //TODO
        //if they are already on a team, redirect them to the home page

        return modelAndView;
    }

    @RequestMapping(value = "/join/{id}")
    public ModelAndView joinTeam(@PathVariable("id") int teamId){

        //TODO
        //if they are already on a team, redirect them to the home page

        //Add member to team


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
    public ModelAndView addTeam(@Valid @ModelAttribute("newTeam")Team newTeam, BindingResult result, ModelMap model){
        //Add new team to database
        //We should have this user Id from their user context when theyre logged in
        int userId = 123;
        List<Integer> memberIds = new ArrayList<>();
        memberIds.add(userId);
        newTeam.setMemberIds(memberIds);
        return new ModelAndView("redirect:/home");
    }
}
