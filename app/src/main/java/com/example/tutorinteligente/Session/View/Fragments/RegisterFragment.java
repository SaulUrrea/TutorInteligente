package com.example.tutorinteligente.Session.View.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.tutorinteligente.R;

public class RegisterFragment extends Fragment {


    private Context mContext;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance(Context mContext) {
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
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
}