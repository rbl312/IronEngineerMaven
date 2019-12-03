package com.COEN174.IronEngineer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@ComponentScan(basePackages = {"com.COEN174.IronEngineer.controllers", "com.COEN174.IronEngineer.entities", "com.COEN174.IronEngineer.repositories"})
public class IronEngineerApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(IronEngineerApplication.class);
	}


	public static void main(String[] args) {
		SpringApplication.run(IronEngineerApplication.class, args);
	}
}
