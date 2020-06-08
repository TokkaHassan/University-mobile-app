package com.example.graduationapplication.studentStuff.firebaseUtils.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class students {

    String key;
    String tableid;

    public String getTableid() {
        return tableid;
    }

    public void setTableid(String tableid) {
        this.tableid = tableid;
    }

    String id;
    String name;
    String password;
    String theE_mail;



    public students() {
    }

    public students(String tableid, String id, String name, String password, String theE_mail) {
        this.tableid = tableid;
        this.id = id;
        this.name = name;
        this.password = password;
        this.theE_mail = theE_mail;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {return name;}


    public void setName(String name) { this.name = name;}
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getTheE_mail() {
        return theE_mail;
    }

    public void setTheE_mail(String theE_mail) {
        this.theE_mail = theE_mail;
    }



}
