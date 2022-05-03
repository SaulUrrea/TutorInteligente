package com.example.tutorinteligente.Main.View.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tutorinteligente.Application.TutorInteligenteApplication;
import com.example.tutorinteligente.Main.Presenter.PresenterHome;
import com.example.tutorinteligente.R;

import javax.inject.Inject;

public class EditProfileActivity extends AppCompatActivity {

    @Inject
    PresenterHome presenterHome;

    private EditText nombreTextView,apellidoTextView;
    private Button btnActualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edituser);
        TutorInteligenteApplication.getTutorInteligenteApplication().getTutorInteligenteComponent().inject(this);
        initVars();


    }

    private void initVars() {
        nombreTextView = findViewById(R.id.usr_nombre);
        apellidoTextView = findViewById(R.id.usr_apellido);
        btnActualizar = findViewById(R.id.btnactualizar);
        valdiateButtons();
    }


    private void valdiateButtons() {
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { presenterHome.updateUserInfo(nombreTextView.getText().toString(),apellidoTextView.getText().toString()); }
        });
    }





}
