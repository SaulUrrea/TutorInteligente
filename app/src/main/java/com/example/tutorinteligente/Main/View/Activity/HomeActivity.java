package com.example.tutorinteligente.Main.View.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tutorinteligente.Application.TutorInteligenteApplication;
import com.example.tutorinteligente.Main.Presenter.PresenterHome;
import com.example.tutorinteligente.R;
import com.example.tutorinteligente.Session.Presenter.PresenterSession;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity {

    @Inject
    PresenterHome presenterHome;

    private TextView nombreTextView,emailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticado);
        TutorInteligenteApplication.getTutorInteligenteApplication().getTutorInteligenteComponent().inject(this);
        initVars();
        presenterHome.getCurrentUser(this);
    }

    private void initVars() {
        nombreTextView = findViewById(R.id.usr_nombre);
        emailTextView = findViewById(R.id.usr_email);
    }
    public void setNameAndEmail(String name, String email){
        nombreTextView.setText(name);
        emailTextView.setText(email);
    }
}
