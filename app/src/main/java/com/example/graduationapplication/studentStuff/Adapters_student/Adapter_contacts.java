package com.example.graduationapplication.studentStuff.Adapters_student;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.graduationapplication.R;
import com.example.graduationapplication.studentStuff.Recycler_classes.Contacts_form;
import com.example.graduationapplication.studentStuff.firebaseUtils.models.professors;

import java.util.List;

public class Adapter_contacts extends RecyclerView.Adapter<Adapter_contacts.ViewHolder> {
    List<professors> items;
    public Adapter_contacts(List<professors> items) {
        this.items = items;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stu_card_contact,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        professors item=items.get(i);
        viewHolder.name.setText(item.getName());
        viewHolder.mobile.setText(item.getMobile());
        viewHolder.email.setText(item.getEmail());


    }

    @Override
    public int getItemCount() {
        if(items==null)return 0;
        return items.size();
    }
    public void changeData(List<professors> professors){
        items=professors;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView mobile;
        TextView email;

        public ViewHolder(View view) {
            super(view);
            name=view.findViewById(R.id.contacts_theName);
            mobile=view.findViewById(R.id.contact_theMobile);
            email=view.findViewById(R.id.contact_theEmail);

        }



    }
}
