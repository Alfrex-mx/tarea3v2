
package com.example.tarea3v2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Category.class, Note.class, History.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NotesDao notesDao();
    public abstract HistoryDao historyDao();
}
