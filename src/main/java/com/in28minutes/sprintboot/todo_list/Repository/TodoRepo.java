package com.in28minutes.sprintboot.todo_list.Repository;

import com.in28minutes.sprintboot.todo_list.DataModels.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Integer> {
    @Query("SELECT t FROM Todo t WHERE t.user.username = :username")
    List<Todo> findByUsername(@Param("username")String username);

    @Query("SELECT t FROM Todo t WHERE t.id = :id AND t.user.username = :username")
    Optional<Todo> getTodoByIdAndUsername(@Param("id") int id, @Param("username") String username);


//    @NonNull
//    List<Todo> findAll();

}
