
package com.example.tarea3v2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;
import java.util.stream.Collectors;

public class AddActivity extends AppCompatActivity {

    private AppDatabase db;
    private EditText categoryNameEditText;
    private EditText noteTitleEditText;
    private EditText noteContentEditText;
    private Spinner categoriesSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();

        categoryNameEditText = findViewById(R.id.edit_text_category_name);
        noteTitleEditText = findViewById(R.id.edit_text_note_title);
        noteContentEditText = findViewById(R.id.edit_text_note_content);
        categoriesSpinner = findViewById(R.id.spinner_categories);

        Button addCategoryButton = findViewById(R.id.button_add_category);
        addCategoryButton.setOnClickListener(v -> addCategory());

        Button addNoteButton = findViewById(R.id.button_add_note);
        addNoteButton.setOnClickListener(v -> addNote());

        loadCategories();
    }

    private void loadCategories() {
        List<Category> categories = db.notesDao().getCategoriesWithNotes().stream()
                .map(cn -> cn.category).collect(Collectors.toList());
        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriesSpinner.setAdapter(adapter);
    }

    private void addCategory() {
        String categoryName = categoryNameEditText.getText().toString();
        if (categoryName.isEmpty()) {
            Toast.makeText(this, "Category name cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Category category = new Category();
        category.categoryName = categoryName;
        db.notesDao().insertCategory(category);

        History history = new History();
        history.action = "insert_category";
        history.details = categoryName;
        history.createdAt = System.currentTimeMillis();
        db.historyDao().insertHistory(history);

        Toast.makeText(this, "Category added", Toast.LENGTH_SHORT).show();
        categoryNameEditText.setText("");
        loadCategories();
    }

    private void addNote() {
        String noteTitle = noteTitleEditText.getText().toString();
        String noteContent = noteContentEditText.getText().toString();
        Category selectedCategory = (Category) categoriesSpinner.getSelectedItem();

        if (noteTitle.isEmpty() || noteContent.isEmpty() || selectedCategory == null) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Note note = new Note();
        note.noteTitle = noteTitle;
        note.noteContent = noteContent;
        note.createdAt = System.currentTimeMillis();
        note.categoryId = selectedCategory.categoryId;
        db.notesDao().insertNote(note);

        History history = new History();
        history.action = "insert_note";
        history.details = noteTitle;
        history.createdAt = System.currentTimeMillis();
        db.historyDao().insertHistory(history);

        Toast.makeText(this, "Note added", Toast.LENGTH_SHORT).show();
        finish();
    }
}
