package com.in28minutes.sprintboot.todo_list.DataModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "username")
    private String username;

    @Column (name = "description")
    private String description;

    @Column (name = "dueDate")
    private LocalDate dueDate;

    @Column (name = "isCompleted")
    private boolean isCompleted;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Todo(){}

    public Todo(Integer id, String username, String description, boolean isCompleted, LocalDate dueDate, User user) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.isCompleted = isCompleted;
        this.dueDate = dueDate;
        this.user = user;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
