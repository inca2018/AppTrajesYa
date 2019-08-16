package inca.jesus.trajesya.Fragmentos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import inca.jesus.trajesya.Activities.Item;
import inca.jesus.trajesya.Adapters.AdapterBuscar;
import inca.jesus.trajesya.Adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.Clases.ProductoX;
import inca.jesus.trajesya.Data.Utils.Constantes;
import inca.jesus.trajesya.R;


public class FragmentBuscando extends Fragment {
    private LinearLayoutManager linearLayout;
    private AdapterBuscar adapter;
    RecyclerView recycler;

    public FragmentBuscando() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_fragment_buscando, container, false);
        recycler=(RecyclerView)view.findViewById(R.id.recycler_buscando);

        if(Constantes.PRODUCTOS_BUSCADOS.size()!=0){

        linearLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        adapter = new AdapterBuscar(getActivity(), Constantes.PRODUCTOS_BUSCADOS, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
                //not required
            }
        });
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(linearLayout);
        }

        return view;
    }



}
