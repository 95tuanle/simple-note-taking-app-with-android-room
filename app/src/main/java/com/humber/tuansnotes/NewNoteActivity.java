package com.humber.tuansnotes;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewNoteActivity extends AppCompatActivity {
    AppDatabase appDatabase;
    EditText name;
    EditText description;
    CheckBox priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        appDatabase = AppDatabase.getAppDatabase(this);
        name = findViewById(R.id.et_name);
        description = findViewById(R.id.et_description);
        priority = findViewById(R.id.cb_priority);
    }

    public void createNewNote(View view) {
        if (name.getText().toString().equals("") || description.getText().toString().equals("")) {
            Toast.makeText(this, "Empty inputs", Toast.LENGTH_SHORT).show();
        } else {
            appDatabase.noteDao().insertNote(new Note(name.getText().toString(), description.getText().toString(), priority.isChecked()));
            finish();
        }
    }
}