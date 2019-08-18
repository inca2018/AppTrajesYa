package inca.jesus.trajesya.Fragmentos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import inca.jesus.trajesya.Activities.ActivityPrincipal;
import inca.jesus.trajesya.Adapters.AdapterItemProductos;
import inca.jesus.trajesya.Adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.Data.Conexion.VolleySingleton;
import inca.jesus.trajesya.Data.Modelo.Categoria;
import inca.jesus.trajesya.Data.Modelo.Estado;
import inca.jesus.trajesya.Data.Modelo.Producto;
import inca.jesus.trajesya.Data.Modelo.SubCategoria;
import inca.jesus.trajesya.Data.Modelo.UnidadTerritorial;
import inca.jesus.trajesya.Data.Utils.Constantes;
import inca.jesus.trajesya.R;

public class Destacados extends Fragment {
    private RecyclerView recyclerNuevos,recycler2,recycler3,recycler4;
    private LinearLayoutManager linearLayout1,linearLayout2,linearLayout3,linearLayout4;
    private AdapterItemProductos adapterNuevos,adapterMasVistos,adapterMasAlquilados,adapterTendendecias;

    Context context;
    /*--------------LISTADOS--*/
    List<Producto> ListaNuevos;
    List<Producto> ListaMasVistos;
    List<Producto> ListaMasAlquilados;
    List<Producto> ListaTendencias;
    public Destacados() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_destacados, container, false);
        context=getActivity();
        recyclerNuevos =view.findViewById(R.id.recycler1);
        recycler2=view.findViewById(R.id.recycler2);
        recycler3=view.findViewById(R.id.recycler3);
        recycler4=view.findViewById(R.id.recycler4);

        /*-------Iniciar Listado------*/
        ListaNuevos=new ArrayList<>();
        ListaMasVistos=new ArrayList<>();
        ListaMasAlquilados=new ArrayList<>();
        ListaTendencias=new ArrayList<>();

        /*-------Armar Listas---------*/
        linearLayout1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        adapterNuevos = new AdapterItemProductos(getActivity(), Constantes.Base_ListaProductoNuevo, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
                //not required
            }
        });
        recyclerNuevos.setAdapter(adapterNuevos);
        recyclerNuevos.setLayoutManager(linearLayout1);

        //ListarProductosNuevos(context);


        linearLayout2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        adapterMasVistos = new AdapterItemProductos(getActivity(),Constantes.Base_ListaProductoMasVisto, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {

            }
        });
        recycler2.setAdapter(adapterMasVistos);
        recycler2.setLayoutManager(linearLayout2);

        //ListarProductosMasVistos(context);

        linearLayout3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        adapterMasAlquilados = new AdapterItemProductos(getActivity(),Constantes.Base_ListaProductoMasAlquilados, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {

            }
        });
        recycler3.setAdapter(adapterMasAlquilados);
        recycler3.setLayoutManager(linearLayout3);

        //ListarProductosMasAlquilados(context);

        linearLayout4 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        adapterTendendecias = new AdapterItemProductos(getActivity(),Constantes.Base_ListaProductoTendencias, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {

            }
        });
        recycler4.setAdapter(adapterTendendecias);
        recycler4.setLayoutManager(linearLayout4);

        //ListarProductosTendencias(context);

        return view;

    }

}
