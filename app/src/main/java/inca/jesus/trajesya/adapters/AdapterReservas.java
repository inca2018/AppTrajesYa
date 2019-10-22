package inca.jesus.trajesya.adapters;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import inca.jesus.trajesya.R;
import inca.jesus.trajesya.data.modelo.Reserva;


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
        public TextView cantidadReserva;
        public TextView distritoReserva;
        public TextView direccionReserva;
        public TextView totalPago;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            reservaCodigo=itemView.findViewById(R.id.txtCodigoReserva);

            reservaFecha=itemView.findViewById(R.id.txtFechaReserva);
            reservaHora = itemView.findViewById(R.id.txtHoraReserva);
            reservaEstado = itemView.findViewById(R.id.txtEstadoReserva);
            cantidadReserva = itemView.findViewById(R.id.txtCantidadTrajes);
            distritoReserva= itemView.findViewById(R.id.txtDistritoReserva);
            direccionReserva= itemView.findViewById(R.id.txtDireccionReserva);
            totalPago= itemView.findViewById(R.id.txtTotalReserva);

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



        holder.reservaFecha.setText(my_Data.get(position).getFechaEntrega());
        holder.reservaHora.setText(my_Data.get(position).getHoraReserva()+" "+my_Data.get(position).getTiempo());

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
        holder.distritoReserva.setText(my_Data.get(position).getUbicacionDireccionReserva().getDistrito().getNombreUnidadTerritorial());
        holder.direccionReserva.setText(my_Data.get(position).getUbicacionDireccionReserva().getDireccionEntrega());

        int cantidad=0;
        if(my_Data.get(position)!=null){
            if(my_Data.get(position).getListaItems()!=null){
                cantidad=my_Data.get(position).getListaItems().size();
                holder.cantidadReserva.setText(""+cantidad);
            }else{
                holder.cantidadReserva.setText("0");
            }
        }else{
            holder.cantidadReserva.setText("0");
        }


        double total=0;
        if(my_Data.get(position).getTipoReserva()==1){
             total=
                    ((my_Data.get(position).getTotalBase()+
                            my_Data.get(position).getTotalDelivery())
                            -my_Data.get(position).getTotalDescuento());
        }else{
             total=
                     ((my_Data.get(position).getTotalUrgencia()+
                             my_Data.get(position).getTotalDelivery())
                             -my_Data.get(position).getTotalDescuento());
        }


        DecimalFormat formateador = new DecimalFormat("###,###.00");

        if(total==0){
            holder.totalPago.setText("S/ 0.00");
        }else{

            String valor=formateador.format(total);
            holder.totalPago.setText("S/ "+valor);
        }


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
