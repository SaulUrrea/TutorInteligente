package com.example.tutorinteligente.Main.Presenter;

import android.content.Context;

import com.example.tutorinteligente.Application.TutorInteligenteApplication;
import com.example.tutorinteligente.Main.View.Activity.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.FirebaseFirestore;

public class PresenterHome {

    Context context;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    public PresenterHome(Context context) {
        this.context = context;
        TutorInteligenteApplication.getTutorInteligenteApplication().getTutorInteligenteComponent().inject(this);
        this.mAuth = FirebaseAuth.getInstance();
        this.db = FirebaseFirestore.getInstance();
    }

    public void getCurrentUser(HomeActivity activity) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        activity.setNameAndEmail(user.getDisplayName(),user.getEmail());
    }
}
