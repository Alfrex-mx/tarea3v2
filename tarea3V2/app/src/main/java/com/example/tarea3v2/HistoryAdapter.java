
package com.example.tarea3v2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<History> historyList;

    public HistoryAdapter(List<History> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        History history = historyList.get(position);
        holder.action.setText(history.action);
        holder.details.setText(history.details);
        holder.date.setText(DateFormat.getDateTimeInstance().format(history.createdAt));
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView action;
        TextView details;
        TextView date;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            action = itemView.findViewById(R.id.text_view_action);
            details = itemView.findViewById(R.id.text_view_details);
            date = itemView.findViewById(R.id.text_view_date);
        }
    }
}
