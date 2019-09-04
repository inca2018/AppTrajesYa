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
import inca.jesus.trajesya.data.utils.Constantes;


/**
 * Created by Jesus on 01/06/2017.
 */

public class AdapterUbicaciones extends RecyclerView.Adapter<AdapterUbicaciones.ViewHolder> {
    private Context context;
    private List<Promocion> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;


    public AdapterUbicaciones(Context context, List<Promocion> my_Data, RecyclerViewOnItemClickListener2
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

        Picasso.get()
                .load(Constantes.PATH_IMAGEN+my_Data.get(position).getImagenPromocion())
                .placeholder(R.drawable.default_imagen)
                .error(R.drawable.default_imagen)
                .into(holder.imagen);


        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse(my_Data.get(position).getLinkPromocion());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);

                //Toast.makeText(context, my_Data.get(position).getNombrePromocion(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return my_Data.size();
    }

}
