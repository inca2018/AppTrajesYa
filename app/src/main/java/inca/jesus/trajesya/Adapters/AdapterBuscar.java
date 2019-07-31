package inca.jesus.trajesya.Adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import inca.jesus.trajesya.Clases.ProductoX;
import inca.jesus.trajesya.R;

/**
 * Created by Jesus on 01/06/2017.
 */

public class AdapterBuscar extends RecyclerView.Adapter<AdapterBuscar.ViewHolder> {
    private Context context;
    private List<ProductoX> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;

    public AdapterBuscar(Context context, List<ProductoX> my_Data, RecyclerViewOnItemClickListener2
            recyclerViewOnItemClickListener) {
        this.context = context;
        this.my_Data = my_Data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nom;
        public TextView vendedor;
        public ImageView foto;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nom = (TextView) itemView.findViewById(R.id.card_buscar_nom);
            foto=(ImageView)itemView.findViewById(R.id.card_buscar_imagen);
            vendedor=(TextView)itemView.findViewById(R.id.card_buscar_vendedor);

        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public AdapterBuscar.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_buscar,parent,false);
        return new AdapterBuscar.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(AdapterBuscar.ViewHolder holder, final int position) {

        holder.nom.setText(my_Data.get(position).getNom_producto());
        holder.vendedor.setText(my_Data.get(position).getVendedor());

        Glide.with(holder.itemView.getContext())
                .load(my_Data.get(position).getIdDrawable())
                .into(holder.foto);

    }
    @Override
    public int getItemCount() {
        return my_Data.size();
    }

}
