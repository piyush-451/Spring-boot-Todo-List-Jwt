package com.in28minutes.sprintboot.todo_list.Service;

import com.in28minutes.sprintboot.todo_list.DataModels.Dto.RegistrationMapper;
import com.in28minutes.sprintboot.todo_list.DataModels.Dto.RegistrationRequest;
import com.in28minutes.sprintboot.todo_list.DataModels.Role;
import com.in28minutes.sprintboot.todo_list.DataModels.User;
import com.in28minutes.sprintboot.todo_list.Exceptions.Exception.ResourceAlreadyPresentException;
import com.in28minutes.sprintboot.todo_list.Exceptions.Exception.ResourceNotFoundException;
import com.in28minutes.sprintboot.todo_list.Repository.RoleRepo;
import com.in28minutes.sprintboot.todo_list.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignUpService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    private final RegistrationMapper registrationMapper;

    public RegistrationRequest register(RegistrationRequest request){

        User newUser  = registrationMapper.toUser(request);
        Optional<String> username = Optional.ofNullable(userRepo.findByUsername(newUser.getUsername())).map(User::getUsername);
        if(username.isPresent()){
            throw new ResourceAlreadyPresentException( "username already taken");
        }
        else{
            Role userRole = roleRepo.findByRolename("ROLE_USER").orElseThrow(() -> new ResourceNotFoundException("role not found"));
            newUser.getRoleList().add(userRole);
            userRole.getUserList().add(newUser);
            newUser = userRepo.save(newUser);
//            roleRepo.save(userRole);
            System.out.println("User saved: " + newUser);
            return registrationMapper.toUserResponse(newUser);
        }
    }
}
