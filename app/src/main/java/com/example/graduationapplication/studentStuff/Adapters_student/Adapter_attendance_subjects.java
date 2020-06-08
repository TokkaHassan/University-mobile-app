package com.example.graduationapplication.studentStuff.Adapters_student;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.graduationapplication.R;

import java.util.List;

public class Adapter_attendance_subjects extends RecyclerView.Adapter<Adapter_attendance_subjects.ViewHolder> {

    List<com.example.graduationapplication.studentStuff.API_models.SubjectsTable.TestDataItem> items;
    OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public Adapter_attendance_subjects(List<com.example.graduationapplication.studentStuff.API_models.SubjectsTable.TestDataItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stu_card_attendance_subjects,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final com.example.graduationapplication.studentStuff.API_models.SubjectsTable.TestDataItem item=items.get(i);
        viewHolder.subject.setText(item.getName());
        if(onItemClickListener!=null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClicked(item);
                }
            });
        }

    }

    public void changeData(List<com.example.graduationapplication.studentStuff.API_models.SubjectsTable.TestDataItem> list){
        items=list;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if(items==null)return 0;
        else return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView subject;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subject=itemView.findViewById(R.id.attendance_card_subject);
        }
    }
    public interface OnItemClickListener {
        void onItemClicked(com.example.graduationapplication.studentStuff.API_models.SubjectsTable.TestDataItem subject);
    }
}
