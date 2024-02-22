package com.example.demo.task.observer;

import com.example.demo.task.Task;

public interface TaskObserver {
    void update(Task task);
}