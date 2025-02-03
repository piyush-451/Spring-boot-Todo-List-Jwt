package com.in28minutes.sprintboot.todo_list.Controllers;

import com.in28minutes.sprintboot.todo_list.DataModels.Dto.RegistrationRequest;
import com.in28minutes.sprintboot.todo_list.DataModels.User;
import com.in28minutes.sprintboot.todo_list.Service.SignUpService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping("/signup")
    public String getSignUp(){
        return "signUp page";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> postSignUp(
            @RequestBody @Valid RegistrationRequest request
    ) throws Exception {

        System.out.println("Request: " + request);
        return ResponseEntity.ok().body(signUpService.register(request));
    }
}
