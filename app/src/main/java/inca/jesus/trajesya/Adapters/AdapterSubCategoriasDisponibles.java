package inca.jesus.trajesya.Adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import inca.jesus.trajesya.Data.Modelo.SubCategoria;
import inca.jesus.trajesya.R;


public class AdapterSubCategoriasDisponibles extends RecyclerView.Adapter<AdapterSubCategoriasDisponibles.ViewHolder> {

    private Context context;
    private List<SubCategoria> my_Data;
    public String RUTA_PATH="http://admin.trajesya.com/assets/images/";
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;

    public AdapterSubCategoriasDisponibles(Context context, List<SubCategoria> my_Data, RecyclerViewOnItemClickListener2
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

            nom = (TextView) itemView.findViewById(R.id.card_categoria_nom);
            imagen = (ImageView) itemView.findViewById(R.id.iv_subcategoria);

        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public AdapterSubCategoriasDisponibles.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_subcategoria,parent,false);
        return new AdapterSubCategoriasDisponibles.ViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(AdapterSubCategoriasDisponibles.ViewHolder holder, final int position) {

        holder.nom.setText(my_Data.get(position).getNombreSubCategoria());

        Glide.with(holder.itemView.getContext())
                .applyDefaultRequestOptions(new RequestOptions()
                        .placeholder(R.drawable.default_imagen)
                        .error(R.drawable.default_imagen))
                .load(RUTA_PATH+my_Data.get(position).getImagenSubCategorias())
                .into(holder.imagen);
        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<my_Data.size();i++){
                    my_Data.get(i).setSelect(false);
                }
                my_Data.get(position).setSelect(true);
                notifyDataSetChanged();
            }
        });
        holder.nom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<my_Data.size();i++){
                    my_Data.get(i).setSelect(false);
                }
                my_Data.get(position).setSelect(true);
                notifyDataSetChanged();
            }
        });

    }
    @Override
    public int getItemCount() {
        return my_Data.size();
    }

    public int posSeleccion(){
        int d=0;
        for(int i=0;i<my_Data.size();i++){
            if(my_Data.get(i).isSelect()==true){
                d=my_Data.get(i).getIdSubCategoria();
            }
        }
        return d;

    }
    public String RecuperarNombreSubCategoria(){
        String d="";
        for(int i=0;i<my_Data.size();i++){
            if(my_Data.get(i).isSelect()==true){
                d=my_Data.get(i).getNombreSubCategoria();
            }
        }
        return d;
    }
    public void LimpiarDatos(){
        my_Data.clear();
    }

}

