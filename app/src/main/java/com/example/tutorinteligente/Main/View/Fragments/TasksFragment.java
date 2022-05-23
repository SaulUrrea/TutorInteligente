package com.example.tutorinteligente.Main.View.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorinteligente.Main.Models.CursosModel;
import com.example.tutorinteligente.Main.View.Adapters.CourseAdapter;
import com.example.tutorinteligente.R;

public class TasksFragment extends Fragment {

    private RecyclerView recyclerView;
    private CourseAdapter mAdapter;
    private TextView tvCount;
    private Context mContext;


    public TasksFragment(Context context) {
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
        var v =inflater.inflate(R.layout.fragment_tasks, container, false);
        initVars(v);
        return v;
    }

    private void initVars(View v) {
        recyclerView = v.findViewById(R.id.recyclerView);
        tvCount = v.findViewById(R.id.tvCount);

        mAdapter = new CourseAdapter();
        CursosModel[] cursos = new CursosModel[]{
                new CursosModel("Matemáticas para niños", "Curso guiado para niños"),
                new CursosModel("Matemáticas para adultos", "Curso guiado para adultos"),
                new CursosModel("Álgebra", "Curso guiado para todo tipo de personas"),
                new CursosModel("Física", "Curso guiado para todo tipo de personas"),
                new CursosModel("Factorización", "Curso guiado para adultos"),
                new CursosModel("Geometría", "Curso guiado para adultos"),
                new CursosModel("Trigonometría", "Curso guiado para adultos"),
                new CursosModel("Álgebra lineal", "Curso guiado para adultos"),
                new CursosModel("Álgebra cuadrática", "Curso guiado para adultos"),
                new CursosModel("Álgebra lineal", "Curso guiado para adultos"),};
        mAdapter.CustomAdapter(cursos);
        tvCount.setText(cursos.length + "");
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(mContext));
    }
}