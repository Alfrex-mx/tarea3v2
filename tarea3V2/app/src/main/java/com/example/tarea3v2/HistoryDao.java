
package com.example.tarea3v2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoryDao {
    @Insert
    void insertHistory(History history);

    @Query("SELECT * FROM history ORDER BY created_at DESC")
    List<History> getAllHistory();

    @Query("SELECT * FROM history WHERE action = :action ORDER BY created_at DESC")
    List<History> getHistoryByAction(String action);

    @Query("SELECT * FROM history WHERE created_at >= :date ORDER BY created_at DESC")
    List<History> getHistoryByDate(long date);
}
