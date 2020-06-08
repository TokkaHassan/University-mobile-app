package com.example.graduationapplication.studentStuff.firebaseUtils.firebase_DAOs;

import com.example.graduationapplication.studentStuff.firebaseUtils.MyDatabase;
import com.example.graduationapplication.studentStuff.firebaseUtils.models.attendance;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;

public class attendanceDAO {
    public static void InsertAttendance (attendance attendance, OnSuccessListener onSuccessListener){
        DatabaseReference attendanceNode = MyDatabase.getAttendanceBranch().push();
        attendance.setKey(attendanceNode.getKey());
        attendanceNode.setValue(attendance).addOnSuccessListener(onSuccessListener);
    }
}
