package com.example.graduationapplication.studentStuff.Database_Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;


@Entity
public class notes implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo
    String Title;
    @ColumnInfo
    String Date;
    @ColumnInfo
    String Content;
    @ColumnInfo
    String Subject;
    @ColumnInfo
    String Type;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public notes() {
    }

    @Ignore
    public notes(String title, String date, String subject, String content,String type) {
        Title = title;
        Date = date;
        Content = content;
        Subject = subject;
        Type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }
}
