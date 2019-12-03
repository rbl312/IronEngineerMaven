package com.COEN174.IronEngineer.controllers;

import com.COEN174.IronEngineer.entities.Competitor;
import com.COEN174.IronEngineer.entities.Log;
import com.COEN174.IronEngineer.entities.Team;
import com.COEN174.IronEngineer.repositories.CompetitorRepository;
import com.COEN174.IronEngineer.repositories.LogRepository;
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

    @Autowired
    private LogRepository logRepository;

    // Function Name: adminView
    // Parameters: Principal of type Principal. Principal is used to retrieve user context (name,email, etc.) provided by Google login API.
    // Expected Result: The home page for an administrator is presented. The home page will contain the administrator's name,
    // name and administrator tools.
    // Description: Returns the administrator home page for an administrator user.
    // Notes: Non-administrator competitors attempting to access this page will be redirected to the competitor home page.
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

    @RequestMapping("/approve/log")
    public ModelAndView viewLogs(Principal principal){
        ModelAndView modelAndView = new ModelAndView("approveLogs");
        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        String userName = (String) details.get("name");
        String userEmail =  (String) details.get("email");
        Competitor c = competitorRepository.findByEmail(userEmail);
        if(c.getIsAdmin() != true){
            return new ModelAndView("redirect:/home");
        }


        Iterable<Log> logs= logRepository.findAll();
        List<Log> approveLogs = new ArrayList<>();
        for (Log log : logs) {
            if(!log.isApproved())
                approveLogs.add(log);
        }

        modelAndView.addObject("approveLogs", approveLogs);

        return modelAndView;
    }

    @RequestMapping("/approve/log/{logId}")
    public ModelAndView approveLog(Principal principal, @PathVariable("logId") Integer logId){
        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        String userName = (String) details.get("name");
        String userEmail =  (String) details.get("email");
        Competitor c = competitorRepository.findByEmail(userEmail);
        if(c.getIsAdmin() != true){
            return new ModelAndView("redirect:/home");
        }

        Log toApprove = logRepository.findByLogId(logId);
        toApprove.setApproved(true);
        logRepository.save(toApprove);

        return new ModelAndView("redirect:/admin/approve/log");
    }

    @RequestMapping("/disapprove/log/{logId}")
    public ModelAndView disapproveLog(Principal principal, @PathVariable("logId") Integer logId){
        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        String userName = (String) details.get("name");
        String userEmail =  (String) details.get("email");
        Competitor c = competitorRepository.findByEmail(userEmail);
        if(c.getIsAdmin() != true){
            return new ModelAndView("redirect:/home");
        }

        Log toApprove = logRepository.findByLogId(logId);
        Optional<Competitor> toModify = competitorRepository.findById(toApprove.getCompetitorId());
        toModify.get().addDistanceBiked(toApprove.getDistanceBiked()*-1);
        toModify.get().addDistanceSwam(toApprove.getDistanceSwam()*-1);
        toModify.get().addDistanceRan(toApprove.getDistanceRan()*-1);
        competitorRepository.save(toModify.get());
        logRepository.delete(toApprove);

        return new ModelAndView("redirect:/admin/approve/log");
    }


    // Function Name: showAllTeams
    // Parameters: Principal of type Principal. Principal is used to retrieve user context (name,email, etc.) provided by Google login API.
    // Expected Result: All teams currently in the system are displayed. Teams will be divided by approved and unapproved and will display other
    // important information about the teams.
    // Description: Returns a list of all teams currently in the system, separated by approved and unapproved team names.
    // Notes: only admins may access this list. From this list administrator tools are available to modify a team as needed.
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

    // Function Name: approveTeam
    // Parameters: None
    // Expected Result: Unapproved teams are displayed to the administrator, allowing them to select a team to approve.
    // Notes: Selecting a team to approve will approve the team's name and register the team for the competition.
    @RequestMapping("/approve")
    public ModelAndView approveTeam(Principal principal){
        ModelAndView modelAndView = new ModelAndView("approveTeam");
        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        String userName = (String) details.get("name");
        String userEmail =  (String) details.get("email");
        Competitor c = competitorRepository.findByEmail(userEmail);
        if(c.getIsAdmin() != true){
            return new ModelAndView("redirect:/home");
        }


        Iterable<Team> teams= teamRepository.findAll();
        List<Team> approveTeams = new ArrayList<>();
        for (Team team : teams) {
            if(team.getApproved() == 0)
                approveTeams.add(team);
        }

        modelAndView.addObject("approveTeams", approveTeams);

        return modelAndView;
    }

    // Function Name: joinTeam
    // Parameters: teamId and principal. teamId is an Integer that represents the primary key of the team to approve in the system
    // database. Principal is of type Principal. Principal is used to retrieve user context (name,email, etc.) provided by Google login API
    // Expected Result: The given team is approved and registered for the competition.
    // Notes: Administrators are redirected to this page from /admin/approve. On a successful team approval, the administrator is
    // returned to /admin/approve and ready to approve another team.
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

    // Function Name: showAllTeams
    // Parameters: None.
    // Expected Result: All teams currently in the system are displayed. Teams will be divided by approved and unapproved and will display other
    // important information about the teams.
    // Description: Returns a list of all teams currently in the system, separated by approved and unapproved team names.
    // Notes: only admins may access this list. From this list administrator tools are available to modify a team as needed.
    @RequestMapping(value = "/team/view")
    public ModelAndView viewTeam(Principal principal){
        ModelAndView modelAndView = new ModelAndView("viewTeam");

        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        String userName = (String) details.get("name");
        String userEmail =  (String) details.get("email");
        Competitor c = competitorRepository.findByEmail(userEmail);
        if(c.getIsAdmin() != true){
            return new ModelAndView("redirect:/home");
        }

        Iterable<Team> teams = teamRepository.findAll();
        List<Team> allTeams = new ArrayList<>();
        for(Team team : teams){
            allTeams.add(team);
        }
        modelAndView.addObject("viewTeam", allTeams);

        return modelAndView;
    }

    // Function Name: viewTeamMembers
    // Parameters: teamId, representing the primary key of a team
    // Expected Result: the team members of a given team are displayed in a view to the administrator
    // Description: Returns the provided team object to be used in displaying the team mates in the model
    // Notes: Works in conjunction with viewTeam
    @RequestMapping(value = "/team/view/{teamId}")
    public ModelAndView viewTeamMembers(@PathVariable("teamId") Integer teamId, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("viewTeamMembers");
        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        String userName = (String) details.get("name");
        String userEmail =  (String) details.get("email");
        Competitor c = competitorRepository.findByEmail(userEmail);
        if(c.getIsAdmin() != true){
            return new ModelAndView("redirect:/home");
        }

        Team chosenTeam = teamRepository.findByTeamId(teamId);
        modelAndView.addObject("team", chosenTeam);
        return modelAndView;
    }

    // Function Name: removeTeam
    // Parameters: teamId (Integer), representing the primary key of a team, and principal (Principal) to handle user context
    // Expected Result: A team is removed from the Iron Engineer database and competition.
    // Description: Removes a team from the Iron Engineer database and competition
    // Notes: All users are removed from the deleted team
    @RequestMapping(value = "/remove/team/{teamId}",method = RequestMethod.GET)
    public ModelAndView removeTeam(@PathVariable("teamId") Integer teamId,Principal principal){

        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        String userName = (String) details.get("name");
        String userEmail =  (String) details.get("email");
        Competitor c = competitorRepository.findByEmail(userEmail);
        if(c.getIsAdmin() != true){
            return new ModelAndView("redirect:/home");
        }

        Team t = teamRepository.findByTeamId(teamId);

        for(Competitor comp :t.getCompetitors()){
            t.removeTeamMember(comp);
            comp.setTeamIdFK(null);
            competitorRepository.save(comp);
        }
        teamRepository.deleteById(t.getTeamId());
        return new ModelAndView("redirect:/admin/team/view");
    }

    // Function Name: removeCompetitor
    // Parameters: competitorId (Integer), result (BindingResult) and principal (Principal).
    // competitorId is the primary key used to identify a competitor. result and principal is used to handle user context.
    // Expected Result: a competitor is removed from the Iron Engineer database and competition.
    // Description: deletes a competitor from the Iron Engineer database and competition.
    // Notes: Administrator competitors cannot be deleted using this function. To delete an administrator direct database access is necessary.
    // The deleted competitor is removed from any team they may have joined.
    @RequestMapping(value = "/team/remove/competitor/{competitorId}",method = RequestMethod.GET)
    public ModelAndView removeCompetitor(@PathVariable("competitorId") Integer competitorId,Principal principal){
        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        Competitor c;

        if(competitorRepository.findById(competitorId).isPresent()){
            c = competitorRepository.findById(competitorId).get();
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
            c.setTeamIdFK(null);
            teamRepository.save(team);
        }

        competitorRepository.save(c);

        return new ModelAndView("redirect:/admin/team/view");

    }


    // Function Name: addAdmins
    // Parameters: None.
    // Description: Prepares the form used to promote a competitor to an administrator account.
    // Notes: Works in conjunction with addAdmin
    @RequestMapping(value="/addadmin",method = RequestMethod.GET)
    public ModelAndView addAdmins(Principal principal){

        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        String userName = (String) details.get("name");
        String userEmail =  (String) details.get("email");
        Competitor c = competitorRepository.findByEmail(userEmail);
        if(c.getIsAdmin() != true){
            return new ModelAndView("redirect:/home");
        }

        ModelAndView modelAndView = new ModelAndView("addAdmin");
        modelAndView.addObject("newAdmin",new Competitor());

        return modelAndView;
    }

    // Function Name: addAdmins
    // Parameters: newAdmin (Competitor), model (ModelMap), and principal (Principal). newAdmin is the competitor that is to be
    // promoted to an administrator. model and principal are used to handle user context.
    // Expected Result: A competitor is promoted to an Administrator account and allowed to use administrator functions.
    // Description: Promotes a competitor to an administrator account and grants them administrator privileges
    // Notes: To promote a user to an administrator, the administrator must know the email of the user.
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
