package com.example.graduationapplication.studentStuff.Activities;

import android.app.DatePickerDialog;
import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.graduationapplication.Base.BaseActivity;
import com.example.graduationapplication.R;
import com.example.graduationapplication.studentStuff.Database_Models.notes;
import com.example.graduationapplication.studentStuff.MyDatabase_notes;

import java.util.Calendar;

public class noteUpdate extends BaseActivity {

    public EditText mNoteUpdateTitle;
    public TextView mNoteUpdateType;
    public TextView mNoteUpdateDate;
    public EditText mNoteUpdateSubject;
    public EditText mNoteUpdateContent;
    public Button mNoteUpdateAdd;
    String type;
    notes note;
    DatePickerDialog.OnDateSetListener onDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //creating the layout design

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_update);
        Intent i = getIntent();
        note = (notes) i.getSerializableExtra("note");
        mNoteUpdateTitle = (EditText) findViewById(R.id.note_update_title);
        mNoteUpdateType = (TextView) findViewById(R.id.note_update_type);
        mNoteUpdateDate = (TextView) findViewById(R.id.note_update_date);
        mNoteUpdateSubject = (EditText) findViewById(R.id.note_update_subject);
        mNoteUpdateContent = (EditText) findViewById(R.id.note_update_content);
        mNoteUpdateAdd = (Button) findViewById(R.id.note_update_add);
        mNoteUpdateTitle.setText(note.getTitle());
        mNoteUpdateDate.setText(note.getDate());
        mNoteUpdateType.setText(note.getType());
        mNoteUpdateSubject.setText(note.getSubject());
        mNoteUpdateContent.setText(note.getContent());
        mNoteUpdateAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setTitle(mNoteUpdateTitle.getText().toString());
                note.setDate(mNoteUpdateDate.getText().toString());
                note.setSubject(mNoteUpdateSubject.getText().toString());
                note.setContent(mNoteUpdateContent.getText().toString());
                note.setType(mNoteUpdateType.getText().toString());

                if(note.getTitle().isEmpty())
                    showMessage("Error", "please state a title for the note", "OK", new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                        }
                    });
                else{
                    MyDatabase_notes.getInstance(noteUpdate.this).notesDao().UpdateNote(note);
                    Toast.makeText(noteUpdate.this, "Note updated successfully", Toast.LENGTH_SHORT).show();
                    finish();}
            }
        });
        mNoteUpdateType.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            PopupMenu pm = new PopupMenu(noteUpdate.this, mNoteUpdateType);
            pm.getMenuInflater().inflate(R.menu.note_type_menu, pm.getMenu());
            pm.setOnMenuItemClickListener(onMenuItemClickListener);
            pm.show();
        }
    };
    PopupMenu.OnMenuItemClickListener onMenuItemClickListener= new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if(item.getItemId()==R.id.note_type_reminder){
                mNoteUpdateType.setText(item.getTitle());
            }
            else if (item.getItemId()==R.id.note_type_todo){
                mNoteUpdateType.setText(item.getTitle());
                //Log.e("note type", type );
            }
            return true;
        }
    };

    public void onDateClicked (View view){
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        onDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int theMonth=month+1;
                String date=dayOfMonth+"/"+theMonth+"/"+year;
                Log.e("making sure",date);
                mNoteUpdateDate.setText(date);
            }
        };
        DatePickerDialog datePickerDialog=new DatePickerDialog(noteUpdate.this,onDateSetListener,year,month,day);
        datePickerDialog.show();
    }
}
