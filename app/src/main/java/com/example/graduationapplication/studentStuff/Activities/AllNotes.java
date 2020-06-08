package com.example.graduationapplication.studentStuff.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.graduationapplication.Base.BaseActivity;
import com.example.graduationapplication.MainActivity;
import com.example.graduationapplication.R;
import com.example.graduationapplication.SessionManager;
import com.example.graduationapplication.studentStuff.Adapters_student.Adapter_notes;
import com.example.graduationapplication.studentStuff.Database_Models.notes;
import com.example.graduationapplication.studentStuff.MyDatabase_notes;

import java.util.List;



public class AllNotes extends BaseActivity {

    public DrawerLayout mStudentDrawerLayout;
    RecyclerView recyclerView;
    Adapter_notes adapterNotes;
    LinearLayoutManager linearLayoutManager;
    List<notes> items;
    ActionBarDrawerToggle actionBarDrawerToggle;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //creating the layout design

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_notes);
        sessionManager= new SessionManager(getApplicationContext());
        Toolbar note_toolbar = findViewById(R.id.note_toolbar);
        setSupportActionBar(note_toolbar);
        note_toolbar.setTitle("All Notes");

        recyclerView = findViewById(R.id.notes_recycler);
        GettingAllNotes();

        adapterNotes = new Adapter_notes(items);
        linearLayoutManager = new LinearLayoutManager(this);
        adapterNotes.setOnNoteClickListener(new Adapter_notes.OnItemClickListener() {
            @Override
            public void onItemClicked(notes note) {
                Intent intent=new Intent(AllNotes.this,noteUpdate.class);
                intent.putExtra("note",note);
                startActivity(intent);
            }
        });
        adapterNotes.setOnNoteLongClickedListener(new Adapter_notes.OnItemClickListener() {
            @Override
            public void onItemClicked(final notes note) {
                askForAction("Warning", "Are you sure you want to delete note?", "YES", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        MyDatabase_notes.getInstance(AllNotes.this).notesDao().DeleteNote(note);
                        adapterNotes.changeData(MyDatabase_notes.getInstance(AllNotes.this).notesDao().SelectAllNotes());
                    }
                }, "NO", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                });
            }
        });
        recyclerView.setAdapter(adapterNotes);
        recyclerView.setLayoutManager(linearLayoutManager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(AllNotes.this, noteModification.class);
                startActivity(intent);


            }
        });
        mStudentDrawerLayout = (DrawerLayout) findViewById(R.id.AllNotes_Drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mStudentDrawerLayout, R.string.stu_nav_open, R.string.stu_nav_close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mStudentDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final NavigationView mStudentNavigationView = (NavigationView) findViewById(R.id.Allnotes_navigation_view);
        mStudentNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                String name = menuItem.getTitle().toString();
                if (id == R.id.nav_student_timeTable) {
                    startActivity(new Intent(AllNotes.this, Schedules.class));
                } else if (id == R.id.nav_student_AllNotes) {
                    startActivity(new Intent(AllNotes.this, AllNotes.class));
                } else if (id == R.id.nav_student_grades) {
                    startActivity(new Intent(AllNotes.this, Grades.class));
                }  else if (id == R.id.nav_student_Attendance) {
                    startActivity(new Intent(AllNotes.this, Attendance.class));
                }else if (id == R.id.nav_student_home) {
                    startActivity(new Intent(AllNotes.this, Entrance_student.class));
                }else if (id == R.id.nav_student_logout) {
                    sessionManager.logoutUser();
                }else if (id == R.id.nav_student_chatRooms) {
                    startActivity(new Intent(AllNotes.this, chatRooms.class));

                }
                mStudentDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void GettingAllNotes() {
        items = MyDatabase_notes.getInstance(AllNotes.this).notesDao().SelectAllNotes();

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        adapterNotes.changeData(MyDatabase_notes.getInstance(AllNotes.this).notesDao().SelectAllNotes());
        super.onRestart();
    }

}
