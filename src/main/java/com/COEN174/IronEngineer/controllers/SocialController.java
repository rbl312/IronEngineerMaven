package com.COEN174.IronEngineer.controllers;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@EnableOAuth2Sso
@RestController
public class SocialController extends WebSecurityConfigurerAdapter {

    // Function Name:
    // Parameters:
    // Description:
    // Notes:
    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

    // Function Name:
    // Parameters:
    // Description:
    // Notes:
    //to logout the user, create a button sending them to /logout
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logout (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ModelAndView("redirect:/");
    }

    // Function Name:
    // Parameters:
    // Description:
    // Notes:
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login**", "/webjars/**", "/error**", "/index.html").permitAll().anyRequest()
                .authenticated().and().logout().logoutSuccessUrl("/index.html").permitAll().and().csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        // @formatter:on
    }

}