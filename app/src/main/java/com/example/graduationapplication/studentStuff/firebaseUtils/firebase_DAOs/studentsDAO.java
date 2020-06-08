package com.example.graduationapplication.studentStuff.firebaseUtils.firebase_DAOs;

import com.example.graduationapplication.studentStuff.firebaseUtils.MyDatabase;
import com.example.graduationapplication.studentStuff.firebaseUtils.models.students;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class studentsDAO {

    public static void  InsertStudent (students student){
        DatabaseReference studentNode= MyDatabase.getStudentsBranch().push();
        student.setKey(studentNode.getKey());
        studentNode.setValue(student);
    }

    public static Query getStudentById (String theId){
        return MyDatabase.getStudentsBranch().orderByChild("id").equalTo(theId);

    }

}
