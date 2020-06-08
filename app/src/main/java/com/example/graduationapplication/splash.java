package com.example.graduationapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.graduationapplication.studentStuff.Activities.Attendance;
import com.example.graduationapplication.studentStuff.Activities.Entrance_student;

public class splash extends AppCompatActivity {
   SessionManager sessionManager;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sessionManager= new SessionManager(getApplicationContext());


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(sessionManager.isLoggedIn()){

                    if(sessionManager.getRole().equals("student"))
                    startActivity(new Intent(splash.this,Entrance_student.class));
                    else
                        startActivity(new Intent(splash.this, Attendance.class));
                }else
                sessionManager.checkLogin();

            }
        },2000);


    }
}
