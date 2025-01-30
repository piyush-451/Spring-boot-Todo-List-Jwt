package com.in28minutes.sprintboot.todo_list.Service;

import com.in28minutes.sprintboot.todo_list.DataModels.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    public Optional<String> verifyUser(User user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        try{
            if(authentication.isAuthenticated()){
                System.out.println("authenticated");
                return Optional.ofNullable(jwtService.genarateToken(user.getUsername()));
            }
        }
        catch (Exception e){
            System.out.println("Authentication failed: " + e.getMessage());
        }

        System.out.println("user is not logged in : ");
        return Optional.empty();
    }
}
