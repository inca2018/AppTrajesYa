package inca.jesus.trajesya.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import inca.jesus.trajesya.adapters.AdapterResena;
import inca.jesus.trajesya.adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.clases.ListResenas;
import inca.jesus.trajesya.clases.ProductoX;
import inca.jesus.trajesya.clases.Resenas;
import inca.jesus.trajesya.R;

public class ItemResenas extends AppCompatActivity {
    LinearLayoutManager linearLayout;
    RecyclerView recycler;
    AdapterResena adapterT;
    TextView total1,total2;
    TextView t5,t4,t3,t2,t1,prom;
    ProgressBar p1,p2,p3,p4,p5;
    ProductoX prod;
    RatingBar rating;
    ImageButton o1,o2,o3,o4,o5;
    List<Resenas> temp=new ArrayList<Resenas>();
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_resenas);
        variables();
        Bundle extras=getIntent().getExtras();
        prod=extras.getParcelable("Producto");
        temp=ListaTemp();
        toolbar_opciones();
        p1.setMax(temp.size());
        p2.setMax(temp.size());
        p3.setMax(temp.size());
        p4.setMax(temp.size());
        p5.setMax(temp.size());

        if(temp.size()==0){
            total1.setText("("+String.valueOf(temp.size())+") RESEÑAS");
            total2.setText(String.valueOf(temp.size()+" calificaciones"));

            t5.setText(String.valueOf(0));
            t4.setText(String.valueOf(0));
            t3.setText(String.valueOf(0));
            t2.setText(String.valueOf(0));
            t1.setText(String.valueOf(0));
            p1.setProgress(0);
            p2.setProgress(0);
            p3.setProgress(0);
            p4.setProgress(0);
            p5.setProgress(0);
            prom.setText("( "+0+" de 5 )");
            rating.setRating(0);
        }else{
            total1.setText("("+String.valueOf(temp.size())+") RESEÑAS");
            total2.setText(String.valueOf(temp.size()+" calificaciones"));
            prom.setText("( "+PromedioTotal()+" de 5 )");
            t5.setText(String.valueOf(Total5()));
            t4.setText(String.valueOf(Total4()));
            t3.setText(String.valueOf(Total3()));
            t2.setText(String.valueOf(Total2()));
            t1.setText(String.valueOf(Total1()));
            p1.setProgress(Total1());
            p2.setProgress(Total2());
            p3.setProgress(Total3());
            p4.setProgress(Total4());
            p5.setProgress(Total5());
            rating.setRating(PromedioTotal());
        }
        linearLayout = new LinearLayoutManager(ItemResenas.this, LinearLayoutManager.VERTICAL,false);
        adapterT = new AdapterResena(ItemResenas.this, temp, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {

            }
        });
        recycler.setAdapter(adapterT);
        recycler.setLayoutManager(linearLayout);

    }
    private void variables() {
        recycler=(RecyclerView)findViewById(R.id.recycler_lista_resenas);
        total1=(TextView)findViewById(R.id.f_total);
        total2=(TextView)findViewById(R.id.f_total2);
        prom=(TextView)findViewById(R.id.promedio);
        t5=(TextView)findViewById(R.id.total_5);
        t4=(TextView)findViewById(R.id.total_4);
        t3=(TextView)findViewById(R.id.total_3);
        t2=(TextView)findViewById(R.id.total_2);
        t1=(TextView)findViewById(R.id.total_1);
        p1=(ProgressBar)findViewById(R.id.firstBar1);
        p2=(ProgressBar)findViewById(R.id.firstBar2);
        p3=(ProgressBar)findViewById(R.id.firstBar3);
        p4=(ProgressBar)findViewById(R.id.firstBar4);
        p5=(ProgressBar)findViewById(R.id.firstBar5);
        rating=(RatingBar)findViewById(R.id.rating_resenass);
        o1=(ImageButton)findViewById(R.id.ope_1);
        o2=(ImageButton)findViewById(R.id.ope_2);
        o3=(ImageButton)findViewById(R.id.ope_3);
        o4=(ImageButton)findViewById(R.id.ope_4);
        o5=(ImageButton)findViewById(R.id.ope_5);
    }
    public int Total5(){
       int contador=0;
       for(int i=0;i<temp.size();i++){
          if(temp.get(i).getRanking_resena()==5.0){
              contador++;
          }
       }
       return contador;
   }
    public int Total4(){
        int contador=0;
        for(int i=0;i<temp.size();i++){
            if(temp.get(i).getRanking_resena()==4.0){
                contador++;
            }
        }
        return contador;
    }
    public int Total3(){
        int contador=0;
        for(int i=0;i<temp.size();i++){
            if(temp.get(i).getRanking_resena()==3.0){
                contador++;
            }
        }
        return contador;
    }
    public int Total2(){
        int contador=0;
        for(int i=0;i<temp.size();i++){
            if(temp.get(i).getRanking_resena()==2.0){
                contador++;
            }
        }
        return contador;
    }
    public int Total1(){
        int contador=0;
        for(int i=0;i<temp.size();i++){
            if(temp.get(i).getRanking_resena()==1.0){
                contador++;
            }
        }
        return contador;
    }
    public float PromedioTotal(){
        float total;
        float suma=0;
        for(int i=0;i<temp.size();i++){
                suma=suma+ temp.get(i).getRanking_resena();

        }
        total=suma/5;
        return total;
    }
    public List<Resenas> ListaTemp(){

        List<Resenas> temp2=new ArrayList<Resenas>();
        if(ListResenas.ALMACEN_RESENAS!=null){
            for(int i=0;i<ListResenas.ALMACEN_RESENAS.size();i++){
                if(ListResenas.ALMACEN_RESENAS.get(i).getProducto().getId()==prod.getId()){
                    temp2.add(ListResenas.ALMACEN_RESENAS.get(i));
                }
            }
            return  temp2;
        }else{

            return null;
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ItemResenas.this,Item.class);
        Bundle extras=getIntent().getExtras();
        intent.putExtra("Producto",extras.getParcelable("Producto"));
        startActivity(intent);
        finish();
    }
    private void toolbar_opciones() {

        o1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemResenas.this,ActivityPrincipal.class);
                intent.putExtra("o","o1");
                startActivity(intent);
            }
        });
        o2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ItemResenas.this,ActivityPrincipal.class);
                intent.putExtra("o","o2");
                startActivity(intent);
            }
        });
        o3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemResenas.this,ActivityPrincipal.class);
                intent.putExtra("o","o3");
                startActivity(intent);
            }
        });
        o4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemResenas.this,ActivityPrincipal.class);
                intent.putExtra("o","o4");
                startActivity(intent);
            }
        });
        o5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemResenas.this,ActivityPrincipal.class);
                intent.putExtra("o","o5");
                startActivity(intent);
            }
        });
    }
}
