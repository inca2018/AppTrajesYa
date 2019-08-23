package inca.jesus.trajesya.adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import inca.jesus.trajesya.clases.Filtro;
import inca.jesus.trajesya.clases.SubFiltro;
import inca.jesus.trajesya.R;

/**
 * Created by Jesus on 01/06/2017.
 */

public class AdapterFiltro extends RecyclerView.Adapter<AdapterFiltro.ViewHolder> {

    private Context context;
    private List<Filtro> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;
    int cont=0;

    public AdapterFiltro(Context context, List<Filtro> my_Data, RecyclerViewOnItemClickListener2
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

            nom = (TextView) itemView.findViewById(R.id.card_filtro_nombre);
            imagen = (ImageView) itemView.findViewById(R.id.card_filtro_imagen);
            linea=(LinearLayout)itemView.findViewById(R.id.card_filtro_linear);

        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public AdapterFiltro.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_filtro,parent,false);
        return new AdapterFiltro.ViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(final AdapterFiltro.ViewHolder holder, final int position) {
        holder.nom.setText(my_Data.get(position).getNombre_filtro());
        holder.nom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<my_Data.size();i++){
                    my_Data.get(i).setSelect(false);
                    my_Data.get(i).setColor(context.getResources().getColor(R.color.blanco));
                    my_Data.get(i).setColor_nombre(context.getResources().getColor(R.color.negro));
                    notifyDataSetChanged();
                }

                my_Data.get(position).setSelect(true);
                my_Data.get(position).setColor(context.getResources().getColor(R.color.colorPrimary));
                my_Data.get(position).setColor_nombre(context.getResources().getColor(R.color.colorPrimary));
                notifyDataSetChanged();
            }
        });

        holder.linea.setBackgroundColor(my_Data.get(position).getColor());
        holder.nom.setTextColor(my_Data.get(position).getColor_nombre());

        if(my_Data.get(position).getVisible()==false){
            holder.imagen.setVisibility(View.GONE);
        }else if(my_Data.get(position).getVisible()==true){
            holder.imagen.setVisibility(View.VISIBLE);
        }
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
    public void validar(int d){
        boolean encontro=false;

        for(int i = 0;i<SubFiltro.LIST_SUB_FILTRO.size();i++){
            if(SubFiltro.LIST_SUB_FILTRO.get(i).getId_filtro()==d){

                if(SubFiltro.LIST_SUB_FILTRO.get(i).isSelect()==true){
                 encontro=true;
                }
            }
        }
        if(encontro==true){
            my_Data.get(d-1).setColor_nombre(context.getResources().getColor(R.color.colorPrimary));
            my_Data.get(d-1).setVisible(true);
            notifyDataSetChanged();
         }else {
            my_Data.get(d-1).setColor_nombre(context.getResources().getColor(R.color.negro));
            my_Data.get(d-1).setVisible(false);
            notifyDataSetChanged();

        }

      }

      public void Vaciar_todo(){
          for(int i = 0;i<Filtro.LISTA_FILTRO.size();i++){
              Filtro.LISTA_FILTRO.get(i).setColor_nombre(context.getResources().getColor(R.color.negro));
              Filtro.LISTA_FILTRO.get(i).setVisible(false);
              }
          notifyDataSetChanged();
      }

    }
