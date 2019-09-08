package inca.jesus.trajesya.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import inca.jesus.trajesya.R;
import inca.jesus.trajesya.activities.ActivityPrincipal;
import inca.jesus.trajesya.activities.LoginActivity;
import inca.jesus.trajesya.data.conexion.VolleySingleton;
import inca.jesus.trajesya.data.modelo.Promocion;
import inca.jesus.trajesya.data.modelo.UbicacionDireccion;
import inca.jesus.trajesya.data.modelo.Usuario;
import inca.jesus.trajesya.data.utils.Constantes;


/**
 * Created by Jesus on 01/06/2017.
 */

public class AdapterUbicaciones extends RecyclerView.Adapter<AdapterUbicaciones.ViewHolder> {
    private Context context;
    private List<UbicacionDireccion> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;


    public AdapterUbicaciones(Context context, List<UbicacionDireccion> my_Data, RecyclerViewOnItemClickListener2
            recyclerViewOnItemClickListener) {
        this.context = context;
        this.my_Data = my_Data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nombreUbicacion;
        public TextView referenciaUbicacion;
        public TextView distritoUbicacion;
        public ImageView accionEliminar;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nombreUbicacion=itemView.findViewById(R.id.txtNombreUbicacion);
            referenciaUbicacion=itemView.findViewById(R.id.txtReferenciaUbicacion);
            distritoUbicacion=itemView.findViewById(R.id.txtDistritoUbicacion);
            accionEliminar = itemView.findViewById(R.id.ivEliminarUbicacion);
        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public AdapterUbicaciones.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_ubicacion,parent,false);
        return new AdapterUbicaciones.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(AdapterUbicaciones.ViewHolder holder, final int position) {

        holder.distritoUbicacion.setText(my_Data.get(position).getDistrito().getNombreUnidadTerritorial());
        holder.nombreUbicacion.setText(my_Data.get(position).getDireccionEntrega());
        holder.referenciaUbicacion.setText(my_Data.get(position).getReferenciaDireccion());
        holder.accionEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Eliminar")
                        .setMessage("¿Desea Eliminar Ubicación?")
                        .setPositiveButton("SI",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        EliminarUbicacionServidor(String.valueOf(my_Data.get(position).getIdUbicacionDireccion()));
                                    }
                                })
                        .setNegativeButton("NO",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                builder.show();
            }
        });
    }

    private void EliminarUbicacionServidor(final String idUbicacionDireccion) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            EliminarElementoLista(idUbicacionDireccion);
                            JSONObject jsonResponse = new JSONObject(response);
                            String Mensaje = jsonResponse.getString("mensaje");
                            Toast.makeText(context, Mensaje, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "EliminarUbicacion");
                params.put("idUbicacion",idUbicacionDireccion);


                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }

    private void EliminarElementoLista(String idUbicacionDireccion) {
        int id=Integer.parseInt(idUbicacionDireccion);
        for(int i=0;i<my_Data.size();i++){
            if(my_Data.get(i).getIdUbicacionDireccion()==id){
                my_Data.remove(i);
            }

        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return my_Data.size();
    }

}
