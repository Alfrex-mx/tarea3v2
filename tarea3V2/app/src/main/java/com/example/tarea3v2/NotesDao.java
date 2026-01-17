
package com.example.tarea3v2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface NotesDao {
    @Insert
    void insertCategory(Category category);

    @Insert
    void insertNote(Note note);

    @Transaction
    @Query("SELECT * FROM categories")
    List<CategoryWithNotes> getCategoriesWithNotes();

    @Query("SELECT * FROM notes WHERE note_title LIKE :searchText OR note_content LIKE :searchText")
    List<Note> searchNotes(String searchText);
}
