package inca.jesus.trajesya.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import inca.jesus.trajesya.Adapters.AdapterBaseFiltro;
import inca.jesus.trajesya.Adapters.AdapterFiltro;
import inca.jesus.trajesya.Adapters.AdapterItemFiltro;
import inca.jesus.trajesya.Adapters.AdapterSubFiltro;
import inca.jesus.trajesya.Adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.Clases.Filtro;
import inca.jesus.trajesya.Clases.ProductoX;
import inca.jesus.trajesya.Clases.SubFiltro;
import inca.jesus.trajesya.R;

public class ListaProductos extends AppCompatActivity {

    ImageView lupa;
    androidx.appcompat.widget.SearchView buscador;
    LinearLayout linear;
    RecyclerView filtrado,filtro,sub_filtro;
    FloatingActionButton boton_filtro;
    LinearLayout l1,l2;
    ImageView boton_Cerrar;
    List<SubFiltro> temp;
    TextView vaciar_filtros;
    LinearLayout linear_botonera;
    Button btn_limpiar,btn_aplicar;
    RecyclerView item_filtros;

    LinearLayoutManager linear1;
    AdapterFiltro adapter;
    AdapterSubFiltro adapter2;
    AdapterItemFiltro adapter3;
    AdapterBaseFiltro adapter4;
    int pos_filtro;


    RecyclerView recycler_filtro;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);
        recycler_filtro=(RecyclerView)findViewById(R.id.filtro_recycler10);

        lupa=(ImageView)findViewById(R.id.filtro_lupa);
        buscador=(SearchView)findViewById(R.id.filtro_busqueda);
        linear=(LinearLayout)findViewById(R.id.filtro_linear);
        linear_botonera=(LinearLayout)findViewById(R.id.botonera);
        btn_aplicar=(Button)findViewById(R.id.boton_aplicar);
        btn_limpiar=(Button)findViewById(R.id.boton_limpiar);
        item_filtros=(RecyclerView)findViewById(R.id.recycler_item_filtros);
        buscador.setVisibility(View.GONE);
        linear.setVisibility(View.VISIBLE);
        linear_botonera.setVisibility(View.GONE);
        temp=new ArrayList<>();
        vaciar_filtros=(TextView)findViewById(R.id.text_vaciar_filtros);
        filtro=(RecyclerView)findViewById(R.id.filtro_filtros);
        sub_filtro=(RecyclerView)findViewById(R.id.filtro_sub_filtros);
        //filtrado=(RecyclerView)findViewById(R.id.filtro_recycler);
        boton_filtro=(FloatingActionButton)findViewById(R.id.boton_filtro);
        l1=(LinearLayout)findViewById(R.id.linear_filtro1);
        l2=(LinearLayout)findViewById(R.id.linear_filtro2);
        boton_Cerrar=(ImageView)findViewById(R.id.boton_cerrar_filtro);

        botones_movimiento();
        toolbasBuscador();


        linear1 = new LinearLayoutManager(ListaProductos.this, LinearLayoutManager.VERTICAL,false);
        adapter4 = new AdapterBaseFiltro(ListaProductos.this,ProductoX.CELULAR, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
            }
        });

        recycler_filtro.setAdapter(adapter4);
        recycler_filtro.setLayoutManager(linear1);

        linear1 = new LinearLayoutManager(ListaProductos.this, LinearLayoutManager.HORIZONTAL,false);
        adapter3 = new AdapterItemFiltro(ListaProductos.this,SubFiltro.ITEM_SUB_FILTRO, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {

            }
        });

        item_filtros.setAdapter(adapter3);
        item_filtros.setLayoutManager(linear1);

            adapter3.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    if(adapter3.getItemCount()==0){
                        item_filtros.setVisibility(View.GONE);
                    }else{
                        item_filtros.setVisibility(View.VISIBLE);
                    }
                }
            });

        linear1 = new LinearLayoutManager(ListaProductos.this, LinearLayoutManager.VERTICAL,false);
        adapter = new AdapterFiltro(ListaProductos.this, Filtro.LISTA_FILTRO, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
            }
          });
        filtro.setAdapter(adapter);
        filtro.setLayoutManager(linear1);

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                pos_filtro=adapter.posSeleccion();
                mostrar_sub(pos_filtro);
            }
        });



    }

    @SuppressLint("WrongConstant")
    private void mostrar_sub(int i) {
           temp=ListarTemporal1(i);

        linear1 = new LinearLayoutManager(ListaProductos.this, LinearLayoutManager.VERTICAL,false);
        adapter2 = new AdapterSubFiltro(ListaProductos.this, temp, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
            }
        });
        sub_filtro.setAdapter(adapter2);
        sub_filtro.setLayoutManager(linear1);

        adapter2.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                adapter.validar(pos_filtro);


               vaciar_filtros.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       adapter2.VaciarData(pos_filtro);
                       adapter.validar(pos_filtro);
                       adapter.notifyDataSetChanged();
                   }
               });
                btn_limpiar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter2.Vaciar_todo();
                        adapter.Vaciar_todo();
                        SubFiltro.ITEM_SUB_FILTRO.clear();
                        adapter3.notifyDataSetChanged();
                    }
                });
                btn_aplicar.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onClick(View v) {

                        adapter2.recuListaPosiciones();
                        l1.setVisibility(View.VISIBLE);
                        l2.setVisibility(View.GONE);
                        linear_botonera.setVisibility(View.GONE);
                        boton_filtro.setVisibility(View.VISIBLE);
                        adapter3.notifyDataSetChanged();
                    }
                });

            }
        });
    }
    private void botones_movimiento() {

        boton_Cerrar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                boton_filtro.setVisibility(View.VISIBLE);
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.GONE);
                linear_botonera.setVisibility(View.GONE);
            }
        });

        boton_filtro.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                boton_filtro.setVisibility(View.GONE);
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.VISIBLE);
                linear_botonera.setVisibility(View.VISIBLE);
            }
        });

    }
    private void toolbasBuscador() {
        buscador.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                buscador.setVisibility(View.GONE);
                linear.setVisibility(View.VISIBLE);
                return false;
            }
        });
        lupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear.setVisibility(View.GONE);
                buscador.setVisibility(View.VISIBLE);
                buscador.setIconified(false);
            }
        });

        buscador.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.trim().isEmpty()) {
                    buscador.setVisibility(View.GONE);
                    linear.setVisibility(View.VISIBLE);
                    return false;
                }

                return false;
            }
        });
    }
    private List<SubFiltro> ListarTemporal1(int id) {

        List<SubFiltro> temp2=new ArrayList<SubFiltro>();
        for(int i = 0; i< SubFiltro.LIST_SUB_FILTRO.size(); i++){
            if(SubFiltro.LIST_SUB_FILTRO.get(i).getId_filtro()==id)
                temp2.add(SubFiltro.LIST_SUB_FILTRO.get(i));
        }
        return  temp2;
    }
}
