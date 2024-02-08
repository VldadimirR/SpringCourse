package com.example.demo.aspect;


import com.example.demo.util.FileLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Aspect
@Component
public class UserActionLoggerAspect {

    private final FileLogger fileLogger;
    private static final Logger logger = LoggerFactory.getLogger(UserActionLoggerAspect.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public UserActionLoggerAspect(FileLogger fileLogger) {
        this.fileLogger = fileLogger;
    }

    @Pointcut("@annotation(com.example.demo.aspect.TrackUserAction)")
    public void trackUserAction() {}

    @AfterReturning(pointcut = "trackUserAction()", returning = "result")
    public void logUserAction(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        logger.info("User '{}' action: Method '{}' in class '{}' called with arguments: {}",
                username, methodName, className, args);

        String logMessage = String.format("[%s] User '%s' action: Method '%s' in class '%s' called with arguments: %s%n",
                LocalDateTime.now().format(formatter), username, methodName, className, Arrays.toString(args));

        fileLogger.logToFile(logMessage);
    }

}
