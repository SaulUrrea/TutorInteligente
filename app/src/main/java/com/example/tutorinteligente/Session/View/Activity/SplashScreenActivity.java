package com.example.tutorinteligente.Session.View.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tutorinteligente.Application.TutorInteligenteApplication;
import com.example.tutorinteligente.Main.View.Activity.HomeActivity;
import com.example.tutorinteligente.Session.Presenter.PresenterSession;

import javax.inject.Inject;

public class SplashScreenActivity extends AppCompatActivity {

    @Inject
    PresenterSession presenterSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TutorInteligenteApplication.getTutorInteligenteApplication().getTutorInteligenteComponent().inject(this);
        if(presenterSession.getFirstSession()){
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }
}
