package com.example.tutorinteligente.Main.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tutorinteligente.Main.View.Fragments.ProfileFragment;
import com.example.tutorinteligente.Main.View.Fragments.TasksFragment;
import com.example.tutorinteligente.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationActivity extends AppCompatActivity {
    ProfileFragment firstFragment= new ProfileFragment();
    TasksFragment secondFragment= new TasksFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_buttom_navigation);
        //loadFragment(firstFragment);
    }

    void setUpNavigation() {
        BottomNavigationView navigation =findViewById(R.id.bottom_navigation);
        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener= new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.taskItem:
                        loadTask();
                        return true;
                    case R.id.profileItem:
                        loadProfile();
                        return true;
                }
                return false;
            }
        };
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private void loadTask() {
        Intent i = new Intent(this, TasksActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }

    private void loadProfile() {
        Intent i = new Intent(this, ProfileActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }

   /* public void  loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fragment);
        transaction.replace(R.id.frame_container,fragment);
        transaction.commit();
    }*/
}
