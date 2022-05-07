package com.example.tutorinteligente.Session.View.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.tutorinteligente.Application.TutorInteligenteApplication;
import com.example.tutorinteligente.Main.View.Activity.ProfileActivity;
import com.example.tutorinteligente.R;
import com.example.tutorinteligente.Session.Models.UserModel;
import com.example.tutorinteligente.Session.Presenter.PresenterSession;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

import javax.inject.Inject;

public class RegisterFragment extends Fragment {


    @Inject
    PresenterSession presenterSession;

    private Context mContext;
    private EditText nombreEd, apellidoEd, emailEd;
    private TextInputEditText passwordTextView, password2TextView;
    private String nombre, apellido, email, password, password2;
    private Button BtnRegistrar;
    private Boolean registerIsCorrect = true;
    private static final Pattern PATRON_CONTRASENA = Pattern.compile("^" + "(?=.*[@#*$%^&+=]{1})" +  ".{8,}" + "$");
    ProgressDialog progressDialog;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public RegisterFragment newInstance(Context mContext) {
        RegisterFragment fragment = new RegisterFragment();
        fragment.setContext(mContext);
        return fragment;
    }

    private void setContext(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TutorInteligenteApplication.getTutorInteligenteApplication().getTutorInteligenteComponent().inject(this);
        var v = inflater.inflate(R.layout.fragment_register, container, false);
        setUpView(v);
        return v;
    }



    private void setUpView(View v) {
        nombreEd = v.findViewById(R.id.nombre);
        apellidoEd = v.findViewById(R.id.apellido);
        emailEd = v.findViewById(R.id.email);
        BtnRegistrar = v.findViewById(R.id.btnregister);
        passwordTextView = v.findViewById(R.id.passwd);
        password2TextView = v.findViewById(R.id.passwd2);
        BtnRegistrar = v.findViewById(R.id.btnregister);
        progressDialog= new ProgressDialog(this.mContext);

        BtnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()){
                    showLoadingMessage(getString(R.string.reg_loading_message));
                    presenterSession.RegisterNewUser(initUserInfo(),password2,RegisterFragment.this);
                }
            }
        });
    }

    public void showError(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.AlertDialogStyle);
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

    public void showLoadingMessage(String mensaje){
        progressDialog.setMessage(mensaje);
        progressDialog.show();
    }
    public void hiddenProgress(){
        progressDialog.dismiss();
    }

    private Boolean validateForm() {
        nombre = nombreEd.getText().toString();
        apellido = apellidoEd.getText().toString();
        email = emailEd.getText().toString().trim();
        password = passwordTextView.getText().toString().trim();
        password2 = password2TextView.getText().toString().trim();

        if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(mContext,
                    getString(R.string.no_nombre),
                    Toast.LENGTH_LONG)
                    .show();
            registerIsCorrect = false;
        }
        if (TextUtils.isEmpty(apellido)) {
            Toast.makeText(mContext,
                    getString(R.string.no_apellido),
                    Toast.LENGTH_LONG)
                    .show();
            registerIsCorrect = false;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(mContext,
                    getString(R.string.no_email),
                    Toast.LENGTH_LONG)
                    .show();
            registerIsCorrect = false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(mContext,
                    getString(R.string.email_malo),
                    Toast.LENGTH_LONG)
                    .show();
            registerIsCorrect = false;
        }
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(password2)) {
            Toast.makeText(mContext,
                    getString(R.string.no_password),
                    Toast.LENGTH_LONG)
                    .show();
            registerIsCorrect = false;
        }
        if (!password.equals(password2)) {
            Toast.makeText(mContext,
                    getString(R.string.hint_passdif),
                    Toast.LENGTH_LONG)
                    .show();
            registerIsCorrect = false;
        }
        if ((!PATRON_CONTRASENA.matcher(password).matches())) {
            Toast.makeText(mContext,
                    getString(R.string.hint_pass_no_cumple),
                    Toast.LENGTH_LONG)
                    .show();
            registerIsCorrect = false;
        }

        if (!registerIsCorrect){
            return true;
        }else{
            return false;
        }

    }

    public void goToMain() {
        Intent i = new Intent(mContext, ProfileActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    private UserModel initUserInfo() {
        UserModel user = new UserModel();
        user.setEmail(email);
        user.setName(nombre);
        user.setLastName(apellido);
        return user;
    }
    public static class newInstance extends RegisterFragment {
        public RegisterFragment newInstance(Context context)  {
            var f = new RegisterFragment();
            f.setContext(context);
            return f;
        }
    }
}