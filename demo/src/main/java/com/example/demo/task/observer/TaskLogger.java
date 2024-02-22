package com.example.demo.task.observer;

import com.example.demo.task.Task;

public class TaskLogger implements TaskObserver {
    @Override
    public void update(Task task) {
        System.out.println("Task updated - Description: " + task.getDescription() + ", Completed: " + task.getStatus());
    }
}