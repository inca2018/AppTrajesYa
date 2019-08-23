package inca.jesus.trajesya.fragmentos;

import android.annotation.SuppressLint;
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
import inca.jesus.trajesya.adapters.AdapterPromociones;
import inca.jesus.trajesya.adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.data.conexion.VolleySingleton;
import inca.jesus.trajesya.data.modelo.Estado;
import inca.jesus.trajesya.data.modelo.Promocion;
import inca.jesus.trajesya.data.utils.Constantes;
import inca.jesus.trajesya.R;


public class Promociones extends Fragment {

    private RecyclerView recyclerPromociones;
    private LinearLayoutManager linearLayout;
    private AdapterPromociones adapterPromociones;
    Context context;
    List<Promocion> ListaPromociones;

    public Promociones() {
        // Required empty public constructor
    }

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_lo_mejor, container, false);
        context=getActivity();

        recyclerPromociones=view.findViewById(R.id.recyclerPromociones);
        ListaPromociones=new ArrayList<>();

        linearLayout = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        adapterPromociones = new AdapterPromociones(getActivity(), Constantes.Base_ListaPromociones, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
            }
        });
        recyclerPromociones.setAdapter(adapterPromociones);
        recyclerPromociones.setLayoutManager(linearLayout);

       // ListarPromocionesDisponibles(context);

        return view;
    }

    public void ListarPromocionesDisponibles(final Context context){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.GESTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                JSONArray categorias=jsonResponse.getJSONArray("promociones");
                                for(int i=0;i<categorias.length();i++){
                                    JSONObject objeto= categorias.getJSONObject(i);
                                    Promocion temp=new Promocion();
                                    temp.setIdPromocion(objeto.getInt("idPromocion"));
                                    temp.setNombrePromocion(objeto.getString("NombrePromocion"));
                                    temp.setImagenPromocion(objeto.getString("imagenPromocion"));
                                    temp.setLinkPromocion(objeto.getString("linkPromocion"));
                                    temp.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    temp.setFechaUpdate(objeto.getString("fechaUpdate"));

                                    Estado estado=new Estado();
                                    estado.setIdEstado(objeto.getInt("Estado_idEstado"));
                                    temp.setEstadoPromocion(estado);
                                    Log.i("Inca","Promocion Recuperada :"+temp.getNombrePromocion());
                                    ListaPromociones.add(temp);
                                }
                                Log.i("Inca","Servidor Listar Promociones");
                                adapterPromociones.notifyDataSetChanged();

                            } else {

                                Toast.makeText(context, "Categorias no Disponibles.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("Inca","Error JSON Promociones:"+e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Inca Error Response", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "ListarPromociones");
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

}
