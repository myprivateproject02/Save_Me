package com.example.save_me.room;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.save_me.model.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Query("SELECT * FROM note")
    LiveData<List<Note>> getAllNotes();

    @Update
    void update(Note note);

    @Query("DELETE FROM note WHERE id = :id")
    void deleteNoteById(int id);

    @Query("DELETE FROM note")
    void deleteAllData();


}
