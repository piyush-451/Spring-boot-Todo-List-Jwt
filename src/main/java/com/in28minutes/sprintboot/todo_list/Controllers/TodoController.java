package com.in28minutes.sprintboot.todo_list.Controllers;

import com.in28minutes.sprintboot.todo_list.DataModels.Dto.TodoRequest;
import com.in28minutes.sprintboot.todo_list.DataModels.Todo;
import com.in28minutes.sprintboot.todo_list.Service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/create")
    public ResponseEntity<String> getTodo(){
        return ResponseEntity.ok("Enter your Task");
    }

    @GetMapping("/todo")
    public ResponseEntity<List<Todo>> getTodoOfUser(){
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getTodosOfUser());
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<TodoRequest> getTodoById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getTodoById(id));
    }

    @PostMapping("/todo")
    public ResponseEntity<TodoRequest> postTodo(@RequestBody @Valid TodoRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.addTodo(request));
    }

    @DeleteMapping("/delete-todo/{id}")
    public ResponseEntity<Todo> deleteTodo(@PathVariable String id){
        return ResponseEntity.ok().body(todoService.deleteTodo(id));
    }

    @PatchMapping("/todo/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String id, @RequestBody Todo updatedTask){
        return ResponseEntity.ok().body(todoService.updateTodo(id,updatedTask));
    }

}
