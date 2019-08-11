package inca.jesus.trajesya.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import inca.jesus.trajesya.Adapters.Adapter3;
import inca.jesus.trajesya.Adapters.AdapterColores;
import inca.jesus.trajesya.Adapters.AdapterTamanos;
import inca.jesus.trajesya.Adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.Clases.CarouselView;
import inca.jesus.trajesya.Clases.ItemCarrito;
import inca.jesus.trajesya.Clases.ItemCompra;
import inca.jesus.trajesya.Clases.ItemFavorito;
import inca.jesus.trajesya.Clases.ListCarrito;
import inca.jesus.trajesya.Clases.ListCompra;
import inca.jesus.trajesya.Clases.ListResenas;
import inca.jesus.trajesya.Clases.ProductoX;
import inca.jesus.trajesya.Clases.Resenas;
import inca.jesus.trajesya.Clases.Sesion;
import inca.jesus.trajesya.Clases.TiposProductos;
import inca.jesus.trajesya.Data.Modelo.Galeria;
import inca.jesus.trajesya.Data.Modelo.Medida;
import inca.jesus.trajesya.Data.Modelo.Producto;
import inca.jesus.trajesya.Data.Utils.Constantes;
import inca.jesus.trajesya.R;

public class Item extends AppCompatActivity {
    ScrollView scroll;
    ImageButton o1,o2,o3,o4,o5;

    public Button btn_comprar;
    public Button favorito_opcion;
    public Button agregarCarrito;
    public TextView  btn_det;
    public TextView tex_Desc,tex_Prec,text_sub,tex_ve,text_descontado,text_nom,text_descri;
    public TextView rating_can;
    public TextView item_puntos;

    public CardView card,card_nuevo;
    public RatingBar rating;
    private RecyclerView recycler;
    private RecyclerView recycler10;
    private RecyclerView colores;
    private  RecyclerView tamanos;
    private Adapter3 adapterT;
    private AdapterColores adapter2;
    private AdapterTamanos adapter3;
    ItemCarrito ItemProducto= new ItemCarrito();
    ItemCompra ItemProducto2= new ItemCompra();
    ItemFavorito ItemFav=new ItemFavorito();
    List<Resenas> temp=new ArrayList<Resenas>();
    List<ProductoX> temp_lista_p=new ArrayList<ProductoX>();
    private LinearLayoutManager linearLayout;
    public int cont=0;
    public String mensaje="";
    public String fecha;
    public boolean resp;
    public Button mas,menos;
    public TextView cantidad;
    public int cont2=1;

    /*----------Variables Nuevas------------*/
    int idProductoRecuperado=0;
    Producto ProductoSeleccionado;

    CarouselView carruselProducto;
    AlertDialog imagenVista;
    String[] Rutas;
    List<Galeria> ListaGalerias;
    TextView nombreProducto,verificadoProducto,precioProducto,descripcioProducto;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        scroll=(ScrollView)findViewById(R.id.scroll_Item);
        nombreProducto=findViewById(R.id.txtNombreProducto);
        verificadoProducto=findViewById(R.id.txtVerificadoPor);
        carruselProducto = findViewById(R.id.pictureplay);
        precioProducto=findViewById(R.id.txtPrecioProducto);
        descripcioProducto=findViewById(R.id.txtDescripcionProducto);


        Bundle extras=getIntent().getExtras();
        idProductoRecuperado=extras.getInt("idProducto");


        ProductoSeleccionado=BuscarProductoSeleccionado(idProductoRecuperado);

        GenerarCarrusel(ProductoSeleccionado);


        /*------------- Seteando Datos-----------*/
        nombreProducto.setText(ProductoSeleccionado.getNombreProducto());
        verificadoProducto.setText(ProductoSeleccionado.getVerificadoProducto());
        precioProducto.setText("Alquiler: S/."+ProductoSeleccionado.getPrecioAlquiler());
        descripcioProducto.setText(ProductoSeleccionado.getDescripcionProducto());


        // temp_lista_p=ListaTemp2();


        variables();

