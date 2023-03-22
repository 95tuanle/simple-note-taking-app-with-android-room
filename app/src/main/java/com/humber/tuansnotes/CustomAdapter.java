package com.humber.tuansnotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private List<Note> notes;
    NoteDao noteDao;

    public CustomAdapter(List<Note> notes, NoteDao noteDao) {
        this.notes = notes;
        this.noteDao = noteDao;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.getName().setText(note.getName());
        holder.getDescription().setText(note.getDescription());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy  HH:hh:ss", Locale.getDefault());
        holder.getDate().setText(simpleDateFormat.format(note.getDate()));
        holder.getPriority().setChecked(note.isPriority());
        holder.getPriority().setOnClickListener(v -> {
            note.setPriority(holder.getPriority().isChecked());
            note.setDate(new Date());
            noteDao.updateNote(note);
            notes = noteDao.getNotes();
            notifyDataSetChanged();
            String priority;
            if (note.isPriority()) {
                priority = "high";
            } else {
                priority = "low";
            }
            Toast.makeText(v.getContext(), note.getName() + " is " + priority + " priority", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView description;
        private final TextView date;
        private final CheckBox priority;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            description = itemView.findViewById(R.id.tv_description);
            date = itemView.findViewById(R.id.tv_date);
            priority = itemView.findViewById(R.id.cb_priority);
        }

        public TextView getName() {
            return name;
        }

        public TextView getDescription() {
            return description;
        }

        public TextView getDate() {
            return date;
        }

        public CheckBox getPriority() {
            return priority;
        }
    }
}
