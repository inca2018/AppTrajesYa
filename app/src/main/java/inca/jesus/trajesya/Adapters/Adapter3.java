package inca.jesus.trajesya.Adapters;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import inca.jesus.trajesya.Activities.Item;
import inca.jesus.trajesya.Clases.ProductoX;
import inca.jesus.trajesya.Data.Utils.Constantes;
import inca.jesus.trajesya.R;


/**
 * Created by Jesus on 01/06/2017.
 */

public class Adapter3 extends RecyclerView.Adapter<Adapter3.ViewHolder> {
    private Context context;
    private List<ProductoX> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;

    public Adapter3(Context context, List<ProductoX> my_Data, RecyclerViewOnItemClickListener2
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
            desc = (TextView) itemView.findViewById(R.id.card_desc);
            precio = (TextView) itemView.findViewById(R.id.card_precio);
            imagen = (ImageView) itemView.findViewById(R.id.card_imagen);
        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public Adapter3.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card1,parent,false);
        return new Adapter3.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(Adapter3.ViewHolder holder, final int position) {


        holder.desc.setText(String.valueOf(my_Data.get(position).getDescuentos()+" %"));
        holder.precio.setText("S/ "+my_Data.get(position).getPrecio());


        Picasso.get()
                .load(my_Data.get(position).getIdDrawable())
                .placeholder(R.drawable.default_imagen)
                .error(R.drawable.default_imagen)
                .into(holder.imagen);

        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Click en Imagen");
                Intent intent = new Intent(context, Item.class);
                intent.putExtra("Producto",my_Data.get(position));
                context.startActivity(intent);

            }
        });

        holder.desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Click en Desc");
                Intent intent = new Intent(context,Item.class);
                intent.putExtra("Producto",my_Data.get(position));
                context.startActivity(intent);


            }
        });

        holder.precio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Click en Precio");
                Intent intent = new Intent(context,Item.class);
                intent.putExtra("Producto",my_Data.get(position));
                context.startActivity(intent);

            }
        });

    }
    @Override
    public int getItemCount() {
        return my_Data.size();
    }

}
