package com.example.graduationapplication;

import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static com.example.graduationapplication.SessionManager.Role;

public class Identification extends AppCompatActivity {

    public TextView mWhoAreYou;
    public TextView mIdenProfessor;
    public TextView mIdenStudent;
    String identification;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);
        mWhoAreYou = (TextView) findViewById(R.id.who_are_you);
        mIdenProfessor = (TextView) findViewById(R.id.iden_professor);
        mIdenStudent = (TextView) findViewById(R.id.iden_student);
        sessionManager=new SessionManager(getApplicationContext());

    }

    public void onBtnClicked (View view){
        if(view.getId()==R.id.iden_professor){
            sessionManager.editor.putString(Role,"doctor").commit();
        }if(view.getId()==R.id.iden_student){
            sessionManager.editor.putString(Role,"student").commit();
        }

        Intent intent= new Intent(Identification.this,MainActivity.class);

        startActivity(intent);
        finish();
    }
}
