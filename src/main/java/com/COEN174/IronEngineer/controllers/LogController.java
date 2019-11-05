package com.COEN174.IronEngineer.controllers;

import com.COEN174.IronEngineer.entities.Competitor;
import com.COEN174.IronEngineer.entities.Log;

import com.COEN174.IronEngineer.repositories.CompetitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class LogController {

    @Autowired
    private CompetitorRepository competitorRepository;

    @RequestMapping("/log")
    public ModelAndView enterLogView() {

        ModelAndView modelAndView = new ModelAndView("log");
        modelAndView.addObject("newLog", new Log());

        return modelAndView;
    }

    @RequestMapping(value = "/addLog", method = RequestMethod.POST)
    public ModelAndView addLog(@Valid @ModelAttribute("newLog")Log newLog, BindingResult result, ModelMap model){
        //Add new team to database
        //We should have this user Id from their user context when theyre logged in
        String userEmail = "gshappell@scu.edu";
        Competitor user = competitorRepository.findByEmail(userEmail);
        if(user == null){
            System.out.println("NULLLLL !!!!!!! D:D:D:D");
            return new ModelAndView("redirect:/home");
        }
        if(newLog == null) {
            System.out.println("NEW LOG IS NULL");
        }
        System.out.println(newLog.getDistanceBiked());
        System.out.println(newLog.getDistanceRan());
        System.out.println(newLog.getDistanceSwam());
        System.out.println("USER STUFF");
        System.out.println(user.getDistanceBiked());
        System.out.println(user.getDistanceRan());
        System.out.println(user.getDistanceSwam());

        user.setDistanceRan(user.getDistanceRan()+newLog.getDistanceRan());
        user.setDistanceBiked(user.getDistanceBiked()+newLog.getDistanceBiked());
        user.setDistanceSwam(user.getDistanceSwam()+newLog.getDistanceSwam());
        competitorRepository.save(user);
        return new ModelAndView("redirect:/home");
    }
}