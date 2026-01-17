
package com.example.tarea3v2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<CategoryWithNotes> categoriesWithNotes;

    public CategoryAdapter(List<CategoryWithNotes> categoriesWithNotes) {
        this.categoriesWithNotes = categoriesWithNotes;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryWithNotes categoryWithNotes = categoriesWithNotes.get(position);
        holder.categoryName.setText(categoryWithNotes.category.categoryName);

        NoteAdapter noteAdapter = new NoteAdapter(categoryWithNotes.notes);
        holder.notesRecyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.notesRecyclerView.setAdapter(noteAdapter);
    }

    @Override
    public int getItemCount() {
        return categoriesWithNotes.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        RecyclerView notesRecyclerView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.text_view_category_name);
            notesRecyclerView = itemView.findViewById(R.id.recycler_view_notes);
        }
    }
}
