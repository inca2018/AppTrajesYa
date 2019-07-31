package inca.jesus.trajesya.Adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import inca.jesus.trajesya.Clases.Segmento_Categorias;
import inca.jesus.trajesya.R;


public class AdapterCategoria extends RecyclerView.Adapter<AdapterCategoria.ViewHolder> {

    private Context context;
    private List<Segmento_Categorias> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;

    public AdapterCategoria(Context context, List<Segmento_Categorias> my_Data, RecyclerViewOnItemClickListener2
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
            imagen = (ImageView) itemView.findViewById(R.id.card_categoria_next);

        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public AdapterCategoria.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_categoria_producto,parent,false);
        return new AdapterCategoria.ViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(AdapterCategoria.ViewHolder holder, final int position) {

        holder.nom.setText(my_Data.get(position).getNombre());
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
                d=my_Data.get(i).getId();
            }
        }
        return d;

    }
    public String RecuNombreSegmento(){
        String d="";
        for(int i=0;i<my_Data.size();i++){
            if(my_Data.get(i).isSelect()==true){
                d=my_Data.get(i).getNombre();
            }
        }
        return d;
    }

}

