package com.example.notes_app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.View_Holder> {
    Activity activity;
    ArrayList<NotesModel> userList;
    public RecyclerAdapter(Activity activity, ArrayList<NotesModel> userList) {
        this.activity=activity;
        this.userList=userList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(activity).inflate(R.layout.item_file,parent,false);
        View_Holder view_holder=new View_Holder(view);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.View_Holder holder, @SuppressLint("RecyclerView") int position) {
        NotesModel notesModel=userList.get(position);
        int id=notesModel.getId();
        String title= notesModel.getTitle();
        String note= notesModel.getNote();
        String date=notesModel.getDate();

        holder.title.setText(""+title);
        holder.note.setText(""+note);
        holder.date.setText(""+date);

        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(activity,holder.menu);
                activity.getMenuInflater().inflate(R.menu.edit_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.item_update)
                        {
                            DBHelper dbHelper=new DBHelper(activity);
                            Intent intent = new Intent(activity,Add_notes_Activity.class );
                            intent.putExtra("Id",id);
                            intent.putExtra("title",title);
                            intent.putExtra("note",note);
                            activity.startActivity(intent);
                            activity.finish();
                        }
                        else if(item.getItemId()==R.id.item_delete)
                        {
                            DBHelper dbHelper=new DBHelper(activity);
                            dbHelper.deleteData(id);
                            userList.remove(position);
                            notifyDataSetChanged();
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }
    public void filterlist(ArrayList<NotesModel> filteredList){
        userList = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder {
        TextView note,title,date;
        ImageView menu;
        public View_Holder(@NonNull View itemView) {
            super(itemView);
            note=itemView.findViewById(R.id.re_note);
            title=itemView.findViewById(R.id.re_title);
            menu=itemView.findViewById(R.id.menu);
            date=itemView.findViewById(R.id.date);
        }
    }
}
