package com.example.graduationapplication.studentStuff.Adapters_student;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.graduationapplication.R;
import com.example.graduationapplication.studentStuff.Recycler_classes.student_form;
import com.example.graduationapplication.studentStuff.firebaseUtils.models.students;

import java.util.List;

public class Adapter_checkList extends RecyclerView.Adapter<Adapter_checkList.ViewHolder> {

    List<students> items;
    onItemClickListener onCheckBoxClicked;

    public Adapter_checkList(List<students> items) {
        this.items = items;
    }

    public onItemClickListener getOnCheckBoxClicked() {
        return onCheckBoxClicked;
    }

    public void setOnCheckBoxClicked(onItemClickListener onCheckBoxClicked) {
        this.onCheckBoxClicked = onCheckBoxClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stu_card_check_list,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final students form = items.get(i);
        viewHolder.name.setText(form.getName());
        if(onCheckBoxClicked!=null){
            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        onCheckBoxClicked.OnItemClicked(form);
                    }
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        if(items==null)return 0;
        else return items.size();

    }

    public void changeData(List<students> forms){
        items=forms;
        notifyDataSetChanged();

    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.checkList_name);
            this.checkBox = itemView.findViewById(R.id.checkBox);
        }

    }
    public interface onItemClickListener{
        void OnItemClicked(students form);
    }
}
