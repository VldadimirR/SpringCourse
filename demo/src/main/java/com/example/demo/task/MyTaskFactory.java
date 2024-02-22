package com.example.demo.task;

public class MyTaskFactory {

    public TaskFactory getTaskFactory(Type type) {
        return switch (type) {
            case REGULAR -> new RegularTaskFactory();
            case SPECIAL -> new SpecialTaskFactory();
        };
    }
}
