package com.example.graduationapplication.studentStuff.firebaseUtils.firebase_DAOs;

import com.example.graduationapplication.studentStuff.firebaseUtils.MyDatabase;
import com.example.graduationapplication.studentStuff.firebaseUtils.models.messages;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class messages_DAO {
    public static void InsertMessage (messages message){
        DatabaseReference messageNode= MyDatabase.getMessagesBranch().push();
        message.setId(messageNode.getKey());
        messageNode.setValue(message);
    }
    public static Query getMessagesByRoom (String roomID){
        return MyDatabase.getMessagesBranch().orderByChild("chatRoomID").equalTo(roomID);

    }
}
