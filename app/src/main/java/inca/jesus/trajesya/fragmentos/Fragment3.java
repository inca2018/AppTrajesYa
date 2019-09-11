package inca.jesus.trajesya.fragmentos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import inca.jesus.trajesya.adapters.AdapterItemFavorito;
import inca.jesus.trajesya.adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.clases.ItemFavorito;
import inca.jesus.trajesya.R;

public class Fragment3 extends Fragment {
    private RecyclerView recycler1;
    private LinearLayoutManager linearLayout1;
    private AdapterItemFavorito adapterT;
    public LinearLayout mensaje;


    public Fragment3() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment3, container, false);
        recycler1=(RecyclerView)view.findViewById(R.id.recycler_favoritos);
        mensaje=(LinearLayout)view.findViewById(R.id.linear_favorito);

        if(ItemFavorito.ListaFavoritos.size()!=0){

          /*  linearLayout1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
            adapterT = new AdapterItemFavorito(getActivity(),ItemFavorito.ListaFavoritos, new RecyclerViewOnItemClickListener2() {
                @Override
                public void onClick(View v, int position) {
                    Intent intent = new Intent(getActivity(),Item.class);
                    intent.putExtra("Producto",ItemFavorito.ListaFavoritos.get(position).getP());
                    startActivity(intent);
                }
            });
            recycler1.setAdapter(adapterT);
            recycler1.setLayoutManager(linearLayout1);
            mensaje.setVisibility(View.GONE);

            adapterT.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();

                    if(adapterT.getItemCount()==0){
                        mensaje.setVisibility(View.VISIBLE);
                        recycler1.setVisibility(View.GONE);
                    }
                }
            });*/
        }else{
            mensaje.setVisibility(View.VISIBLE);
        }

        return view;
    }


}
