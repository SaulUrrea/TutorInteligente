package com.example.tutorinteligente.Main.Presenter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.tutorinteligente.Application.TutorInteligenteApplication;
import com.example.tutorinteligente.Main.View.Activity.ProfileActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

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

    public void getCurrentUser(ProfileActivity activity) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        activity.setNameAndEmail(user.getDisplayName(),user.getEmail());
    }


    public void updateUserInfo(String name, String lastname) {
        FirebaseUser uid = FirebaseAuth.getInstance().getCurrentUser();
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("last_name", lastname);

        this.db.collection("users").document(uid.getUid())
                .update(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i("Completo", "writing document");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("error", "Error writing document", e);
                    }
                });

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name+" "+lastname)
                .build();

        uid.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("actualizado", "User profile updated.");
                        }
                    }
                });


    }
}
