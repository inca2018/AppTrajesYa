package inca.jesus.trajesya.fragmentos;

import android.annotation.SuppressLint;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import inca.jesus.trajesya.adapters.AdapterBuscar;
import inca.jesus.trajesya.adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.data.utils.Constantes;
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
