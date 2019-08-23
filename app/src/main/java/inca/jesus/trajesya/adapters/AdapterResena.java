package inca.jesus.trajesya.adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import inca.jesus.trajesya.clases.Resenas;
import inca.jesus.trajesya.R;

/**
 * Created by Jesus on 01/06/2017.
 */

public class AdapterResena extends RecyclerView.Adapter<AdapterResena.ViewHolder> {
    private Context context;
    private List<Resenas> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;

    public AdapterResena(Context context, List<Resenas> my_Data, RecyclerViewOnItemClickListener2
            recyclerViewOnItemClickListener) {
        this.context = context;
        this.my_Data = my_Data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView usuario;
        public TextView fechas;
        public RatingBar rating;
        public TextView titulo;
        public TextView descripcion;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            usuario = (TextView) itemView.findViewById(R.id.card_resena_usuario);
            fechas = (TextView) itemView.findViewById(R.id.card_resena_fecha);
            rating = (RatingBar) itemView.findViewById(R.id.card_resena_rating);
            titulo = (TextView) itemView.findViewById(R.id.card_resena_titulo);
            descripcion = (TextView) itemView.findViewById(R.id.card_resena_desc);
        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public AdapterResena.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_resena,parent,false);
        return new AdapterResena.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(AdapterResena.ViewHolder holder, final int position) {

        holder.usuario.setText(my_Data.get(position).getUsuario());
        holder.fechas.setText(my_Data.get(position).getFecha());
        holder.rating.setRating(my_Data.get(position).getRanking_resena());
        holder.titulo.setText(my_Data.get(position).getTitulo());
        holder.descripcion.setText(my_Data.get(position).getResena_Descripcion());

    }
    @Override
    public int getItemCount() {
        return my_Data.size();
    }

}
