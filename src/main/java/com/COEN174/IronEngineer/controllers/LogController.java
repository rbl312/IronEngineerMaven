package com.COEN174.IronEngineer.controllers;

import com.COEN174.IronEngineer.entities.Competitor;
import com.COEN174.IronEngineer.entities.Log;

import com.COEN174.IronEngineer.repositories.CompetitorRepository;
import com.COEN174.IronEngineer.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

@Controller
public class LogController {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    private CompetitorRepository competitorRepository;

    @Autowired
    private LogRepository logRepository;

    // Function Name: enterLogView
    // Parameters: None.
    // Expected Result: A view for entering distances into a user's log is returned.
    // Description: This function returns a view on the user's log that allows the user to update their distances completed for the competition.
    // Notes: Works in conjunction with addLog
    @RequestMapping("/log")
    public ModelAndView enterLogView() {

        ModelAndView modelAndView = new ModelAndView("log");
        modelAndView.addObject("newLog", new Log());

        return modelAndView;
    }

    // Function Name: addLog
    // Parameters: newLog (Log), result (BindingResult), model (ModelMap), principal (Principal). newLog is a Log object used for updating competitors distances in the database.
    // result, model, and principal are used to handle user context.
    // Expected Result: a new log of distances is submitted to the database, updating a competitor's completed distances for the competition.
    // Description: This function works in conjunction with enterLogView to display a log that users can use to update their distances completed.
    // Notes: The distances are measured in miles, and may contain decimals.
    @RequestMapping(value = "/addLog", method = RequestMethod.POST)
    public ModelAndView addLog(@Valid @ModelAttribute("newLog")Log newLog, BindingResult result, ModelMap model, Principal principal){

        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        Map<String, String> map = new LinkedHashMap<>();
        String userName =  (String) details.get("name");
        String userEmail = (String) details.get("email");

        Competitor user = competitorRepository.findByEmail(userEmail);
        if(user == null){
            LOGGER.log(Level.WARNING, "Unable to find Competitor in Database, redirecting to home");
            return new ModelAndView("redirect:/home");
        }
        if(newLog == null) {
            LOGGER.log(Level.WARNING, "Training Log is null");
            return new ModelAndView("redirect:/log");
        }

        //Check to make sure integrity box is checked
        //If not redirect the user back to the log
        if(!newLog.isIntegrityChecked()){
            return new ModelAndView("redirect:/log");
        }

        user.addDistanceRan(newLog.getDistanceRan());
        user.addDistanceBiked(newLog.getDistanceBiked());
        user.addDistanceSwam(newLog.getDistanceSwam());
        newLog.setCompetitorId(user.getCompetitorId());
        competitorRepository.save(user);
        logRepository.save(newLog);
        return new ModelAndView("redirect:/home");
    }
}