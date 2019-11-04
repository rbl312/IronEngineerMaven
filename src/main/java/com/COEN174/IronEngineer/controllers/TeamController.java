package com.COEN174.IronEngineer.controllers;

import com.COEN174.IronEngineer.entities.Competitor;
import com.COEN174.IronEngineer.entities.Team;
import com.COEN174.IronEngineer.repositories.CompetitorRepository;
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


//    @RequestMapping("/find")
//    public ModelAndView findTeam(){
//        ModelAndView modelAndView = new ModelAndView("findTeam");
//
//        //TODO
//        //Replace this with a function which gets all joinable teams
//        //For the purposes of this demo, that means any team with less than 3 people
//
//        List<Team> joinableTeams = new ArrayList<>();
//        Team team1 = new Team(11, "Team1", null);
//        Team team2 = new Team(12, "Team2", null);
//        joinableTeams.add(team1);
//        joinableTeams.add(team2);
//
//        modelAndView.addObject("joinableTeams", joinableTeams);
//
//        //TODO
//        //if they are already on a team, redirect them to the home page
//
//        return modelAndView;
//    }

    @RequestMapping(value = "/join/{teamName}")
    public ModelAndView joinTeam(@PathVariable("teamName") String teamName,@RequestParam String email){

        //TODO
        //if they are already on a team, redirect them to the home page

        //Add member to team
        Competitor c = competitorRepository.findByEmail(email);
        Integer teamMateId = c.getId();

        Team t = teamRepository.findByName(teamName);
        t.addTeamMember(teamMateId);
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
//    public ModelAndView addTeam(@Valid @ModelAttribute("newTeam")Team newTeam, BindingResult result, ModelMap model){
//        //Add new team to database
//        //We should have this user Id from their user context when theyre logged in
//        int userId = 123;
//        List<Integer> memberIds = new ArrayList<>();
//        memberIds.add(userId);
//        newTeam.setMemberIds(memberIds);
//        return new ModelAndView("redirect:/home");
//    }
    public @ResponseBody String addNewTeam(@RequestParam String leaderEmail,@RequestParam String teamName){
        Competitor leader = competitorRepository.findByEmail(leaderEmail);
        if(leader == null){
            return "Invalid Competitor.\n";
        }
        Integer leaderId = leader.getId();

        Team t = new Team();
        t.setName(teamName);
        t.addTeamMember(leaderId);
        teamRepository.save(t);

        return "Saved.";
    }

    @RequestMapping(value ="/update/{competitorId}/{catergory}")
    public @ResponseBody String updateTeamDistance(@PathVariable("competitorId") Integer competitorId,@PathVariable("catergory") String catergory,@RequestParam Integer distance){
        Team t = teamRepository.findByCompetitorId(competitorId);
        Optional<Competitor> c = competitorRepository.findById(competitorId);
        if(!(c.isPresent())){
            return "Invalid Competitor";
        }
        Competitor comp = c.get();
        if(catergory == "swimming"){
            Integer dist = comp.getDistanceSwam();
            dist += distance;
            comp.setDistanceSwam(dist);
            competitorRepository.save(comp);
        }
        if(catergory == "running"){
            Integer dist = comp.getDistanceRan();
            dist += distance;
            comp.setDistanceRan(dist);
            competitorRepository.save(comp);
        }
        if(catergory == "biking"){
            Integer dist = comp.getDistanceBiked();
            dist += distance;
            comp.setDistanceBiked(dist);
            competitorRepository.save(comp);
        }
        return "distances updated.\n";
    }


}
