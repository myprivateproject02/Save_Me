package com.example.save_me.di;

import android.app.Application;

import androidx.room.Room;

import com.example.save_me.room.NoteDao;
import com.example.save_me.room.NoteDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class DataBaseModule {

    @Provides
    @Singleton
    public static NoteDatabase getDatabase(final Application application) {
        return Room.databaseBuilder(application.getApplicationContext(), NoteDatabase.class, "note_database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static NoteDao getNoteDao(NoteDatabase noteDatabase) {
        return noteDatabase.noteDao();
    }

}
