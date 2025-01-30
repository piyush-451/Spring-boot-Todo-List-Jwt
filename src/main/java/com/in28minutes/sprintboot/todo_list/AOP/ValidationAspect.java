package com.in28minutes.sprintboot.todo_list.AOP;

import com.in28minutes.sprintboot.todo_list.DataModels.Todo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Aspect
public class ValidationAspect {
    private final Logger logger = LoggerFactory.getLogger(ValidationAspect.class);
    @Around("@annotation(com.in28minutes.sprintboot.todo_list.Annotations.ValidateTask) && args(task,..)")
    public Object validateTask(ProceedingJoinPoint jp, Todo task) throws Throwable{
        if(task.getDescription() == null || task.getDescription().trim().isEmpty()){
            throw new IllegalArgumentException("Description can not be empty");
        }
        if(task.getDueDate() == null || task.getDueDate().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Date must be present or Date cannot be int the past");
        }

        logger.info("Task is Valid : {}" , task);
        return jp.proceed();
    }
}
