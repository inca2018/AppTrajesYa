package inca.jesus.trajesya.Adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import inca.jesus.trajesya.Clases.Segmento_SubCategorias;
import inca.jesus.trajesya.R;

/**
 * Created by Jesus on 01/06/2017.
 */

public class AdapterSubCategoria extends RecyclerView.Adapter<AdapterSubCategoria.ViewHolder> {

    private Context context;
    private List<Segmento_SubCategorias> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;

    public AdapterSubCategoria(Context context, List<Segmento_SubCategorias> my_Data, RecyclerViewOnItemClickListener2
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

            nom = (TextView) itemView.findViewById(R.id.card_sub_categoria_nom2);
            imagen = (ImageView) itemView.findViewById(R.id.card_sub_categoria_next2);

        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public AdapterSubCategoria.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_sub_categoria_producto,parent,false);
        return new AdapterSubCategoria.ViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(AdapterSubCategoria.ViewHolder holder, final int position) {

        holder.nom.setText(my_Data.get(position).getNombre());

    }
    @Override
    public int getItemCount() {
        return my_Data.size();
    }
}
