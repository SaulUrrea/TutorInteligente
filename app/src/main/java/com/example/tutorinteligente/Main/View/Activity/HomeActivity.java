package com.example.tutorinteligente.Main.View.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tutorinteligente.Application.TutorInteligenteApplication;
import com.example.tutorinteligente.Main.Presenter.PresenterHome;
import com.example.tutorinteligente.Main.View.Fragments.ProfileFragment;
import com.example.tutorinteligente.Main.View.Fragments.TasksFragment;
import com.example.tutorinteligente.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity {

    @Inject
    public PresenterHome presenterHome;

    ProfileFragment profileFragment= new ProfileFragment(this);
    TasksFragment tasksFragment= new TasksFragment(this);
    FragmentManager mFragmentManager;
    BottomNavigationView bottomNavigation;
    Fragment mActiveFragment = profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttom_navigation);
        TutorInteligenteApplication.getTutorInteligenteApplication().getTutorInteligenteComponent().inject(this);
        setupBottomNav();
    }




    private void setupBottomNav(){
        mFragmentManager = getSupportFragmentManager();

        mFragmentManager.beginTransaction()
                .add(R.id.fragment_container,profileFragment, null)
                .commit();

        mFragmentManager.beginTransaction()
                .add(R.id.fragment_container,tasksFragment, null)
                .hide(tasksFragment)
                .commit();

        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setSelectedItemId(R.id.profileItem);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.profileItem:
                        mFragmentManager.beginTransaction()
                                .hide(mActiveFragment)
                                .show(profileFragment)
                                .commit();
                        mActiveFragment = profileFragment;
                        return true;
                    case R.id.taskItem:
                        mFragmentManager.beginTransaction()
                                .hide(mActiveFragment)
                                .show(tasksFragment)
                                .commit();
                        mActiveFragment = tasksFragment;
                        return true;
                }
                return false;
            }
        });

    }
}
