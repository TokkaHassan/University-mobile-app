package com.example.graduationapplication.studentStuff.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.graduationapplication.studentStuff.Database_Models.notes;

import java.util.List;

@Dao
public interface notesDAO {
    @Insert
    public void InsertNote (notes note);
    @Delete
    public void DeleteNote (notes note);
    @Update
    public void UpdateNote (notes note);
    @Query( "select * from notes")
    public List<notes> SelectAllNotes ();
    @Query("select * from notes where Subject Like :mySubChoice")
    public List<notes> SelectBySubject (String mySubChoice);
    @Query("select * from notes where Date Like :myDateChoice")
    public List<notes> SelectByDate (String myDateChoice);
    @Query("select * from notes where (Date Like :myDateChoice " + " AND Type Like :type)")
    public List<notes> getCurrentNotesByType (String myDateChoice ,String type);




}
