package com.example.tutorinteligente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {
    EditText user;
    EditText password;
    MaterialButton btIgresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initVars();
    }

    private void initVars() {
        user=findViewById(R.id.eduser);
        password=findViewById(R.id.edPassword);
        btIgresar=findViewById(R.id.btIngresar);
        btIgresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = validateUser(user.getText().toString(), password.getText().toString());
                Toast toast = Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT);
                toast.show();
            }


        });

    }

    private String validateUser(String user, String password) {

        if (user.isEmpty() || password.isEmpty()){
            return "Debes rellenar los campos antes de ingresar";

        }
        else{
            return "Ingreso Exitoso";
        }

    }

}