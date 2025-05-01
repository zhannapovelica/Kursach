package com.example.kurs.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reception {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime date;
    private String description;
    private String location;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicalCourse_id")
    private MedicalCourse course;
    private boolean accept;

    public Reception(){}

    public Reception(User user, LocalDateTime date, String description, String location, MedicalCourse course) {
        this.user = user;
        this.date = date;
        this.description = description;
        this.location = location;
        this.course = course;
        this.accept = false;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public long getId() {
        return id;
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

    public MedicalCourse getCourse() {
        return course;
    }

    public void setCourse(MedicalCourse course) {
        this.course = course;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
