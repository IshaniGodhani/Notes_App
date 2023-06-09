package com.example.notes_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
        ImageView menu,home,folder;
        SearchView searchView;
        RecyclerView recyclerView;
        RecyclerAdapter adapter;
        FloatingActionButton fab;
        ArrayList<NotesModel> userList= new ArrayList<NotesModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);
        fab=findViewById(R.id.fab);


        DBHelper dbHelper = new DBHelper(MainActivity.this);
        Cursor cursor = dbHelper.viewData();
        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String note = cursor.getString(2);
            NotesModel user = new NotesModel(id, title, note);
            userList.add(user);


        }
        adapter=new RecyclerAdapter(this,userList);
        LinearLayoutManager manager=new LinearLayoutManager(MainActivity.this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Add_notes_Activity.class);
                startActivity(intent);
            }
        });
    }

}