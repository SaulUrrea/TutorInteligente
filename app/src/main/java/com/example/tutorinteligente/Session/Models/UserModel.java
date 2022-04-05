package com.example.tutorinteligente.Session.Models;

import com.google.firebase.auth.FirebaseUser;

public class UserModel {
    private String email, name, lastName, uid;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUid() {
        return uid;
    }


    @Override
    public String toString() {
        return "UserModel{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }

}