        cards_acciones();
        Setear_datos();
        recycler_similares();
        validarFavorito();
        Boton_Detalles();
        Mostrar_Fechas();
        Mostrar_Reseñas();
        Boton_Agregar_Carrito();
        Boton_Comprar();
        Boton_Favorito();
        //Recylcer COLORES
        linearLayout = new LinearLayoutManager(Item.this, LinearLayoutManager.HORIZONTAL,false);
        adapter2 = new AdapterColores(Item.this, TiposProductos.LIST_COLORES, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
            }
        });
        colores.setAdapter(adapter2);
        colores.setLayoutManager(linearLayout);


        //Recylcer de TAMAÑOS
        linearLayout = new LinearLayoutManager(Item.this, LinearLayoutManager.HORIZONTAL,false);
        adapter3 = new AdapterTamanos(Item.this, TiposProductos.LIST_TAMA, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
            }
        });
        tamanos.setAdapter(adapter3);
        tamanos.setLayoutManager(linearLayout);



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




        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);

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
                            /*case R.id.action_3:
                                Intent intent3 = new Intent(Item.this,ActivityPrincipal.class);
                                intent3.putExtra("o","o3");
                                startActivity(intent3);
                                return true;*/
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

    private void Mostrar_Fechas() {
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        int monthDay = today.monthDay;
        int month = today.month+1;
        int year=today.year;
        fecha=monthDay+"/"+month+"/"+year;
    }
    private void Mostrar_Reseñas() {
        //temp=ListaTemp();
        if(temp.size()==0){
            rating_can.setText("(0) RESEÑAS");
            rating.setRating(0);
        }else{
            rating_can.setText("("+temp.size()+") RESEÑAS");
            rating.setRating(round(PromedioTotal(),2));

        }
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
    private void Boton_Favorito() {
        favorito_opcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Sesion.USUARIO.getId()==null){
                    Intent intent = new Intent(Item.this,ActivityPrincipal.class);
                    intent.putExtra("o","o5");
                    startActivity(intent);

                }else{

                    /*if(ItemFavorito.ListaFavoritos.size()!=0){
                        for(int i=0;i<ItemFavorito.ListaFavoritos.size();i++){
                            if(ItemFavorito.ListaFavoritos.get(i).getP().getId()==prod.getId()){
                                resp=true;
                            }else{
                                resp=false;
                            }
                        }
                    }*/
                    if(resp==true){
                        mensaje="Ya agrego el Producto en Favoritos";
                    }
                    if(resp==false){
                        /*ItemFav=new ItemFavorito(cont+1,prod,"Jesus Inca Cardenas",fecha);
                        ItemFavorito.ListaFavoritos.add(ItemFav);
                        mensaje="Producto Agregado a Favoritos!!";*/

                    }
                    Toast.makeText(Item.this,mensaje, Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    private void Boton_Agregar_Carrito() {
        agregarCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*if(ListCarrito.CARRITO_LISTA.size()!=0){
                    for(int i=0;i<ListCarrito.CARRITO_LISTA.size();i++){
                        if(ListCarrito.CARRITO_LISTA.get(i).getP().getId()==prod.getId()){
                            resp=true;
                        }else{
                            resp=false;
                        }
                    }
                }*/
                if(resp==true){
                    mensaje="Ya agrego el Producto en Carrito";
                }
                if(resp==false){
                    /*ItemProducto=new ItemCarrito(cont+1,prod,fecha,Sesion.USUARIO.getNombre(),cont2,prod.getPrecio());
                    ListCarrito.CARRITO_LISTA.add(ItemProducto);
                    ItemProducto2=new ItemCompra(cont+1,"Envio "+cont+1,fecha,2,prod,cont2,prod.getPrecio());
                    ListCompra.CARRITO_COMPRA.add(ItemProducto2);
                    mensaje="Producto Agregado con Exito!!";*/
                }
                Snackbar.make(v,mensaje, Snackbar.LENGTH_LONG)
                        .show();
            }
        });
    }
    private void validarFavorito() {

        if(ItemFavorito.ListaFavoritos.size()!=0){
            for(int i=0;i<ItemFavorito.ListaFavoritos.size();i++){
               /* if(ItemFavorito.ListaFavoritos.get(i).getP().getId()==prod.getId()){

                }else{

                }*/
            }
        }

    }
    private void recycler_similares() {
        //Recylcer de PRODUCTOS SIMILARES
        linearLayout = new LinearLayoutManager(Item.this, LinearLayoutManager.HORIZONTAL,false);
        adapterT = new Adapter3(Item.this, temp_lista_p, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
            }
        });
        recycler.setAdapter(adapterT);
        recycler.setLayoutManager(linearLayout);


        linearLayout = new LinearLayoutManager(Item.this, LinearLayoutManager.HORIZONTAL,false);
        adapterT = new Adapter3(Item.this, temp_lista_p, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
            }
        });
        recycler10.setAdapter(adapterT);
        recycler10.setLayoutManager(linearLayout);
    }
    private void Setear_datos() {

        //MOSTRAR DATA
        /*text_nom.setText(prod.getNom_producto());
        tex_ve.setText(prod.getVendedor());
        tex_Desc.setText("Precio Promoción S/ "+String.valueOf(prod.getPrecio()));
        tex_Prec.setText("Precio miCumple S/ "+String.valueOf(prod.getPrecio()));
        DecimalFormat formateador = new DecimalFormat("###,###.##");
        double resu=(100*prod.getPrecio())/(100+prod.getDescuentos());
        text_descontado.setText("Precio normal S/ "+formateador.format(resu));
        item_puntos.setText("Acumulas: "+prod.getDescuentos()+" Puntos");
        text_descri.setText(prod.getDescripcion());*/
    }
    private void cards_acciones() {

        //CAR DE RESEÑAS............................................
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(temp.size()==0){

                }else{
                    Intent intent = new Intent(Item.this,ItemResenas.class);
                    Bundle extras=getIntent().getExtras();
                    intent.putExtra("Producto",extras.getParcelable("Producto"));
                    startActivity(intent);
                }
            }
        });
        //CARD NUEVA RESEÑA..............................................
        card_nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Sesion.USUARIO.getId()==null){
                    Intent intent = new Intent(Item.this,ActivityPrincipal.class);
                    intent.putExtra("o","o5");
                    startActivity(intent);

                }else{
                Intent intent = new Intent(Item.this,NuevaResena.class);
                Bundle extras=getIntent().getExtras();
                intent.putExtra("Producto",extras.getParcelable("Producto"));
                startActivity(intent);
                finish();
                }

            }
        });
    }

    private void variables() {
        mas=(Button)findViewById(R.id.mas);
        menos=(Button)findViewById(R.id.menos);
        cantidad=(TextView)findViewById(R.id.cantidad);
        colores=(RecyclerView)findViewById(R.id.recycler_colores);
        tamanos=(RecyclerView)findViewById(R.id.recycler_tallas);
        recycler10=(RecyclerView)findViewById(R.id.recycler_proveedor);

        card=(CardView)findViewById(R.id.card_resena);
        card_nuevo=(CardView)findViewById(R.id.card_nueva_resena);
        agregarCarrito=(Button)findViewById(R.id.item_boton_add_carrito);
        btn_comprar=(Button)findViewById(R.id.boton_comprar);

        favorito_opcion=(Button)findViewById(R.id.boton_Deseos);

        recycler=(RecyclerView)findViewById(R.id.recycler_item);
        rating=(RatingBar)findViewById(R.id.item_resena_rating);
        rating_can=(TextView)findViewById(R.id.item_resena_cantidad);



       // tex_ve.setPaintFlags(tex_ve.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
       // text_descontado.setPaintFlags(text_descontado.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);

    }
   /* public List<Resenas> ListaTemp(){

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
    }*/
    /*public List<ProductoX> ListaTemp2(){

        List<ProductoX> temp2=new ArrayList<ProductoX>();
        if(ProductoX.TODO!=null){
            for(int i=0;i<ProductoX.TODO.size();i++){
                if(ProductoX.TODO.get(i).getId()!=prod.getId()){
                   if(ProductoX.TODO.get(i).getCategoria()==prod.getCategoria()){

                       temp2.add(ProductoX.TODO.get(i));
                   }

                }

            }
            return  temp2;
        }else{

            return null;
        }
    }*/
    public float PromedioTotal(){
        float total;
        float suma=0;
        for(int i=0;i<temp.size();i++){
            suma=suma+ temp.get(i).getRanking_resena();

        }
        total=suma/5;
        return total;
    }
    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
    public void Boton_Detalles(){
        btn_det=(TextView)findViewById(R.id.boton_detalles);
        btn_det.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Item.this,ItemDetalle.class);
                Bundle extras=getIntent().getExtras();
                intent.putExtra("Producto",extras.getParcelable("Producto"));
                startActivity(intent);
            }
        });
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

