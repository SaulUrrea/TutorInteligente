package com.example.tutorinteligente.Main.View.Activity;

import android.os.Bundle;

import com.example.tutorinteligente.R;

public class TasksActivity extends BottomNavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        setUpNavigation();
    }
}