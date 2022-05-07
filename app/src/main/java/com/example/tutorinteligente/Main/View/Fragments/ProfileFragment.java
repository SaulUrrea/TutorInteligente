package com.example.tutorinteligente.Main.View.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.tutorinteligente.R;

public class ProfileFragment extends Fragment {


    private Context mContext;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(Context context) {
        ProfileFragment fragment = new ProfileFragment();
        fragment.setContext(context);
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
        var v =inflater.inflate(R.layout.fragment_profile, container, false);
        initVars(v);
        return v;
    }

    private void initVars(View v) {

    }
}