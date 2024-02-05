package com.example.androidtest2app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.androidtest2app.placeholder.Exam_RecyclerViewAdapter;

import java.util.ArrayList;

public class examsActivity extends AppCompatActivity {

    ArrayList<examsModel> examsModels = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);

        RecyclerView recyclerView = findViewById(R.id.examsRecyclerView);

        setUpExamModels();

        Exam_RecyclerViewAdapter adapter = new Exam_RecyclerViewAdapter(this, examsModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpExamModels() {
        String[] examNames = new String[5];
        String[] examDates = new String[5];
        String[] examTimes = new String[5];
        String[] examLocs = new String[5];

        for (int i = 0; i < examNames.length; i++) {
            examsModels.add(new examsModel(examNames[i],
                    examDates[i],
                    examTimes[i],
                    examLocs[i]));
        }

    }


}