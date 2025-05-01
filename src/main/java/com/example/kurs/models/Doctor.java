package com.example.kurs.models;

import jakarta.persistence.*;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;
    private String fullName;
    private int age;
    private String position;
    @Column(length = 1000)
    private String specialization;
    @Column(length = 1000)
    private String contacts;
    @Column(length = 1000)
    private String education;
    @Column(length = 1000)
    private String experience;
    @Column(length = 1000)
    private String aboutCourse;
    @Column(length = 1000)
    private String strategy;
    @Column(length = 1000)
    private String moreInfo;
    @Column(length = 1000)
    private String goal;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicalCourse_id") // Внешний ключ в таблице doctors
    private MedicalCourse course;
    public Doctor() {}

    public Doctor(String fullName, int age, String position, String specialization, String contacts,
                  String education, String experience, String aboutCourse,
                  String moreInfo, String goal, String strategy, byte[] image) {
        this.fullName = fullName;
        this.age = age;
        this.position = position;
        this.specialization = specialization;
        this.contacts = contacts;
        this.education = education;
        this.experience = experience;
        this.aboutCourse = aboutCourse;
        this.moreInfo = moreInfo;
        this.goal = goal;
        this.strategy = strategy;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MedicalCourse getCourse() {
        return course;
    }

    public void setCourse(MedicalCourse course) {
        this.course = course;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getAboutCourse() {
        return aboutCourse;
    }

    public void setAboutCourse(String aboutCourse) {
        this.aboutCourse = aboutCourse;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
