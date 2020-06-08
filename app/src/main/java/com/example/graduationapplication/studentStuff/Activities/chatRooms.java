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
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.graduationapplication.Base.BaseActivity;

import com.example.graduationapplication.MainActivity;
import com.example.graduationapplication.R;
import com.example.graduationapplication.SessionManager;
import com.example.graduationapplication.studentStuff.Adapters_student.Adapter_notes;
import com.example.graduationapplication.studentStuff.Adapters_student.Adapter_rooms;
import com.example.graduationapplication.studentStuff.firebaseUtils.MyDatabase;
import com.example.graduationapplication.studentStuff.firebaseUtils.firebase_DAOs.chatRooms_DAO;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



public class chatRooms extends BaseActivity {

    public RecyclerView mChatRoomsRecycler;
    public NavigationView mChatRoomsNavigationView;
    public DrawerLayout mChatRoomsDrawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Adapter_rooms adapter;
    RecyclerView.LayoutManager layoutManager;
    List<com.example.graduationapplication.studentStuff.firebaseUtils.models.chatRooms> rooms;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_rooms);
        Toolbar toolbar = findViewById(R.id.chatRooms_toolbar);
        setSupportActionBar(toolbar);
        sessionManager=new SessionManager(getApplicationContext());


        FloatingActionButton fab = findViewById(R.id.chatRoom_floating);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialDialog dialog=new MaterialDialog.Builder(chatRooms.this)
                        .title("Enter chat room's title and subject")
                        .customView(R.layout.dialog_edit_text, true)
                        .positiveText("ok")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                View view = dialog.getCustomView();
                                EditText chatTitle = view.findViewById(R.id.dialog_chat_text);
                                EditText chatSubject = view.findViewById(R.id.dialog_chat_subject);
                                String title = chatTitle.getText().toString();
                                String subject = chatSubject.getText().toString();
                                if(!title.isEmpty()&& !subject.isEmpty()){
                                com.example.graduationapplication.studentStuff.firebaseUtils.models.chatRooms room = new com.example.graduationapplication.studentStuff.firebaseUtils.models.chatRooms(title, subject);
                                chatRooms_DAO.InsertChatRoom(room,onAddListener,onFailureListener);}
                                else{
                                    Toast.makeText(chatRooms.this,"error:\n Missing title or subject",Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .show();

            }
        });
        mChatRoomsRecycler = (RecyclerView) findViewById(R.id.chatRooms_recycler);
        adapter=new Adapter_rooms(rooms);
        adapter.setOnNoteClickListener(new Adapter_rooms.OnItemClickListener() {
            @Override
            public void onItemClicked(com.example.graduationapplication.studentStuff.firebaseUtils.models.chatRooms room) {
                Intent intent=new Intent(chatRooms.this,chat.class);
                intent.putExtra("chatID",room.getId());
                startActivity(intent);
            }
        });
        layoutManager= new LinearLayoutManager(this);
        mChatRoomsRecycler.setAdapter(adapter);
        mChatRoomsRecycler.setLayoutManager(layoutManager);

        mChatRoomsDrawerLayout = (DrawerLayout) findViewById(R.id.chatRooms_DrawerLayout);
         actionBarDrawerToggle = new ActionBarDrawerToggle(this, mChatRoomsDrawerLayout, R.string.nav_prof_open, R.string.nav_prof_close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mChatRoomsDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // setting the functionality of the navigation menu as in the action of clicking each item in it.

        final NavigationView mChatRoomsNavigationView = (NavigationView) findViewById(R.id.chatRooms_navigation_view);
        mChatRoomsNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                String name = menuItem.getTitle().toString();
                if (id == R.id.nav_student_timeTable) {
                    startActivity(new Intent(chatRooms.this, Schedules.class));
                } else if (id == R.id.nav_student_AllNotes) {
                    startActivity(new Intent(chatRooms.this, AllNotes.class));
                } else if (id == R.id.nav_student_grades) {
                    startActivity(new Intent(chatRooms.this, Grades.class));
                } else if (id == R.id.nav_student_Attendance) {
                    startActivity(new Intent(chatRooms.this, Attendance.class));
                }else if (id == R.id.nav_student_chatRooms) {
                    startActivity(new Intent(chatRooms.this, chatRooms.class));
                }else if (id == R.id.nav_student_home) {
                    startActivity(new Intent(chatRooms.this, Entrance_student.class));
                    finish();
                }else if (id == R.id.nav_student_logout) {
                    sessionManager.logoutUser();
                }
                mChatRoomsDrawerLayout.closeDrawers();
                return true;
            }
        });


        MyDatabase.getChatRoomsBranch().addValueEventListener(valueEventListener);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    ValueEventListener valueEventListener= new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            rooms= new ArrayList<>();
            for(DataSnapshot item: dataSnapshot.getChildren()){
                com.example.graduationapplication.studentStuff.firebaseUtils.models.chatRooms room = item.getValue(com.example.graduationapplication.studentStuff.firebaseUtils.models.chatRooms.class);
                rooms.add(room);
            }
            adapter.changeData(rooms);
            Log.e("chat rooms listener","entered onDateChange");
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

            showMessage("Error", "Something went wrong in chat rooms", "OK", new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    dialog.dismiss();
                }
            });
            Log.e("chat rooms listener",databaseError.getMessage());
        }
    };



    OnSuccessListener onAddListener =new OnSuccessListener() {
        @Override
        public void onSuccess(Object o) {
            Toast.makeText(chatRooms.this,"Chat room added successfully",Toast.LENGTH_SHORT);

        }
    };
    OnFailureListener onFailureListener= new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(chatRooms.this,"Failed to add chat room",Toast.LENGTH_SHORT);
        }
    };

}
