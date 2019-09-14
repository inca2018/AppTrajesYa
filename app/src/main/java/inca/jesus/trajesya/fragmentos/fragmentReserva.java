package inca.jesus.trajesya.fragmentos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;


import inca.jesus.trajesya.activities.ActivityPrincipal;
import inca.jesus.trajesya.adapters.AdapterItemProductosMini;
import inca.jesus.trajesya.adapters.AdapterItemReserva;
import inca.jesus.trajesya.adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.clases.ListCarrito;
import inca.jesus.trajesya.R;
import inca.jesus.trajesya.data.utils.Constantes;

public class fragmentReserva extends Fragment {
    private RecyclerView recycler1ItemReserva,recycler2;
    private LinearLayoutManager linearLayout1,linearLayout2;
    private AdapterItemReserva adapterItemReserva;
    private AdapterItemProductosMini adapterPromocion;
    public LinearLayout sectorListaVacia, accionBotonReservar, sectorAccionSeguirComprando;
    TextView precio_total;
    Button btn_seguir,btn_compra;
    Context context;

    public fragmentReserva() {
    }

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_reserva, container, false);
        context=getActivity();
        recycler1ItemReserva =view.findViewById(R.id.carrito_recycler);
        sectorListaVacia =view.findViewById(R.id.loc1);
        accionBotonReservar =view.findViewById(R.id.carrito_re2);
        sectorAccionSeguirComprando =view.findViewById(R.id.loc2);
        btn_seguir=view.findViewById(R.id.boton_final2);
        btn_compra=view.findViewById(R.id.boton_final1);
        recycler2=view.findViewById(R.id.recyclerOfertas);
        precio_total=view.findViewById(R.id.total_precio);
        DecimalFormat formateador = new DecimalFormat("###,###.##");
        precio_total.setText("S/."+formateador.format(getTotal()));
        ofertas();
        boton_seguir();
        boton_comprar();
        verificarItemReservas();
        return view;
    }
    @SuppressLint("WrongConstant")
    private void verificarItemReservas() {

        if(Constantes.RESERVA_ITEMS.size()==0){
            accionBotonReservar.setVisibility(View.GONE);
            recycler1ItemReserva.setVisibility(View.GONE);
            sectorListaVacia.setVisibility(View.VISIBLE);
            sectorAccionSeguirComprando.setVisibility(View.VISIBLE);
        }else{

            linearLayout1 = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
            adapterItemReserva = new AdapterItemReserva(getActivity(), Constantes.RESERVA_ITEMS, new RecyclerViewOnItemClickListener2() {
                @Override
                public void onClick(View v, int position) {
                    //not required
                }
            });
            recycler1ItemReserva.setAdapter(adapterItemReserva);
            recycler1ItemReserva.setLayoutManager(linearLayout1);

            accionBotonReservar.setVisibility(View.VISIBLE);
            recycler1ItemReserva.setVisibility(View.VISIBLE);
            sectorListaVacia.setVisibility(View.GONE);
            sectorAccionSeguirComprando.setVisibility(View.GONE);

            MostrarTotal();
            adapterItemReserva.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    adapterItemReserva.GenerarTotales();
                    if(adapterItemReserva.getItemCount()==0){
                        accionBotonReservar.setVisibility(View.GONE);
                        recycler1ItemReserva.setVisibility(View.GONE);
                        sectorListaVacia.setVisibility(View.VISIBLE);
                        sectorAccionSeguirComprando.setVisibility(View.VISIBLE);
                    }else{
                        MostrarTotal();
                    }
                }
            });
        }

    }
    public void MostrarTotal(){
        adapterItemReserva.GenerarTotales();
        double totalRecuperado= adapterItemReserva.TotalAcumulado();
        DecimalFormat formateador = new DecimalFormat("###,###.00");
        if(totalRecuperado>0){
            precio_total.setText("S/."+formateador.format(totalRecuperado));
        }else{
            precio_total.setText("S/. 0.00");
        }

    }
    private void boton_comprar() {
    btn_compra.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*Intent intent = new Intent(getActivity(),CompraActivity.class);
            startActivity(intent);*/
            ((ActivityPrincipal)context).opcionEnvioReserva();
        }
    });
    }
    private void boton_seguir() {
    btn_seguir.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((ActivityPrincipal)context).opcionInicio();
        }
    });
    }
    private void ofertas() {
        linearLayout2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        adapterPromocion = new AdapterItemProductosMini(getActivity(),Constantes.Base_ListaProductoPromociones, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
            }
        });
        recycler2.setAdapter(adapterPromocion);
        recycler2.setLayoutManager(linearLayout2);
    }
    public double getTotal(){
        Double total=0.0;
        if(ListCarrito.CARRITO_LISTA.size()==0){
            return total;
        }else{
            for(int i=0;i<ListCarrito.CARRITO_LISTA.size();i++){
                total=total+ListCarrito.CARRITO_LISTA.get(i).getTotal();
            }
        }
        return total;
    }


}
