package inca.jesus.trajesya.adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import inca.jesus.trajesya.clases.ItemCarrito;
import inca.jesus.trajesya.R;
import inca.jesus.trajesya.data.modelo.ReservaItem;
import inca.jesus.trajesya.data.utils.Constantes;

/**
 * Created by Jesus on 01/06/2017.
 */

public class AdapterItemCarrito extends RecyclerView.Adapter<AdapterItemCarrito.ViewHolder>  {
    private Context context;
    private List<ReservaItem> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;


    public AdapterItemCarrito(Context context, List<ReservaItem> my_Data, RecyclerViewOnItemClickListener2
            recyclerViewOnItemClickListener) {
        this.context = context;
        this.my_Data = my_Data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nombreProducto;
        public TextView precioProducto;
        public TextView descuentoProducto;
        public TextView descontado;
        public TextView revisadoProducto;
        public ImageView imagenProducto;
        public Button  menos,mas;
        public TextView cantidad;
        public ImageButton eliminar;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nombreProducto = (TextView) itemView.findViewById(R.id.tvItemNombre);
            precioProducto = (TextView) itemView.findViewById(R.id.tvItemPrecio);
            descuentoProducto = (TextView) itemView.findViewById(R.id.tvItemDescuento);

            revisadoProducto = (TextView) itemView.findViewById(R.id.tvItemRevisado);
            imagenProducto = (ImageView) itemView.findViewById(R.id.ivItemImagen);
            menos=(Button)itemView.findViewById(R.id.btnItemAccionMenos);
            mas=(Button)itemView.findViewById(R.id.btnItemAccionMas);
            eliminar=(ImageButton)itemView.findViewById(R.id.ibItemEliminar);
            cantidad=(TextView)itemView.findViewById(R.id.tvItemCantidad);
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
        DecimalFormat formateador = new DecimalFormat("###,###.00");
        holder.nombreProducto.setText(my_Data.get(position).getProductoItem().getNombreProducto());
        holder.precioProducto.setText("Precio : S./ "+String.valueOf(formateador.format(my_Data.get(position).getProductoItem().getPrecioAlquiler())));
        holder.descuentoProducto.setText("- "+String.valueOf(formateador.format(my_Data.get(position).getProductoItem().getPrecioPromocion()))+" %");

        //double resu=(100*my_Data.get(position).getP().getPrecio())/(100+my_Data.get(position).getP().getDescuentos());

        //holder.descontado.setText(String.valueOf("Precio miCumple: S/."+formateador.format(resu)));
        holder.revisadoProducto.setText("Autentificado y Revisado por: "+my_Data.get(position).getProductoItem().getVerificadoProducto());

        Picasso.get()
                .load(Constantes.PATH_IMAGEN+my_Data.get(position).getProductoItem().getImagenProducto())
                .placeholder(R.drawable.default_imagen)
                .error(R.drawable.default_imagen)
                .into(holder.imagenProducto);

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
                if(my_Data.get(position).getCantidad()<Constantes.CANTIDAD_MAX_STOCK){

                        int total=my_Data.get(position).getCantidad()+1;
                     my_Data.get(position).setCantidad(total);
                     my_Data.get(position).setTotal(my_Data.get(position).getProductoItem().getPrecioAlquiler()*my_Data.get(position).getCantidad());

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

                    my_Data.get(position).setTotal(my_Data.get(position).getProductoItem().getPrecioAlquiler()*my_Data.get(position).getCantidad());
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
