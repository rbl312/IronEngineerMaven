package com.COEN174.IronEngineer.controllers;

import com.COEN174.IronEngineer.entities.Competitor;
import com.COEN174.IronEngineer.entities.Log;

import com.COEN174.IronEngineer.repositories.CompetitorRepository;
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

    @RequestMapping("/log")
    public ModelAndView enterLogView() {

        ModelAndView modelAndView = new ModelAndView("log");
        modelAndView.addObject("newLog", new Log());

        return modelAndView;
    }

    @RequestMapping(value = "/addLog", method = RequestMethod.POST)
    public ModelAndView addLog(@Valid @ModelAttribute("newLog")Log newLog, BindingResult result, ModelMap model, Principal principal){
        //Add new team to database
        //We should have this user Id from their user context when theyre logged in
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
        }

        user.setDistanceRan(user.getDistanceRan()+newLog.getDistanceRan());
        user.setDistanceBiked(user.getDistanceBiked()+newLog.getDistanceBiked());
        user.setDistanceSwam(user.getDistanceSwam()+newLog.getDistanceSwam());
        competitorRepository.save(user);
        return new ModelAndView("redirect:/home");
    }
}