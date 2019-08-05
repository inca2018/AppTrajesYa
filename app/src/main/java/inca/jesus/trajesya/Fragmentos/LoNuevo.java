package inca.jesus.trajesya.Fragmentos;


import android.annotation.SuppressLint;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import inca.jesus.trajesya.Adapters.AdapterCategoriasDisponibles;
import inca.jesus.trajesya.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoNuevo extends Fragment {

    private RecyclerView recycler1;
    private LinearLayoutManager linearLayout1;
    private AdapterCategoriasDisponibles adapter1;


    public LoNuevo() {
        // Required empty public constructor
    }


    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_lo_nuevo, container, false);

        recycler1=(RecyclerView)view.findViewById(R.id.recycler_nuevo);

        linearLayout1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        /*adapter1 = new AdapterCategoriasDisponibles(getActivity(), Segmento_Productos.CATEGORIAs2, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(getActivity(),"position: "+position, Toast.LENGTH_SHORT).show();
            }
        });*/
        recycler1.setAdapter(adapter1);
        recycler1.setLayoutManager(linearLayout1);
        return view;
    }
}
