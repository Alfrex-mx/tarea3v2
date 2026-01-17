
package com.example.tarea3v2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class Category {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    public int categoryId;

    @ColumnInfo(name = "category_name")
    public String categoryName;

    @Override
    public String toString() {
        return categoryName;
    }
}
