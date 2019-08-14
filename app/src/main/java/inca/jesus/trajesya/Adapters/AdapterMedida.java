package inca.jesus.trajesya.Adapters;

import android.content.Context;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import inca.jesus.trajesya.Data.Modelo.Medida;
import inca.jesus.trajesya.R;

/**
 * Created by Jesus on 01/06/2017.
 */

public class AdapterMedida extends RecyclerView.Adapter<AdapterMedida.ViewHolder> {

    private Context context;
    private List<Medida> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;

    public AdapterMedida(Context context, List<Medida> my_Data, RecyclerViewOnItemClickListener2
            recyclerViewOnItemClickListener) {
        this.context = context;
        this.my_Data = my_Data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nom;
        public CardView card;


        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            nom = (TextView) itemView.findViewById(R.id.tam_nombre);
            card=(CardView)itemView.findViewById(R.id.card_tam);


        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public AdapterMedida.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tam,parent,false);
        return new AdapterMedida.ViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(final AdapterMedida.ViewHolder holder, final int position) {
        holder.nom.setText(my_Data.get(position).getSimboloMedida());

        if(my_Data.get(position).isSelect()==true){
            holder.nom.setTextColor(context.getResources().getColor(R.color.deseo));
        }else{
            holder.nom.setTextColor(context.getResources().getColor(R.color.negro));
        }

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0;i<my_Data.size();i++){
                    my_Data.get(i).setSelect(false);
                    my_Data.get(i).setColor(context.getResources().getColor(R.color.negro));
                }

                my_Data.get(position).setSelect(true);
                my_Data.get(position).setColor(context.getResources().getColor(R.color.deseo));
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return my_Data.size();
    }

}
