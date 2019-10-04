package inca.jesus.trajesya.fragmentos;

import android.content.Context;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import inca.jesus.trajesya.adapters.AdapterItemProductos;
import inca.jesus.trajesya.adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.data.conexion.VolleySingleton;
import inca.jesus.trajesya.data.modelo.Categoria;
import inca.jesus.trajesya.data.modelo.Estado;
import inca.jesus.trajesya.data.modelo.Producto;
import inca.jesus.trajesya.data.modelo.SubCategoria;
import inca.jesus.trajesya.data.modelo.UnidadTerritorial;
import inca.jesus.trajesya.data.utils.Constantes;
import inca.jesus.trajesya.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentInicioRecomendaciones extends Fragment {

    private RecyclerView recycler1,recycler2,recycler3,recycler4;
    private LinearLayoutManager linearLayout1,linearLayout2,linearLayout3,linearLayout4;


    private AdapterItemProductos adapterVistosRecien,adapterPromocion,adapterMasTradicionales,adapterMasOtros,adapterTendencias;

    Context context;
    /*--------------LISTADOS--*/
    List<Producto> ListaVistosRecien;
    List<Producto> ListaPromocion;
    List<Producto> ListaMasTradicionales;
    List<Producto> ListaMasOtros;


    public fragmentInicioRecomendaciones() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_recomendaciones, container, false);
        context=getActivity();
        recycler1=view.findViewById(R.id.recycler1);
        recycler2=view.findViewById(R.id.recycler2);
        recycler3=view.findViewById(R.id.recycler3);
        recycler4=view.findViewById(R.id.recycler4);


        ListaVistosRecien=new ArrayList<>();
        ListaPromocion=new ArrayList<>();
        ListaMasTradicionales=new ArrayList<>();
        ListaMasOtros=new ArrayList<>();


        linearLayout1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        adapterVistosRecien = new AdapterItemProductos(getActivity(), Constantes.Base_ListaProductoRecientes, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
            }
        });
        recycler1.setAdapter(adapterVistosRecien);
        recycler1.setLayoutManager(linearLayout1);



        linearLayout2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        adapterPromocion = new AdapterItemProductos(getActivity(),Constantes.Base_ListaProductoPromociones, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
            }
        });
        recycler2.setAdapter(adapterPromocion);
        recycler2.setLayoutManager(linearLayout2);

        linearLayout3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        adapterMasTradicionales = new AdapterItemProductos(getActivity(),Constantes.Base_ListaProductosTopTradicionales, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {

            }
        });
        recycler3.setAdapter(adapterMasTradicionales);
        recycler3.setLayoutManager(linearLayout3);

        linearLayout4 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        adapterMasOtros = new AdapterItemProductos(getActivity(),Constantes.Base_ListaProductosTopOtros, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {

            }
        });
        recycler4.setAdapter(adapterMasOtros);
        recycler4.setLayoutManager(linearLayout4);

        return view;
    }

}
