package inca.jesus.trajesya.Adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import inca.jesus.trajesya.Activities.Item;
import inca.jesus.trajesya.Clases.ProductoX;
import inca.jesus.trajesya.Data.Conexion.VolleySingleton;
import inca.jesus.trajesya.Data.Modelo.Producto;
import inca.jesus.trajesya.Data.Utils.Constantes;
import inca.jesus.trajesya.R;

/**
 * Created by Jesus on 01/06/2017.
 */

public class AdapterBuscar extends RecyclerView.Adapter<AdapterBuscar.ViewHolder> {
    private Context context;
    private List<Producto> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;

    public AdapterBuscar(Context context, List<Producto> my_Data, RecyclerViewOnItemClickListener2
            recyclerViewOnItemClickListener) {
        this.context = context;
        this.my_Data = my_Data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nom;
        public TextView vendedor;
        public ImageView foto;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nom = (TextView) itemView.findViewById(R.id.card_buscar_nom);
            foto=(ImageView)itemView.findViewById(R.id.card_buscar_imagen);
            vendedor=(TextView)itemView.findViewById(R.id.card_buscar_vendedor);

        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public AdapterBuscar.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_buscar,parent,false);
        return new AdapterBuscar.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(AdapterBuscar.ViewHolder holder, final int position) {

        holder.nom.setText(my_Data.get(position).getNombreProducto());
        holder.vendedor.setText("Verificado por: "+my_Data.get(position).getVerificadoProducto());

        Picasso.get()
                .load(Constantes.PATH_IMAGEN+my_Data.get(position).getImagenProducto())
                .placeholder(R.drawable.default_imagen)
                .error(R.drawable.default_imagen)
                .into(holder.foto);

        holder.foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrarVisitaBuscador(my_Data.get(position).getIdProducto(),context);
                Intent intent = new Intent(context, Item.class);
                intent.putExtra("idProducto",my_Data.get(position).getIdProducto());
                context.startActivity(intent);
            }
        });
        holder.nom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrarVisitaBuscador(my_Data.get(position).getIdProducto(),context);
                Intent intent = new Intent(context, Item.class);
                intent.putExtra("idProducto",my_Data.get(position).getIdProducto());
                context.startActivity(intent);
            }
        });
        holder.vendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrarVisitaBuscador(my_Data.get(position).getIdProducto(),context);
                Intent intent = new Intent(context, Item.class);
                intent.putExtra("idProducto",my_Data.get(position).getIdProducto());
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return my_Data.size();
    }

    private void RegistrarVisitaBuscador(final int idProductoE,final Context context) {

        final String idProducto=String.valueOf(idProductoE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.GESTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Log.i("Inca","Servidor Registrar Visita ID:"+idProducto);

                            } else {
                                Log.e("Inca","No se Pudo Registrar Visita ID:"+idProducto);
                            }
                        } catch (
                                JSONException e) {
                            e.printStackTrace();
                            Log.e("Inca","Error JSON:"+e);
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
                params.put("operacion", "RegistrarVisitaProducto");
                params.put("idProducto", idProducto);
                params.put("origen", "2");
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }

}
