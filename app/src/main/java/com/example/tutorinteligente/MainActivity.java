package com.example.tutorinteligente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class MainActivity extends AppCompatActivity {

    private EditText emailTextView, passwordTextView;
    private Button BtnLogin, BtnRegistro;
    private ProgressBar progressbar;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        emailTextView = findViewById(R.id.email);
        passwordTextView = findViewById(R.id.password);
        BtnLogin = findViewById(R.id.login);
        BtnRegistro = findViewById(R.id.registro);
        progressbar = findViewById(R.id.progressBar);

        BtnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),RegistroActivity.class);
                startActivity(i);
            }
        });

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                loginUserAccount();
            }
        });
    }

    private void loginUserAccount()
    {
        progressbar.setVisibility(View.VISIBLE);

        String email, password;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();


        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.no_email),
                    Toast.LENGTH_LONG)
                    .show();
            progressbar.setVisibility(View.GONE);
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.no_password),
                    Toast.LENGTH_LONG)
                    .show();
            progressbar.setVisibility(View.GONE);
            return;
        }


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),
                                            getString(R.string.aut_exitosa),
                                            Toast.LENGTH_SHORT)
                                            .show();

                                    progressbar.setVisibility(View.GONE);

                                    Intent intent
                                            = new Intent(MainActivity.this,
                                            AutenticadoActivity.class);
                                    startActivity(intent);
                                }

                                else {

                                    Toast.makeText(getApplicationContext(),
                                            getString(R.string.aut_no_exitosa),
                                            Toast.LENGTH_LONG)
                                            .show();

                                    progressbar.setVisibility(View.GONE);
                                }
                            }
                        });
    }
}
