package com.humber.tuansnotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    List<Note> notes;
    NoteDao noteDao;
    CustomAdapter customAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);
        noteDao = AppDatabase.getAppDatabase(this).noteDao();
        notes = noteDao.getNotes();
        if (notes.isEmpty()) {
            noteDao.insertNote(new Note("Note 6", "Description for note 6", false));
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            noteDao.insertNote(new Note("Nguyen Anh Tuan Le N01414195", "ChatGPT is a chatbot launched by OpenAI in November 2022. It is built on top of OpenAI's GPT-3 family of large language models and is fine-tuned with both supervised and reinforcement learning techniques. Wikipedia November 30, 2022", true));
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            noteDao.insertNote(new Note("Note 4", "Description for note 4", true));
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            noteDao.insertNote(new Note("Note 3", "Description for note 3", true));
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            noteDao.insertNote(new Note("Note 2", "Nguyen Anh Tuan Le N01414195", true));
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            noteDao.insertNote(new Note("Note 1", "Description for note 1", true));
            notes = noteDao.getNotes();
        }
        customAdapter = new CustomAdapter(notes, noteDao);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));

    }

    public void createNewNote(View view) {
        Intent intent = new Intent(this, NewNoteActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        notes = noteDao.getNotes();
        recyclerView.setAdapter(new CustomAdapter(notes, noteDao));
        recyclerView.invalidate();
//        recyclerView.getAdapter().notifyDataSetChanged();
        System.out.println("?????????");
    }
}