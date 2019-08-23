package inca.jesus.trajesya.adapters;

import android.content.Context;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import inca.jesus.trajesya.clases.TiposProductos;
import inca.jesus.trajesya.R;

/**
 * Created by Jesus on 01/06/2017.
 */

public class AdapterColores extends RecyclerView.Adapter<AdapterColores.ViewHolder> {

    private Context context;
    private List<TiposProductos> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;

    public AdapterColores(Context context, List<TiposProductos> my_Data, RecyclerViewOnItemClickListener2
            recyclerViewOnItemClickListener) {
        this.context = context;
        this.my_Data = my_Data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;

    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nom;
        public ImageView imagen;
        public CardView card;


        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            nom = (TextView) itemView.findViewById(R.id.colores_nombre);
            imagen= (ImageView) itemView.findViewById(R.id.colores_imagen);
            card=(CardView)itemView.findViewById(R.id.card_col);


        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public AdapterColores.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_colores,parent,false);
        return new AdapterColores.ViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(final AdapterColores.ViewHolder holder, final int position) {

        holder.nom.setText(my_Data.get(position).getNombre());
        if(my_Data.get(position).isSelect()==true){
            holder.nom.setTextColor(context.getResources().getColor(R.color.deseo));
        }else{
            holder.nom.setTextColor(context.getResources().getColor(R.color.negro));
        }

        Picasso.get()
                .load(my_Data.get(position).getImagen())
                .placeholder(R.drawable.default_imagen)
                .error(R.drawable.default_imagen)
                .into(holder.imagen);

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
