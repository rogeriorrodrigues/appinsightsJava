package com.azureinsights.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @RequestMapping("/")
    public String index() {

        logger.warn("This is a WARN message.");
        logger.error("This is an ERROR message.");

        return "Welcome to Spring Logging! Check the console to see the log messages.";
    }
}