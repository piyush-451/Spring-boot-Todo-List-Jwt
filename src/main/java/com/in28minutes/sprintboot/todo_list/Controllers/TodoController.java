package com.in28minutes.sprintboot.todo_list.Controllers;

import com.in28minutes.sprintboot.todo_list.DataModels.Todo;
import com.in28minutes.sprintboot.todo_list.Service.TodoService;
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

    @PostMapping("/todo")
    public ResponseEntity<?> postTodo(@RequestBody Todo newTask){
        if(todoService.addTodo(newTask))
            return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("unable to enter new task . try again");
    }

    @DeleteMapping("/delete-todo/{id}")
    public ResponseEntity<Todo> deleteTodo(@PathVariable String id){
        return ResponseEntity.ok().body(todoService.deleteTodo(id));
    }

    @PutMapping("/todo/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String id, @RequestBody Todo updatedTask){
        return ResponseEntity.ok().body(todoService.updateTodo(id,updatedTask));
    }

}
