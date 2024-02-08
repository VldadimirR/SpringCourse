package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class FileLogger {

    private static final Logger logger = LoggerFactory.getLogger(FileLogger.class);
    private static final String LOG_FILE_PATH = "demo/src/main/java/com/example/demo/user_actions.log";

    public void logToFile(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE_PATH, true)) {
            writer.write(message);
        } catch (IOException e) {
            logger.error("Failed to write to log file", e);
        }
    }
}
