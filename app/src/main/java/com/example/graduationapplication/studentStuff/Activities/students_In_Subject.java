package com.example.graduationapplication.studentStuff.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.graduationapplication.R;
import com.example.graduationapplication.studentStuff.Adapters_student.Adapter_checkList;
import com.example.graduationapplication.studentStuff.firebaseUtils.MyDatabase;
import com.example.graduationapplication.studentStuff.firebaseUtils.firebase_DAOs.attendanceDAO;
import com.example.graduationapplication.studentStuff.firebaseUtils.models.attendance;
import com.example.graduationapplication.studentStuff.firebaseUtils.models.students;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class students_In_Subject extends AppCompatActivity {

    public Button mCheckListSubmit;
    private RecyclerView mCheckListRecycler;
    Adapter_checkList adapter_checkList;
    RecyclerView.LayoutManager layoutManager;
    List<students> studentsList;
    ArrayList<String> attended;
    String needed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students__in__subject);
        getStudents();
        Intent intent = getIntent();
        final String subject_id = intent.getStringExtra("subject_id");
        mCheckListRecycler = (RecyclerView) findViewById(R.id.checkList_recycler);
        adapter_checkList = new Adapter_checkList(null);
        layoutManager = new LinearLayoutManager(this);
        mCheckListRecycler.setAdapter(adapter_checkList);
        mCheckListRecycler.setLayoutManager(layoutManager);
        attended=new ArrayList<>();
        adapter_checkList.setOnCheckBoxClicked(new Adapter_checkList.onItemClickListener() {
            @Override
            public void OnItemClicked(students form) {

                attended.add(form.getTableid());
                Log.e("checkBox",attended.get(0));
            }
        });
       needed=createOneString();


        mCheckListSubmit = (Button) findViewById(R.id.checkList_submit);
        mCheckListSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String formattedDate = new SimpleDateFormat("d/M/yyyy", Locale.getDefault()).format(new Date());

                attendance theAttendance =new attendance(formattedDate,subject_id,needed);
                attendanceDAO.InsertAttendance(theAttendance, new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        startActivity(new Intent(students_In_Subject.this,Attendance.class));
                        finish();
                    }
                });

            }

        });
    }

    public void getStudents() {
        MyDatabase.getStudentsBranch().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentsList = new ArrayList<>();
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    students student = item.getValue(students.class);
                    studentsList.add(student);
                }
                adapter_checkList.changeData(studentsList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public String createOneString (){
        //Log.e("check string array",list.get(0));
        String attendedStudents="";
        for (int i=0;i<attended.size();i++){
            if(i==(attended.size()-1))attendedStudents += attended.get(i);
            else attendedStudents += attended.get(i)+",";
            Log.e("check string",attendedStudents);
        }
        return attendedStudents;
    }
}
