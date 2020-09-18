package com.example.save_me.viewmodel;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.save_me.model.Note;
import com.example.save_me.repo.NoteRepository;

import java.util.List;

public class NoteViewmodel extends ViewModel {

    NoteRepository noteRepository;
    LiveData<List<Note>> getAllNotes;


    @ViewModelInject
    public NoteViewmodel(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void insert(Note note) {
        noteRepository.insert(note);
    }

    public void update(Note note){
        noteRepository.update(note);
    }

    public void deleteNote(Note note){
        noteRepository.deleteNote(note);
    }

    public LiveData<List<Note>> getAllNotes() {
        return noteRepository.getAllNotes();
    }



    public void delete(){
        noteRepository.deleteAll();
    }


}
