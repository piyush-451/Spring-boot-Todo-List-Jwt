package com.in28minutes.sprintboot.todo_list.Repository;

import com.in28minutes.sprintboot.todo_list.DataModels.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    boolean existsByUsername(String user);
}
