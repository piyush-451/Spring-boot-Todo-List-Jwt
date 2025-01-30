package com.in28minutes.sprintboot.todo_list.Repository;

import com.in28minutes.sprintboot.todo_list.DataModels.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepo extends JpaRepository<Profile,Integer> {

}
