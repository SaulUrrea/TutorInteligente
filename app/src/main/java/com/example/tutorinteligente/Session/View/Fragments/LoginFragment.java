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
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.tutorinteligente.Application.TutorInteligenteApplication;
import com.example.tutorinteligente.Main.View.Activity.ProfileActivity;
import com.example.tutorinteligente.R;
import com.example.tutorinteligente.Session.Presenter.PresenterSession;
import com.google.android.material.button.MaterialButton;

import javax.inject.Inject;

public class LoginFragment extends Fragment {


    @Inject
    PresenterSession presenterSession;

    private EditText emailTextView, passwordTextView;
    private String email, password;
    private MaterialButton btnLogin;
    private Context mContext;
    ProgressDialog progressDialog;

    public LoginFragment() {
        // Required empty public constructor
    }

    public LoginFragment newInstance(Context mContext) {
        LoginFragment fragment = new LoginFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TutorInteligenteApplication.getTutorInteligenteApplication().getTutorInteligenteComponent().inject(this);
        var v = inflater.inflate(R.layout.fragment_login, container, false);
        initVars(v);
        return v;

    }

    private void initVars(View v) {
        emailTextView = v.findViewById(R.id.email);
        passwordTextView = v.findViewById(R.id.password);
        btnLogin = v.findViewById(R.id.btnLogin);
        progressDialog=new ProgressDialog(mContext);
        valdiateButtons();
    }

    private void valdiateButtons() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
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
            presenterSession.loginWithEmail(email,password,LoginFragment.this);
        }
    }

    public void showLoadingMessage(String mensaje){
        progressDialog.setMessage(mensaje);
        progressDialog.show();
    }

    public void hiddenProgress(){
        progressDialog.dismiss();
    }

    public void goToMain() {
        Intent i = new Intent(mContext, ProfileActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }


    public void showErrorMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.AlertDialogStyle);
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

    public static class newInstance extends LoginFragment {
        public LoginFragment newInstance(Context context)  {
            var f = new LoginFragment();
            f.setContext(context);
            return f;
        }
    }
}