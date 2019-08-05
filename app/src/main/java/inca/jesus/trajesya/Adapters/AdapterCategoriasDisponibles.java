package inca.jesus.trajesya.Adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import inca.jesus.trajesya.Data.Modelo.Categoria;
import inca.jesus.trajesya.R;

/**
 * Created by Jesus on 01/06/2017.
 */

public class AdapterCategoriasDisponibles extends RecyclerView.Adapter<AdapterCategoriasDisponibles.ViewHolder> {

    private Context context;
    private List<Categoria> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;
    public String RUTA_PATH="http://admin.trajesya.com/assets/images/";

    public AdapterCategoriasDisponibles(Context context, List<Categoria> my_Data, RecyclerViewOnItemClickListener2
            recyclerViewOnItemClickListener) {
        this.context = context;
        this.my_Data = my_Data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;

    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public TextView nom;
        public ImageView imagen;
        public LinearLayout linea;


        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            nom = (TextView) itemView.findViewById(R.id.card_categ_nom);
            imagen = (ImageView) itemView.findViewById(R.id.card_categ_imagen);
            linea=(LinearLayout)itemView.findViewById(R.id.card_cate_line);

        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public AdapterCategoriasDisponibles.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_categorias,parent,false);
        return new AdapterCategoriasDisponibles.ViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(final AdapterCategoriasDisponibles.ViewHolder holder, final int position) {

        holder.nom.setText(my_Data.get(position).getNombreCategoria());

        //Glide.with(holder.itemView.getContext())
                //.load(RUTA_PATH+my_Data.get(position).getImagenCategoria())
                //.into(holder.imagen);

        Glide.with(holder.itemView.getContext())
                .applyDefaultRequestOptions(new RequestOptions()
                        .placeholder(R.drawable.default_imagen)
                        .error(R.drawable.default_imagen))
                .load(RUTA_PATH+my_Data.get(position).getImagenCategoria())
                .into(holder.imagen);


        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<my_Data.size();i++){
                    my_Data.get(i).setSelect(false);
                    my_Data.get(i).setColor(context.getResources().getColor(R.color.blanco));
                    my_Data.get(i).setColor_nombre(context.getResources().getColor(R.color.negro));
                }
                my_Data.get(position).setSelect(true);
                my_Data.get(position).setColor(context.getResources().getColor(R.color.colorPrimary));
                my_Data.get(position).setColor_nombre(context.getResources().getColor(R.color.colorPrimary));
                notifyDataSetChanged();
            }
        });
        holder.nom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<my_Data.size();i++){
                    my_Data.get(i).setSelect(false);
                    my_Data.get(i).setColor(context.getResources().getColor(R.color.blanco));
                    my_Data.get(i).setColor_nombre(context.getResources().getColor(R.color.negro));
                }
                my_Data.get(position).setSelect(true);
                my_Data.get(position).setColor(context.getResources().getColor(R.color.colorPrimary));
                my_Data.get(position).setColor_nombre(context.getResources().getColor(R.color.colorPrimary));
                notifyDataSetChanged();
             }
        });
        if(my_Data.get(position).isSelect()==true){
            holder.linea.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            holder.nom.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }else{
            holder.linea.setBackgroundColor(context.getResources().getColor(R.color.blanco));
            holder.nom.setTextColor(context.getResources().getColor(R.color.negro));
        }
    }
    @Override
    public int getItemCount() {
        return my_Data.size();
    }
    public int RecuperarIdCategoria(){
        int d=0;
        for(int i=0;i<my_Data.size();i++){
            if(my_Data.get(i).isSelect()==true){
                d=my_Data.get(i).getIdCategoria();
            }
        }
        return d;
     }
     public String RecuperarNombreCategoria(){
         String d="";
         for(int i=0;i<my_Data.size();i++){
             if(my_Data.get(i).isSelect()==true){
                 d=my_Data.get(i).getNombreCategoria();
             }
         }
         return d;
     }

     public void LimpiarDatos(){
        my_Data.clear();
     }
}
