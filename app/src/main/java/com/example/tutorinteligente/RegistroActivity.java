package com.example.tutorinteligente;

import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {


    private EditText nombreTextView, apellidoTextView, emailTextView, passwordTextView, password2TextView;
    private Button BtnRegistrar, BtnLogin;
    private ProgressBar progressbar;
    private FirebaseAuth mAuth;

    private static final Pattern PATRON_CONTRASENA =
            Pattern.compile("^" +
                    "(?=.*[@#*$%^&+=]{2})" + // por lo menos 2 caracteres especial
                    "(?=\\S+$)" +            // no espacios en blanco
                    ".{8,}" +                // mímimo 8 carácteres
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();

        nombreTextView = findViewById(R.id.nombre);
        apellidoTextView = findViewById(R.id.apellido);
        emailTextView = findViewById(R.id.email);
        passwordTextView = findViewById(R.id.passwd);
        password2TextView = findViewById(R.id.passwd2);
        BtnRegistrar = findViewById(R.id.btnregister);
        progressbar = findViewById(R.id.progressbar);
        BtnLogin = findViewById(R.id.btnlogin);

        BtnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                registerNewUser();
            }
        });

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void registerNewUser()
    {
        progressbar.setVisibility(View.VISIBLE);

        String nombre, apellido, email, password, password2;
        nombre = nombreTextView.getText().toString();
        apellido = apellidoTextView.getText().toString();
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();
        password2 = password2TextView.getText().toString();

        if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.no_nombre),
                    Toast.LENGTH_LONG)
                    .show();
            progressbar.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(apellido)) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.no_apellido),
                    Toast.LENGTH_LONG)
                    .show();
            progressbar.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.no_email),
                    Toast.LENGTH_LONG)
                    .show();
            progressbar.setVisibility(View.GONE);
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.email_malo),
                    Toast.LENGTH_LONG)
                    .show();
            progressbar.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(password2)) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.no_password),
                    Toast.LENGTH_LONG)
                    .show();
            progressbar.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(password2)) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.no_password),
                    Toast.LENGTH_LONG)
                    .show();
            progressbar.setVisibility(View.GONE);
            return;
        }
        if (!password.equals(password2)) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.hint_passdif),
                    Toast.LENGTH_LONG)
                    .show();
            progressbar.setVisibility(View.GONE);
            return;
        }
        if ((!PATRON_CONTRASENA.matcher(password).matches())) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.hint_pass_no_cumple),
                    Toast.LENGTH_LONG)
                    .show();
            progressbar.setVisibility(View.GONE);
            return;
        }

        mAuth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful()) {

                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(nombre + " " + apellido)
                                        .build();

                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d("TAG", "User profile updated.");
                                                }
                                            }
                                        });

                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.reg_exitoso),
                                    Toast.LENGTH_LONG)
                                    .show();

                           progressbar.setVisibility(View.GONE);

                            Intent intent
                                    = new Intent(RegistroActivity.this,
                                    MainActivity.class);
                            startActivity(intent);
                        }
                        else {

                            Toast.makeText(
                                    getApplicationContext(),
                                    getString(R.string.reg_fallido),
                                    Toast.LENGTH_LONG)
                                    .show();

                            progressbar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}
