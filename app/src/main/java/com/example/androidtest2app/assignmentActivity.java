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

public class assignmentActivity extends AppCompatActivity implements RecyclerViewInterface{

    ArrayList<assignmentModel> assignmentModels = new ArrayList<>();
    Assignment_RecyclerViewAdapter adapter;
    EditText assignmentTitleEt, assignmentDueDateEt, assignmentClassEt;
    Button addAssignment, returnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignment_activity);

        assignmentTitleEt = findViewById(R.id.assignmentTitleEt);
        assignmentDueDateEt = findViewById(R.id.assignmentDueDateEt);
        assignmentClassEt = findViewById(R.id.assignmentClassEt);
        addAssignment = findViewById(R.id.updateAssignmentsBtn);
        returnMain = findViewById(R.id.assignmentsToHomeBtn);

        RecyclerView recyclerView = findViewById(R.id.assignmentsRecyclerView);

        updateAssignmentModels(recyclerView);

        returnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        addAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String assignmentTitle = assignmentTitleEt.getText().toString();
                String assignmentDueDate = assignmentDueDateEt.getText().toString();
                String assignmentClass = assignmentClassEt.getText().toString();

                assignmentModels.add(new assignmentModel(assignmentTitle, assignmentDueDate, assignmentClass));
                adapter.notifyItemInserted(assignmentModels.size() - 1);
                updateAssignmentModels(recyclerView);
            }
        });
    }

    private void updateAssignmentModels(RecyclerView recyclerView) {
        adapter = new Assignment_RecyclerViewAdapter(this, assignmentModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void onItemLongClick(int position) {
        assignmentModels.remove(position);
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void onItemClick(int position) {

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.assignments_update);
        EditText newAssignmentTitle = dialog.findViewById(R.id.editAssignmentTitle);
        EditText newAssignmentDueDate = dialog.findViewById(R.id.editAssignmentDueDate);
        EditText newAssignmentClass = dialog.findViewById(R.id.editAssignmentClass);

        Button updateAssignment = dialog.findViewById(R.id.updateAssignmentBtn);

        newAssignmentTitle.setText((assignmentModels.get(position)).getAssignmentTitle());
        newAssignmentDueDate.setText((assignmentModels.get(position)).getAssignmentDueDate());
        newAssignmentClass.setText((assignmentModels.get(position)).getAssignmentClass());

        updateAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assignmentModels.set(position, new assignmentModel(newAssignmentTitle.getText().toString(),
                        newAssignmentDueDate.getText().toString(),
                        newAssignmentClass.getText().toString()));
                adapter.notifyItemChanged(position);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}