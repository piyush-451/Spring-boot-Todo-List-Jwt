package com.in28minutes.sprintboot.todo_list.Repository;

import com.in28minutes.sprintboot.todo_list.DataModels.Todo;
import org.springframework.lang.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Integer> {
    List<Todo> findByUsername(String username);

//    @NonNull
//    List<Todo> findAll();

}
