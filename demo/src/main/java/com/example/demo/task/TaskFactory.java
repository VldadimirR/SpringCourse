package com.example.demo.task;

import com.example.demo.work.Worker;

public interface TaskFactory {
    Task createTask(String description , Type type);
}