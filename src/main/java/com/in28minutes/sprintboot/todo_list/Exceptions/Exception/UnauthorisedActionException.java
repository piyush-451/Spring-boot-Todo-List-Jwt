package com.in28minutes.sprintboot.todo_list.Exceptions.Exception;

public class UnauthorisedActionException extends RuntimeException {
    public UnauthorisedActionException(String message) {
        super(message);
    }
}
