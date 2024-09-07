package com.emazon.user_service.domain.model;

import java.time.LocalDate;

public class User {
    private Long id;
    private String document;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String cellphone;
    private LocalDate birthdate;
    private Role role;

    public User(Long id, String document, String name, String lastname, String email, String password, String cellphone, LocalDate birthdate, Role role) {
        this.id = id;
        this.document = document;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.cellphone = cellphone;
        this.birthdate = birthdate;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
