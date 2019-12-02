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

    // Function Name: findAllTeams
    // Parameters: None.
    // Expected Result: Returns all valid teams registered in the system.
    // Description: Returns all valid teams found in the Iron Engineer database.
    // Notes:
    @GetMapping("/all")
    public @ResponseBody Iterable<Team> findAllTeams(){
        return teamRepository.findAll();
    }

    // Function Name: findTeam
    // Parameters: None.
    // Expected Result: Returns all approved and joinable teams.
    // Description: Returns approved and joinable teams to be displayed to competitors looking to join a team.
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

    // Function Name: joinTeam
    // Parameters: teamId (Integer) and principal (Principal). teamId is the primary key used to identify a team in the Iron Engineer system and principal is used to handle user context.
    // Expected Result: A competitor joins the given team and the addition is reflected in the Iron Engineer database.
    // Description: A competitor is added to the team represented by teamId. The team and competitor information in the Iron Engineer database and system is modified to reflect the addition.
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

    // Function Name: createTeam
    // Parameters: None.
    // Expected Result: An Iron Engineer team view is created and displayed to the user to create a new team.
    // Description: A view for creating a new team is returned to the user. The user may use this view to name their team and register it to the Iron Engineer system.
    // Notes: Works in conjunction with addTeam.
    @RequestMapping("/create")
    public ModelAndView createTeam(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("newTeam", new Team());

        //TODO
        //if they are already on a team, redirect them to the home page

        return modelAndView;
    }

    // Function Name: addTeam
    // Parameters: newTeam (Team), result (BindingResult), model (ModelMap), principal (Principal). newTeam is a Team object that is used to collect and store team information. result, model, and principal are used
    // to handle user context.
    // Expected Result: A new team is created and added to the Iron Engineer database and system.
    // Description: A new team is created and added to the database, with the included details that the competitor has entered such as team name.
    // Notes: When a team is initially created it is set to unapproved. Unapproved teams are not publicly displayed until approved by an administrator.
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
