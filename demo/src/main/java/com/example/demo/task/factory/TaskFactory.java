package com.example.demo.task.factory;

import com.example.demo.task.Task;
import com.example.demo.task.enumeration.Type;

public interface TaskFactory {
    Task createTask(String description , Type type);
}