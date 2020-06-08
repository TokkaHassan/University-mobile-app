package com.example.graduationapplication.studentStuff.Adapters_student;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.graduationapplication.R;
import com.example.graduationapplication.SessionManager;
import com.example.graduationapplication.studentStuff.firebaseUtils.models.messages;

import java.util.List;

public class Adapter_messages extends RecyclerView.Adapter<Adapter_messages.ViewHolder> {

    List<messages> items;
    public static final int me=20;
    public static final int others=30;
    onItemClickListener onMessageClicked;
    SessionManager sessionManager;


    public onItemClickListener getOnMessageClicked() {
        return onMessageClicked;
    }

    public void setOnMessageClicked(onItemClickListener onMessageClicked) {
        this.onMessageClicked = onMessageClicked;
    }

    public Adapter_messages(List<messages> items, Context context) {
        this.items = items;
        this.sessionManager=new SessionManager(context);
    }

    @Override
    public int getItemViewType(int position) {

        if(items.get(position).getSenderID().equals(sessionManager.getID())){
            return me;
        }else
            return others;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view=null;
        if(viewType==me)
         view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stu_card_message_me,viewGroup,false);
        else if (viewType==others){
            view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stu_card_messages_others,viewGroup,false);

        }
        return new ViewHolder(view,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final messages message=items.get(i);
        viewHolder.content.setText(message.getText());
        viewHolder.time.setText(message.getTimeStamp());

        if(onMessageClicked!=null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMessageClicked.onItemClicked(message);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if(items==null) return 0;
        else return items.size();
    }
    public void AddMessage(messages message){

        items.add(message);
        notifyDataSetChanged();
    }

    public void changeData(List<messages> newItems){
        items=newItems;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView content;
        TextView time;
        public ViewHolder(@NonNull View itemView,int viewType) {
            super(itemView);
            if(viewType==me){
                this.content=itemView.findViewById(R.id.message_me_content);
                this.time=itemView.findViewById(R.id.message_me_clock);
            }else if(viewType==others){
                this.content=itemView.findViewById(R.id.message_others_content);
                this.time=itemView.findViewById(R.id.message_others_clock);
            }


        }
    }

    public interface onItemClickListener {
        void onItemClicked(messages message);
    }
}
