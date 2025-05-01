package com.example.kurs.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class FidBack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private String fidBack;
    private long likes;
    private long dislikes;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    public FidBack() {this.createdAt = LocalDateTime.now();}
    public FidBack(User user, String fidBack) {
        this.user = user;
        this.fidBack = fidBack;
        this.likes = 0;
        this.dislikes = 0;
        this.createdAt = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFidBack() {
        return fidBack;
    }

    public void setFidBack(String fidback) {
        this.fidBack = fidback;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public long getDislikes() {
        return dislikes;
    }

    public void setDislikes(long dislikes) {
        this.dislikes = dislikes;
    }
}
