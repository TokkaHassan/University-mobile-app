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

public class Adapter_notes extends RecyclerView.Adapter<Adapter_notes.ViewHolder> {

    List<notes> items;
    OnItemClickListener onNoteClickListener;

    public OnItemClickListener getOnNoteLongClickedListener() {
        return onNoteLongClickedListener;
    }

    public void setOnNoteLongClickedListener(OnItemClickListener onNoteLongClickedListener) {
        this.onNoteLongClickedListener = onNoteLongClickedListener;
    }

    OnItemClickListener onNoteLongClickedListener;

    public OnItemClickListener getOnNoteClickListener() {
        return onNoteClickListener;
    }

    public void setOnNoteClickListener(OnItemClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
    }

    public Adapter_notes(List<notes> items) {
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
        final notes note = items.get(i);
        viewHolder.title.setText(note.getTitle());
        viewHolder.date.setText(note.getDate());

        if(onNoteClickListener!=null){
         viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 onNoteClickListener.onItemClicked(note);

             }
         });
        }
        if(onNoteLongClickedListener!=null){
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onNoteLongClickedListener.onItemClicked(note);
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

    public void changeData(List<notes> newItems){
        items=newItems;
        notifyDataSetChanged();

    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.note_card_title);
            date=itemView.findViewById(R.id.note_card_date);
        }
    }
    public interface OnItemClickListener {
        void onItemClicked(notes note);
    }
}
