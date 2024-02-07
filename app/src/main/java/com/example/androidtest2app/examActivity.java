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

public class examActivity extends AppCompatActivity implements RecyclerViewInterface {
    
    Exam_RecyclerViewAdapter adapter;
    Button addExam, returnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_activity);
        addExam = findViewById(R.id.updateExamsBtn);
        returnMain = findViewById(R.id.examsToHomeBtn);
        Context context = this;

        RecyclerView recyclerView = findViewById(R.id.examsRecyclerView);

        updateExamModels(recyclerView);

        returnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        addExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.exams_update);
                EditText newExamName = dialog.findViewById(R.id.editExamName);
                EditText newExamDate = dialog.findViewById(R.id.editExamDate);
                EditText newExamTime = dialog.findViewById(R.id.editExamTime);
                EditText newExamLoc = dialog.findViewById(R.id.editExamLoc);
                Button updateExam = dialog.findViewById(R.id.updateExamBtn);
                updateExam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        examModel.examModels.add(new examModel(newExamName.getText().toString(),
                                newExamDate.getText().toString(),
                                newExamTime.getText().toString(),
                                newExamLoc.getText().toString()));
                        adapter.notifyItemInserted(examModel.examModels.size() - 1);
                        recyclerView.scrollToPosition(examModel.examModels.size() - 1);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    private void updateExamModels(RecyclerView recyclerView) {
        adapter = new Exam_RecyclerViewAdapter(this, examModel.examModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position) {
        examModel.examModels.remove(position);
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void onItemClick(int position) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.exams_update);
        EditText newExamName = dialog.findViewById(R.id.editExamName);
        EditText newExamDate = dialog.findViewById(R.id.editExamDate);
        EditText newExamTime = dialog.findViewById(R.id.editExamTime);
        EditText newExamLoc = dialog.findViewById(R.id.editExamLoc);

        Button updateExam = dialog.findViewById(R.id.updateExamBtn);

        newExamName.setText((examModel.examModels.get(position)).getExamName());
        newExamDate.setText((examModel.examModels.get(position)).getExamDate());
        newExamTime.setText((examModel.examModels.get(position)).getExamTime());
        newExamLoc.setText((examModel.examModels.get(position)).getExamLoc());

        updateExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                examModel.examModels.set(position, new examModel(newExamName.getText().toString(),
                        newExamDate.getText().toString(),
                        newExamTime.getText().toString(),
                        newExamLoc.getText().toString()));
                adapter.notifyItemChanged(position);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}