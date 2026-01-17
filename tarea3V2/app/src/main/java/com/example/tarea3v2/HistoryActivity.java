
package com.example.tarea3v2;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.Calendar;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private AppDatabase db;
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private Spinner filterSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();

        recyclerView = findViewById(R.id.recycler_view_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        filterSpinner = findViewById(R.id.spinner_filter_action);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.history_actions, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(spinnerAdapter);

        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedAction = parent.getItemAtPosition(position).toString();
                if (selectedAction.equals("All")) {
                    loadHistory();
                } else {
                    loadHistoryByAction(selectedAction);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                loadHistory();
            }
        });

        Button filterDateButton = findViewById(R.id.button_filter_date);
        filterDateButton.setOnClickListener(v -> showDatePicker());

        loadHistory();
    }

    private void loadHistory() {
        List<History> historyList = db.historyDao().getAllHistory();
        adapter = new HistoryAdapter(historyList);
        recyclerView.setAdapter(adapter);
    }

    private void loadHistoryByAction(String action) {
        List<History> historyList = db.historyDao().getHistoryByAction(action);
        adapter = new HistoryAdapter(historyList);
        recyclerView.setAdapter(adapter);
    }

    private void loadHistoryByDate(long date) {
        List<History> historyList = db.historyDao().getHistoryByDate(date);
        adapter = new HistoryAdapter(historyList);
        recyclerView.setAdapter(adapter);
    }

    private void showDatePicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.set(year1, monthOfYear, dayOfMonth, 0, 0, 0);
                    loadHistoryByDate(selectedDate.getTimeInMillis());
                }, year, month, day);
        datePickerDialog.show();
    }
}
