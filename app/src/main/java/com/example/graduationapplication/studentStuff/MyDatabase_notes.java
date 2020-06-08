package com.example.graduationapplication.studentStuff;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.graduationapplication.studentStuff.DAOs.notesDAO;
import com.example.graduationapplication.studentStuff.Database_Models.notes;

@Database(entities = {notes.class}, version = 2, exportSchema = false)
public abstract class MyDatabase_notes extends RoomDatabase {
    private static MyDatabase_notes myDatabase;
    public abstract notesDAO notesDao();
    public static final Migration MIGRATION_1_2= new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE notes "
                    + " ADD COLUMN Type TEXT");
        }
    };

     public static MyDatabase_notes getInstance (Context context){
         if(myDatabase==null){
             myDatabase = Room.databaseBuilder(context.getApplicationContext(),MyDatabase_notes.class,
                     "Notes Database").allowMainThreadQueries().build();
         }
             return myDatabase=Room.databaseBuilder(context.getApplicationContext(),
                     MyDatabase_notes.class, "Notes Database")
                     .addMigrations(MIGRATION_1_2)
                     .allowMainThreadQueries()
                     .build();
     }

}
