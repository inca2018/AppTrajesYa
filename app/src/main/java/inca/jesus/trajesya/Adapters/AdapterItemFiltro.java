package inca.jesus.trajesya.Adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import inca.jesus.trajesya.Clases.SubFiltro;
import inca.jesus.trajesya.R;

/**
 * Created by Jesus on 01/06/2017.
 */

public class AdapterItemFiltro extends RecyclerView.Adapter<AdapterItemFiltro.ViewHolder> {

    private Context context;
    private List<SubFiltro> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;

    public AdapterItemFiltro(Context context, List<SubFiltro> my_Data, RecyclerViewOnItemClickListener2
            recyclerViewOnItemClickListener) {
        this.context = context;
        this.my_Data = my_Data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;

    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nom;
        public ImageView borrar;


        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            nom = (TextView) itemView.findViewById(R.id.item_filtro_nom);
            borrar = (ImageView) itemView.findViewById(R.id.item_filtro_borrar);


        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public AdapterItemFiltro.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tem_filtro,parent,false);
        return new AdapterItemFiltro.ViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(final AdapterItemFiltro.ViewHolder holder, final int position) {
        holder.nom.setText(my_Data.get(position).getNombre_sub_filtro());
        holder.borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_Data.remove(position);
                notifyItemRemoved(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return my_Data.size();
    }

}
