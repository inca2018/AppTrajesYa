package inca.jesus.trajesya.Adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import inca.jesus.trajesya.Clases.ProductoX;
import inca.jesus.trajesya.R;


/**
 * Created by Jesus on 01/06/2017.
 */

public class Adapter1 extends RecyclerView.Adapter<Adapter1.ViewHolder> {
    private Context context;
    private List<ProductoX> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;

    public Adapter1(Context context, List<ProductoX> my_Data, RecyclerViewOnItemClickListener2
            recyclerViewOnItemClickListener) {
        this.context = context;
        this.my_Data = my_Data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView desc;
        public TextView precio;
        public ImageView imagen;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            desc = (TextView) itemView.findViewById(R.id.card_desc);
            precio = (TextView) itemView.findViewById(R.id.card_precio);
            imagen = (ImageView) itemView.findViewById(R.id.card_imagen);
        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public Adapter1.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card1,parent,false);
        return new Adapter1.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(Adapter1.ViewHolder holder, final int position) {

        holder.desc.setText(my_Data.get(position).getDescuentos());
        holder.precio.setText("S/ "+my_Data.get(position).getPrecio());
        Glide.with(holder.itemView.getContext())
                .load(my_Data.get(position).getIdDrawable())
                .into(holder.imagen);
    }
    @Override
    public int getItemCount() {
        return my_Data.size();
    }

}
