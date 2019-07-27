package inca.jesus.trajesya.Adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import inca.jesus.trajesya.Clases.ItemCarrito;
import inca.jesus.trajesya.Clases.ItemCompra;
import inca.jesus.trajesya.Clases.ItemFavorito;
import inca.jesus.trajesya.Clases.ListCarrito;
import inca.jesus.trajesya.Clases.ListCompra;
import inca.jesus.trajesya.Clases.Sesion;
import inca.jesus.trajesya.R;

/**
 * Created by Jesus on 01/06/2017.
 */

public class AdapterItemFavorito extends RecyclerView.Adapter<AdapterItemFavorito.ViewHolder>  {
    private Context context;
    private List<ItemFavorito> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;
    private int cant=0;

    public AdapterItemFavorito(Context context, List<ItemFavorito> my_Data, RecyclerViewOnItemClickListener2
            recyclerViewOnItemClickListener) {
        this.context = context;
        this.my_Data = my_Data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView titulo;
        public TextView precio;
        public TextView precio2;
        public TextView precio3;
        public ImageView imagen;
        public ImageButton eliminar;
        public Button agregar;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            titulo = (TextView) itemView.findViewById(R.id.favorito_carrito_titulo);
            precio = (TextView) itemView.findViewById(R.id.favorito_carrito_precio);
            precio2 = (TextView) itemView.findViewById(R.id.favorito_carrito_precio2);
            precio3= (TextView) itemView.findViewById(R.id.favorito_carrito_precio3);
            imagen = (ImageView) itemView.findViewById(R.id.favorito_carrito_foto);
            eliminar=(ImageButton)itemView.findViewById(R.id.favorito_carrito_eliminar);
            agregar=(Button)itemView.findViewById(R.id.favorito_Carrito_agregar);

        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public AdapterItemFavorito.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_favorito,parent,false);
        return new AdapterItemFavorito.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(AdapterItemFavorito.ViewHolder holder, final int position) {

        holder.titulo.setText(my_Data.get(position).getP().getNom_producto());
        holder.precio.setText("Precio normal S./ "+String.valueOf(my_Data.get(position).getP().getPrecio()));
        holder.precio2.setText("Precio Promoción S./ "+String.valueOf(my_Data.get(position).getP().getPrecio()));
        holder.precio3.setText("Precio miCumple S./ "+String.valueOf(my_Data.get(position).getP().getPrecio()));

        Glide.with(holder.itemView.getContext())
                .load(my_Data.get(position).getP().getIdDrawable())
                .into(holder.imagen);
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    my_Data.remove(position);
                    notifyDataSetChanged();
              }
        });

        holder.agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Time today = new Time(Time.getCurrentTimezone());
                today.setToNow();
                int monthDay = today.monthDay;
                int month = today.month+1;
                int year=today.year;
                String fecha=monthDay+"/"+month+"/"+year;

                ItemCarrito ItemProducto=new ItemCarrito(1,my_Data.get(position).getP(),fecha, Sesion.USUARIO.getNombre(),0,my_Data.get(position).getP().getPrecio());
                ListCarrito.CARRITO_LISTA.add(ItemProducto);
                ItemCompra ItemProducto2=new ItemCompra(1,"Envio "+my_Data.get(position).getP().getId()+1,fecha,2,my_Data.get(position).getP(),0,my_Data.get(position).getP().getPrecio());
                ListCompra.CARRITO_COMPRA.add(ItemProducto2);
                Toast.makeText(context, "Agregado a Carrito", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public int getItemCount() {
        return my_Data.size();
    }
}
