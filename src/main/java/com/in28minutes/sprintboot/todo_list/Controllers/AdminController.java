package com.in28minutes.sprintboot.todo_list.Controllers;

import com.in28minutes.sprintboot.todo_list.DataModels.Role;
import com.in28minutes.sprintboot.todo_list.DataModels.Todo;
import com.in28minutes.sprintboot.todo_list.DataModels.User;
import com.in28minutes.sprintboot.todo_list.Repository.RoleRepo;
import com.in28minutes.sprintboot.todo_list.Repository.UserRepo;
import com.in28minutes.sprintboot.todo_list.Service.AdminService;
import com.in28minutes.sprintboot.todo_list.Service.TodoService;
import com.in28minutes.sprintboot.todo_list.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;


@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private TodoService todoService;

    @PatchMapping("/assign-roles/{id}")
    ResponseEntity<String> assignAdmin(@PathVariable String id,@RequestBody List<String> roles){
        Collection<? extends GrantedAuthority> authorities = userService.getCurrentUserDetails().getAuthorities();
        System.out.println(authorities);
        User updatedUser = adminService.assignRoles(id,roles);
        String message = String.format("Role assigned to user: %s", updatedUser.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/todo-all")
    public ResponseEntity<List<Todo>> getAllTodo(){
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getAllTodo());
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAlLUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<String> deleteSingleUser(@PathVariable String id){
        return ResponseEntity.ok("user deleted : " + adminService.deleteSingleUser(id));
    }

    @PostMapping("/add-roles")
    public ResponseEntity<?> addRole(@RequestBody List<String> roles){
        return ResponseEntity.ok().body("roles added : " + String.join(", " , roles) + adminService.addRoles(roles));
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles(){
        return ResponseEntity.ok().body(adminService.getAllRoles());
    }

    @DeleteMapping("/delete-role/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable String id){
        return ResponseEntity.ok().body(adminService.deleteRole(id));
    }

    @GetMapping("/users-by-role/{id}")
    public ResponseEntity<?> getUsersByRole(@PathVariable String id){
        return ResponseEntity.ok().body(adminService.getUsersByRole(id));
    }

    @PatchMapping("revoke-roles/{id}")
    public ResponseEntity<?> revokeRoles(@PathVariable String id, @RequestBody List<String> roles){
        return ResponseEntity.ok().body(adminService.revokeRoles(id,roles));
    }
}
