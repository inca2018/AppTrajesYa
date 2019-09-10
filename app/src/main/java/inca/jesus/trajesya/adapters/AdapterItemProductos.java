package inca.jesus.trajesya.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import inca.jesus.trajesya.activities.ActivityPrincipal;

import inca.jesus.trajesya.data.conexion.VolleySingleton;
import inca.jesus.trajesya.data.modelo.Producto;
import inca.jesus.trajesya.data.modelo.Sesion;
import inca.jesus.trajesya.data.utils.Constantes;
import inca.jesus.trajesya.R;


/**
 * Created by Jesus on 01/06/2017.
 */

public class AdapterItemProductos extends RecyclerView.Adapter<AdapterItemProductos.ViewHolder> {
    private Context context;
    private List<Producto> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;
    public Sesion sesion=new Sesion();
    public Producto producto=new Producto();


    public AdapterItemProductos(Context context, List<Producto> my_Data, RecyclerViewOnItemClickListener2
            recyclerViewOnItemClickListener) {
        this.context = context;
        this.my_Data = my_Data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView desc;
        public TextView precio;
        public TextView nombre;
        public ImageView imagen;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            desc = (TextView) itemView.findViewById(R.id.card_desc);
            precio = (TextView) itemView.findViewById(R.id.card_precio);
            imagen = (ImageView) itemView.findViewById(R.id.card_imagen);
            nombre= itemView.findViewById(R.id.card_nombre_producto);

        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public AdapterItemProductos.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_producto_disponible,parent,false);
        return new AdapterItemProductos.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(AdapterItemProductos.ViewHolder holder, final int position) {

        DecimalFormat formateador = new DecimalFormat("###,###.00");
        final double precioAlquiler=my_Data.get(position).getPrecioAlquiler();
        if(precioAlquiler==0){
            holder.precio.setText("S/ 0.00");
        }else{

            String valor=formateador.format(my_Data.get(position).getPrecioAlquiler());
            holder.precio.setText("S/ "+valor);
        }

        holder.nombre.setText(my_Data.get(position).getNombreProducto());


        if(my_Data.get(position).getPrecioPromocion()==0){
            holder.desc.setText(String.valueOf(""));
        }else{
            holder.desc.setText(String.valueOf("-"+(int)my_Data.get(position).getPrecioPromocion()+"% Desc."));
        }

        Picasso.get()
                .load(Constantes.PATH_IMAGEN+my_Data.get(position).getImagenProducto())
                .placeholder(R.drawable.default_imagen)
                .error(R.drawable.default_imagen)
                .into(holder.imagen);

        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrarVisitaProductoSeleccion(my_Data.get(position).getIdProducto(),context);
                ((ActivityPrincipal)context).opcionItem(my_Data.get(position).getIdProducto());
                producto.setIdProducto(my_Data.get(position).getIdProducto());
                sesion.RegistrarProducto(context,producto);
            }
        });
        holder.desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrarVisitaProductoSeleccion(my_Data.get(position).getIdProducto(),context);
                ((ActivityPrincipal)context).opcionItem(my_Data.get(position).getIdProducto());
                producto.setIdProducto(my_Data.get(position).getIdProducto());
                sesion.RegistrarProducto(context,producto);
            }
        });
        holder.precio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrarVisitaProductoSeleccion(my_Data.get(position).getIdProducto(),context);
                ((ActivityPrincipal)context).opcionItem(my_Data.get(position).getIdProducto());
                producto.setIdProducto(my_Data.get(position).getIdProducto());
                sesion.RegistrarProducto(context,producto);
            }
        });

    }
    @Override
    public int getItemCount() {
        return my_Data.size();
    }

    private void RegistrarVisitaProductoSeleccion(final int idProductoE,final Context context) {

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
                params.put("origen", "1");
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }

}
