package com.in28minutes.sprintboot.todo_list.DataModels;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private final Collection<GrantedAuthority> authorities;


    public MyUserDetails(String username, String password,List<Role> roleList) {
        this.username = username;
        this.password = password;
        authorities = setAuthorities(roleList);

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return roleList.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return authorities;
    }

    private Collection<GrantedAuthority> setAuthorities(List<Role> roleList) {
//        roleList
//                .forEach(item -> System.out.println(item.getName()));
        return roleList.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }




    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

}
