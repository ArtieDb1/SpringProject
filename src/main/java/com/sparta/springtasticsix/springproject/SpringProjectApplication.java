package com.sparta.springtasticsix.springproject;

import com.sparta.springtasticsix.springproject.controllers.CityController;
import com.sparta.springtasticsix.springproject.controllers.CountryController;
import com.sparta.springtasticsix.springproject.logger.CustomLogger;
import com.sparta.springtasticsix.springproject.model.entities.CityDTO;
import com.sparta.springtasticsix.springproject.model.repositories.CityRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class})
public class SpringProjectApplication {


    public static final Logger logger = Logger.getLogger(SpringProjectApplication.class.getName());

    public static void main(String[] args) {

        CustomLogger.setUpLogger(logger);

        SpringApplication.run(SpringProjectApplication.class, args);

        logger.log(Level.INFO,"This is a test output");

    }

}
