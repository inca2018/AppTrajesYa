package inca.jesus.trajesya.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.zxing.common.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import inca.jesus.trajesya.R;
import inca.jesus.trajesya.data.conexion.VolleySingleton;
import inca.jesus.trajesya.data.modelo.Reserva;
import inca.jesus.trajesya.data.modelo.UbicacionDireccion;
import inca.jesus.trajesya.data.utils.Constantes;


/**
 * Created by Jesus on 01/06/2017.
 */

public class AdapterReservas extends RecyclerView.Adapter<AdapterReservas.ViewHolder> {
    private Context context;
    private List<Reserva> my_Data;
    private RecyclerViewOnItemClickListener2 recyclerViewOnItemClickListener;


    public AdapterReservas(Context context, List<Reserva> my_Data, RecyclerViewOnItemClickListener2
            recyclerViewOnItemClickListener) {
        this.context = context;
        this.my_Data = my_Data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView reservaCodigo;
        public TextView reservaTipo;
        public TextView reservaFecha;
        public TextView reservaHora;
        public TextView reservaEstado;
        public TextView reservaDetalles;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            reservaCodigo=itemView.findViewById(R.id.txtCodigoReserva);
            reservaTipo=itemView.findViewById(R.id.txtReservaTipo);
            reservaFecha=itemView.findViewById(R.id.txtReservaFecha);
            reservaHora = itemView.findViewById(R.id.txtReservaHora);
            reservaEstado = itemView.findViewById(R.id.txtReservaEstado);
            reservaDetalles = itemView.findViewById(R.id.txtReservaDetalles);
        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public AdapterReservas.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_reserva_disponible,parent,false);
        return new AdapterReservas.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(AdapterReservas.ViewHolder holder, final int position) {

        int idReserva=my_Data.get(position).getIdReserva();
        String correlativo=Correlativo(idReserva);
        holder.reservaCodigo.setText(correlativo);
        if(my_Data.get(position).getTipoReserva()==1){
            holder.reservaTipo.setText("NORMAL");
            holder.reservaTipo.setTextColor(context.getResources().getColor( R.color.verde));
        }else{
            holder.reservaTipo.setText("URGENTE");
            holder.reservaTipo.setTextColor(context.getResources().getColor( R.color.rojo));
        }

        holder.reservaFecha.setText("Fecha: "+my_Data.get(position).getFechaEntrega());
        holder.reservaHora.setText("Hora: "+my_Data.get(position).getHoraReserva()+" "+my_Data.get(position).getTiempo());
 
        String estado=my_Data.get(position).getEstadoReserva().getNombreEstado();
        holder.reservaEstado.setText(my_Data.get(position).getEstadoReserva().getNombreEstado());
        switch (estado){
            case "Nueva":
                holder.reservaEstado.setTextColor(context.getResources().getColor( R.color.nueva));
                break;
            case "Asignada":
                holder.reservaEstado.setTextColor(context.getResources().getColor( R.color.asignada));
                break;
            case "Cerrada":
                holder.reservaEstado.setTextColor(context.getResources().getColor( R.color.cerrada));
                break;
            case "Anulada":
                holder.reservaEstado.setTextColor(context.getResources().getColor( R.color.anulada));
                break;
        }

        holder.reservaDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        SpannableString mitextoU = new SpannableString("DETALLE");
        mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
        holder.reservaDetalles.setText(mitextoU);

    }
    @Override
    public int getItemCount() {
        return my_Data.size();
    }

    public String Correlativo(int idReserva){
        String correlativo="";
        int len=String.valueOf(idReserva).length();
        int numCeros=5-len;
        String cero="0";
        String ceros=  repeat(cero,numCeros);
        correlativo="R-"+ceros+idReserva;
        return correlativo;
    }

    public static String repeat(String val, int count){
        StringBuilder buf = new StringBuilder(val.length() * count);
        while (count-- > 0) {
            buf.append(val);
        }
        return buf.toString();
    }
}
