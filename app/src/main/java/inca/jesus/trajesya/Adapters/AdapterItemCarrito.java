package inca.jesus.trajesya.Adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

import inca.jesus.trajesya.Clases.ItemCarrito;
import inca.jesus.trajesya.R;

/**
 * Created by Jesus on 01/06/2017.
 */

public class AdapterItemCarrito extends RecyclerView.Adapter<AdapterItemCarrito.ViewHolder>  {
    private Context context;
    private List<ItemCarrito> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;


    public AdapterItemCarrito(Context context, List<ItemCarrito> my_Data, RecyclerViewOnItemClickListener2
            recyclerViewOnItemClickListener) {
        this.context = context;
        this.my_Data = my_Data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView titulo;
        public TextView precio;
        public TextView desc;
        public TextView descontado;
        public TextView vendedor;
        public ImageView imagen;
        public Button  menos,mas;
        public TextView cantidad;
        public ImageButton eliminar;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            titulo = (TextView) itemView.findViewById(R.id.item_carrito_titulo);
            precio = (TextView) itemView.findViewById(R.id.item_carrito_precio);
            desc = (TextView) itemView.findViewById(R.id.item_carrito_desc);
            descontado = (TextView) itemView.findViewById(R.id.item_carrito_descontado);
            vendedor = (TextView) itemView.findViewById(R.id.item_carrito_cliente);
            imagen = (ImageView) itemView.findViewById(R.id.item_carrito_foto);
            menos=(Button)itemView.findViewById(R.id.item_carrito_boton_menos);
            mas=(Button)itemView.findViewById(R.id.item_carrito_mas);
            eliminar=(ImageButton)itemView.findViewById(R.id.item_carrito_eliminar);
            cantidad=(TextView)itemView.findViewById(R.id.item_carrito_cantidad);
        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public AdapterItemCarrito.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_carrito,parent,false);
        return new AdapterItemCarrito.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(AdapterItemCarrito.ViewHolder holder, final int position) {
        DecimalFormat formateador = new DecimalFormat("###,###.##");
        holder.titulo.setText(my_Data.get(position).getP().getNom_producto());
        holder.precio.setText("Precio Promoción: S./ "+String.valueOf(my_Data.get(position).getP().getPrecio()));
        holder.desc.setText("Precio Normal: S./ "+String.valueOf(my_Data.get(position).getP().getPrecio()));
        double resu=(100*my_Data.get(position).getP().getPrecio())/(100+my_Data.get(position).getP().getDescuentos());

        holder.descontado.setText(String.valueOf("Precio miCumple: S/."+formateador.format(resu)));
        holder.vendedor.setText("Enviado y Vendido por: "+my_Data.get(position).getP().getVendedor());
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
        holder.cantidad.setText(String.valueOf(my_Data.get(position).getCantidad()));
        holder.mas.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                if(my_Data.get(position).getCantidad()<10){

                        int total=my_Data.get(position).getCantidad()+1;
                     my_Data.get(position).setCantidad(total);
                     my_Data.get(position).setTotal(my_Data.get(position).getP().getPrecio()*my_Data.get(position).getCantidad());

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

                    my_Data.get(position).setTotal(my_Data.get(position).getP().getPrecio()*my_Data.get(position).getCantidad());
                    notifyDataSetChanged();
                }

            }
        });


    }
    @Override
    public int getItemCount() {
        return my_Data.size();
    }


    public String TotalAcumulado(){
        String d="";
        double total=0;

        for (int i=0;i<my_Data.size();i++){
         total=total+my_Data.get(i).getTotal();
        }

        d=String.valueOf(total);
        return d;
    }

}
