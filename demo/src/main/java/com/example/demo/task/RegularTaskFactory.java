package com.example.demo.task;

import java.util.Date;

public class RegularTaskFactory implements TaskFactory {
    @Override
    public Task createTask(String description, Type type) {

        return Task.builder()
                .description(description)
                .status(Status.ACTIVE)
                .type(type)
                .creationDate(new Date())
                .workers(null)
                .build();
    }
}