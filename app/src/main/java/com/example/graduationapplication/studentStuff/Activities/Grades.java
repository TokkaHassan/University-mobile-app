package com.example.graduationapplication.studentStuff.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.graduationapplication.Base.BaseActivity;
import com.example.graduationapplication.MainActivity;
import com.example.graduationapplication.R;
import com.example.graduationapplication.SessionManager;
import com.example.graduationapplication.studentStuff.APIManager;
import com.example.graduationapplication.studentStuff.API_models.GradesInfo.TheGrades;
import com.example.graduationapplication.studentStuff.API_models.SubjectsTable.Subjects;
import com.example.graduationapplication.studentStuff.Adapters_student.Adapter_grades;
import com.example.graduationapplication.studentStuff.Recycler_classes.Grades_form;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Grades extends BaseActivity {


    public Toolbar mStudentToolbar;
    public RecyclerView mGradesRecyclerView;
    public DrawerLayout mStudentDrawerLayout;
    Adapter_grades adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Grades_form> wantedGradesList;
    ActionBarDrawerToggle actionBarDrawerToggle;
    SessionManager sessionManager;
    Grades_form grade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //creating the layout design

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
        sessionManager =new SessionManager(getApplicationContext());

        mStudentToolbar = (Toolbar) findViewById(R.id.grades_toolbar);
        mStudentToolbar.setTitle("My Grades");
        setSupportActionBar(mStudentToolbar);
        mGradesRecyclerView = (RecyclerView) findViewById(R.id.grades_recyclerView);
        CreateList();
        adapter = new Adapter_grades(wantedGradesList);
        layoutManager = new LinearLayoutManager(this);
        mGradesRecyclerView.setAdapter(adapter);
        mGradesRecyclerView.setLayoutManager(layoutManager);


        mStudentDrawerLayout = (DrawerLayout) findViewById(R.id.grades_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mStudentDrawerLayout, R.string.stu_nav_open, R.string.stu_nav_close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mStudentDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final NavigationView mStudentNavigationView = (NavigationView) findViewById(R.id.grades_navigation_view);
        mStudentNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.nav_student_timeTable) {
                    startActivity(new Intent(Grades.this, Schedules.class));
                } else if (id == R.id.nav_student_AllNotes) {
                    startActivity(new Intent(Grades.this, AllNotes.class));
                } else if (id == R.id.nav_student_grades) {
                    startActivity(new Intent(Grades.this, Grades.class));
                }  else if (id == R.id.nav_student_Attendance) {
                    startActivity(new Intent(Grades.this, Attendance.class));
                }else if (id == R.id.nav_student_home) {
                    startActivity(new Intent(Grades.this, Entrance_student.class));
                    finish();
                }else if (id == R.id.nav_student_chatRooms) {
                    startActivity(new Intent(Grades.this, chatRooms.class));
                }else if (id == R.id.nav_student_logout) {
                    sessionManager.logoutUser();
                }
                mStudentDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
    public void CreateList() {
       /* items = new ArrayList<>();
        Grades_form form = null;
        for (int i = 0; i < 35; i++) {
            if (i % 2 == 0)
                form = new Grades_form("subject" + i, "A");
            else
                form = new Grades_form("subject" + i, "B");
            items.add(form);
        }
    }*/
        APIManager.getAPIs().getStudentsGradesCalls().enqueue(new Callback<TheGrades>() {
            @Override
            public void onResponse(Call<TheGrades> call, Response<TheGrades> response) {
                if(response.isSuccessful()){
                    wantedGradesList=new ArrayList<>();

                for(com.example.graduationapplication.studentStuff.API_models.GradesInfo.TestDataItem item : response.body().getTestData()){
                  Log.e("grades",sessionManager.getTableID());
                  Log.e("grades in tab",item.getStudentId());
                    if(item.getStudentId().equals(sessionManager.getTableID())){
                      Grades_form grades_form=new Grades_form(item.getCourseId(),item.getGrade());
                      wantedGradesList.add(grades_form);

                  }
                }
                Log.e("wanted grades",wantedGradesList.size()+"");


                }

            }

            @Override
            public void onFailure(Call<TheGrades> call, Throwable t) {

                showMessage("Error", "A problem occurred with connection", "ok", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        dialog.dismiss();
                    }
                });
            }
        });

        APIManager.getAPIs().getSubjectTableCalls().enqueue(new Callback<Subjects>() {
            @Override
            public void onResponse(Call<Subjects> call, Response<Subjects> response) {
                if(response.isSuccessful()){
                    List<com.example.graduationapplication.studentStuff.API_models.SubjectsTable.TestDataItem>items=response.body().getTestData();
                    for(int i=0;i<wantedGradesList.size();i++){
                        grade=wantedGradesList.get(i);
                        for(com.example.graduationapplication.studentStuff.API_models.SubjectsTable.TestDataItem item:items){
                            if(item.getId().equals(grade.getSubject())){
                                grade.setSubject(item.getName());
                                wantedGradesList.set(i,grade);
                                break;
                            }

                        }
                    }
                    adapter.changeData(wantedGradesList);
                }
            }

            @Override
            public void onFailure(Call<Subjects> call, Throwable t) {

                showMessage("Error", "A problem occurred with connection", "ok", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        dialog.dismiss();
                    }
                });
            }
        });

}
}
