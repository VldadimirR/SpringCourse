package com.example.demo.task;

import java.util.Date;

public class SpecialTaskFactory implements TaskFactory {
    @Override
    public Task createTask(String description, Type type) {

        // Дополнительная логика для создания специальной задачи

        return Task.builder()
                .description(description)
                .status(Status.ACTIVE)
                .type(type)
                .creationDate(new Date())
                .workers(null)
                .build();
    }
}