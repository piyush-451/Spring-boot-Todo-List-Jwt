package com.in28minutes.sprintboot.todo_list.Service;

import com.in28minutes.sprintboot.todo_list.DataModels.Role;
import com.in28minutes.sprintboot.todo_list.DataModels.User;
import com.in28minutes.sprintboot.todo_list.Repository.RoleRepo;
import com.in28minutes.sprintboot.todo_list.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SignUpService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    public boolean register(User newUser) throws Exception{

        Optional<String> username = Optional.ofNullable(userRepo.findByUsername(newUser.getUsername())).map(User::getUsername);
        if(username.isPresent()){
            throw new Exception("username already taken");
        }
        else{
            Role userRole = roleRepo.findByRolename("ROLE_USER");
            newUser.getRoleList().add(userRole);
            userRole.getUserList().add(newUser);
            userRepo.save(newUser);
//            roleRepo.save(userRole);
            return true;
        }
    }
}
