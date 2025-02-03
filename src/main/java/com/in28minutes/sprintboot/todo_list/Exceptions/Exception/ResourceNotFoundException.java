package com.in28minutes.sprintboot.todo_list.Exceptions.Exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
