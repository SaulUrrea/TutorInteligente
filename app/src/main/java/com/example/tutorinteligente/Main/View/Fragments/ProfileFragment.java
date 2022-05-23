package com.example.tutorinteligente.Main.View.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tutorinteligente.Main.View.Activity.HomeActivity;
import com.example.tutorinteligente.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProfileFragment extends Fragment {

    private EditText edName;
    private TextView nombreTextView,emailTextView;
    private FloatingActionButton btnEdit;
    private MaterialButton btnSave;
    private Boolean isVisible = false;

    private Context mContext;

    public ProfileFragment(Context context) {
        setContext(context);
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
        var v =inflater.inflate(R.layout.fragment_profile, container, false);
        initVars(v);
        return v;
    }

    private void initVars(View v) {
        nombreTextView = v.findViewById(R.id.tvName);
        emailTextView = v.findViewById(R.id.tvEmail);
        edName = v.findViewById(R.id.edName);
        btnEdit = v.findViewById(R.id.btnEdit);
        btnSave = v.findViewById(R.id.btnGuardar);

        HomeActivity activity = (HomeActivity) mContext;
        activity.presenterHome.getCurrentUser(this);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isVisible){
                    btnSave.setVisibility(View.GONE);
                    edName.setVisibility(View.GONE);
                    nombreTextView.setVisibility(View.VISIBLE);
                    btnEdit.setImageResource(R.drawable.ic_baseline_edit_24);
                    isVisible = false;
                }else{
                    btnSave.setVisibility(View.VISIBLE);
                    edName.setVisibility(View.VISIBLE);
                    nombreTextView.setVisibility(View.GONE);
                    btnEdit.setImageResource(R.drawable.ic_baseline_close_24);
                    isVisible = true;
                }

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edName.getText().toString();
                if (!name.isEmpty()){
                    activity.presenterHome.updateUserInfo(name);
                    btnSave.setVisibility(View.GONE);
                    edName.setVisibility(View.GONE);
                    nombreTextView.setVisibility(View.VISIBLE);
                    btnEdit.setImageResource(R.drawable.ic_baseline_edit_24);
                    nombreTextView.setText(name);
                }
            }
        });

    }

    public void setNameAndEmail(String displayName, String email) {
        nombreTextView.setText(displayName);
        emailTextView.setText(email);
    }
}