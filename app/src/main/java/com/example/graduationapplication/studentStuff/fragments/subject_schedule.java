package com.example.graduationapplication.studentStuff.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.graduationapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class subject_schedule extends Fragment {


    public subject_schedule() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subject_schedule, container, false);
    }


}
