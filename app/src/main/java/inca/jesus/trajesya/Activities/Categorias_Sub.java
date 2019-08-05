package inca.jesus.trajesya.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import inca.jesus.trajesya.Adapters.AdapterSubCategoriasDisponibles;
import inca.jesus.trajesya.Clases.Segmento_Categorias;
import inca.jesus.trajesya.Clases.Segmento_Productos;
import inca.jesus.trajesya.R;

public class Categorias_Sub extends AppCompatActivity {

     private RecyclerView recycler2;
    private LinearLayoutManager linearLayout1;
    private AdapterSubCategoriasDisponibles adapter2;
    Segmento_Productos cate;
    List<Segmento_Categorias> tempSub;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias__sub);
        recycler2=(RecyclerView)findViewById(R.id.recycler_subcategorias);
        tempSub=new ArrayList<>();
        Bundle extras=getIntent().getExtras();
        cate = extras.getParcelable("Categoria");
        System.out.println("Dato Categoria:"+cate.getNombre());
        tempSub=ListarTemporal1();



        /*linearLayout1 = new LinearLayoutManager(Categorias_Sub.this, LinearLayoutManager.VERTICAL,false);
        adapter2 = new AdapterSubCategoriasDisponibles(Categorias_Sub.this,tempSub, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {

            }
        });
        recycler2.setAdapter(adapter2);
        recycler2.setLayoutManager(linearLayout1);*/


    }

    private List<Segmento_Categorias> ListarTemporal1() {

        List<Segmento_Categorias> temp2=new ArrayList<Segmento_Categorias>();
        for(int i = 0; i< Segmento_Categorias.SUBCATEGORIA.size(); i++){
            if(Segmento_Categorias.SUBCATEGORIA.get(i).getId_categoria()==cate.getId())
                temp2.add(temp2.get(i));
            System.out.println("Dato Sub for:"+ temp2.get(i).getNombre());
        }
        return  temp2;
    }
}
