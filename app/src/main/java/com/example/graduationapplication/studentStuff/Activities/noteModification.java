package com.example.graduationapplication.studentStuff.Activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class noteModification extends BaseActivity {


    public Button mNoteAdd;
    public EditText mNoteTitle;
    public EditText mNoteSubject;
    public EditText mNoteContent;
    public Button mNoteType;
    public TextView mNoteDate;
    PopupMenu popupMenu;
    String type;
    DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //creating the layout design

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_modification);
        mNoteTitle = (EditText) findViewById(R.id.note_title);
        mNoteSubject = (EditText) findViewById(R.id.note_subject);
        mNoteContent = (EditText) findViewById(R.id.note_content);
        mNoteAdd = (Button) findViewById(R.id.note_add);
        mNoteAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sTitle = mNoteTitle.getText().toString();
                String sDate = mNoteDate.getText().toString();
                String sSubject = mNoteSubject.getText().toString();
                String sContent = mNoteContent.getText().toString();
                notes note = new notes(sTitle, sDate, sSubject, sContent,type);
                if(sTitle.isEmpty())
                    showMessage("Error", "please state a title for the note", "OK", new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                        }
                    });
                else{MyDatabase_notes.getInstance(noteModification.this).notesDao().InsertNote(note);
                    Toast.makeText(noteModification.this, "Note added successfully", Toast.LENGTH_SHORT).show();
                    finish();}

            }
        });

        mNoteType = (Button) findViewById(R.id.note_type);
        mNoteDate =  findViewById(R.id.note_date);
        mNoteType.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            PopupMenu pm = new PopupMenu(noteModification.this, mNoteType);
            pm.getMenuInflater().inflate(R.menu.note_type_menu, pm.getMenu());
            pm.setOnMenuItemClickListener(onMenuItemClickListener);
            pm.show();
        }
    };
    PopupMenu.OnMenuItemClickListener onMenuItemClickListener= new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if(item.getItemId()==R.id.note_type_reminder){
                type=item.getTitle().toString();
                mNoteType.setText(type);
            }
            else if (item.getItemId()==R.id.note_type_todo){
                type=item.getTitle().toString();
                Log.e("note type", type );
                mNoteType.setText(type);
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
                mNoteDate.setText(date);
            }
        };
        DatePickerDialog datePickerDialog=new DatePickerDialog(noteModification.this,onDateSetListener,year,month,day);

        datePickerDialog.show();
    }
}
