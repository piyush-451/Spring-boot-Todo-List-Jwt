package com.in28minutes.sprintboot.todo_list.Repository;

import com.in28minutes.sprintboot.todo_list.DataModels.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepo extends JpaRepository<Role,Integer> {
    boolean existsByName(String roleAdmin);

    @Query("SELECT r FROM Role r WHERE r.name = :roleName")
    Role findByRolename(@Param("roleName") String roleName);
}
