package com.example.tutorinteligente.Session.View.Activity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tutorinteligente.Application.TutorInteligenteApplication;
import com.example.tutorinteligente.R;
import com.example.tutorinteligente.Session.Models.UserModel;
import com.example.tutorinteligente.Session.Presenter.PresenterSession;
import com.google.android.material.textfield.TextInputEditText;


import java.util.regex.Pattern;

import javax.inject.Inject;

public class RegistrationActivity extends AppCompatActivity {

    @Inject
    PresenterSession presenterSession;

    private EditText nombreTextView, apellidoTextView, emailTextView;
    private TextInputEditText passwordTextView, password2TextView;
    private Button BtnRegistrar, BtnLogin;
    private String nombre, apellido, email, password, password2;
    private Boolean registerIsCorrect = true;
    ProgressDialog progressDialog;

    private static final Pattern PATRON_CONTRASENA = Pattern.compile("^" + "(?=.*[@#*$%^&+=]{1})" +  ".{8,}" + "$");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        TutorInteligenteApplication.getTutorInteligenteApplication().getTutorInteligenteComponent().inject(this);
        initVars();
        valdiateButtons();
    }

    private void initVars() {
        nombreTextView = findViewById(R.id.nombre);
        apellidoTextView = findViewById(R.id.apellido);
        emailTextView = findViewById(R.id.email);
        passwordTextView = findViewById(R.id.passwd);
        password2TextView = findViewById(R.id.passwd2);
        BtnRegistrar = findViewById(R.id.btnregister);
        BtnLogin = findViewById(R.id.btnlogin);
        progressDialog=new ProgressDialog(this);
    }

    private void valdiateButtons() {
        BtnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()){
                    showLoadingMessage(getString(R.string.reg_loading_message));
                    presenterSession.RegisterNewUser(initUserInfo(),password2, RegistrationActivity.this);
                }
            }
        });

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { goToLogin(); }
        });
    }

    private UserModel initUserInfo() {
        UserModel user = new UserModel();
        user.setEmail(email);
        user.setName(nombre);
        user.setLastName(apellido);
        return user;
    }

    private Boolean validateForm() {
        nombre = nombreTextView.getText().toString();
        apellido = apellidoTextView.getText().toString();
        email = emailTextView.getText().toString().trim();
        password = passwordTextView.getText().toString().trim();
        password2 = password2TextView.getText().toString().trim();

        if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.no_nombre),
                    Toast.LENGTH_LONG)
                    .show();
            registerIsCorrect = false;
        }
        if (TextUtils.isEmpty(apellido)) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.no_apellido),
                    Toast.LENGTH_LONG)
                    .show();
            registerIsCorrect = false;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.no_email),
                    Toast.LENGTH_LONG)
                    .show();
            registerIsCorrect = false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.email_malo),
                    Toast.LENGTH_LONG)
                    .show();
            registerIsCorrect = false;
        }
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(password2)) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.no_password),
                    Toast.LENGTH_LONG)
                    .show();
            registerIsCorrect = false;
        }
        if (!password.equals(password2)) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.hint_passdif),
                    Toast.LENGTH_LONG)
                    .show();
            registerIsCorrect = false;
        }
        if ((!PATRON_CONTRASENA.matcher(password).matches())) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.hint_pass_no_cumple),
                    Toast.LENGTH_LONG)
                    .show();
            registerIsCorrect = false;
        }

        if (registerIsCorrect != false){
            return true;
        }else{
            return false;
        }

    }

    public void showLoadingMessage(String mensaje){
        progressDialog.setMessage(mensaje);
        progressDialog.show();
    }

    public void hiddenProgress(){
        progressDialog.dismiss();
    }

    public void goToLogin() {
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }else{
            startActivity(i);
        }
    }

    public void showError(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogStyle);
        builder.setTitle(getString(R.string.alert));
        builder.setMessage(message);
        builder.setPositiveButton(getString(R.string.alert_dismiss), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}
