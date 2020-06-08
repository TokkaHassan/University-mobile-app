package com.example.graduationapplication.studentStuff.firebaseUtils.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.List;
@IgnoreExtraProperties
public class chatRooms {
    String id;
    String name;
    String subject;

    public chatRooms(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }


    public chatRooms() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
