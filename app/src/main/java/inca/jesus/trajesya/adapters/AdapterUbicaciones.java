package inca.jesus.trajesya.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import inca.jesus.trajesya.R;
import inca.jesus.trajesya.data.modelo.Promocion;
import inca.jesus.trajesya.data.modelo.UbicacionDireccion;
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
        public TextView desc;
        public TextView precio;
        public ImageView imagen;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imagen = itemView.findViewById(R.id.iv_promocionImagen);
        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public AdapterUbicaciones.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_promocion,parent,false);
        return new AdapterUbicaciones.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(AdapterUbicaciones.ViewHolder holder, final int position) {

    }
    @Override
    public int getItemCount() {
        return my_Data.size();
    }

}
