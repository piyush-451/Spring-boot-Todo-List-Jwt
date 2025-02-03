package com.in28minutes.sprintboot.todo_list.DataModels.Dto;

import com.in28minutes.sprintboot.todo_list.DataModels.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class TodoMapper {
    public Todo toTodoEntity(TodoRequest request) {
        return Todo.builder()
                .description(request.getDescription())
//                .dueDate(LocalDate.parse(request.getDueDate()))
                .dueDate(request.getDueDate())
                .isCompleted(request.isCompleted())
                .build();
    }

    public TodoRequest toTodoResponse(Todo newTask) {
        return TodoRequest.builder()
                .id(newTask.getId())
                .description(newTask.getDescription())
//                .dueDate(newTask.getDueDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .dueDate(newTask.getDueDate())
                .isCompleted(newTask.isCompleted())
                .build()
                .addHateoasLink();
    }
}
