package com.humber.tuansnotes;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM note ORDER BY priority DESC, date DESC")
    List<Note> getNotes();

    @Insert
    void insertNote(Note note);

    @Update
    void updateNote(Note note);
}
