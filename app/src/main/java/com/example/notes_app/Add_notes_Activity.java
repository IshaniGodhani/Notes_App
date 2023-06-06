package com.example.notes_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        notes=findViewById(R.id.content);
        check=findViewById(R.id.check);


        if(getIntent().getExtras()!=null)
        {

            String up_title = getIntent().getStringExtra("title");
            String up_note = getIntent().getStringExtra("note");
            title.setText("" + up_title);
            notes.setText("" + up_note);
        }


        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(getIntent().getExtras()==null)
                {
                    String titles = title.getText().toString();
                    String note = notes.getText().toString();
                    if (title.getText().toString().equals("") && notes.getText().toString().equals(""))
                        {
                            title.setError("Enter Title");
                            notes.setError("Enter Note");
                        }
                        else if (title.getText().toString().equals(""))
                        {
                            title.setError("Enter Title");
                        }
                        else if (notes.getText().toString().equals(""))
                        {
                            notes.setError("Enter Note");
                        }
                        else {
                            title.setError(null);
                            notes.setError(null);
                            DBHelper dbHelper=new DBHelper(Add_notes_Activity.this);
                            dbHelper.insertData(titles, note);
                            Intent intent = new Intent(Add_notes_Activity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                    if(getIntent().getExtras()!=null)
                    {
                        int id = getIntent().getIntExtra("Id", 0);
                        String txt1 = title.getText().toString();
                        String txt2 = notes.getText().toString();

                        if (title.getText().toString().equals("") && notes.getText().toString().equals("")) {
                            title.setError("Enter Title");
                            notes.setError("Enter Notes");
                        } else if (title.getText().toString().equals("")) {
                            title.setError("Enter Title");
                        } else if (notes.getText().toString().equals("")) {
                            notes.setError("Enter Notes");
                        } else {
                            title.setError(null);
                            notes.setError(null);
                            DBHelper dbHelper = new DBHelper(Add_notes_Activity.this);
                            dbHelper.updateData(id, txt1, txt2);
                            Intent intent = new Intent(Add_notes_Activity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                }
            }
        });

    }
}