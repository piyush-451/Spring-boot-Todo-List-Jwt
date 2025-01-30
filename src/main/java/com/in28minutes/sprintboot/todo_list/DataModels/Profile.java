package com.in28minutes.sprintboot.todo_list.DataModels;

import jakarta.persistence.*;

@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bio")
    private String bio;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Profile() {
    }

    public Profile(Integer id, String bio, User user) {
        this.id = id;
        this.bio = bio;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
