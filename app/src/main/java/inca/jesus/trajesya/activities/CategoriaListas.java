package inca.jesus.trajesya.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import inca.jesus.trajesya.adapters.AdapterBaseFiltro;
import inca.jesus.trajesya.adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.clases.ProductoX;
import inca.jesus.trajesya.clases.Segmento_Productos;
import inca.jesus.trajesya.R;


public class CategoriaListas extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.LayoutManager linearLayout;
    private AdapterBaseFiltro adapter;
    ImageButton o1,o2,o3,o4,o5;
    Segmento_Productos cat;

    List<ProductoX> temp=new ArrayList<ProductoX>();
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_listas);
        recycler=(RecyclerView)findViewById(R.id.recycler_lista_cat);
        o1=(ImageButton)findViewById(R.id.ope_1);
        o2=(ImageButton)findViewById(R.id.ope_2);
        o3=(ImageButton)findViewById(R.id.ope_3);
        o4=(ImageButton)findViewById(R.id.ope_4);
        o5=(ImageButton)findViewById(R.id.ope_5);

        Bundle extras=getIntent().getExtras();
        cat=extras.getParcelable("Categoria");

        toolbar_opciones();

        temp=ListaTemp();

        linearLayout =  new LinearLayoutManager(CategoriaListas.this, LinearLayoutManager.VERTICAL,false);
        adapter = new AdapterBaseFiltro(CategoriaListas.this,temp, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(CategoriaListas.this,Item.class);
                intent.putExtra("Producto",temp.get(position));
                startActivity(intent);
            }
        });
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(linearLayout);
    }

    public List<ProductoX> ListaTemp(){

        List<ProductoX> temp2=new ArrayList<ProductoX>();
        if(ProductoX.TODO!=null){
            for(int i=0;i<ProductoX.TODO.size();i++){
                if(ProductoX.TODO.get(i).getCategoria()==cat.getId()){
                    temp2.add(ProductoX.TODO.get(i));
                }
            }
            return  temp2;
        }else{
            return null;
        }
    }
    private void toolbar_opciones() {

        o1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoriaListas.this,ActivityPrincipal.class);
                intent.putExtra("o","o1");
                startActivity(intent);
            }
        });
        o2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CategoriaListas.this,ActivityPrincipal.class);
                intent.putExtra("o","o2");
                startActivity(intent);
            }
        });
        o3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoriaListas.this,ActivityPrincipal.class);
                intent.putExtra("o","o3");
                startActivity(intent);
            }
        });
        o4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoriaListas.this,ActivityPrincipal.class);
                intent.putExtra("o","o4");
                startActivity(intent);
            }
        });
        o5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoriaListas.this,ActivityPrincipal.class);
                intent.putExtra("o","o5");
                startActivity(intent);
            }
        });
    }

}
