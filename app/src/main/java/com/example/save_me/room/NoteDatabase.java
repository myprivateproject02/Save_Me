package com.example.save_me.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.save_me.model.Note;


@Database(entities = {Note.class}, version = 1,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();



}
