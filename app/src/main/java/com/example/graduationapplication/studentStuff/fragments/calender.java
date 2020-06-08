package com.example.graduationapplication.studentStuff.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.graduationapplication.Base.BaseFragment;
import com.example.graduationapplication.R;
import com.example.graduationapplication.studentStuff.Activities.AllNotes;
import com.example.graduationapplication.studentStuff.Activities.noteUpdate;
import com.example.graduationapplication.studentStuff.Adapters_student.Adapter_notes;
import com.example.graduationapplication.studentStuff.Database_Models.notes;
import com.example.graduationapplication.studentStuff.MyDatabase_notes;
import com.example.graduationapplication.studentStuff.firebaseUtils.MyDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class calender extends BaseFragment {


    public final String todo="To Do";
    public final String reminder="Reminder";
    public RecyclerView mTodayTodoRecycler;
    public RecyclerView mTodayRemindersRecycler;
    Adapter_notes adapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.LayoutManager layoutManagerReminder;

    Adapter_notes adapterReminder;


    public calender() {
        // Required empty public constructor

    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.stu_fragment_calender, container, false);

        String formattedDate = new SimpleDateFormat("d/M/yyyy", Locale.getDefault()).format(new Date());
        mTodayTodoRecycler = view.findViewById(R.id.today_todo_recycler);
        List<notes> todoList = MyDatabase_notes.getInstance(container.getContext()).notesDao().getCurrentNotesByType(formattedDate,todo);
        if(todoList.isEmpty()) Log.e("query problem","didnt get any list");
        Log.e("date",formattedDate);
        adapter = new Adapter_notes(todoList);
        layoutManager=new LinearLayoutManager(this.getActivity());
        mTodayTodoRecycler.setAdapter(adapter);
        mTodayTodoRecycler.setLayoutManager(layoutManager);
        mTodayRemindersRecycler = view.findViewById(R.id.today_reminders_recycler);
        adapter.setOnNoteClickListener(new Adapter_notes.OnItemClickListener() {
            @Override
            public void onItemClicked(notes note) {
                Intent intent=new Intent(container.getContext(), noteUpdate.class);
                intent.putExtra("note",note);
                startActivity(intent);
            }
        });
        adapter.setOnNoteLongClickedListener(new Adapter_notes.OnItemClickListener() {
            @Override
            public void onItemClicked(final notes note) {
                askForAction("Warning", "Are you sure you want to delete note?", "YES", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        MyDatabase_notes.getInstance(container.getContext()).notesDao().DeleteNote(note);
                        adapter.changeData(MyDatabase_notes.getInstance(container.getContext()).notesDao().SelectAllNotes());
                    }
                }, "NO", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                });
            }
        });
        List<notes> reminderList = MyDatabase_notes.getInstance(container.getContext()).notesDao().getCurrentNotesByType(formattedDate,reminder);
        adapterReminder=new Adapter_notes(reminderList);
        layoutManagerReminder=new LinearLayoutManager(this.getActivity());
        mTodayRemindersRecycler.setAdapter(adapterReminder);
        mTodayRemindersRecycler.setLayoutManager(layoutManagerReminder);

        return view;
    }



}
