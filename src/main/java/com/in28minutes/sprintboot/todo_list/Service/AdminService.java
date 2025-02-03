package com.in28minutes.sprintboot.todo_list.Service;

import com.in28minutes.sprintboot.todo_list.DataModels.Role;
import com.in28minutes.sprintboot.todo_list.DataModels.User;
import com.in28minutes.sprintboot.todo_list.Exceptions.Exception.DeletingDefaultRolesException;
import com.in28minutes.sprintboot.todo_list.Exceptions.Exception.ResourceAlreadyPresentException;
import com.in28minutes.sprintboot.todo_list.Exceptions.Exception.ResourceNotFoundException;
import com.in28minutes.sprintboot.todo_list.Repository.RoleRepo;
import com.in28minutes.sprintboot.todo_list.Repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    public User assignRoles(String id, List<String> roles) {
        Integer userId = Integer.parseInt(id);
        User updatedUser = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));

        List<String> noPresentRoles = new ArrayList<>();
        for (String roleName : roles) {
            Optional<Role> existingRole = roleRepo.findByRolename("ROLE_" + roleName);
            if(existingRole.isEmpty()){
                noPresentRoles.add(roleName);
                continue;
            }

            if(!updatedUser.getRoleList().contains(existingRole.get())){
                updatedUser.getRoleList().add(existingRole.get());
                existingRole.get().getUserList().add(updatedUser);
            }

        }

        userRepo.save(updatedUser);

        if(!noPresentRoles.isEmpty()){
            throw new ResourceNotFoundException("roles not found: " + noPresentRoles);
        }


        return updatedUser;
    }

    @Transactional
    public String deleteSingleUser(String id) {
        Integer userId= Integer.parseInt(id);
        User toBeDeletedUser = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not present"));

        for (Role role : toBeDeletedUser.getRoleList()) {
            role.getUserList().remove(toBeDeletedUser);
        }

        userRepo.delete(toBeDeletedUser);
        return toBeDeletedUser.getUsername();
    }

    public List<String> addRoles(List<String> roles) {
        List<String> rolesToRemove = roles.stream()
                .filter(roleName -> roleRepo.findByRolename("ROLE_" + roleName).isEmpty())
                .peek(roleName -> {
                    Role newRole = new Role("ROLE_" + roleName);
                    roleRepo.save(newRole);
                })
                .toList();

        roles.removeAll(rolesToRemove);

        if(!roles.isEmpty()){
            throw new ResourceAlreadyPresentException("roles already present: " + roles);
        }

        return roles;
    }

    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

    public String deleteRole(String id) {
        Integer roleId = Integer.parseInt(id);
        Role toBeDeletedRole = roleRepo.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("role not found"));

        if(toBeDeletedRole.getName().equals("ROLE_USER") || toBeDeletedRole.getName().equals("ROLE_ADMIN")){
            throw new DeletingDefaultRolesException("cannot delete default roles");
        }

        for(User user: toBeDeletedRole.getUserList()){
            user.getRoleList().remove(toBeDeletedRole);
        }

        roleRepo.delete(toBeDeletedRole);
        return toBeDeletedRole.getName();
    }

    public List<String> getUsersByRole(String id) {
        Integer roleId = Integer.parseInt(id);
        Role role = roleRepo.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("role not found"));

        return role.getUserList().stream()
                .map(User::getUsername)
                .toList();
    }

    public User revokeRoles(String id, List<String> revokedRoles) {
        Integer userId = Integer.parseInt(id);
        User updatedUser = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));

        List<Role> roleToRemove = new ArrayList<>();
        for(Role role: updatedUser.getRoleList()){
            if(role.getName().equals("ROLE_USER") || role.getName().equals("ROLE_ADMIN")){
                continue;
            }
            if(revokedRoles.contains(role.getName().substring(5))){
                role.getUserList().remove((updatedUser));
                revokedRoles.remove(role.getName().substring(5));
                roleToRemove.add(role);
            }
        }

        updatedUser.getRoleList().removeAll(roleToRemove);

        userRepo.save(updatedUser);

        if(revokedRoles.contains("USER") || revokedRoles.contains("ADMIN")){
            throw new DeletingDefaultRolesException("cannot revoke default roles");
        }

        if(!revokedRoles.isEmpty()){
            throw new ResourceNotFoundException("roles not found: " + revokedRoles);
        }


        return updatedUser;
    }
}
