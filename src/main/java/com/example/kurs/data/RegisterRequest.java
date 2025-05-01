package com.example.kurs.data;

public class RegisterRequest {
    private String name;
    private String surname;
    private String lastName;
    private String age;
    private String email;
    private String password;
    private String role;

    public RegisterRequest(String name, String surname, String lastName, String age, String email, String password, String role) {
        this.name = name;
        this.surname = surname;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
