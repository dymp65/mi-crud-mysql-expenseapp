package com.praktisi.expenseappmysql;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.praktisi.expenseappmysql.Helpers.DatabaseHelper;
import com.praktisi.expenseappmysql.Helpers.NoteHelper;
import com.praktisi.expenseappmysql.Models.Note;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText titleEditText, contentEditText;
    private Button addButton, viewButton;
    private DatabaseHelper databaseHelper;
    private NoteHelper noteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleEditText = findViewById(R.id.titleEditText);
        contentEditText = findViewById(R.id.contentEditText);
        addButton = findViewById(R.id.addButton);
        viewButton = findViewById(R.id.viewButton);

        databaseHelper = new DatabaseHelper();
        noteHelper = new NoteHelper();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String content = contentEditText.getText().toString();
                Note note = new Note();
                note.setTitle(title);
                note.setContent(content);
                noteHelper.addNoteAsync(note, new DatabaseHelper.DatabaseCallback<Boolean>() {
                    @Override
                    public void onComplete(Boolean result) {
                        if (result) {
                            Toast.makeText(MainActivity.this, "Note added", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Failed to add note", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteHelper.getNotesAsync(new DatabaseHelper.DatabaseCallback<List<Note>>() {
                    @Override
                    public void onComplete(List<Note> notes) {
                        for (Note note : notes) {
                            Toast.makeText(MainActivity.this, note.getTitle() + ": " + note.getContent(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
