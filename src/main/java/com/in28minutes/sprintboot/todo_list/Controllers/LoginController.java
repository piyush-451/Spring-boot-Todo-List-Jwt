package com.in28minutes.sprintboot.todo_list.Controllers;

import com.in28minutes.sprintboot.todo_list.DataModels.User;
import com.in28minutes.sprintboot.todo_list.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


//    private final AuthenticationService authenticationService;
//
//    public LoginController(AuthenticationService authenticationService) {
//        this.authenticationService = authenticationService;
//    }

    @GetMapping("/login")
    public String getLogin(){
        System.out.println("from the controller");
        return "enter user details";
    }

    @PostMapping("/login")
    public ResponseEntity<String> postLogin(
            @RequestBody User user
    ){
//        if(authenticationService.authenticate(user))
//            return ResponseEntity.ok().body(user);
//        return ResponseEntity.badRequest().body("you are not registered please sign up");

        Optional<String> returnedToken = loginService.verifyUser(user);
        return returnedToken
                .map(token -> ResponseEntity.ok().body(token))
                .orElseGet(() -> ResponseEntity.badRequest().body("login failed"));

    }
}
