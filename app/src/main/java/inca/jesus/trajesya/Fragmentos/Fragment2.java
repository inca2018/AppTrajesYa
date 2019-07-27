package inca.jesus.trajesya.Fragmentos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import inca.jesus.trajesya.Activities.ListaProductos;
import inca.jesus.trajesya.Adapters.AdapterCategoria;
import inca.jesus.trajesya.Adapters.AdapterSegmento;
import inca.jesus.trajesya.Adapters.AdapterSubCategoria;
import inca.jesus.trajesya.Adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.Clases.Segmento_Categorias;
import inca.jesus.trajesya.Clases.Segmento_Productos;
import inca.jesus.trajesya.Clases.Segmento_SubCategorias;
import inca.jesus.trajesya.R;

public class Fragment2 extends Fragment {
    private RecyclerView recycler1;
    private LinearLayoutManager linearLayout1;
    private AdapterSegmento adapter1;
    private RecyclerView recycler3;
    private LinearLayoutManager linearLayout3;
    private AdapterSubCategoria adapter3;
    private RecyclerView recycler2;
    private LinearLayoutManager linearLayout2;
    private AdapterCategoria adapter2;
    int pos_cate=0;
    List<Segmento_Categorias> tempSub;
    List<Segmento_SubCategorias> tempSub2;
    TextView titulo_segmento,titulo_segmento2,titulo_categoria;
    LinearLayout l1,l2,l3;

    public Fragment2() {
        // Required empty public constructor
    }
    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment2, container, false);
        recycler1=(RecyclerView)view.findViewById(R.id.recycler_categorias);
        recycler2=(RecyclerView)view.findViewById(R.id.recycler_categorias2);
        recycler3=(RecyclerView)view.findViewById(R.id.recycler_categorias3);
        titulo_segmento=(TextView)view.findViewById(R.id.titulo_categoria);
        titulo_segmento2=(TextView)view.findViewById(R.id.titulo_categoria2);
        titulo_categoria=(TextView)view.findViewById(R.id.titulo_sub_categoria);
        l1=(LinearLayout)view.findViewById(R.id.linear_categoria);
        l2=(LinearLayout)view.findViewById(R.id.linear_sub_categoria);
        l3=(LinearLayout)view.findViewById(R.id.linear_atras);
        tempSub=new ArrayList<>();
        tempSub2=new ArrayList<>();
        l1.setVisibility(View.VISIBLE);

        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.GONE);
            }
        });


        linearLayout1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        adapter1 = new AdapterSegmento(getActivity(), Segmento_Productos.CATEGORIAs, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {

            }
        });
        recycler1.setAdapter(adapter1);
        recycler1.setLayoutManager(linearLayout1);

        adapter1.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                int dd=adapter1.posSeleccion();
                String nom_Titu=adapter1.RecuNombreSegmento();
                titulo_segmento.setText(nom_Titu);
                titulo_segmento2.setText(nom_Titu);
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.GONE);
                mostra_sub(dd);
            }
        });

        return view;
    }

    @SuppressLint("WrongConstant")
    private void mostra_sub(int id) {
        System.out.println("dentro de sub xxx "+id);
        tempSub=ListarTemporal1(id);
        linearLayout1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        adapter2 = new AdapterCategoria(getActivity(),tempSub, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
            }
        });
        recycler2.setAdapter(adapter2);
        recycler2.setLayoutManager(linearLayout1);



        adapter2.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                int dd=adapter2.posSeleccion();
                mostra_sub_sub(dd);
                String dato=adapter2.RecuNombreSegmento();
                titulo_categoria.setText(dato);

                l1.setVisibility(View.GONE);
                l2.setVisibility(View.VISIBLE);
            }
        });

    }
    @SuppressLint("WrongConstant")
    private void mostra_sub_sub(int dd) {
        tempSub2=ListarTemporal2(dd);
        linearLayout1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        adapter3 = new AdapterSubCategoria(getActivity(),tempSub2 , new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
                   Intent intent=new Intent(getActivity(),ListaProductos.class);
                   startActivity(intent);

            }
        });
        recycler3.setAdapter(adapter3);
        recycler3.setLayoutManager(linearLayout1);

    }

    private List<Segmento_Categorias> ListarTemporal1(int id) {

        System.out.println("dentro de list temp xxx "+id);

        List<Segmento_Categorias> temp2=new ArrayList<Segmento_Categorias>();
        for(int i = 0; i< Segmento_Categorias.SUBCATEGORIA.size(); i++){
            if(Segmento_Categorias.SUBCATEGORIA.get(i).getId_categoria()==id)
                temp2.add(Segmento_Categorias.SUBCATEGORIA.get(i));
        }
        return  temp2;
    }
    private List<Segmento_SubCategorias> ListarTemporal2(int id) {


        List<Segmento_SubCategorias> temp2=new ArrayList<Segmento_SubCategorias>();
        for(int i = 0; i< Segmento_SubCategorias.SUBCATEGORIA_SUB.size(); i++){
            if(Segmento_SubCategorias.SUBCATEGORIA_SUB.get(i).getId_subcategoria()==id)
                temp2.add(Segmento_SubCategorias.SUBCATEGORIA_SUB.get(i));
        }
        return  temp2;
      }
    }


