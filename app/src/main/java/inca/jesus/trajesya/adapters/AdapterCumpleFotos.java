package inca.jesus.trajesya.adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import inca.jesus.trajesya.clases.CarouselView;
import inca.jesus.trajesya.clases.CumpleFotos_class;
import inca.jesus.trajesya.R;

/**
 * Created by Jesus on 01/06/2017.
 */

public class AdapterCumpleFotos extends RecyclerView.Adapter<AdapterCumpleFotos.ViewHolder> {

    private Context context;
    private List<CumpleFotos_class> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;
    int cont=0;

    public AdapterCumpleFotos(Context context, List<CumpleFotos_class> my_Data, RecyclerViewOnItemClickListener2
            recyclerViewOnItemClickListener) {
        this.context = context;
        this.my_Data = my_Data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;

    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public TextView nom;
        public CarouselView imagen;
        public TextView nom_Evento;
        public  TextView Fecha_Evento;



        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            nom = (TextView) itemView.findViewById(R.id.titulo_cumple_foto);
            imagen = (CarouselView) itemView.findViewById(R.id.pictureplay);
            nom_Evento=(TextView)itemView.findViewById(R.id.nombre_evento);
            Fecha_Evento=(TextView)itemView.findViewById(R.id.fecha_evento);

        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public AdapterCumpleFotos.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_cumple_fotos,parent,false);
        return new AdapterCumpleFotos.ViewHolder(itemView);

    }
    @Override

    public void onBindViewHolder(final AdapterCumpleFotos.ViewHolder holder, final int position) {
        holder.nom.setText(my_Data.get(position).getNombre());
        holder.imagen.setImageResources(my_Data.get(position).getFotos());
        holder.nom_Evento.setText(my_Data.get(position).getUsuario());
        holder.Fecha_Evento.setText(my_Data.get(position).getFecha());
        holder.imagen.setOnPageClickListener(new CarouselView.OnPageClickListener() {
            @Override
            public void onPageClick(int position) {
            }
        });
    }
    @Override
    public int getItemCount() {
        return my_Data.size();
    }

    }
