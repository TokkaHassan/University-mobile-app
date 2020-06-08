package com.example.graduationapplication.studentStuff.repository;

import com.example.graduationapplication.studentStuff.APIManager;
import com.example.graduationapplication.studentStuff.API_models.StudentsInfo.StudentInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class studentInfoRepository {
    public studentInfoRepository() {
    }
    public void getStudentsInfo (){
        APIManager.getAPIs().getStudentInfoCalls().enqueue(new Callback<StudentInfo>() {
            @Override
            public void onResponse(Call<StudentInfo> call, Response<StudentInfo> response) {

            }

            @Override
            public void onFailure(Call<StudentInfo> call, Throwable t) {

            }
        });
    }
}

public interface OnInfoPreparedListener {
    void OnInfoprepared(List com.example.graduationapplication.studentStuff.API_models.StudentsInfo.TestDataItem);

}