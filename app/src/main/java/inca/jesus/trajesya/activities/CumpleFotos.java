package inca.jesus.trajesya.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import inca.jesus.trajesya.adapters.AdapterCumpleFotos;
import inca.jesus.trajesya.adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.clases.CumpleFotos_class;
import inca.jesus.trajesya.R;

public class CumpleFotos extends AppCompatActivity {
    LinearLayoutManager linear;
    AdapterCumpleFotos adapter;
    RecyclerView recycler;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cumple_fotos);
        recycler=(RecyclerView)findViewById(R.id.recylcer_cumple_fotos);



        linear = new LinearLayoutManager(CumpleFotos.this, LinearLayoutManager.VERTICAL,false);
        adapter = new AdapterCumpleFotos(CumpleFotos.this, CumpleFotos_class.LISTA_GENERAL, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
            }
        });
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(linear);
    }


}
