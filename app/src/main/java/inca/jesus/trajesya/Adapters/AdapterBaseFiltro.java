package inca.jesus.trajesya.Adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import inca.jesus.trajesya.Clases.ListResenas;
import inca.jesus.trajesya.Clases.ProductoX;
import inca.jesus.trajesya.Clases.Resenas;
import inca.jesus.trajesya.Data.Utils.Constantes;
import inca.jesus.trajesya.R;

/**
 * Created by Jesus on 01/06/2017.
 */
public class AdapterBaseFiltro extends RecyclerView.Adapter<AdapterBaseFiltro.ViewHolder> {
    private Context context;
    private List<ProductoX> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;

    List<Resenas> temp=new ArrayList<Resenas>();

    public AdapterBaseFiltro(Context context, List<ProductoX> my_Data, RecyclerViewOnItemClickListener2
            recyclerViewOnItemClickListener) {
        this.context = context;
        this.my_Data = my_Data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView titulo;
        public ImageView imagen;
        public TextView descontado;
        public TextView desc;
        public TextView precio;
        public RatingBar rating;
        public TextView cant_resena;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            titulo = (TextView) itemView.findViewById(R.id.card_list_titulo);
            imagen = (ImageView) itemView.findViewById(R.id.card_list_imagen);
            descontado = (TextView) itemView.findViewById(R.id.item_d2);
            desc = (TextView) itemView.findViewById(R.id.item_d3);
            precio = (TextView) itemView.findViewById(R.id.item_d1);
            rating=(RatingBar)itemView.findViewById(R.id.card_list_rating);
            cant_resena = (TextView) itemView.findViewById(R.id.card_list_cant_resenas);

        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }
    public AdapterBaseFiltro.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_base_filtrar,parent,false);
        return new AdapterBaseFiltro.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(AdapterBaseFiltro.ViewHolder holder, final int position) {

        //FALTA VARIAVLE PLUS
        holder.titulo.setText(my_Data.get(position).getNom_producto());

        Picasso.get()
                .load(my_Data.get(position).getNom_producto())
                .placeholder(R.drawable.default_imagen)
                .error(R.drawable.default_imagen)
                .into(holder.imagen);

        holder.desc.setText("Acumula "+String.valueOf(my_Data.get(position).getDescuentos()+" Puntos"));
        DecimalFormat formateador = new DecimalFormat("###,###.##");
        double resu=(100*my_Data.get(position).getPrecio())/(100+my_Data.get(position).getDescuentos());
        holder.descontado.setText(String.valueOf("Precio mi Cumple S/."+formateador.format(resu)));
        holder.precio.setText("Precio Promoción S/ "+formateador.format(my_Data.get(position).getPrecio()));
        //FALTA VARIABLE RATING
        //FALTA VARIABLE CANT_RESENA
        temp=ListaTemp(position);

        if(temp.size()==0){
            holder.cant_resena.setText("(0) RESEÑAS");
            holder.rating.setRating(0);

        }else{
            holder.cant_resena.setText("("+temp.size()+") RESEÑAS");
            holder.rating.setRating(round(PromedioTotal(),2));
        }
    }
    @Override
    public int getItemCount() {
        return my_Data.size();
    }

    public List<Resenas> ListaTemp(int position){

        List<Resenas> temp2=new ArrayList<Resenas>();
        if(ListResenas.ALMACEN_RESENAS!=null){
            for(int i=0;i<ListResenas.ALMACEN_RESENAS.size();i++){
                if(ListResenas.ALMACEN_RESENAS.get(i).getProducto().getId()==my_Data.get(position).getId()){
                    temp2.add(ListResenas.ALMACEN_RESENAS.get(i));
                }
            }
            return  temp2;
        }else{

            return null;
        }
    }

    public float PromedioTotal(){
        float total;
        float suma=0;
        for(int i=0;i<temp.size();i++){
            suma=suma+ temp.get(i).getRanking_resena();

        }
        total=suma/5;
        return total;
    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}
