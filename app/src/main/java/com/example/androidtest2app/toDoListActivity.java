package com.example.androidtest2app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class toDoListActivity extends AppCompatActivity implements RecyclerViewInterface {
    ToDoList_RecyclerViewAdapter adapter;
    Button addToDoList, returnMain;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_list_activity);
        Context context = this;
        addToDoList = findViewById(R.id.addEventBtn);
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
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.todolist_update);
                EditText newEventName = dialog.findViewById(R.id.editEventName);
                EditText newEventDate = dialog.findViewById(R.id.editEventDate);

                Button updateEvent = dialog.findViewById(R.id.updateEventBtn);
                updateEvent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toDoListModel.toDoListModels.add(new toDoListModel(newEventName.getText().toString(),
                                newEventDate.getText().toString()));
                        adapter.notifyItemInserted(toDoListModel.toDoListModels.size());
                        dialog.dismiss();
                    }
                });
                dialog.show();
                /*String toDoListTitle = "To-Do List " + (toDoListModel.toDoListModels.size() + 1);

                toDoListModel.toDoListModels.add(new toDoListModel(toDoListTitle));
                adapter.notifyItemInserted(toDoListModel.toDoListModels.size() - 1);
                recyclerView.scrollToPosition(toDoListModel.toDoListModels.size() - 1);*/
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
        toDoListModel.toDoListModels.remove(position);
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void onItemClick(int position) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.todolist_update);
        EditText newEventName = dialog.findViewById(R.id.editEventName);
        EditText newEventDate = dialog.findViewById(R.id.editEventDate);
        Button updateToDoList = dialog.findViewById(R.id.updateEventBtn);
        newEventName.setText(toDoListModel.toDoListModels.get(position).getEventName());
        newEventDate.setText(toDoListModel.toDoListModels.get(position).getEventDate());
        updateToDoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDoListModel.toDoListModels.set(position, new toDoListModel(newEventName.getText().toString(),
                        newEventDate.getText().toString()));
                adapter.notifyItemChanged(position);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
