package com.in28minutes.sprintboot.todo_list.Service;

import com.in28minutes.sprintboot.todo_list.Annotations.ValidateTask;
import com.in28minutes.sprintboot.todo_list.DataModels.Todo;
import com.in28minutes.sprintboot.todo_list.DataModels.User;
import com.in28minutes.sprintboot.todo_list.Repository.TodoRepo;
import com.in28minutes.sprintboot.todo_list.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepo todoRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;


    public List<Todo> getAllTodo() {
        return todoRepo.findAll();
    }

    @ValidateTask
    public boolean addTodo(Todo newTask){
        String username = userService.getCurrentUserDetails().getUsername();
        User user = userRepo.findByUsername(username);
        newTask.setUsername(username);
        newTask.setUser(user);
        userRepo.save(user);
        todoRepo.save(newTask);
        user.getTodoList().add(newTask);
        return true;
    }

    public List<Todo> getTodosOfUser(){
        String username = userService.getCurrentUserDetails().getUsername();
        return todoRepo.findByUsername(username);
    }

    public Todo deleteTodo(String id) {
        Todo deletedItem = todoRepo.findById(Integer.parseInt(id)).orElseThrow(() -> new RuntimeException("Item not found"));
        todoRepo.delete(deletedItem);
        return deletedItem;

    }

    public Todo updateTodo(String id, Todo updatedTask) {
        Todo newUpdatedTask = todoRepo.findById(Integer.parseInt(id)).orElseThrow(() -> new RuntimeException("Item not found"));
        newUpdatedTask.setDescription(updatedTask.getDescription());
        newUpdatedTask.setDueDate(updatedTask.getDueDate());
        newUpdatedTask.setCompleted(updatedTask.isCompleted());

        todoRepo.save(newUpdatedTask);

        return newUpdatedTask;
    }
}
