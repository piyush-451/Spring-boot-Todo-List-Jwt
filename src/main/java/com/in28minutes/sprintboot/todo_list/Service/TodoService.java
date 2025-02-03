package com.in28minutes.sprintboot.todo_list.Service;

import com.in28minutes.sprintboot.todo_list.Annotations.ValidateTask;
import com.in28minutes.sprintboot.todo_list.DataModels.Dto.TodoMapper;
import com.in28minutes.sprintboot.todo_list.DataModels.Dto.TodoRequest;
import com.in28minutes.sprintboot.todo_list.DataModels.Todo;
import com.in28minutes.sprintboot.todo_list.DataModels.User;
import com.in28minutes.sprintboot.todo_list.Exceptions.Exception.ResourceNotFoundException;
import com.in28minutes.sprintboot.todo_list.Exceptions.Exception.UnauthorisedActionException;
import com.in28minutes.sprintboot.todo_list.Repository.TodoRepo;
import com.in28minutes.sprintboot.todo_list.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TodoService {

    @Autowired
    private TodoRepo todoRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    private final TodoMapper todoMapper;


    public List<Todo> getAllTodo() {
        return todoRepo.findAll();
    }

    @ValidateTask
    public TodoRequest addTodo(TodoRequest request){
        Todo newTask = todoMapper.toTodoEntity(request);
        String username = userService.getCurrentUserDetails().getUsername();
        User user = userRepo.findByUsername(username);
        newTask.setUser(user);
        user.getTodoList().add(newTask);
        userRepo.save(user);
        newTask = todoRepo.save(newTask);
        return todoMapper.toTodoResponse(newTask);
    }

    public List<Todo> getTodosOfUser(){
        String username = userService.getCurrentUserDetails().getUsername();
        return todoRepo.findByUsername(username);
    }

    public Todo deleteTodo(String id) {
        String username = userService.getCurrentUserDetails().getUsername();
        Todo deletedItem = todoRepo.getTodoByIdAndUsername(Integer.parseInt(id),username).orElseThrow(() -> new RuntimeException("Item not found"));
        todoRepo.delete(deletedItem);
        return deletedItem;

    }

    public Todo updateTodo(String id, Todo updatedTask) {
        Todo newUpdatedTask = todoRepo.findById(Integer.parseInt(id))
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));

        String currentUsername = userService.getCurrentUserDetails().getUsername();

        if(!Objects.equals(newUpdatedTask.getUser().getUsername(), currentUsername)){
            throw new UnauthorisedActionException("you are not authorised to update this task");
        }

        for (Field field : Todo.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(updatedTask);
                if (value != null) {
                    field.set(newUpdatedTask, value);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error updating field: " + field.getName(), e);
            }
        }

        return todoRepo.save(newUpdatedTask);
    }

    public TodoRequest getTodoById(Integer id) {
        String username = userService.getCurrentUserDetails().getUsername();
        Todo todo = todoRepo.getTodoByIdAndUsername(id, username).orElseThrow(() -> new ResourceNotFoundException("Todo not found"));
        return todoMapper.toTodoResponse(todo);
    }
}
