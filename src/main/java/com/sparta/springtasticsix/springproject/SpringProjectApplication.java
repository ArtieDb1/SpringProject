package com.sparta.springtasticsix.springproject;

import com.sparta.springtasticsix.springproject.logger.CustomLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class SpringProjectApplication {

    public static final Logger logger = Logger.getLogger(SpringProjectApplication.class.getName());

    public static void main(String[] args) {

        CustomLogger.setUpLogger(logger);

        SpringApplication.run(SpringProjectApplication.class, args);

        logger.log(Level.INFO,"This is a test output");
    }

}
