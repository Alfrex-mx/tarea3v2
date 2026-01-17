
package com.example.tarea3v2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "history")
public class History {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "history_id")
    public int historyId;

    @ColumnInfo(name = "action")
    public String action;

    @ColumnInfo(name = "created_at")
    public long createdAt;

    @ColumnInfo(name = "details")
    public String details;
}
