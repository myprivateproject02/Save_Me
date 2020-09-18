package com.example.save_me.repo;

import androidx.lifecycle.LiveData;


import com.example.save_me.model.Note;
import com.example.save_me.room.NoteDao;

import java.util.List;

import javax.inject.Inject;

public class NoteRepository {

    private NoteDao noteDao;
    LiveData<List<Note>> allNotes;
    

    @Inject
    public NoteRepository(NoteDao noteDao) {
        this.noteDao = noteDao;
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        noteDao.insert(note);
    }

    public void update(Note note) {
        noteDao.update(note);
    }

    public void deleteNote(Note note) {
        noteDao.deleteNoteById(note.getId());
    }


    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }


    public void deleteAll() {
        noteDao.deleteAllData();
    }

}
