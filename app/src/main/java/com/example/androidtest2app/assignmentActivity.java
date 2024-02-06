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
    EditText assignmentNameEt, assignmentTimeEt, assignmentProfEt;
    Button addAssignment, returnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignment_activity);

        assignmentNameEt = findViewById(R.id.assignmentNameEt);
        assignmentTimeEt = findViewById(R.id.assignmentTimeEt);
        assignmentProfEt = findViewById(R.id.assignmentProfEt);
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
                String assignmentName = assignmentNameEt.getText().toString();
                String assignmentTime = assignmentTimeEt.getText().toString();
                String assignmentProf = assignmentProfEt.getText().toString();

                assignmentModels.add(new assignmentModel(assignmentName, assignmentTime, assignmentProf));
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
        Intent intent = new Intent(this, MainActivity.assignment);
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
        EditText newAssignmentName = dialog.findViewById(R.id.editAssignmentName);
        EditText newAssignmentTime = dialog.findViewById(R.id.editAssignmentTime);
        EditText newAssignmentProf = dialog.findViewById(R.id.editAssignmentProf);

        Button updateAssignment = dialog.findViewById(R.id.updateAssignmentBtn);

        newAssignmentName.setText((assignmentModels.get(position)).getAssignmentName());
        newAssignmentTime.setText((assignmentModels.get(position)).getAssignmentTime());
        newAssignmentProf.setText((assignmentModels.get(position)).getAssignmentProf());

        updateAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assignmentModels.set(position, new assignmentModel(newAssignmentName.getText().toString(),
                        newAssignmentTime.getText().toString(),
                        newAssignmentProf.getText().toString()));
                adapter.notifyItemChanged(position);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}