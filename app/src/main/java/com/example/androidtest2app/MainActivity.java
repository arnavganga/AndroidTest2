package com.example.androidtest2app;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.androidtest2app.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button examsBtn = findViewById(R.id.examsBtn);
        Button classesBtn = findViewById(R.id.classesBtn);
        Button assignmentsBtn = findViewById(R.id.assignmentsBtn);
        Button toDoBtn = findViewById(R.id.toDoBtn);

        toDoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openToDoListsActivity();
            }
        });

        assignmentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAssignmentsActivity();
            }
        });

        examsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExamsActivity();
            }
        });

        classesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openClassesActivity();
            }
        });




        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
            }
        });
    }

    private void openToDoListsActivity(){
        Intent intent = new Intent(this, toDoListActivity.class);
        startActivity(intent);
    }
    private void openExamsActivity(){
        Intent intent = new Intent(this, examActivity.class);
        startActivity(intent);
    }
    private void openAssignmentsActivity(){
        Intent intent = new Intent(this, assignmentActivity.class);
        startActivity(intent);
    }

    private void openClassesActivity(){
        Intent intent = new Intent(this, classActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}