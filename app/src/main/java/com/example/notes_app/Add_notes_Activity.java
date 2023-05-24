package com.example.notes_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Add_notes_Activity extends AppCompatActivity {
    ImageView back;
    EditText title,notes;
    FloatingActionButton check;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        back=findViewById(R.id.back);
        title=findViewById(R.id.title);
        notes=findViewById(R.id.note);
        check=findViewById(R.id.check);

    }
}