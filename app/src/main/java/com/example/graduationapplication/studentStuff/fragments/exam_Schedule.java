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
public class exam_Schedule extends Fragment {


    public exam_Schedule() {
        // Required empty public constructor
    }
View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_exam__schedule, container, false);
        return view;
    }

}
