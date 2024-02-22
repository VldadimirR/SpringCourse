package com.example.demo.task.factory;

import com.example.demo.task.enumeration.Type;

public class MyTaskFactory {

    public TaskFactory getTaskFactory(Type type) {

        return switch (type) {
            case REGULAR -> new RegularTaskFactory();
            case SPECIAL -> new SpecialTaskFactory();
        };
    }
}
