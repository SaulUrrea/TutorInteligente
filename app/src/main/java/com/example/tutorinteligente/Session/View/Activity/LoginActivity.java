package com.example.tutorinteligente.Session.View.Activity;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.tutorinteligente.Application.TutorInteligenteApplication;
import com.example.tutorinteligente.Main.View.Activity.HomeActivity;
import com.example.tutorinteligente.R;
import com.example.tutorinteligente.Session.Presenter.PresenterSession;
import com.example.tutorinteligente.Session.View.Adapters.LoginAdapter;
import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;


public class LoginActivity extends AppCompatActivity {

    @Inject
    PresenterSession presenterSession;

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TutorInteligenteApplication.getTutorInteligenteApplication().getTutorInteligenteComponent().inject(this);
        initVars();
    }

    private void initVars() {
        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager);

        tabLayout.addTab(tabLayout.newTab().setText("Ingresar "));
        tabLayout.addTab(tabLayout.newTab().setText("Registro"));

        final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(), this,tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
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
