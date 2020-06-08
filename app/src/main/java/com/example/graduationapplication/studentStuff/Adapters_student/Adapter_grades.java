package com.example.graduationapplication.studentStuff.Adapters_student;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.graduationapplication.R;
import com.example.graduationapplication.studentStuff.Recycler_classes.Grades_form;

import java.util.List;

public class Adapter_grades extends RecyclerView.Adapter<Adapter_grades.ViewHolder> {

    List<Grades_form> items;

    public Adapter_grades(List<Grades_form> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stu_card_grade,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Grades_form grades_form=items.get(i);
        viewHolder.subject.setText(grades_form.getSubject());
        viewHolder.grade.setText(grades_form.getGrade());

    }

    @Override
    public int getItemCount() {
        if(items==null)return 0;
        return items.size();
    }
    public void changeData(List<Grades_form> newItems){
        items=newItems;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView subject;
        TextView grade;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.subject=itemView.findViewById(R.id.grade_subject);
            this.grade=itemView.findViewById(R.id.grade_myGrade);
        }
    }
}
