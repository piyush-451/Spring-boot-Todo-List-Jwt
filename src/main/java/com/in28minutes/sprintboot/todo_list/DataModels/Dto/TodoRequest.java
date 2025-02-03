package com.in28minutes.sprintboot.todo_list.DataModels.Dto;

import com.in28minutes.sprintboot.todo_list.Annotations.CheckDateFormat;
import com.in28minutes.sprintboot.todo_list.Controllers.TodoController;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Getter
@Setter
@Builder
public class TodoRequest extends RepresentationModel<TodoRequest> {
    private Integer id;

    @NotBlank(message = "Description is required")
    @NotBlank(message = "Description is required")
//    @Pattern(regexp = "^[a-zA-Z0-9]{5,}$", message = "Description must be at least 5 characters long and contain only letters and numbers")
    @Size(min = 5, message = "Description must be at least 5 characters long")
    private String description;

    @NotNull(message = "Due date is required")
//    @CheckDateFormat(message = "Due date must be in the format yyyy-mm-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "Due date must be in the format yyyy-mm-dd")
    @FutureOrPresent(message = "Due date cannot be in the past")
    private LocalDate dueDate;

    private boolean isCompleted = false;

    public TodoRequest addHateoasLink() {
        add(linkTo(methodOn(TodoController.class).getTodoById(id)).withSelfRel());
        add(linkTo(methodOn(TodoController.class).getTodoOfUser()).withRel("All Todos").withType("GET"));
        add(linkTo(methodOn(TodoController.class).deleteTodo(String.valueOf(id))).withRel("Delete Todo").withType("DELETE"));
        add(linkTo(methodOn(TodoController.class).updateTodo(String.valueOf(id), null)).withRel("Update Todo").withType("PATCH"));
        return this;
    }
}
