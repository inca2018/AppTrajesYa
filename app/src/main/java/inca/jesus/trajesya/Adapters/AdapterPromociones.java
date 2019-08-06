package inca.jesus.trajesya.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import inca.jesus.trajesya.Activities.Item;
import inca.jesus.trajesya.Clases.ProductoX;
import inca.jesus.trajesya.Data.Modelo.Promocion;
import inca.jesus.trajesya.Data.Utils.Constantes;
import inca.jesus.trajesya.R;


/**
 * Created by Jesus on 01/06/2017.
 */

public class AdapterPromociones extends RecyclerView.Adapter<AdapterPromociones.ViewHolder> {
    private Context context;
    private List<Promocion> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;


    public AdapterPromociones(Context context, List<Promocion> my_Data, RecyclerViewOnItemClickListener2
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

    public AdapterPromociones.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_promocion,parent,false);
        return new AdapterPromociones.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(AdapterPromociones.ViewHolder holder, final int position) {



        Glide.with(holder.itemView.getContext())
                .load(Constantes.PATH_IMAGEN+my_Data.get(position).getImagenPromocion())
                .into(holder.imagen);
        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*System.out.println("Click en Imagen");
                Intent intent = new Intent(context, Item.class);
                intent.putExtra("Producto",my_Data.get(position));
                context.startActivity(intent);*/
                Toast.makeText(context, my_Data.get(position).getNombrePromocion(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public int getItemCount() {
        return my_Data.size();
    }

}
