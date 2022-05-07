package com.example.tutorinteligente.Main.View.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tutorinteligente.Application.TutorInteligenteApplication;
import com.example.tutorinteligente.Main.Presenter.PresenterHome;
import com.example.tutorinteligente.R;

import javax.inject.Inject;

public class ProfileActivity extends BottomNavigationActivity {

    @Inject
    PresenterHome presenterHome;

    private TextView nombreTextView,emailTextView;
    private Button btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticado);
        TutorInteligenteApplication.getTutorInteligenteApplication().getTutorInteligenteComponent().inject(this);
        setUpNavigation();
        initVars();
        valdiateButtons();
        presenterHome.getCurrentUser(this);
    }

    private void initVars() {
        nombreTextView = findViewById(R.id.usr_nombre);
        emailTextView = findViewById(R.id.usr_email);
        btnEditar = findViewById(R.id.btneditar);
    }
    public void setNameAndEmail(String name, String email){
        nombreTextView.setText(name);
        emailTextView.setText(email);
    }

    private void valdiateButtons() {
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { goToEdit(); }
        });
    }


    private void goToEdit() {
        Intent i = new Intent(getApplicationContext(), EditProfileActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }else{
            startActivity(i);
        }
    }


}
