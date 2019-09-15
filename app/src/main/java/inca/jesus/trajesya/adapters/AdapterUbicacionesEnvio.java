package inca.jesus.trajesya.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import inca.jesus.trajesya.R;
import inca.jesus.trajesya.data.conexion.VolleySingleton;
import inca.jesus.trajesya.data.modelo.UbicacionDireccion;
import inca.jesus.trajesya.data.utils.Constantes;


/**
 * Created by Jesus on 01/06/2017.
 */

public class AdapterUbicacionesEnvio extends RecyclerView.Adapter<AdapterUbicacionesEnvio.ViewHolder> {
    private Context context;
    private List<UbicacionDireccion> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;


    public AdapterUbicacionesEnvio(Context context, List<UbicacionDireccion> my_Data, RecyclerViewOnItemClickListener2
            recyclerViewOnItemClickListener) {
        this.context = context;
        this.my_Data = my_Data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nombreUbicacion;
        public TextView referenciaUbicacion;
        public TextView distritoUbicacion;
        public ImageView ivCheckSelected;
        public CardView AreaCard;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nombreUbicacion=itemView.findViewById(R.id.txtNombreUbicacion);
            referenciaUbicacion=itemView.findViewById(R.id.txtReferenciaUbicacion);
            distritoUbicacion=itemView.findViewById(R.id.txtDistritoUbicacion);
            ivCheckSelected = itemView.findViewById(R.id.ivCheckSelected);
            AreaCard= itemView.findViewById(R.id.AreaCard);
        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public AdapterUbicacionesEnvio.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_ubicacion_envio,parent,false);
        return new AdapterUbicacionesEnvio.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(AdapterUbicacionesEnvio.ViewHolder holder, final int position) {

        holder.distritoUbicacion.setText(my_Data.get(position).getDistrito().getNombreUnidadTerritorial());
        holder.nombreUbicacion.setText(my_Data.get(position).getDireccionEntrega());
        holder.referenciaUbicacion.setText(my_Data.get(position).getReferenciaDireccion());

        if(my_Data.get(position).isSelect()){
            holder.ivCheckSelected.setVisibility(View.VISIBLE);
        }else{
            holder.ivCheckSelected.setVisibility(View.GONE);
        }
        holder.AreaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(my_Data.get(position).isSelect()){
                    my_Data.get(position).setSelect(false);
                }else{
                    QuitarSeleccion();
                    MarcarSeleccion(position);
                }
                notifyDataSetChanged();
            }
        });
        holder.nombreUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(my_Data.get(position).isSelect()){
                    my_Data.get(position).setSelect(false);
                }else{
                    QuitarSeleccion();
                    MarcarSeleccion(position);
                }
                notifyDataSetChanged();
            }
        });

        holder.distritoUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(my_Data.get(position).isSelect()){
                    my_Data.get(position).setSelect(false);
                }else{
                    QuitarSeleccion();
                    MarcarSeleccion(position);
                }
                notifyDataSetChanged();
            }
        });

        holder.referenciaUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(my_Data.get(position).isSelect()){
                    my_Data.get(position).setSelect(false);
                }else{
                    QuitarSeleccion();
                    MarcarSeleccion(position);
                }
                notifyDataSetChanged();
            }
        });
    }

    private void MarcarSeleccion(int position) {
        my_Data.get(position).setSelect(true);
        Constantes.UBICACION_SELECT=my_Data.get(position);
    }

    private void QuitarSeleccion() {
        for(int i=0;i<my_Data.size();i++){
            my_Data.get(i).setSelect(false);
        }
    }

    @Override
    public int getItemCount() {
        return my_Data.size();
    }

    public UbicacionDireccion RecuperarUbicacionSeleccionada(){
        UbicacionDireccion temp=new UbicacionDireccion();
        for(int i=0;i<my_Data.size();i++){
            if(my_Data.get(i).isSelect()){
                temp=my_Data.get(i);
            }
        }
        return  temp;
    }
}
