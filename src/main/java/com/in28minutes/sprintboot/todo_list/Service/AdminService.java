package com.in28minutes.sprintboot.todo_list.Service;

import com.in28minutes.sprintboot.todo_list.DataModels.Role;
import com.in28minutes.sprintboot.todo_list.DataModels.User;
import com.in28minutes.sprintboot.todo_list.Repository.RoleRepo;
import com.in28minutes.sprintboot.todo_list.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    public User assignAdminRole(String id) {
        Integer userId = Integer.parseInt(id);
        User toBeAdminUser = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));
        Role adminRole = roleRepo.findByRolename("ROLE_ADMIN");
        Optional<User> userFound = adminRole.getUserList().stream()
                .filter(existingUser -> Objects.equals(existingUser.getId(), userId))
                .findFirst();

        if(userFound.isPresent()){
            throw new RuntimeException("user is already admin");
        }

        adminRole.getUserList().add(toBeAdminUser);
        toBeAdminUser.getRoleList().add(adminRole);
        userRepo.save(toBeAdminUser);

        return toBeAdminUser;
    }
}
