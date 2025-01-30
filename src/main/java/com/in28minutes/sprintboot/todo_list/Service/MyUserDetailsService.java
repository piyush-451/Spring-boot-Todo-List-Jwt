package com.in28minutes.sprintboot.todo_list.Service;

import com.in28minutes.sprintboot.todo_list.DataModels.MyUserDetails;
import com.in28minutes.sprintboot.todo_list.DataModels.User;
import com.in28minutes.sprintboot.todo_list.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepo.findByUsername(username);
            if (user == null) {
                System.out.println("User not found: " + username);
                throw new UsernameNotFoundException("User not found with username: " + username);
            } else {
//                System.out.println("The user extracted from DB: " + user);
                return new MyUserDetails(user.getUsername(), user.getPassword(),user.getRoleList());
            }
        } catch (UsernameNotFoundException e) {
            System.err.println("Exception caught: " + e.getMessage());
            throw e;
        }
    }
}
