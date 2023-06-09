package com.example.notes_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
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
        searchView=findViewById(R.id.search);



        DBHelper dbHelper = new DBHelper(MainActivity.this);
        Cursor cursor = dbHelper.viewData();
        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String note = cursor.getString(2);
            String time= cursor.getString(3);
            SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm a");
            Date date=new Date(time);
            NotesModel user = new NotesModel(id, title, note,date);
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
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<NotesModel> filteredList = new ArrayList<>();
                for (NotesModel singleNote : userList){
                    if (singleNote.getTitle().toLowerCase().contains(newText.toLowerCase())
                            || singleNote.getNote().toLowerCase().contains(newText.toLowerCase())){
                        filteredList.add(singleNote);
                    }
                    adapter.filterlist(filteredList);
                }
                return true;
            }
        });
    }

}