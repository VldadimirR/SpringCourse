package com.example.demo.task;

import com.example.demo.task.enumeration.Type;

public record TaskDTO(
        String description,
        Type type
) {
}
