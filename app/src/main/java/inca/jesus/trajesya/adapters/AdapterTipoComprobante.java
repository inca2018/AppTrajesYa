package inca.jesus.trajesya.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import inca.jesus.trajesya.R;
import inca.jesus.trajesya.data.modelo.TipoComprobante;
import inca.jesus.trajesya.data.modelo.TipoTarjeta;
import inca.jesus.trajesya.data.utils.Constantes;


/**
 * Created by Jesus on 01/06/2017.
 */

public class AdapterTipoComprobante extends RecyclerView.Adapter<AdapterTipoComprobante.ViewHolder> {
    private Context context;
    private List<TipoComprobante> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;


    public AdapterTipoComprobante(Context context, List<TipoComprobante> my_Data, RecyclerViewOnItemClickListener2
            recyclerViewOnItemClickListener) {
        this.context = context;
        this.my_Data = my_Data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nombre;
        public ImageView ivCheckSelected;
        public CardView AreaCard;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nombre=itemView.findViewById(R.id.txtNombreOpcion);
            ivCheckSelected = itemView.findViewById(R.id.ivCheckSelected);
            AreaCard= itemView.findViewById(R.id.AreaCard);
        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public AdapterTipoComprobante.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_tipo_pago,parent,false);
        return new AdapterTipoComprobante.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(AdapterTipoComprobante.ViewHolder holder, final int position) {

        holder.nombre.setText(my_Data.get(position).getNombreTipoComprobante());


        if(my_Data.get(position).isSelect()){
            holder.ivCheckSelected.setVisibility(View.VISIBLE);
        }else{
            holder.ivCheckSelected.setVisibility(View.GONE);
        }
        holder.AreaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(my_Data.get(position).isSelect()){
                    my_Data.get(position).setSelect(false);
                }else{
                    QuitarSeleccion();
                    MarcarSeleccion(position);
                }
                notifyDataSetChanged();
            }
        });
        holder.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(my_Data.get(position).isSelect()){
                    my_Data.get(position).setSelect(false);
                }else{
                    QuitarSeleccion();
                    MarcarSeleccion(position);
                }
                notifyDataSetChanged();
            }
        });
    }

    public void MarcarSeleccion(int position) {
        my_Data.get(position).setSelect(true);
        Constantes.TIPO_COMPROBANTE_SELECT=my_Data.get(position);
    }

    public void QuitarSeleccion() {
        for(int i=0;i<my_Data.size();i++){
            my_Data.get(i).setSelect(false);
        }
    }

    @Override
    public int getItemCount() {
        return my_Data.size();
    }

    public TipoComprobante RecuperarTipoPagoSeleccionada(){
        TipoComprobante temp=new TipoComprobante();
        for(int i=0;i<my_Data.size();i++){
            if(my_Data.get(i).isSelect()){
                temp=my_Data.get(i);
            }
        }
        return  temp;
    }
}
