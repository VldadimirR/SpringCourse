package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ErrorHandlingAspect {

    @Around("@annotation(HandleError)")
    public Object handleErrors(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Exception e) {

            System.out.println("An error occurred: " + e.getMessage());
            throw e;
        }
    }
}