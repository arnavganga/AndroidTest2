package com.example.androidtest2app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class toDoListActivity extends AppCompatActivity implements RecyclerViewInterface {
    ToDoList_RecyclerViewAdapter adapter;
    Button addToDoList, returnMain;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_list_activity);
        addToDoList = findViewById(R.id.addToDoListBtn);
        returnMain = findViewById(R.id.toDoListToHomeBtn);
        recyclerView = findViewById(R.id.toDoListsRecyclerView);

        updateToDoListModels();

        returnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        addToDoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toDoListTitle = "To-Do List " + (toDoListModel.toDoListModels.size() + 1);

                toDoListModel.toDoListModels.add(new toDoListModel(toDoListTitle));
                adapter.notifyItemInserted(toDoListModel.toDoListModels.size() - 1);
                recyclerView.scrollToPosition(toDoListModel.toDoListModels.size() - 1);
            }
        });
    }

    private void updateToDoListModels() {
        adapter = new ToDoList_RecyclerViewAdapter(this, toDoListModel.toDoListModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.todolist_update);
        EditText newToDoListName = dialog.findViewById(R.id.editToDoListName);
        Button updateToDoList = dialog.findViewById(R.id.updateToDoListBtn);
        newToDoListName.setText(toDoListModel.toDoListModels.get(position).getToDoListName());
        updateToDoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDoListModel.toDoListModels.set(position, new toDoListModel(newToDoListName.getText().toString()));
                adapter.notifyItemChanged(position);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onItemClick(int position) {
        toDoListModel.toDoListModels.remove(position);
        adapter.notifyItemRemoved(position);
    }
}
