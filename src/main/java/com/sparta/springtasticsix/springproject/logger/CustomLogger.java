package com.sparta.springtasticsix.springproject.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomLogger {

    public static void setUpLogger(Logger logger) {
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);
        CustomConsoleHandler.setUpConsoleHandler(logger);
    }
}
