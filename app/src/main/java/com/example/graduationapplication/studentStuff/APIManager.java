package com.example.graduationapplication.studentStuff;

import android.content.Context;
import android.util.Log;

import com.example.graduationapplication.studentStuff.API_calls.student_info_calls;

import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {
    private static Retrofit retrofit;
    private static Retrofit getInstance(){
        if(retrofit==null){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(@NotNull String s) {
                    Log.e("stu_inf_check",s);
                }
            });
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();
            retrofit=new Retrofit.Builder()
                    .baseUrl("https://youseftest.000webhostapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }



    public static student_info_calls getAPIs (){
        student_info_calls  student_info_calls =getInstance().create(com.example.graduationapplication.studentStuff.API_calls.student_info_calls.class);
        return student_info_calls;
    }
}
