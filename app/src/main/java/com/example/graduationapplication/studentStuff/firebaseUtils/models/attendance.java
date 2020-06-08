package com.example.graduationapplication.studentStuff.firebaseUtils.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class attendance {
    String key;
    String date;
    String course_id;
    String attended_students;

    public attendance() {
    }

    public attendance(String date, String course_id, String attended_students) {
        this.date = date;
        this.course_id = course_id;
        this.attended_students = attended_students;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getAttended_students() {
        return attended_students;
    }

    public void setAttended_students(String attended_students) {
        this.attended_students = attended_students;
    }
}
