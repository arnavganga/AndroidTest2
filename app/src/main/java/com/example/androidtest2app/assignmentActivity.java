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

import java.util.ArrayList;

public class assignmentActivity extends AppCompatActivity implements RecyclerViewInterface {
    Assignment_RecyclerViewAdapter adapter;
    Button addAssignment, returnMain, sortAssignmentsDateBtn, sortAssignmentsClassBtn;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignment_activity);
        addAssignment = findViewById(R.id.updateAssignmentsBtn);
        returnMain = findViewById(R.id.assignmentsToHomeBtn);
        sortAssignmentsDateBtn = findViewById(R.id.sortAssignmentsDateBtn);
        sortAssignmentsClassBtn = findViewById(R.id.sortAssignmentsClassBtn);
        Context context = this;
        recyclerView = findViewById(R.id.assignmentsRecyclerView);

        updateAssignmentModels();

        returnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        addAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.assignments_update);
                EditText newAssignmentTitle = dialog.findViewById(R.id.editAssignmentTitle);
                EditText newAssignmentDueDate = dialog.findViewById(R.id.editAssignmentDueDate);
                EditText newAssignmentClass = dialog.findViewById(R.id.editAssignmentClass);
                EditText newAssignmentProgress = dialog.findViewById(R.id.editAssignmentProgress);
                Button updateAssignment = dialog.findViewById(R.id.updateAssignmentBtn);
                updateAssignment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        assignmentModel.assignmentModels.add(new assignmentModel(newAssignmentTitle.getText().toString(),
                                newAssignmentDueDate.getText().toString(),
                                newAssignmentClass.getText().toString(),
                                Integer.parseInt(newAssignmentProgress.getText().toString())));
                        adapter.notifyItemInserted(assignmentModel.assignmentModels.size() - 1);
                        recyclerView.scrollToPosition(assignmentModel.assignmentModels.size() - 1);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        sortAssignmentsDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bubbleSortDate(assignmentModel.assignmentModels);
                updateAssignmentModels();
            }
        });

        sortAssignmentsClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bubbleSortClass(assignmentModel.assignmentModels);
                updateAssignmentModels();
            }
        });
    }

    private static void bubbleSortDate(ArrayList<assignmentModel> assignments) {
        int n = assignments.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (assignments.get(j).assignmentDueDate.compareTo(assignments.get(j + 1).assignmentDueDate) > 0) {
                    // Swap if the current assignment is greater than the next one
                    assignmentModel temp = assignments.get(j);
                    assignments.set(j, assignments.get(j + 1));
                    assignments.set(j + 1, temp);
                }
            }
        }
    }

    private static void bubbleSortClass(ArrayList<assignmentModel> assignments) {
        int n = assignments.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (assignments.get(j).assignmentClass.compareTo(assignments.get(j + 1).assignmentClass) > 0) {
                    // Swap if the current assignment is greater than the next one
                    assignmentModel temp = assignments.get(j);
                    assignments.set(j, assignments.get(j + 1));
                    assignments.set(j + 1, temp);
                }
            }
        }
    }

    private void updateAssignmentModels() {
        adapter = new Assignment_RecyclerViewAdapter(this, assignmentModel.assignmentModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position) {
        assignmentModel.assignmentModels.remove(position);
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void onItemClick(int position) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.assignments_update);
        EditText newAssignmentTitle = dialog.findViewById(R.id.editAssignmentTitle);
        EditText newAssignmentDueDate = dialog.findViewById(R.id.editAssignmentDueDate);
        EditText newAssignmentClass = dialog.findViewById(R.id.editAssignmentClass);
        EditText newAssignmentProgress = dialog.findViewById(R.id.editAssignmentProgress);

        Button updateAssignment = dialog.findViewById(R.id.updateAssignmentBtn);

        newAssignmentTitle.setText(assignmentModel.assignmentModels.get(position).getAssignmentTitle());
        newAssignmentDueDate.setText(assignmentModel.assignmentModels.get(position).getAssignmentDueDate());
        newAssignmentClass.setText(assignmentModel.assignmentModels.get(position).getAssignmentClass());
        newAssignmentProgress.setText(String.valueOf(assignmentModel.assignmentModels.get(position).getProgress()));

        updateAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assignmentModel.assignmentModels.set(position, new assignmentModel(newAssignmentTitle.getText().toString(),
                        newAssignmentDueDate.getText().toString(),
                        newAssignmentClass.getText().toString(),
                        Integer.parseInt(newAssignmentProgress.getText().toString())));
                adapter.notifyItemChanged(position);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
