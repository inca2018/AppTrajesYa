package inca.jesus.trajesya.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import inca.jesus.trajesya.Adapters.Adapter3;
import inca.jesus.trajesya.Adapters.AdapterColores;
import inca.jesus.trajesya.Adapters.AdapterItemProductos;
import inca.jesus.trajesya.Adapters.AdapterMedida;
import inca.jesus.trajesya.Adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.Clases.CarouselView;
import inca.jesus.trajesya.Clases.ItemCarrito;
import inca.jesus.trajesya.Clases.ItemCompra;
import inca.jesus.trajesya.Clases.ItemFavorito;
import inca.jesus.trajesya.Clases.ProductoX;
import inca.jesus.trajesya.Clases.Resenas;
import inca.jesus.trajesya.Clases.Sesion;
import inca.jesus.trajesya.Data.Conexion.VolleySingleton;
import inca.jesus.trajesya.Data.Modelo.Galeria;
import inca.jesus.trajesya.Data.Modelo.Medida;
import inca.jesus.trajesya.Data.Modelo.Producto;
import inca.jesus.trajesya.Data.Utils.Constantes;
import inca.jesus.trajesya.R;

public class Item extends AppCompatActivity {


    /*----------Variables Nuevas------------*/
    int idProductoRecuperado=0;
    Producto ProductoSeleccionado;

    private AdapterItemProductos adapterProductosSimilares;
    private AdapterItemProductos adapterProductosSimilares2;

    public RecyclerView recycler_similares1;
    public RecyclerView recycler_similares2;
    public RecyclerView recycler_medidas;

    CarouselView carruselProducto;
    AlertDialog imagenVista;
    String[] Rutas;
    List<Galeria> ListaGalerias;
    TextView nombreProducto;
    TextView verificadoProducto;
    TextView precioProducto;
    TextView descripcioProducto;
    TextView categoriaSimiliar;
    TextView subcategoriaSimilar;
    TextView tituloTalla;

    ScrollView scroll;

    public Button agregarCarrito;
    private AdapterMedida adapter3;
    private LinearLayoutManager linearLayout;
    public String mensaje="";
    public boolean resp;
    public Button mas,menos;
    public TextView cantidad;
    public int cont2=1;
    Context context;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        context=getApplicationContext();

        scroll=findViewById(R.id.scroll_Item);
        nombreProducto=findViewById(R.id.txtNombreProducto);
        verificadoProducto=findViewById(R.id.txtVerificadoPor);
        carruselProducto = findViewById(R.id.pictureplay);
        precioProducto=findViewById(R.id.txtPrecioProducto);
        descripcioProducto=findViewById(R.id.txtDescripcionProducto);
        recycler_similares1=findViewById(R.id.recycler_similares1);
        recycler_similares2=findViewById(R.id.recycler_similares2);
        recycler_medidas=findViewById(R.id.recycler_medidas);
        agregarCarrito=findViewById(R.id.item_boton_add_carrito);
        tituloTalla=findViewById(R.id.tituloTalla);
        mas=findViewById(R.id.mas);
        menos=findViewById(R.id.menos);
        cantidad=findViewById(R.id.cantidad);


        Bundle extras=getIntent().getExtras();
        idProductoRecuperado=extras.getInt("idProducto");

        ProductoSeleccionado=BuscarProductoSeleccionado(idProductoRecuperado);

        GenerarCarrusel(ProductoSeleccionado);

        /*------------- Seteando Datos-----------*/
        nombreProducto.setText(ProductoSeleccionado.getNombreProducto());
        verificadoProducto.setText(ProductoSeleccionado.getVerificadoProducto());
        precioProducto.setText("Alquiler: S/."+ProductoSeleccionado.getPrecioAlquiler());
        descripcioProducto.setText(ProductoSeleccionado.getDescripcionProducto());

        GenerarTamanos(ProductoSeleccionado);

        MostrarProductosSimilares(ProductoSeleccionado);
        MostrarProductosSimilares2(ProductoSeleccionado);

        RecuperarCategoria(ProductoSeleccionado);
        RecuperarSubCategoria(ProductoSeleccionado);


        Boton_Agregar_Carrito();
        Boton_Comprar();


