package com.sparta.springtasticsix.springproject.logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomConsoleHandler {
    public static void setUpConsoleHandler(Logger logger) {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
    }
}
