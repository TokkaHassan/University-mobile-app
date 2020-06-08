package com.example.graduationapplication.studentStuff.Activities;

import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.example.graduationapplication.R;
import com.example.graduationapplication.studentStuff.fragments.exam_Schedule;
import com.example.graduationapplication.studentStuff.fragments.subject_schedule;

public class Schedules extends AppCompatActivity {

    private TabItem mScheduleTabFullSchedule;
    private TabItem mScheduleTabExamSchedule;
    private TabLayout mScheduleTabLayout;
    private FrameLayout mScheduleFragmentContainer;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //creating the layout design

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedules);
        mScheduleTabFullSchedule = (TabItem) findViewById(R.id.schedule_tab_full_schedule);
        mScheduleTabExamSchedule = (TabItem) findViewById(R.id.schedule_tab_exam_schedule);
        mScheduleTabLayout = (TabLayout) findViewById(R.id.schedule_tab_layout);
        mScheduleFragmentContainer = (FrameLayout) findViewById(R.id.schedule_fragment_container);

        fragment=new subject_schedule();
        getSupportFragmentManager().beginTransaction().replace(R.id.schedule_fragment_container,fragment).commit();
        mScheduleTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                    fragment=new subject_schedule();
                }else if(tab.getPosition()==1){
                    fragment=new exam_Schedule();

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.schedule_fragment_container,fragment).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
