package com.example.kurs.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class MedicalCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Doctor> doctors = new ArrayList<>();
    @Column(length = 1000)
    private String purpose;
    @Column(length = 1000)
    private String description;
    @Column(length = 1000)
    private String  duration;
    private String capacity;
    @Column(length = 1000)
    private String location;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reception> receptions = new ArrayList<>();
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;
    public MedicalCourse() {}

    public MedicalCourse(String name, String purpose, String description, String duration, String capacity, String location, byte[] image) {
        this.name = name;
        this.purpose = purpose;
        this.description = description;
        this.duration = duration;
        this.capacity = capacity;
        this.location = location;
        this.image = image;
    }
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }
    public void addReception(Reception reception) {
        receptions.add(reception);
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
