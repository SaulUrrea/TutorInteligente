package com.example.tutorinteligente.Session.Presenter;



import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.tutorinteligente.Application.TutorInteligenteApplication;
import com.example.tutorinteligente.R;
import com.example.tutorinteligente.Session.Models.UserModel;
import com.example.tutorinteligente.Session.View.Fragments.LoginFragment;
import com.example.tutorinteligente.Session.View.Fragments.RegisterFragment;
import com.example.tutorinteligente.Utils.GlobalVars;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PresenterSession {

    Context context;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    public PresenterSession(Context context) {
        this.context = context;
        TutorInteligenteApplication.getTutorInteligenteApplication().getTutorInteligenteComponent().inject(this);
        this.mAuth = FirebaseAuth.getInstance();
        this.db = FirebaseFirestore.getInstance();
    }

    public void loginWithEmail(String email, String password, LoginFragment activity) {
        this.mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    setFirstSession();
                                    activity.hiddenProgress();
                                    activity.goToMain();
                                } else {
                                    activity.hiddenProgress();
                                    activity.showErrorMessage();
                                }
                            }
                        });
    }

    public void RegisterNewUser(UserModel user, String password,  RegisterFragment fragment){
        try {
            this.mAuth.createUserWithEmailAndPassword(user.getEmail(), password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            fragment.hiddenProgress();
                            if (task.isSuccessful()) {
                                setFirstSession();
                                user.setUid(mAuth.getCurrentUser().getUid());
                                saveUserInfo(user);
                                setUserName(user.getName(),user.getLastName(),fragment);
                            }
                            else {
                                fragment.showError("El registro ha fallado, por favor intenta nuevamente");
                            }
                        }
                    });
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void setFirstSession(){
        SharedPreferences sharedPref = context.getSharedPreferences(null, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(context.getString(R.string.isFirstSession), true);
        editor.apply();
    }
    public Boolean getFirstSession(){
        SharedPreferences sharedPref = context.getSharedPreferences(null, Context.MODE_PRIVATE);
        Boolean isFirstSession = sharedPref.getBoolean(context.getString(R.string.isFirstSession), false);
        return isFirstSession;
    }

    public void setUserName(String name, String last_name, RegisterFragment fragment ){
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name + " " + last_name)
                .build();

        this.mAuth.getCurrentUser().updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            fragment.goToMain();
                        }
                    }
                });
    }

    public void saveUserInfo(UserModel user){
        try {
            Map<String, Object> data = new HashMap<>();
            data.put(GlobalVars.USER_NAME, user.getName());
            data.put(GlobalVars.USER_LAST_NAME, user.getLastName());
            data.put(GlobalVars.USER_EMAIL, user.getEmail());
            data.put(GlobalVars.USER_UID, user.getUid());
            db.collection(GlobalVars.USER_COLLECCION).document(user.getUid()).set(data);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
