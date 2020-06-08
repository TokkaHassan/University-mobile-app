package com.example.graduationapplication.studentStuff.firebaseUtils.firebase_DAOs;

import com.example.graduationapplication.studentStuff.firebaseUtils.MyDatabase;
import com.example.graduationapplication.studentStuff.firebaseUtils.models.chatRooms;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;

public class chatRooms_DAO {
    public static void InsertChatRoom (chatRooms chatRoom, OnSuccessListener onSuccessListener, OnFailureListener onFailureListener){
        DatabaseReference chatRoomNode = MyDatabase.getChatRoomsBranch().push();
        chatRoom.setId(chatRoomNode.getKey());
        chatRoomNode.setValue(chatRoom).addOnSuccessListener(onSuccessListener).addOnFailureListener(onFailureListener);
    }
}
