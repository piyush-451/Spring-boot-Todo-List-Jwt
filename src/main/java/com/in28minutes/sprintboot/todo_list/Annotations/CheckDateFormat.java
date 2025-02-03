package com.in28minutes.sprintboot.todo_list.Annotations;

import com.in28minutes.sprintboot.todo_list.AOP.CheckDateFormatValidate;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CheckDateFormatValidate.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckDateFormat {
    String message() default "Invalid Date Format";
    String pattern() default "yyyy-MM-dd";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
