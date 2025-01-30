package com.in28minutes.sprintboot.todo_list.DataModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")  // Map to the 'users' table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")  // Map 'username' to the correct column
    private String username;

    @Column(name = "password")  // Map 'password' to the correct column
    private String password;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Profile profile;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Todo> todoList = new ArrayList<>();

    @ManyToMany(mappedBy = "userList", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Role> roleList = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public User(){}

    public User(Integer id, String username, String password, Profile profile, List<Todo> todoList, List<Role> roleList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.profile = profile;
        this.todoList = todoList;
        this.roleList = roleList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Todo> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsernameName(String name) {
        this.username = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", profile=" + profile +
                ", todoList=" + todoList +
                ", roleList=" + roleList +
                '}';
    }
}
