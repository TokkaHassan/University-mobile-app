package com.example.graduationapplication.studentStuff.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.graduationapplication.Base.BaseActivity;
import com.example.graduationapplication.MainActivity;
import com.example.graduationapplication.R;
import com.example.graduationapplication.SessionManager;
import com.example.graduationapplication.studentStuff.firebaseUtils.models.students;
import com.example.graduationapplication.studentStuff.firebaseUtils.firebase_DAOs.studentsDAO;
import com.example.graduationapplication.studentStuff.fragments.Contacts;
import com.example.graduationapplication.studentStuff.fragments.calender;
import com.example.graduationapplication.studentStuff.fragments.home;



public class Entrance_student extends BaseActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Fragment fragment;
    public BottomNavigationView mStuEnteranceBottomNav;
    Toolbar stu_toolbar;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       //creating the layout design
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterance_student);

        sessionManager= new SessionManager(getApplicationContext());

        //setting the custom made toolbar as the one used for this layout
        stu_toolbar = findViewById(R.id.student_toolbar);
        setSupportActionBar(stu_toolbar);
        stu_toolbar.setTitle("Student Zone");

        //setting a drawer layout inorder to be able to put a navigation menu in the toolbar
        drawerLayout = findViewById(R.id.student_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_prof_open, R.string.nav_prof_close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // setting the functionality of the navigation menu as in the action of clicking each item in it.

        final NavigationView navigationView = (NavigationView) findViewById(R.id.student_navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                String name = menuItem.getTitle().toString();
               if (id == R.id.nav_student_timeTable) {
                   startActivity(new Intent(Entrance_student.this, Schedules.class));
                } else if (id == R.id.nav_student_AllNotes) {
                    startActivity(new Intent(Entrance_student.this, AllNotes.class));
                } else if (id == R.id.nav_student_grades) {
                    startActivity(new Intent(Entrance_student.this, Grades.class));
                } else if (id == R.id.nav_student_Attendance) {
                   startActivity(new Intent(Entrance_student.this, Attendance.class));
                }else if (id == R.id.nav_student_chatRooms) {
                   startActivity(new Intent(Entrance_student.this, chatRooms.class));

               }else if (id == R.id.nav_student_logout) {
                   sessionManager.logoutUser();
               }
                drawerLayout.closeDrawers();
                return true;
            }
        });


        //setting the frame layout to hold a fragment and to switch between three fragments using the botttom navigation view
        fragment = new home();
        getSupportFragmentManager().beginTransaction().replace(R.id.stu_fragmentContainer, fragment).commit();
        mStuEnteranceBottomNav = (BottomNavigationView) findViewById(R.id.stu_enterance_bottom_nav);
        mStuEnteranceBottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    // setting the functionality and action of each item in the bottom navigation view
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == R.id.navigation_home) {
                fragment = new home();
                stu_toolbar.setTitle(item.getTitle());
                getSupportFragmentManager().beginTransaction().replace(R.id.stu_fragmentContainer, fragment).commit();

            } else if (item.getItemId() == R.id.navigation_contacts) {
                fragment = new Contacts();
                stu_toolbar.setTitle(item.getTitle());
                getSupportFragmentManager().beginTransaction().replace(R.id.stu_fragmentContainer, fragment).commit();

            } else if (item.getItemId() == R.id.navigation_calender) {
                fragment = new calender();
                getSupportFragmentManager().beginTransaction().replace(R.id.stu_fragmentContainer, fragment).commit();
                stu_toolbar.setTitle(item.getTitle());
            }
            return false;
        }
    };



}
