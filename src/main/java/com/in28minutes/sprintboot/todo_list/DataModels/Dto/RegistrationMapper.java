package com.in28minutes.sprintboot.todo_list.DataModels.Dto;

import com.in28minutes.sprintboot.todo_list.DataModels.Profile;
import com.in28minutes.sprintboot.todo_list.DataModels.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RegistrationMapper {
    public User toUser(RegistrationRequest request) {
        return User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .roleList(new ArrayList<>())
                .build();
    }

    public RegistrationRequest toUserResponse(User newUser) {
        return RegistrationRequest.builder()
                .username(newUser.getUsername())
                .build();
    }
}
