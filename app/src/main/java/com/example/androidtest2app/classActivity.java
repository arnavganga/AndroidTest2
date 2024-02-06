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

public class classActivity extends AppCompatActivity implements RecyclerViewInterface{

    Class_RecyclerViewAdapter adapter;
    EditText classNameEt, classTimeEt, classProfEt;
    Button addClass, returnMain;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_activity);

        classNameEt = findViewById(R.id.classNameEt);
        classTimeEt = findViewById(R.id.classTimeEt);
        classProfEt = findViewById(R.id.classProfEt);
        addClass = findViewById(R.id.updateClassesBtn);
        returnMain = findViewById(R.id.classesToHomeBtn);
        
        RecyclerView recyclerView = findViewById(R.id.classesRecyclerView);
        
        updateClassModels(recyclerView);
        
        returnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
        
        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String className = classNameEt.getText().toString();
                String classTime = classTimeEt.getText().toString();
                String classProf = classProfEt.getText().toString();

                classModel.classModels.add(new classModel(className, classTime, classProf));
                adapter.notifyItemInserted(classModel.classModels.size() - 1);
                updateClassModels(recyclerView);
            }
        });
    }

    private void updateClassModels(RecyclerView recyclerView) {
        adapter = new Class_RecyclerViewAdapter(this, classModel.classModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void onItemLongClick(int position) {
        classModel.classModels.remove(position);
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void onItemClick(int position) {

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.classes_update);
        EditText newClassName = dialog.findViewById(R.id.editClassName);
        EditText newClassTime = dialog.findViewById(R.id.editClassTime);
        EditText newClassProf = dialog.findViewById(R.id.editClassProf);

        Button updateClass = dialog.findViewById(R.id.updateClassBtn);

        newClassName.setText((classModel.classModels.get(position)).getClassName());
        newClassTime.setText((classModel.classModels.get(position)).getClassTime());
        newClassProf.setText((classModel.classModels.get(position)).getClassProf());

        updateClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classModel.classModels.set(position, new classModel(newClassName.getText().toString(),
                        newClassTime.getText().toString(),
                        newClassProf.getText().toString()));
                adapter.notifyItemChanged(position);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}