         cantidad.setText(String.valueOf(cont2));
         mas.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 int tem=Integer.parseInt(cantidad.getText().toString());
                 if(tem<10){
                     cont2=cont2+1;
                     cantidad.setText(String.valueOf(cont2));
                 }else{
                     Toast.makeText(Item.this, "Stock no Disponible.", Toast.LENGTH_SHORT).show();
                 }
             }
         });
        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 int tem=Integer.parseInt(cantidad.getText().toString());
                 if(tem!=0){
                     cont2=cont2-1;
                     cantidad.setText(String.valueOf(cont2));
                 }
            }
        });

       MenuAcciones();

    }


    private void RecuperarSubCategoria(Producto productoSeleccionado) {
        subcategoriaSimilar=findViewById(R.id.txtNombreSubCategoria);
        String Temp="";
        for(int i=0;i<Constantes.Base_SubCategorias_Todo.size();i++){
            if(Constantes.Base_SubCategorias_Todo.get(i).getIdSubCategoria()==productoSeleccionado.getSubCategoriaProducto().getIdSubCategoria()){
                Temp=Constantes.Base_SubCategorias_Todo.get(i).getNombreSubCategoria();
            }
        }
        subcategoriaSimilar.setText("Más de "+Temp);
    }
    private void RecuperarCategoria(Producto productoSeleccionado) {

        categoriaSimiliar=findViewById(R.id.txtNombreCategoria);
        String Temp="";
        for(int i=0;i<Constantes.Base_Categorias_Todo.size();i++){
            if(Constantes.Base_Categorias_Todo.get(i).getIdCategoria()==productoSeleccionado.getCategoriaProducto().getIdCategoria()){
                Temp=Constantes.Base_Categorias_Todo.get(i).getNombreCategoria();
            }
        }
        categoriaSimiliar.setText("Más de "+Temp);
    }
    private void MenuAcciones() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_1:
                                Intent intent = new Intent(Item.this,ActivityPrincipal.class);
                                intent.putExtra("o","o1");
                                startActivity(intent);
                                return true;
                            case R.id.action_2:
                                Intent intent2 = new Intent(Item.this,ActivityPrincipal.class);
                                intent2.putExtra("o","o2");
                                startActivity(intent2);
                                return true;
                            case R.id.action_4:
                                Intent intent4 = new Intent(Item.this,ActivityPrincipal.class);
                                intent4.putExtra("o","o4");
                                startActivity(intent4);
                                return true;
                            case R.id.action_5:
                                Intent intent5 = new Intent(Item.this,ActivityPrincipal.class);
                                intent5.putExtra("o","o5");
                                startActivity(intent5);
                                return true;
                        }
                        return false;
                    }
                });
    }
    private void MostrarProductosSimilares(Producto productoSeleccionado) {

        List<Producto> ProductosSimilares=RecuperarSimilares(productoSeleccionado);

        if(ProductosSimilares!=null){
            linearLayout = new LinearLayoutManager(Item.this, LinearLayoutManager.HORIZONTAL,false);
            adapterProductosSimilares = new AdapterItemProductos(Item.this, ProductosSimilares, new RecyclerViewOnItemClickListener2() {
                @Override
                public void onClick(View v, int position) {
                }
            });
            recycler_similares1.setAdapter(adapterProductosSimilares);
            recycler_similares1.setLayoutManager(linearLayout);
        }else{
            Log.i("Inca","No tiene productos similares 1");
        }


    }
    private void MostrarProductosSimilares2(Producto productoSeleccionado) {

            List<Producto> ProductosSimilares2=RecuperarSimilares2(productoSeleccionado);

        if(ProductosSimilares2!=null){
            linearLayout = new LinearLayoutManager(Item.this, LinearLayoutManager.HORIZONTAL,false);
            adapterProductosSimilares2 = new AdapterItemProductos(Item.this, ProductosSimilares2, new RecyclerViewOnItemClickListener2() {
                @Override
                public void onClick(View v, int position) {
                }
            });
            recycler_similares2.setAdapter(adapterProductosSimilares2);
            recycler_similares2.setLayoutManager(linearLayout);
        }else{
            Log.i("Inca","No tiene productos similares 2");
        }

    }
    private List<Producto> RecuperarSimilares(Producto productoSeleccionado) {
        List<Producto> Temp=new ArrayList<>();

        for(int i=0;i<Constantes.Base_Producto_Todo.size();i++){
            if(Constantes.Base_Producto_Todo.get(i).getCategoriaProducto().getIdCategoria()==productoSeleccionado.getCategoriaProducto().getIdCategoria()){
                Temp.add(Constantes.Base_Producto_Todo.get(i));
            }
        }
     return Temp;
    }
    private List<Producto> RecuperarSimilares2(Producto productoSeleccionado) {
        List<Producto> Temp=new ArrayList<>();

        for(int i=0;i<Constantes.Base_Producto_Todo.size();i++){
            if(Constantes.Base_Producto_Todo.get(i).getSubCategoriaProducto().getIdSubCategoria()==productoSeleccionado.getSubCategoriaProducto().getIdSubCategoria()){
                Temp.add(Constantes.Base_Producto_Todo.get(i));
            }
        }
        return Temp;
    }
    private void GenerarTamanos(Producto productoSeleccionado) {

        List<Medida> Medidas;
        Medidas=productoSeleccionado.getMedidaProducto();

        if(Medidas!=null){
            tituloTalla.setText("Tallas Disponibles:");
            //Recylcer de TAMAÑOS
            linearLayout = new LinearLayoutManager(Item.this, LinearLayoutManager.HORIZONTAL,false);
            adapter3 = new AdapterMedida(Item.this, Medidas, new RecyclerViewOnItemClickListener2() {
                @Override
                public void onClick(View v, int position) {
                }
            });
            recycler_medidas.setAdapter(adapter3);
            recycler_medidas.setLayoutManager(linearLayout);

        }else{
            tituloTalla.setText("Sin Tallas Disponibles");
            Log.i("Inca","No se encontraron Medidas");
        }

    }
    private void GenerarCarrusel(Producto productoSeleccionado) {
        /*----------Carrusel----------------*/

         ListaGalerias=productoSeleccionado.getGaleriaProducto();
        if(ListaGalerias==null){
            Rutas=new String[1];
            Rutas[0]=Constantes.PATH_IMAGEN+productoSeleccionado.getImagenProducto();
            carruselProducto.setImageResources(Rutas);
            carruselProducto.setOnPageClickListener(new CarouselView.OnPageClickListener() {
                @Override
                public void onPageClick(int position) {
                    final LayoutInflater inflater = (LayoutInflater) Item.this.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                    final View dialoglayout4 = inflater.inflate(R.layout.alert_imagen, null);
                    final ImageView ImagenVista=dialoglayout4.findViewById(R.id.iv_imagen_zoom);


                    Picasso.get()
                            .load(Rutas[0])
                            .placeholder(R.drawable.default_imagen)
                            .error(R.drawable.default_imagen)
                            .into(ImagenVista);

                    ImagenVista.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            imagenVista.dismiss();
                        }
                    });

                    AlertDialog.Builder builder4 = new AlertDialog.Builder(Item.this);
                    builder4.setView(dialoglayout4);
                    imagenVista=builder4.show();
                }
            });
        }else{
            Rutas=new String[ListaGalerias.size()];

            for(int i=0;i<ListaGalerias.size();i++){
                Rutas[i]=Constantes.PATH_IMAGEN+ListaGalerias.get(i).getImagenGaleria();
            }

            Collections.reverse(ListaGalerias);

            carruselProducto.setImageResources(Rutas);
            carruselProducto.setOnPageClickListener(new CarouselView.OnPageClickListener() {
                @Override
                public void onPageClick(int position) {

                    final LayoutInflater inflater = (LayoutInflater) Item.this.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                    final View dialoglayout4 = inflater.inflate(R.layout.alert_imagen, null);
                    final ImageView ImagenVista=dialoglayout4.findViewById(R.id.iv_imagen_zoom);


                    Picasso.get()
                            .load(Constantes.PATH_IMAGEN+ListaGalerias.get(position).getImagenGaleria())
                            .placeholder(R.drawable.default_imagen)
                            .error(R.drawable.default_imagen)
                            .into(ImagenVista);

                    ImagenVista.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            imagenVista.dismiss();
                        }
                    });

                    AlertDialog.Builder builder4 = new AlertDialog.Builder(Item.this);
                    builder4.setView(dialoglayout4);
                    imagenVista=builder4.show();
                }
            });
        }
    }
    private Producto BuscarProductoSeleccionado(int idProductoRecuperado) {
        Producto temp=new Producto();

        for(int i=0;i< Constantes.Base_Producto_Todo.size();i++){
            if(Constantes.Base_Producto_Todo.get(i).getIdProducto()==idProductoRecuperado){
                temp=Constantes.Base_Producto_Todo.get(i);
            }
        }
        return temp;
    }
    private void Boton_Comprar() {
        /*btn_comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 if(Sesion.USUARIO.getId()==null){
                    Intent intent = new Intent(Item.this,ActivityPrincipal.class);
                    intent.putExtra("o","o5");
                    startActivity(intent);
                }else{
                    ItemProducto2=new ItemCompra(cont+1,"Envio "+cont+1,fecha,2,prod,1,prod.getPrecio());
                    ListCompra.CARRITO_COMPRA.add(ItemProducto2);
                    Intent intent = new Intent(Item.this,CompraActivity.class);
                    startActivity(intent);
                }
            }
        });*/
    }
    private void Boton_Agregar_Carrito() {
       /* agregarCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ListCarrito.CARRITO_LISTA.size()!=0){
                    for(int i=0;i<ListCarrito.CARRITO_LISTA.size();i++){
                        if(ListCarrito.CARRITO_LISTA.get(i).getP().getId()==prod.getId()){
                            resp=true;
                        }else{
                            resp=false;
                        }
                    }
                }
                if(resp==true){
                    mensaje="Ya agrego el Producto en Carrito";
                }
                if(resp==false){
                    ItemProducto=new ItemCarrito(cont+1,prod,fecha,Sesion.USUARIO.getNombre(),cont2,prod.getPrecio());
                    ListCarrito.CARRITO_LISTA.add(ItemProducto);
                    ItemProducto2=new ItemCompra(cont+1,"Envio "+cont+1,fecha,2,prod,cont2,prod.getPrecio());
                    ListCompra.CARRITO_COMPRA.add(ItemProducto2);
                    mensaje="Producto Agregado con Exito!!";
                }
                Snackbar.make(v,mensaje, Snackbar.LENGTH_LONG)
                        .show();
            }
        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        scroll.scrollTo(0,0);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        scroll.scrollTo(0,0);

    }
}

