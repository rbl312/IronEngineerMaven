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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        if(user.getIsAdmin() != true){
            return new ModelAndView("redirect:/home");
        }

        ModelAndView modelAndView = new ModelAndView("admin");
        modelAndView.addObject("name", userName);
        modelAndView.addObject("isAdmin", user.getIsAdmin());
        return modelAndView;

    }

    @RequestMapping(value = "/allteam", method = RequestMethod.GET)
    public ModelAndView showAllTeams(Principal principal){
        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        String userName = (String) details.get("name");
        String userEmail =  (String) details.get("email");
        Competitor user = competitorRepository.findByEmail(userEmail);
        if(user.getIsAdmin() != true){
            return new ModelAndView("redirect:/home");
        }

        Iterable<Team> teams = teamRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("allTeams");
        List<Team> approvedTeams = new ArrayList<>();
        List<Team> unapprovedTeams = new ArrayList<>();

        for (Team team : teams) {
            if(team.getApproved() == 1) {
                if (team.getCompetitors().size() < 3)
                    approvedTeams.add(team);
            }
            else{
                unapprovedTeams.add(team);
            }
        }

        modelAndView.addObject("approvedTeams", approvedTeams);
        modelAndView.addObject("unapprovedTeams",unapprovedTeams);
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
        if(c.getIsAdmin() != true){
            return new ModelAndView("redirect:/home");
        }

        Team t = teamRepository.findByTeamId(teamId);
        t.setApproved(1);
        teamRepository.save(t);

        //redirect to home page
        return new ModelAndView("redirect:/admin/approve");
    }

    @RequestMapping(value = "/team/view")
    public ModelAndView viewTeam(){
        ModelAndView modelAndView = new ModelAndView("viewTeam");

        Iterable<Team> teams = teamRepository.findAll();
        List<Team> allTeams = new ArrayList<>();
        for(Team team : teams){
            allTeams.add(team);
        }
        modelAndView.addObject("viewTeam", allTeams);

        return modelAndView;
    }

    @RequestMapping(value = "/team/view/{teamId}")
    public ModelAndView viewTeamMembers(@PathVariable("teamId") Integer teamId) {
        ModelAndView modelAndView = new ModelAndView("viewTeamMembers");

        Team chosenTeam = teamRepository.findByTeamId(teamId);
        modelAndView.addObject("team", chosenTeam);
        return modelAndView;
    }
    @RequestMapping(value = "/remove/team/{team_id}",method = RequestMethod.GET)
    public ModelAndView removeTeam(@PathVariable("team_id") Integer team_id,Principal principal){

        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        String userName = (String) details.get("name");
        String userEmail =  (String) details.get("email");
        Competitor c = competitorRepository.findByEmail(userEmail);
        if(c.getIsAdmin() != true){
            return new ModelAndView("redirect:/home");
        }

        Team t = teamRepository.findByTeamId(team_id);

        for(Competitor comp :t.getCompetitors()){
            t.removeTeamMember(comp);
            comp.setTeamIdFK(null);
            competitorRepository.save(comp);
        }
        teamRepository.deleteById(t.getTeamId());
        return new ModelAndView("redirect:/admin/team/view");
    }

    @RequestMapping(value = "/delete/competitor/{competitor_id}",method = RequestMethod.GET)
    public ModelAndView deleteCompetitor(@PathVariable("competitor_id") Integer competitor_id,Principal principal){
        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        Competitor c;

        if(competitorRepository.findById(competitor_id).isPresent()){
            c = competitorRepository.findById(competitor_id).get();
        }
        else{
            //TODO: return an erroR page here
            return new ModelAndView("redirect:/home");
        }
        if(c.getIsAdmin() == true){
            //todo: cant deelte admin error page
            return new ModelAndView("redirect:/home");
        }
        if(c.getTeamIdFK()!=null){
            Team team = teamRepository.findByTeamId(c.getTeamIdFK());
            team.removeTeamMember(c);
        }
        competitorRepository.delete(c);

        return new ModelAndView("redirect:/admin/team/view");

    }

    @RequestMapping(value = "/team/remove/competitor/{competitor_id}",method = RequestMethod.GET)
    public ModelAndView removeCompetitor(@PathVariable("competitor_id") Integer competitor_id, BindingResult result,Principal principal){
        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        Competitor c;

        if(competitorRepository.findById(competitor_id).isPresent()){
            c = competitorRepository.findById(competitor_id).get();
        }
        else{
            //TODO: return an erroR page here
            return new ModelAndView("redirect:/home");
        }
        if(c.getTeamIdFK()!=null){
            Team team = teamRepository.findByTeamId(c.getTeamIdFK());
            team.removeTeamMember(c);
            c.setTeamIdFK(null);
            teamRepository.save(team);
        }

        competitorRepository.save(c);

        return new ModelAndView("redirect:/admin/team/view");

    }

    @RequestMapping(value="/addadmin",method = RequestMethod.GET)
    public ModelAndView addAdmins(){
        ModelAndView modelAndView = new ModelAndView("addAdmin");
        modelAndView.addObject("newAdmin",new Competitor());

        return modelAndView;
    }

    @RequestMapping(value = "/addadmin/promote", method = RequestMethod.POST)
    public ModelAndView addAdmin(@Valid @ModelAttribute("newAdmin") Competitor newAdmin, ModelMap model, Principal principal){
        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        String adminEmail = (String) details.get("email");
        String userEmail = newAdmin.getEmail();
        Competitor admin = competitorRepository.findByEmail(adminEmail);

        if(admin.getIsAdmin() != true){
            return new ModelAndView("redirect:/home");
        }

        Competitor promotedAdmin = competitorRepository.findByEmail(userEmail);

        if(promotedAdmin == null) {
            return new ModelAndView("redirect:/home");
        }

        if(promotedAdmin.getTeamIdFK() != null){
            Team t = teamRepository.findByTeamId(promotedAdmin.getTeamIdFK());
            t.removeTeamMember(promotedAdmin);
            teamRepository.save(t);
        }

        promotedAdmin.setIsAdmin(true);
        competitorRepository.save(promotedAdmin);

        return new ModelAndView("redirect:/admin/addadmin");
    }


}
