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
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.graduationapplication.Base.BaseActivity;
import com.example.graduationapplication.MainActivity;
import com.example.graduationapplication.R;
import com.example.graduationapplication.SessionManager;
import com.example.graduationapplication.studentStuff.APIManager;
import com.example.graduationapplication.studentStuff.API_models.StudentsInfo.StudentInfo;
import com.example.graduationapplication.studentStuff.API_models.SubjectsTable.Subjects;
import com.example.graduationapplication.studentStuff.Adapters_student.Adapter_attendance_subjects;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Attendance extends BaseActivity {

    public Toolbar mStudentToolbar;
    public DrawerLayout mAttendanceDrawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    RecyclerView recyclerView;
    Adapter_attendance_subjects adapter;
    RecyclerView.LayoutManager layoutManager;
    List<String> AllSubjectsName;
    SessionManager sessionManager;
    List<com.example.graduationapplication.studentStuff.API_models.SubjectsTable.TestDataItem> forStudentList;
    List<com.example.graduationapplication.studentStuff.API_models.SubjectsTable.TestDataItem> forProfList;
    List<com.example.graduationapplication.studentStuff.API_models.SubjectsTable.TestDataItem> subjects;
    String current_couses;
    ArrayList<String> MySubjects;
    ArrayList<com.example.graduationapplication.studentStuff.API_models.SubjectsTable.TestDataItem> wantedSubjects=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //creating the layout design

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        sessionManager = new SessionManager(getApplicationContext());

        mStudentToolbar = (Toolbar) findViewById(R.id.attendance_toolbar);

        mStudentToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askForAction("Alert", "Do you want to log out ?", "YES", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        sessionManager.logoutUser();
                        dialog.dismiss();
                    }
                }, "NO", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                });
            }
        });

        if (sessionManager.getRole().equals("student")) {
            final NavigationView mAttendanceNavigationView = (NavigationView) findViewById(R.id.attendance_navigation_view);
            mAttendanceDrawerLayout = (DrawerLayout) findViewById(R.id.attendance_Drawer_layout);
            mStudentToolbar.setTitle("Attendance");
                actionBarDrawerToggle = new ActionBarDrawerToggle(this, mAttendanceDrawerLayout, R.string.stu_nav_open, R.string.stu_nav_close);
                actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
                mAttendanceDrawerLayout.addDrawerListener(actionBarDrawerToggle);
                actionBarDrawerToggle.syncState();
            setSupportActionBar(mStudentToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                mAttendanceNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        String name = menuItem.getTitle().toString();
                        if (id == R.id.nav_student_timeTable) {
                            startActivity(new Intent(Attendance.this, Schedules.class));
                        } else if (id == R.id.nav_student_AllNotes) {
                            startActivity(new Intent(Attendance.this, AllNotes.class));
                        } else if (id == R.id.nav_student_grades) {
                            startActivity(new Intent(Attendance.this, Grades.class));
                        } else if (id == R.id.nav_student_Attendance) {
                            startActivity(new Intent(Attendance.this, Attendance.class));
                        } else if (id == R.id.nav_student_home) {
                            startActivity(new Intent(Attendance.this, Entrance_student.class));
                        } else if (id == R.id.nav_student_chatRooms) {
                            startActivity(new Intent(Attendance.this, chatRooms.class));
                        } else if (id == R.id.nav_student_logout) {
                            sessionManager.logoutUser();
                        }
                        mAttendanceDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }else mStudentToolbar.setTitle("Log Out");
    recyclerView = findViewById(R.id.attendance_recycler);
           //GettingCurrentCourses();
           //GettingWantedSubjects();

        GettingAllSubjects();
        GettingCurrentCourses();
            adapter = new Adapter_attendance_subjects(subjects);
            adapter.setOnItemClickListener(new Adapter_attendance_subjects.OnItemClickListener() {
                @Override
                public void onItemClicked(com.example.graduationapplication.studentStuff.API_models.SubjectsTable.TestDataItem subject) {
                    if (sessionManager.getRole().equals("student")) {
                        showMessage("Attendance", "Number of lectures: " + "\n" + "Attended ones: ", "ok", new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        });
                    } else {
                        Intent intent = new Intent(Attendance.this, students_In_Subject.class);
                        intent.putExtra("subject_id",subject.getId());
                        intent.putExtra("course_id",current_couses);
                        startActivity(intent);
                    }
                }
            });
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(layoutManager);
        }

    private void GettingAllSubjects() {
        APIManager.getAPIs().getSubjectTableCalls().enqueue(new Callback<Subjects>() {
            @Override
            public void onResponse(Call<Subjects> call, Response<Subjects> response) {
                if(response.isSuccessful()){
                   subjects =response.body().getTestData();
                    /*AllSubjectsName=new ArrayList<>();
                   for(com.example.graduationapplication.studentStuff.API_models.SubjectsTable.TestDataItem item:response.body().getTestData()){
                       Log.e("getting subj name",response.body().getTestData().size()+"");
                       AllSubjectsName.add(item.getName());
                   }

                   Log.e("all names",""+AllSubjectsName.size());*/

                }
                adapter.changeData(subjects);
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

    public void GettingCurrentCourses(){
        GettingAllSubjects();

        APIManager.getAPIs().getStudentInfoCalls().enqueue(new Callback<StudentInfo>() {
            @Override
            public void onResponse(Call<StudentInfo> call, Response<StudentInfo> response) {
                if(response.isSuccessful()){
                    List<com.example.graduationapplication.studentStuff.API_models.StudentsInfo.TestDataItem> users=response.body().getTestData();
                    for(com.example.graduationapplication.studentStuff.API_models.StudentsInfo.TestDataItem item:users){
                        if(sessionManager.getID().equals(item.getSid())){
                            current_couses=item.getCurrentC();

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<StudentInfo> call, Throwable t) {

            }
        });
        if(current_couses!=null){
        char[] subject_IDs;
        subject_IDs= current_couses.toCharArray();
        MySubjects =new ArrayList<>();
        String oneSub;
        for (int i=0;i<subject_IDs.length;i++){
            if(i%2==0){
                oneSub=subject_IDs[i]+"";
                MySubjects.add(oneSub);
            }
        }
        }

    }

    public void GettingWantedSubjects (){
        if(current_couses!=null){
        for(int i=0;i<MySubjects.size();i++){
        for(com.example.graduationapplication.studentStuff.API_models.SubjectsTable.TestDataItem item:subjects){
            if(item.getId().equals(MySubjects.get(i))){
                wantedSubjects.add(item);
                break;
            }
        }

        }adapter.changeData(wantedSubjects);

    }}
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
