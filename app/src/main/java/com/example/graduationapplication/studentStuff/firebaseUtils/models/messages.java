package com.example.graduationapplication.studentStuff.firebaseUtils.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class messages {
    String id;

    public messages(String senderName, String text, String senderID, String chatRoomID, String timeStamp) {
        this.senderName = senderName;
        this.text = text;
        this.senderID = senderID;
        this.chatRoomID = chatRoomID;
        this.timeStamp = timeStamp;
    }

    String senderName;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    String text;
    String senderID;
    String chatRoomID;

    public messages() {
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    String timeStamp;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getChatRoomID() {
        return chatRoomID;
    }

    public void setChatRoomID(String chatRoomID) {
        this.chatRoomID = chatRoomID;
    }
}
