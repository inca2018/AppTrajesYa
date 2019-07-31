package inca.jesus.trajesya.Fragmentos;

import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import inca.jesus.trajesya.Adapters.Adapter3;
import inca.jesus.trajesya.Adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.Clases.ProductoX;
import inca.jesus.trajesya.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Recomendaciones extends Fragment {

    private RecyclerView recycler1,recycler2,recycler3,recycler4,recycler5;
    private LinearLayoutManager linearLayout1,linearLayout2,linearLayout3,linearLayout4,linearLayout5;
    private Adapter3 adapterT;


    public Recomendaciones() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_recomendaciones, container, false);
        recycler1=(RecyclerView)view.findViewById(R.id.recycler1);
        recycler2=(RecyclerView)view.findViewById(R.id.recycler2);
        recycler3=(RecyclerView)view.findViewById(R.id.recycler3);
        recycler4=(RecyclerView)view.findViewById(R.id.recycler4);
        recycler5=(RecyclerView)view.findViewById(R.id.recycler5);

        linearLayout1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        adapterT = new Adapter3(getActivity(), ProductoX.LCDx5, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
            }
        });
        recycler1.setAdapter(adapterT);
        recycler1.setLayoutManager(linearLayout1);

        linearLayout2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        adapterT = new Adapter3(getActivity(),ProductoX.CELULARx5, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
            }
        });
        recycler2.setAdapter(adapterT);
        recycler2.setLayoutManager(linearLayout2);

        linearLayout3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        adapterT = new Adapter3(getActivity(),ProductoX.CASACASx5, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {

            }
        });
        recycler3.setAdapter(adapterT);
        recycler3.setLayoutManager(linearLayout3);

        linearLayout4 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        adapterT = new Adapter3(getActivity(),ProductoX.BLUSAx5, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {

            }
        });
        recycler4.setAdapter(adapterT);
        recycler4.setLayoutManager(linearLayout4);

        linearLayout5 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        adapterT = new Adapter3(getActivity(), ProductoX.LCDx5, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {

            }
        });
        recycler5.setAdapter(adapterT);
        recycler5.setLayoutManager(linearLayout5);
        return view;
    }
}
