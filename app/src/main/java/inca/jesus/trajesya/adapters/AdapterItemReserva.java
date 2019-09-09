package inca.jesus.trajesya.adapters;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import inca.jesus.trajesya.activities.Item;
import inca.jesus.trajesya.clases.ProductoX;
import inca.jesus.trajesya.R;


/**
 * Created by Jesus on 01/06/2017.
 */

public class AdapterItemReserva extends RecyclerView.Adapter<AdapterItemReserva.ViewHolder> {
    private Context context;
    private List<ProductoX> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;

    public AdapterItemReserva(Context context, List<ProductoX> my_Data, RecyclerViewOnItemClickListener2
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

    public AdapterItemReserva.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card1,parent,false);
        return new AdapterItemReserva.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(AdapterItemReserva.ViewHolder holder, final int position) {

         DecimalFormat formateador = new DecimalFormat("###,###.00");
        double precioAlquiler=my_Data.get(position).getPrecio();
        if(my_Data.get(position).getDescuentos()==0){
            holder.desc.setVisibility(View.GONE);
        }else{
            holder.desc.setVisibility(View.VISIBLE);
            holder.desc.setText(String.valueOf(my_Data.get(position).getDescuentos()+" %"));
        }
        holder.precio.setText("S/ "+precioAlquiler);


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
