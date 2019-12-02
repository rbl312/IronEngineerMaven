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

    // Function Name: user
    // Parameters: principal (Principal), used to handle user context.
    // Expected Result: user's principal is returned.
    // Description: principal is returned for the user, allowing for access to user context.
    // Notes: Principal is used to handle user context such as email, name, password, etc.
    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

    // Function Name: logout
    // Parameters: request (HttpServletRequest), and response (HttpServletResponse). request and response are used to handle the HTTP requests for logging a user out.
    // Expected Result: The user is logged out of the Iron Engineer system.
    // Description: User is logged out of the Iron Engineer system and returned to the sign in page.
    // Notes: User is authenticated before logging out to ensure security.
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logout (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ModelAndView("redirect:/");
    }

    // Function Name: configure
    // Parameters: http (HttpSecurity) used to handle HTTP during configuration.
    // Expected Result: HTTP requests are authorized and configured properly by the system.
    // Description: Properly authorizes and configures incoming HTTP requests to the Iron Engineer system.
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