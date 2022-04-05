package com.example.tutorinteligente.Session.View.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import com.example.tutorinteligente.Application.TutorInteligenteApplication;
import com.example.tutorinteligente.Main.Presenter.PresenterHome;
import com.example.tutorinteligente.Main.View.Activity.HomeActivity;
import com.example.tutorinteligente.R;
import com.example.tutorinteligente.Session.Presenter.PresenterSession;

import javax.inject.Inject;


public class LoginActivity extends AppCompatActivity {

    @Inject
    PresenterSession presenterSession;

    private EditText emailTextView, passwordTextView;
    private Button BtnLogin, BtnRegistro;
    ProgressDialog progressDialog;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TutorInteligenteApplication.getTutorInteligenteApplication().getTutorInteligenteComponent().inject(this);
        initVars();
        valdiateButtons();
    }

    private void initVars() {
        emailTextView = findViewById(R.id.email);
        passwordTextView = findViewById(R.id.password);
        BtnLogin = findViewById(R.id.login);
        BtnRegistro = findViewById(R.id.registro);
        progressDialog=new ProgressDialog(this);
    }

    private void valdiateButtons() {
        BtnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { goToRegister(); }
        });

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserAccount();
            }
        });
    }

    private void loginUserAccount() {
        email = emailTextView.getText().toString().trim();
        password = passwordTextView.getText().toString().trim();
        showLoadingMessage(getString(R.string.login_loading_message));

        if (TextUtils.isEmpty(email)) {
            emailTextView.setError(getString(R.string.no_email));
            hiddenProgress();
        }

        if (TextUtils.isEmpty(password)) {
            passwordTextView.setError(getString(R.string.no_password));
            hiddenProgress();
        }

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            presenterSession.loginWithEmail(email,password,this);
        }
    }

    public void goToMain() {
        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }else{
            startActivity(i);
        }
    }

    private void goToRegister() {
        Intent i = new Intent(getApplicationContext(), RegistrationActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }else{
            startActivity(i);
        }
    }
    public void showLoadingMessage(String mensaje){
        progressDialog.setMessage(mensaje);
        progressDialog.show();
    }

    public void hiddenProgress(){
        progressDialog.dismiss();
    }

    public void showErrorMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogStyle);
        builder.setTitle(getString(R.string.alert));
        builder.setMessage(getString(R.string.login_error));
        builder.setPositiveButton(getString(R.string.alert_dismiss), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}
