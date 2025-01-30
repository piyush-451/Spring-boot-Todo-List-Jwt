package com.in28minutes.sprintboot.todo_list.Controllers;

import com.in28minutes.sprintboot.todo_list.DataModels.User;
import com.in28minutes.sprintboot.todo_list.Service.SignUpService;
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
            @RequestBody User newUser
    ) throws Exception {

        System.out.println("the new user : " + newUser + "\n\n");

        boolean signupSuccess = signUpService.register(newUser);
        if(signupSuccess) return ResponseEntity.ok().body(newUser);
        else return ResponseEntity.badRequest().body("Username already taken");
    }
}
