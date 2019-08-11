package inca.jesus.trajesya.Adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import inca.jesus.trajesya.Activities.Item;
import inca.jesus.trajesya.Data.Modelo.Producto;

import inca.jesus.trajesya.Data.Utils.Constantes;
import inca.jesus.trajesya.R;

/**
 * Created by Jesus on 01/06/2017.
 */

public class AdapterProductosDisponibles extends RecyclerView.Adapter<AdapterProductosDisponibles.ViewHolder> {

    private Context context;
    private List<Producto> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;


    public AdapterProductosDisponibles(Context context, List<Producto> my_Data, RecyclerViewOnItemClickListener2
            recyclerViewOnItemClickListener) {
        this.context = context;
        this.my_Data = my_Data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;

    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public TextView nom;
        public ImageView imagen;


        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            nom = itemView.findViewById(R.id.card_producto);
            imagen = itemView.findViewById(R.id.iv_producto);

        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public AdapterProductosDisponibles.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_producto,parent,false);
        return new AdapterProductosDisponibles.ViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(AdapterProductosDisponibles.ViewHolder holder, final int position) {

        holder.nom.setText(my_Data.get(position).getNombreProducto());

        Picasso.get()
                .load(Constantes.PATH_IMAGEN +my_Data.get(position).getImagenProducto())
                .placeholder(R.drawable.default_imagen)
                .error(R.drawable.default_imagen)
                .into(holder.imagen);

        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
    public void LimpiarDatos(){
        my_Data.clear();
    }
}
