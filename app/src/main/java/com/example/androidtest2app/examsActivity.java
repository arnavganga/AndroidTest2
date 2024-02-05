package com.example.androidtest2app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidtest2app.placeholder.Exam_RecyclerViewAdapter;

import java.util.ArrayList;

public class examsActivity extends AppCompatActivity {

    ArrayList<examsModel> examsModels = new ArrayList<>();
    EditText examNameEt, examDateEt, examTimeEt, examLocEt;
    Button addExam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);

        examNameEt = findViewById(R.id.examNameEt);
        examDateEt = findViewById(R.id.examDateEt);
        examTimeEt = findViewById(R.id.examTimeEt);
        examLocEt = findViewById(R.id.examLocEt);
        addExam = findViewById(R.id.updateExamsBtn);

        RecyclerView recyclerView = findViewById(R.id.examsRecyclerView);

        updateExamModels(recyclerView);

        addExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String examName = examNameEt.getText().toString();
                String examTime = examTimeEt.getText().toString();
                String examDate = examDateEt.getText().toString();
                String examLoc = examLocEt.getText().toString();

                examsModels.add(new examsModel(examName, examDate, examTime, examLoc));
                updateExamModels(recyclerView);
            }
        });
    }

    private void updateExamModels(RecyclerView recyclerView) {
        Exam_RecyclerViewAdapter adapter = new Exam_RecyclerViewAdapter(this, examsModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
