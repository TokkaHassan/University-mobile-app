package com.example.graduationapplication.studentStuff.Adapters_student;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.graduationapplication.R;
import com.example.graduationapplication.studentStuff.Database_Models.notes;

import java.util.List;

public class Adapter_rooms extends RecyclerView.Adapter<Adapter_rooms.ViewHolder> {
    List<com.example.graduationapplication.studentStuff.firebaseUtils.models.chatRooms> items;
   OnItemClickListener onRoomClickListener;
   OnItemClickListener onRoomLongClickedListener;


    public OnItemClickListener getOnRoomLongClickedListener() {
        return onRoomLongClickedListener;
    }

    public void setOnNoteLongClickedListener(OnItemClickListener onRoomLongClickedListener) {
        this.onRoomLongClickedListener = onRoomLongClickedListener;
    }


    public OnItemClickListener getOnRoomClickListener() {
        return onRoomClickListener;
    }

    public void setOnNoteClickListener(OnItemClickListener onRoomClickListener) {
        this.onRoomClickListener = onRoomClickListener;
    }

    public Adapter_rooms(List<com.example.graduationapplication.studentStuff.firebaseUtils.models.chatRooms> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stu_card_note,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final com.example.graduationapplication.studentStuff.firebaseUtils.models.chatRooms room = items.get(i);
        viewHolder.title.setText(room.getName());
        viewHolder.subject.setText(room.getSubject());

        if(onRoomClickListener!=null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRoomClickListener.onItemClicked(room);

                }
            });
        }
        if(onRoomLongClickedListener!=null){
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onRoomLongClickedListener.onItemClicked(room);
                    return true;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if(items==null)return 0;
        else
            return items.size();
    }

    public void changeData(List<com.example.graduationapplication.studentStuff.firebaseUtils.models.chatRooms> newItems){
        items=newItems;
        notifyDataSetChanged();

    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView subject;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.note_card_title);
            subject=itemView.findViewById(R.id.note_card_date);
        }
    }
    public interface OnItemClickListener {
        void onItemClicked(com.example.graduationapplication.studentStuff.firebaseUtils.models.chatRooms room);
    }
}
