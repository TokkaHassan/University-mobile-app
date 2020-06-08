package com.example.graduationapplication.studentStuff.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.graduationapplication.R;
import com.example.graduationapplication.SessionManager;
import com.example.graduationapplication.studentStuff.APIManager;
import com.example.graduationapplication.studentStuff.API_models.Registeration.TestDataItem;
import com.example.graduationapplication.studentStuff.API_models.StudentsInfo.StudentInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.graduationapplication.SessionManager.ID;


public class home extends Fragment {
    public TextView mHomeTheName;
    public TextView mHomeTheID;
    public TextView mHomeTheGPA;
    SessionManager sessionManager;


    public home() {
    }

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.stu_fragment_home, container, false);
        mHomeTheName =  view.findViewById(R.id.home_theName);
        mHomeTheID =  view.findViewById(R.id.home_theID);
        mHomeTheGPA =  view.findViewById(R.id.home_theGPA);
        sessionManager= new SessionManager(container.getContext().getApplicationContext());

        APIManager.getAPIs().getStudentInfoCalls().enqueue(new Callback<StudentInfo>() {
            @Override
            public void onResponse(Call<StudentInfo> call, Response<StudentInfo> response) {
                if(response.isSuccessful()){

                    List<com.example.graduationapplication.studentStuff.API_models.StudentsInfo.TestDataItem> items= response.body().getTestData();
                    for(com.example.graduationapplication.studentStuff.API_models.StudentsInfo.TestDataItem item:items){
                        if(item.getName().equals(sessionManager.getName())){
                            sessionManager.getEditor().putString(ID,item.getSid()).commit();
                            mHomeTheName.setText(item.getName());
                            mHomeTheID.setText(sessionManager.getID());
                            mHomeTheGPA.setText(item.getGpa());

                            break;
                        }
                    }

                    Log.e("api","success");
                }else{
                    Log.e("api_onSuccess:",response.code()+"");
                }
               // Log.e("api","success");

            }

            @Override
            public void onFailure(Call<StudentInfo> call, Throwable t) {

                Log.e("api","failure");
                Log.e("api",t.getMessage());

            }
        });

        return view;


    }

}
