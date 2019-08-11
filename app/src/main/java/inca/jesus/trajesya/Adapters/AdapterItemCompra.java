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
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import inca.jesus.trajesya.Clases.ItemCompra;
import inca.jesus.trajesya.R;


public class AdapterItemCompra extends RecyclerView.Adapter<AdapterItemCompra.ViewHolder>  {
    private Context context;
    private List<ItemCompra> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;


    public AdapterItemCompra(Context context, List<ItemCompra> my_Data, RecyclerViewOnItemClickListener2
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
        public TextView dato_num_ped;
        public TextView dato_fecha;
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
            dato_num_ped=(TextView)itemView.findViewById(R.id.item_compra_num_pedido);
            dato_fecha=(TextView)itemView.findViewById(R.id.item_compra_fecha);
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

    public AdapterItemCompra.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_compra_item,parent,false);
        return new AdapterItemCompra.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(AdapterItemCompra.ViewHolder holder, final int position) {

        holder.titulo.setText(my_Data.get(position).getProductoX().getNom_producto());
        holder.precio.setText("S./ "+String.valueOf(my_Data.get(position).getTotal()));
        holder.desc.setText(String.valueOf(my_Data.get(position).getProductoX().getDescuentos()+" %"));

        DecimalFormat formateador = new DecimalFormat("###,###.##");
        double resu=(100*my_Data.get(position).getProductoX().getPrecio())/(100+my_Data.get(position).getProductoX().getDescuentos());

        holder.descontado.setText(String.valueOf("S/."+formateador.format(resu)));


        Picasso.get()
                .load(my_Data.get(position).getProductoX().getIdDrawable())
                .placeholder(R.drawable.default_imagen)
                .error(R.drawable.default_imagen)
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

                    Double precio_total=my_Data.get(position).getTotal();
                    precio_total=precio_total+my_Data.get(position).getProductoX().getPrecio();
                    my_Data.get(position).setTotal(precio_total);


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

                    Double precio_total=my_Data.get(position).getTotal();
                    precio_total=precio_total-my_Data.get(position).getProductoX().getPrecio();
                    my_Data.get(position).setTotal(precio_total);

                    notifyDataSetChanged();
                }

            }
        });

    }
    @Override
    public int getItemCount() {
        return my_Data.size();
    }


}
