package com.in28minutes.sprintboot.todo_list.AOP;

import com.in28minutes.sprintboot.todo_list.Annotations.CheckDateFormat;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CheckDateFormatValidate implements ConstraintValidator<CheckDateFormat, String> {

    private String pattern;

    @Override
    public void initialize(CheckDateFormat constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("Date: " + date);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            // Attempt to parse the date using the formatter
            formatter.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
