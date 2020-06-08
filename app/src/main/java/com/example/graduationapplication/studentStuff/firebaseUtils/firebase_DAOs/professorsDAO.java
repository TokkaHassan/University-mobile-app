package com.example.graduationapplication.studentStuff.firebaseUtils.firebase_DAOs;

import com.example.graduationapplication.studentStuff.firebaseUtils.MyDatabase;
import com.example.graduationapplication.studentStuff.firebaseUtils.models.professors;
import com.google.firebase.database.DatabaseReference;

public class professorsDAO {
    public static void  InsertProfessor (professors professor){
        DatabaseReference professorNode = MyDatabase.getProfessorsBranch().push();
        professor.setKey(professorNode.getKey());
        professorNode.setValue(professor);
    }
}
