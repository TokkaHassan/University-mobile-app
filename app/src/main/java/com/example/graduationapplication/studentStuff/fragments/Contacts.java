package com.example.graduationapplication.studentStuff.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.graduationapplication.Base.BaseActivity;
import com.example.graduationapplication.Base.BaseFragment;
import com.example.graduationapplication.R;
import com.example.graduationapplication.studentStuff.APIManager;
import com.example.graduationapplication.studentStuff.API_models.StudentsInfo.StudentInfo;
import com.example.graduationapplication.studentStuff.Adapters_student.Adapter_contacts;
import com.example.graduationapplication.studentStuff.Recycler_classes.Contacts_form;
import com.example.graduationapplication.studentStuff.firebaseUtils.MyDatabase;
import com.example.graduationapplication.studentStuff.firebaseUtils.firebase_DAOs.professorsDAO;
import com.example.graduationapplication.studentStuff.firebaseUtils.models.professors;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Contacts extends BaseFragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Adapter_contacts adapter;
    View view;
    List<Contacts_form>items;
    Contacts_form contact;
    Random random;
    List<professors> contactsProf;

    public Contacts() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.stu_fragment_contacts, container, false);
        recyclerView=view.findViewById(R.id.stu_contact_recycler);
        CreateContacts();
        adapter=new Adapter_contacts(contactsProf);
        layoutManager=new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }
    public void CreateContacts(){
        //items=new ArrayList<>();
        showProgressBar();
        MyDatabase.getProfessorsBranch().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hideProgressBar();
                contactsProf=new ArrayList<>();
                for(DataSnapshot item:dataSnapshot.getChildren()){
                    professors professor=item.getValue(professors.class);
                    contactsProf.add(professor);
                }
                adapter.changeData(contactsProf);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       /* APIManager.getAPIs().getStudentInfoCalls().enqueue(new Callback<StudentInfo>() {
            @Override
            public void onResponse(Call<StudentInfo> call, Response<StudentInfo> response) {
                hideProgressBar();
                if(response.isSuccessful()){
                    List<com.example.graduationapplication.studentStuff.API_models.StudentsInfo.TestDataItem> users=response.body().getTestData();
                    for(com.example.graduationapplication.studentStuff.API_models.StudentsInfo.TestDataItem item: users){
                        if (item.getRole().equals("doctor")){
                            Contacts_form contacts_form=new Contacts_form(item.getName(), (String) item.getPhone());
                            items.add(contacts_form);

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<StudentInfo> call, Throwable t) {

                hideProgressBar();
                showMessage("Error", "A problem occurred with connection", "ok", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        dialog.dismiss();
                    }
                });
            }
        });*/
    }

}
