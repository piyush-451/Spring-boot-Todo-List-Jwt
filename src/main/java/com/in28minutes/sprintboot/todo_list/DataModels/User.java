package com.in28minutes.sprintboot.todo_list.DataModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@Table(name = "users")  // Map to the 'users' table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")  // Map 'username' to the correct column
    private String username;

//    @JsonIgnore
    @Column(name = "password")  // Map 'password' to the correct column
    private String password;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Profile profile;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Todo> todoList;

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
