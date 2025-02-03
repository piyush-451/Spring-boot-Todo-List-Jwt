package com.in28minutes.sprintboot.todo_list.Exceptions.Exception;

public class ResourceAlreadyPresentException extends RuntimeException {
    public ResourceAlreadyPresentException(String message) {
        super(message);
    }
}
