package com.example.graduationapplication.studentStuff.firebaseUtils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyDatabase {
    private static FirebaseDatabase firebaseDatabase ;

    public static FirebaseDatabase getInstance() {
        if(firebaseDatabase==null){
            firebaseDatabase= FirebaseDatabase.getInstance();
        }
        return firebaseDatabase;
    }

    public static DatabaseReference getStudentsBranch (){
        return getInstance().getReference("students");
    }

    public static DatabaseReference getChatRoomsBranch (){
        return getInstance().getReference("chatRooms");
    }
    public static DatabaseReference getMessagesBranch (){
        return getInstance().getReference("messages");
    }
    public static DatabaseReference getProfessorsBranch(){
        return getInstance().getReference("professors");
    }
    public static DatabaseReference getAttendanceBranch(){
        return getInstance().getReference("Attendance");
    }
}
