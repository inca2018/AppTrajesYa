package inca.jesus.trajesya.Fragmentos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import inca.jesus.trajesya.Adapters.AdapterSubCategoriasDisponibles;
import inca.jesus.trajesya.Adapters.AdapterCategoriasDisponibles;
import inca.jesus.trajesya.Adapters.AdapterProductosDisponibles;
import inca.jesus.trajesya.Adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.Data.Modelo.Producto;
import inca.jesus.trajesya.Clases.Segmento_Categorias;
import inca.jesus.trajesya.Clases.Segmento_SubCategorias;
import inca.jesus.trajesya.Data.Conexion.VolleySingleton;
import inca.jesus.trajesya.Data.Modelo.Categoria;
import inca.jesus.trajesya.Data.Modelo.Estado;
import inca.jesus.trajesya.Data.Modelo.Grupo;
import inca.jesus.trajesya.Data.Modelo.SubCategoria;
import inca.jesus.trajesya.Data.Modelo.UnidadTerritorial;
import inca.jesus.trajesya.Data.Utils.Constantes;
import inca.jesus.trajesya.R;

public class Fragment2 extends Fragment {
    private RecyclerView recyclerCategorias;
    private LinearLayoutManager linearLayout;
    private AdapterCategoriasDisponibles adapterCategoria;
    private RecyclerView recyclerProducto;
    private AdapterProductosDisponibles adapterProducto;
    private RecyclerView recyclerSubCategorias;
    private AdapterSubCategoriasDisponibles adapterSubCategoria;
    List<Segmento_Categorias> tempSub;
    List<Segmento_SubCategorias> tempSub2;
    TextView titulo_segmento,titulo_segmento2,titulo_categoria;
    LinearLayout panelSubCategoria, panelProductos,l3;

    /* Listados */
    List<Categoria> ListaCategoria;
    List<SubCategoria> ListaSubCategoria;
    List<Producto> ListaProducto;
    public Context context;

    public Fragment2() {
        // Required empty public constructor
    }
    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment2, container, false);
        recyclerCategorias =view.findViewById(R.id.recycler_categorias);
        recyclerSubCategorias =view.findViewById(R.id.recycler_categorias2);
        recyclerProducto =view.findViewById(R.id.recycler_categorias3);
        titulo_segmento=view.findViewById(R.id.titulo_categoria);
        titulo_segmento2=view.findViewById(R.id.titulo_categoria2);
        titulo_categoria=view.findViewById(R.id.titulo_sub_categoria);
        panelSubCategoria =view.findViewById(R.id.linear_categoria);
        panelProductos =view.findViewById(R.id.linear_sub_categoria);
        l3=view.findViewById(R.id.linear_atras);
        tempSub=new ArrayList<>();
        tempSub2=new ArrayList<>();
        panelSubCategoria.setVisibility(View.VISIBLE);

        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panelSubCategoria.setVisibility(View.VISIBLE);
                panelProductos.setVisibility(View.GONE);
            }
        });

        /*----------Listados--------------------*/
        context= getActivity();
        ListaCategoria=new ArrayList<>();
        ListaSubCategoria=new ArrayList<>();
        ListaProducto=new ArrayList<>();



        /*----------ArmarRecycler Categorias-----------------*/
        linearLayout = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
        adapterCategoria = new AdapterCategoriasDisponibles(context, Constantes.Base_Categorias_Todo, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
                //not required
            }
        });
        recyclerCategorias.setAdapter(adapterCategoria);
        recyclerCategorias.setLayoutManager(linearLayout);


        /*----------Listar Categorias-----------------*/
        //ListarCategoriasDisponibles(context);



        /*----------Verificar Cambios en Lista Categorias-----------------*/
        adapterCategoria.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                /*-------Setear Valores -----------*/
                int idCategoria= adapterCategoria.RecuperarIdCategoria();
                String nom_Titu= adapterCategoria.RecuperarNombreCategoria();
                titulo_segmento.setText(nom_Titu);
                titulo_segmento2.setText(nom_Titu);

                Log.e("Inca","Ingreso Adapter Categoria");
                /*----------Limpiar Lista SubCategorias-----------------*/
                ListaSubCategoria.clear();
                ListaSubCategoria=RecuperarSubCategorias(idCategoria);
                Log.e("Inca","IdCategoria Enviado:"+idCategoria);
                if(idCategoria!=0){
                    panelSubCategoria.setVisibility(View.VISIBLE);
                    panelProductos.setVisibility(View.GONE);
                    /*----------ArmarRecycler SubCategorias-----------------*/
                    linearLayout = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
                    adapterSubCategoria = new AdapterSubCategoriasDisponibles(context,ListaSubCategoria, new RecyclerViewOnItemClickListener2() {
                        @Override
                        public void onClick(View v, int position) {
                            //not required
                        }
                    });
                    recyclerSubCategorias.setAdapter(adapterSubCategoria);
                    recyclerSubCategorias.setLayoutManager(linearLayout);

                    /*----------Listar SubCategorias-----------------*/
                   // ListarSubCategoriasDisponibles(context,idCategoria);


                    /*----------Verificar Cambios en Lista SubCategorias-----------------*/
                    adapterSubCategoria.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                        @Override
                        public void onChanged() {
                            super.onChanged();
                            int idSubCategoria= adapterSubCategoria.posSeleccion();
                            //mostra_sub_sub(dd);
                            String dato= adapterSubCategoria.RecuperarNombreSubCategoria();
                            titulo_categoria.setText(dato);


                            Log.e("Inca","Ingreso Adapter SubCategoria");
                            /*----------Limpiar Lista Productos-----------------*/
                            ListaProducto.clear();
                            ListaProducto=RecuperarProductos(idSubCategoria);
                            if(idSubCategoria!=0){
                                panelSubCategoria.setVisibility(View.GONE);
                                panelProductos.setVisibility(View.VISIBLE);
                                /*----------ArmarRecycler Productos-----------------*/
                                linearLayout = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
                                adapterProducto = new AdapterProductosDisponibles(context,ListaProducto , new RecyclerViewOnItemClickListener2() {
                                    @Override
                                    public void onClick(View v, int position) {
                                        //Intent intent=new Intent(getActivity(),ListaProductos.class);
                                        //startActivity(intent);
                                        //not Required
                                    }
                                });
                                recyclerProducto.setAdapter(adapterProducto);
                                recyclerProducto.setLayoutManager(linearLayout);

                                /*----------Listar Productos-----------------*/
                                //ListarProductosDisponibles(context,idSubCategoria);
                            }

                        }
                    });
                }
            }
        });


        return view;
    }

    private List<Producto> RecuperarProductos(int idSubCategoria) {
        List<Producto> temp=new ArrayList<>();

        for(int i=0;i<Constantes.Base_Producto_Todo.size();i++){
            if(Constantes.Base_Producto_Todo.get(i).getSubCategoriaProducto().getIdSubCategoria()==idSubCategoria){
                temp.add(Constantes.Base_Producto_Todo.get(i));
            }
        }
        return temp;
    }

    private List<SubCategoria> RecuperarSubCategorias(int idCategoria) {
        List<SubCategoria> temp=new ArrayList<>();

        for(int i=0;i<Constantes.Base_SubCategorias_Todo.size();i++){
            if(Constantes.Base_SubCategorias_Todo.get(i).getCategoriaSubCategoria().getIdCategoria()==idCategoria){
                 temp.add(Constantes.Base_SubCategorias_Todo.get(i));
            }
        }
        return temp;
    }

    }


