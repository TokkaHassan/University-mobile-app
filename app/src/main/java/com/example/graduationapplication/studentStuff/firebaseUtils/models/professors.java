package com.example.graduationapplication.studentStuff.firebaseUtils.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class professors {
    String key;
    String name;
    String uniID;
    String email;
    String password;
    String mobile;

    public professors() {
    }

    public professors(String name, String uniID, String email, String password) {
        this.name = name;
        this.uniID = uniID;
        this.email = email;
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUniID() {
        return uniID;
    }

    public void setUniID(String uniID) {
        this.uniID = uniID;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
