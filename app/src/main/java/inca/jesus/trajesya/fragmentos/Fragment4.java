package inca.jesus.trajesya.fragmentos;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import inca.jesus.trajesya.activities.CompraActivity;
import inca.jesus.trajesya.activities.Item;
import inca.jesus.trajesya.adapters.Adapter3;
import inca.jesus.trajesya.adapters.AdapterItemCarrito;
import inca.jesus.trajesya.adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.clases.ListCarrito;
import inca.jesus.trajesya.clases.ProductoX;
import inca.jesus.trajesya.R;

public class Fragment4 extends Fragment {
    private RecyclerView recycler1,recycler2;
    private LinearLayoutManager linearLayout1,linearLayout2;
    private AdapterItemCarrito adapterT;
    private Adapter3 adapter;
    public LinearLayout l1,l2,l3;
    TextView precio_total;
    Button btn_seguir,btn_compra;

    public Fragment4() {
    }

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment4, container, false);
        recycler1=(RecyclerView)view.findViewById(R.id.carrito_recycler);
        l1=(LinearLayout)view.findViewById(R.id.loc1);
        l2=(LinearLayout)view.findViewById(R.id.carrito_re2);
        l3=(LinearLayout)view.findViewById(R.id.loc2);

        btn_seguir=(Button)view.findViewById(R.id.boton_final2);
        btn_compra=(Button) view.findViewById(R.id.boton_final1);

        recycler2=(RecyclerView)view.findViewById(R.id.recyclerOfertas);
        precio_total=(TextView)view.findViewById(R.id.total_precio);

        DecimalFormat formateador = new DecimalFormat("###,###.##");
        precio_total.setText("S/."+formateador.format(getTotal()));

        ofertas();

        boton_seguir();
        boton_comprar();
        if(ListCarrito.CARRITO_LISTA.size()!=0){

            linearLayout1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
            adapterT = new AdapterItemCarrito(getActivity(), ListCarrito.CARRITO_LISTA, new RecyclerViewOnItemClickListener2() {
                @Override
                public void onClick(View v, int position) {
                    Intent intent = new Intent(getActivity(),Item.class);
                    intent.putExtra("Producto",ListCarrito.CARRITO_LISTA.get(position).getP());
                    startActivity(intent);
                }
            });
            recycler1.setAdapter(adapterT);
            recycler1.setLayoutManager(linearLayout1);

            l2.setVisibility(View.VISIBLE);
            recycler1.setVisibility(View.VISIBLE);

            l1.setVisibility(View.GONE);
            l3.setVisibility(View.GONE);

            adapterT.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    if(adapterT.getItemCount()==0){
                        l2.setVisibility(View.GONE);
                        recycler1.setVisibility(View.GONE);

                        l1.setVisibility(View.VISIBLE);
                        l3.setVisibility(View.VISIBLE);
                    }else{
                        String total=adapterT.TotalAcumulado();
                        Double dd=Double.parseDouble(total);
                        DecimalFormat formateador = new DecimalFormat("###,###.##");
                        precio_total.setText("S/."+formateador.format(dd));
                    }
                }
            });
            }else{
            l2.setVisibility(View.GONE);
            recycler1.setVisibility(View.GONE);

            l1.setVisibility(View.VISIBLE);
            l3.setVisibility(View.VISIBLE);

        }
        return view;
    }

    private void boton_comprar() {
    btn_compra.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(),CompraActivity.class);
            startActivity(intent);
        }
    });
    }
    private void boton_seguir() {

    btn_seguir.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), ActivityPrincipal.class);
            intent.putExtra("o","o1");
            startActivity(intent);
        }
    });
    }
    private void ofertas() {
        linearLayout2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        adapter = new Adapter3(getActivity(),ProductoX.BLUSAx5, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getActivity(),Item.class);
                intent.putExtra("Producto",ProductoX.BLUSAx5.get(position));
                startActivity(intent);
            }
        });
        recycler2.setAdapter(adapter);
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
