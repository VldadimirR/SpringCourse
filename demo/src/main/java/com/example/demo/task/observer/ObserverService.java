package com.example.demo.task.observer;

import com.example.demo.task.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObserverService {

    private  List<TaskObserver> observers;

    public ObserverService(List<TaskObserver> observers) {
        this.observers = observers;
    }

    public void registerObserver(TaskObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TaskObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Task task) {
        for (TaskObserver observer : observers) {
            observer.update(task);
        }
    }
}
