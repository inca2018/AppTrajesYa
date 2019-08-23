package inca.jesus.trajesya.adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import inca.jesus.trajesya.clases.SubFiltro;
import inca.jesus.trajesya.R;

/**
 * Created by Jesus on 01/06/2017.
 */

public class AdapterSubFiltro extends RecyclerView.Adapter<AdapterSubFiltro.ViewHolder> {

    private Context context;
    private List<SubFiltro> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;

    public AdapterSubFiltro(Context context, List<SubFiltro> my_Data, RecyclerViewOnItemClickListener2
            recyclerViewOnItemClickListener) {
        this.context = context;
        this.my_Data = my_Data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;

    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nom;
        public CheckBox check;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nom = (TextView) itemView.findViewById(R.id.subfiltro_nom);
            check = (CheckBox) itemView.findViewById(R.id.subfiltro_check);
        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }
    public AdapterSubFiltro.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_sub_filtro,parent,false);
        return new AdapterSubFiltro.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final AdapterSubFiltro.ViewHolder holder, final int position) {
        holder.nom.setText(my_Data.get(position).getNombre_sub_filtro());
        holder.check.setChecked(my_Data.get(position).isSelect());

        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<SubFiltro.LIST_SUB_FILTRO.size();i++){
                    if(SubFiltro.LIST_SUB_FILTRO.get(i).getId()==posSeleccion()){
                        SubFiltro.LIST_SUB_FILTRO.get(i).setSelect(false);
                        SubFiltro.LIST_SUB_FILTRO.get(i).setColor_nombre(context.getResources().getColor(R.color.negro));
                        notifyDataSetChanged();
                    }
                }
                my_Data.get(position).setSelect(true);
                my_Data.get(position).setColor_nombre(context.getResources().getColor(R.color.colorPrimary));
                holder.nom.setTextColor(my_Data.get(position).getColor_nombre());
                notifyDataSetChanged();
            }

        });
     holder.nom.setTextColor(my_Data.get(position).getColor_nombre());
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
     public void recuListaPosiciones(){

         for(int i=0;i<SubFiltro.LIST_SUB_FILTRO.size();i++){
              if(SubFiltro.LIST_SUB_FILTRO.get(i).isSelect()==true){
                 SubFiltro.ITEM_SUB_FILTRO.add(SubFiltro.LIST_SUB_FILTRO.get(i));
              }
          }
     }


    public void VaciarData(int d){
        for(int i=0;i<SubFiltro.LIST_SUB_FILTRO.size();i++){
            if(SubFiltro.LIST_SUB_FILTRO.get(i).getId_filtro()==d){
                SubFiltro.LIST_SUB_FILTRO.get(i).setSelect(false);
                SubFiltro.LIST_SUB_FILTRO.get(i).setColor_nombre(context.getResources().getColor(R.color.negro));
            }
        }
        notifyDataSetChanged();
     }

    public void Vaciar_todo(){
        for(int i = 0;i<SubFiltro.LIST_SUB_FILTRO.size();i++){
            SubFiltro.LIST_SUB_FILTRO.get(i).setColor_nombre(context.getResources().getColor(R.color.negro));
            SubFiltro.LIST_SUB_FILTRO.get(i).setSelect(false);
        }

        notifyDataSetChanged();
    }

}
