package com.example.demo.config;

import com.example.demo.task.factory.MyTaskFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MyTaskFactory productFactory(){
        return new MyTaskFactory();
    }
}
