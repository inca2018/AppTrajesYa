package inca.jesus.trajesya.adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

import inca.jesus.trajesya.R;
import inca.jesus.trajesya.activities.ActivityPrincipal;
import inca.jesus.trajesya.data.conexion.VolleySingleton;
import inca.jesus.trajesya.data.modelo.Producto;
import inca.jesus.trajesya.data.modelo.ReservaItem;
import inca.jesus.trajesya.data.modelo.Sesion;
import inca.jesus.trajesya.data.utils.Constantes;

/**
 * Created by Jesus on 01/06/2017.
 */

public class AdapterItemCarrito extends RecyclerView.Adapter<AdapterItemCarrito.ViewHolder>  {
    private Context context;
    private List<ReservaItem> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;
    public Sesion sesion=new Sesion();
    public Producto producto=new Producto();

    public AdapterItemCarrito(Context context, List<ReservaItem> my_Data, RecyclerViewOnItemClickListener2
            recyclerViewOnItemClickListener) {
        this.context = context;
        this.my_Data = my_Data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nombreProducto;
        public TextView precioProducto;
        public TextView descuentoProducto;
        public TextView talla;
        public TextView revisadoProducto;
        public ImageView imagenProducto;
        public Button  menos,mas;
        public TextView cantidad;
        public ImageButton eliminar;
        public LinearLayout sectorDescuento;
        public LinearLayout sectorTalla;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nombreProducto =itemView.findViewById(R.id.tvItemNombre);
            precioProducto = itemView.findViewById(R.id.tvItemPrecio);
            descuentoProducto = itemView.findViewById(R.id.tvItemDescuento);
            talla= itemView.findViewById(R.id.tvItemTalla);
            revisadoProducto =  itemView.findViewById(R.id.tvItemRevisado);
            imagenProducto =  itemView.findViewById(R.id.ivItemImagen);
            menos=itemView.findViewById(R.id.btnItemAccionMenos);
            mas=itemView.findViewById(R.id.btnItemAccionMas);
            eliminar=itemView.findViewById(R.id.ibItemEliminar);
            cantidad=itemView.findViewById(R.id.tvItemCantidad);
            sectorDescuento=itemView.findViewById(R.id.sectorDescuento);
            sectorTalla=itemView.findViewById(R.id.sectorTalla);
        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public AdapterItemCarrito.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_reserva,parent,false);
        return new AdapterItemCarrito.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(AdapterItemCarrito.ViewHolder holder, final int position) {
        DecimalFormat formateador = new DecimalFormat("###,###.00");
        holder.nombreProducto.setText(my_Data.get(position).getProductoItem().getNombreProducto());
        if(my_Data.get(position).getProductoItem().getPrecioAlquiler()>0){
            holder.precioProducto.setText("S./ "+formateador.format(my_Data.get(position).getProductoItem().getPrecioAlquiler()));
        }else{
            holder.precioProducto.setText("S./ 0.00");
        }

        if(my_Data.get(position).getProductoItem().getPrecioPromocion()>0){
            holder.descuentoProducto.setText("- "+formateador.format(my_Data.get(position).getProductoItem().getPrecioPromocion())+" %");
            holder.sectorDescuento.setVisibility(View.VISIBLE);
        }else{
            holder.descuentoProducto.setText("- 0%");
            holder.sectorDescuento.setVisibility(View.GONE);
        }

        if(my_Data.get(position).getMedidaReservaItem()!=null){
            if(my_Data.get(position).getMedidaReservaItem().getIdMedida()>0){
                holder.sectorTalla.setVisibility(View.VISIBLE);
                holder.talla.setText(my_Data.get(position).getMedidaReservaItem().getNombreMedida());
            }else{
                holder.sectorTalla.setVisibility(View.GONE);
            }
        }else{
            holder.sectorTalla.setVisibility(View.GONE);
        }

        holder.revisadoProducto.setText("Autentificado y Revisado por: "+my_Data.get(position).getProductoItem().getVerificadoProducto());

        Picasso.get()
                .load(Constantes.PATH_IMAGEN+my_Data.get(position).getProductoItem().getImagenProducto())
                .placeholder(R.drawable.default_imagen)
                .error(R.drawable.default_imagen)
                .into(holder.imagenProducto);

        holder.imagenProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrarVisitaProductoSeleccion(my_Data.get(position).getProductoItem().getIdProducto(),context);
                ((ActivityPrincipal)context).opcionItem(my_Data.get(position).getProductoItem().getIdProducto());
                producto.setIdProducto(my_Data.get(position).getProductoItem().getIdProducto());
                sesion.RegistrarProducto(context,producto);
            }
        });

        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    my_Data.remove(position);
                    notifyDataSetChanged();
              }
        });
        holder.cantidad.setText(String.valueOf(my_Data.get(position).getCantidad()));
        holder.mas.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                if(my_Data.get(position).getCantidad()<Constantes.CANTIDAD_MAX_STOCK){
                    int total=my_Data.get(position).getCantidad()+1;
                     my_Data.get(position).setCantidad(total);
                     my_Data.get(position).setTotal(my_Data.get(position).getProductoItem().getPrecioAlquiler()*my_Data.get(position).getCantidad());
                    notifyDataSetChanged();
                }
            }
        });
        holder.menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(my_Data.get(position).getCantidad()!=1){
                    int total=my_Data.get(position).getCantidad()-1;
                    my_Data.get(position).setCantidad(total);
                    my_Data.get(position).setTotal(my_Data.get(position).getProductoItem().getPrecioAlquiler()*my_Data.get(position).getCantidad());
                    notifyDataSetChanged();
                }
            }
        });

        int total=my_Data.get(position).getCantidad();
        my_Data.get(position).setCantidad(total);
        my_Data.get(position).setTotal(my_Data.get(position).getProductoItem().getPrecioAlquiler()*my_Data.get(position).getCantidad());

    }
    @Override
    public int getItemCount() {
        return my_Data.size();
    }

    public void GenerarTotales(){
        for (int i=0;i<my_Data.size();i++){
            my_Data.get(i).setTotal(my_Data.get(i).getCantidad()*my_Data.get(i).getProductoItem().getPrecioAlquiler());
        }
    }
    public double TotalAcumulado(){
        double total=0;

        for (int i=0;i<my_Data.size();i++){
         total=total+my_Data.get(i).getTotal();
        }
        return total;
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
