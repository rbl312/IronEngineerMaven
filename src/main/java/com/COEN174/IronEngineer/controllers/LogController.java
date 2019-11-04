package com.COEN174.IronEngineer.controllers;

import com.COEN174.IronEngineer.entities.Log;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class LogController {

    @RequestMapping("/log")
    public ModelAndView enterLogView() {

        ModelAndView modelAndView = new ModelAndView("log");
        modelAndView.addObject("newLog", new Log());

        return modelAndView;
    }

    @RequestMapping(value = "/addLog", method = RequestMethod.POST)
    public ModelAndView addTeam(@Valid @ModelAttribute("newLog")Log newLog, BindingResult result, ModelMap model){
        //Add new team to database
        //We should have this user Id from their user context when theyre logged in
        int userId = 123;
        //Competitor user = getCompetitorByUserId(userId);
        //competitor.addLog(newLog);
        return new ModelAndView("redirect:/home");
    }
}