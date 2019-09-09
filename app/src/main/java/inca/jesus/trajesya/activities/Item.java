package inca.jesus.trajesya.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import inca.jesus.trajesya.adapters.AdapterItemProductos;
import inca.jesus.trajesya.adapters.AdapterMedida;
import inca.jesus.trajesya.adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.clases.CarouselView;
import inca.jesus.trajesya.data.modelo.Galeria;
import inca.jesus.trajesya.data.modelo.Medida;
import inca.jesus.trajesya.data.modelo.Producto;
import inca.jesus.trajesya.data.modelo.ReservaItem;
import inca.jesus.trajesya.data.modelo.Sesion;
import inca.jesus.trajesya.data.modelo.Usuario;
import inca.jesus.trajesya.data.utils.Constantes;
import inca.jesus.trajesya.R;

public class Item extends AppCompatActivity {


    /*----------Variables Nuevas------------*/
    int idProductoRecuperado = 0;
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
    private AdapterMedida adapterMedida;
    private LinearLayoutManager linearLayout;

    public Button mas, menos;
    public TextView cantidad;
    public int contadorStock = 1;


    BottomNavigationView bottomNavigationView;


    Sesion sesion;
    Context context;
    Usuario usuarioRecuperado;

    Medida MedidaSeleccionada;

    Button btnReservar, btnAgregar;
    AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        context = getApplicationContext();


        MedidaSeleccionada = new Medida();

        scroll = findViewById(R.id.scroll_Item);
        nombreProducto = findViewById(R.id.txtNombreProducto);
        verificadoProducto = findViewById(R.id.txtVerificadoPor);
        carruselProducto = findViewById(R.id.pictureplay);
        precioProducto = findViewById(R.id.txtPrecioProducto);
        descripcioProducto = findViewById(R.id.txtDescripcionProducto);
        recycler_similares1 = findViewById(R.id.recycler_similares1);
        recycler_similares2 = findViewById(R.id.recycler_similares2);
        recycler_medidas = findViewById(R.id.recycler_medidas);

        tituloTalla = findViewById(R.id.tituloTalla);
        mas = findViewById(R.id.mas);
        menos = findViewById(R.id.menos);
        cantidad = findViewById(R.id.cantidad);
        btnReservar = findViewById(R.id.btnReservar);
        btnAgregar = findViewById(R.id.btnAgregarReserva);


        Bundle extras = getIntent().getExtras();
        idProductoRecuperado = extras.getInt("idProducto");

        ProductoSeleccionado = BuscarProductoSeleccionado(idProductoRecuperado);

        GenerarCarrusel(ProductoSeleccionado);

        /*------------- Seteando Datos-----------*/
        nombreProducto.setText(ProductoSeleccionado.getNombreProducto());
        verificadoProducto.setText(ProductoSeleccionado.getVerificadoProducto());

        DecimalFormat formateador = new DecimalFormat("###,###.00");
        double precioAlquiler=ProductoSeleccionado.getPrecioAlquiler();
        precioProducto.setText("Alquiler: S/." +precioAlquiler);

        descripcioProducto.setText(ProductoSeleccionado.getDescripcionProducto());

        GenerarTamanos(ProductoSeleccionado);

        MostrarProductosSimilares(ProductoSeleccionado);
        MostrarProductosSimilares2(ProductoSeleccionado);

        RecuperarCategoria(ProductoSeleccionado);
        RecuperarSubCategoria(ProductoSeleccionado);

        accionesBotones();


        cantidad.setText(String.valueOf(contadorStock));
        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tem = Integer.parseInt(cantidad.getText().toString());
                if (tem < Constantes.CANTIDAD_MAX_STOCK) {
                    contadorStock = contadorStock + 1;
                    cantidad.setText(String.valueOf(contadorStock));
                } else { 
                    Toast.makeText(Item.this, "Stock no Disponible.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tem = Integer.parseInt(cantidad.getText().toString());
                if (tem != 0) {
                    contadorStock = contadorStock - 1;
                    cantidad.setText(String.valueOf(contadorStock));
                }
            }
        });

        MenuAcciones();

    }

    private void RecuperarSubCategoria(Producto productoSeleccionado) {
        subcategoriaSimilar = findViewById(R.id.txtNombreSubCategoria);
        String Temp = "";
        for (int i = 0; i < Constantes.Base_SubCategorias_Todo.size(); i++) {
            if (Constantes.Base_SubCategorias_Todo.get(i).getIdSubCategoria() == productoSeleccionado.getSubCategoriaProducto().getIdSubCategoria()) {
                Temp = Constantes.Base_SubCategorias_Todo.get(i).getNombreSubCategoria();
            }
        }
        subcategoriaSimilar.setText("Más de " + Temp);
    }

    private void RecuperarCategoria(Producto productoSeleccionado) {

        categoriaSimiliar = findViewById(R.id.txtNombreCategoria);
        String Temp = "";
        for (int i = 0; i < Constantes.Base_Categorias_Todo.size(); i++) {
            if (Constantes.Base_Categorias_Todo.get(i).getIdCategoria() == productoSeleccionado.getCategoriaProducto().getIdCategoria()) {
                Temp = Constantes.Base_Categorias_Todo.get(i).getNombreCategoria();
            }
        }
        categoriaSimiliar.setText("Más de " + Temp);
    }

    private void MenuAcciones() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_1:
                                Intent intent = new Intent(Item.this, ActivityPrincipal.class);
                                intent.putExtra("o", "o1");
                                startActivity(intent);
                                return true;
                            case R.id.action_2:
                                Intent intent2 = new Intent(Item.this, ActivityPrincipal.class);
                                intent2.putExtra("o", "o2");
                                startActivity(intent2);
                                return true;
                            case R.id.action_4:
                                Intent intent4 = new Intent(Item.this, ActivityPrincipal.class);
                                intent4.putExtra("o", "o4");
                                startActivity(intent4);
                                return true;
                            case R.id.action_5:
                                Intent intent5 = new Intent(Item.this, ActivityPrincipal.class);
                                intent5.putExtra("o", "o5");
                                startActivity(intent5);
                                return true;
                        }
                        return false;
                    }
                });
    }

    private void MostrarProductosSimilares(Producto productoSeleccionado) {

        List<Producto> ProductosSimilares = RecuperarSimilares(productoSeleccionado);

        if (ProductosSimilares != null) {
            linearLayout = new LinearLayoutManager(Item.this, LinearLayoutManager.HORIZONTAL, false);
            adapterProductosSimilares = new AdapterItemProductos(Item.this, ProductosSimilares, new RecyclerViewOnItemClickListener2() {
                @Override
                public void onClick(View v, int position) {
                }
            });
            recycler_similares1.setAdapter(adapterProductosSimilares);
            recycler_similares1.setLayoutManager(linearLayout);
        } else {
            Log.i("Inca", "No tiene productos similares 1");
        }


    }

    private void MostrarProductosSimilares2(Producto productoSeleccionado) {

        List<Producto> ProductosSimilares2 = RecuperarSimilares2(productoSeleccionado);

        if (ProductosSimilares2 != null) {
            linearLayout = new LinearLayoutManager(Item.this, LinearLayoutManager.HORIZONTAL, false);
            adapterProductosSimilares2 = new AdapterItemProductos(Item.this, ProductosSimilares2, new RecyclerViewOnItemClickListener2() {
                @Override
                public void onClick(View v, int position) {
                }
            });
            recycler_similares2.setAdapter(adapterProductosSimilares2);
            recycler_similares2.setLayoutManager(linearLayout);
        } else {
            Log.i("Inca", "No tiene productos similares 2");
        }

    }

    private List<Producto> RecuperarSimilares(Producto productoSeleccionado) {
        List<Producto> Temp = new ArrayList<>();

        for (int i = 0; i < Constantes.Base_Producto_Todo.size(); i++) {
            if (Constantes.Base_Producto_Todo.get(i).getCategoriaProducto().getIdCategoria() == productoSeleccionado.getCategoriaProducto().getIdCategoria()) {
                Temp.add(Constantes.Base_Producto_Todo.get(i));
            }
        }
        return Temp;
    }

    private List<Producto> RecuperarSimilares2(Producto productoSeleccionado) {
        List<Producto> Temp = new ArrayList<>();

        for (int i = 0; i < Constantes.Base_Producto_Todo.size(); i++) {
            if (Constantes.Base_Producto_Todo.get(i).getSubCategoriaProducto().getIdSubCategoria() == productoSeleccionado.getSubCategoriaProducto().getIdSubCategoria()) {
                Temp.add(Constantes.Base_Producto_Todo.get(i));
            }
        }
        return Temp;
    }

    private void GenerarTamanos(Producto productoSeleccionado) {

        List<Medida> Medidas;
        Medidas = productoSeleccionado.getMedidaProducto();

        if (Medidas != null) {
            tituloTalla.setText("Tallas Disponibles:");
            //Recylcer de TAMAÑOS
            linearLayout = new LinearLayoutManager(Item.this, LinearLayoutManager.HORIZONTAL, false);
            adapterMedida = new AdapterMedida(Item.this, Medidas, new RecyclerViewOnItemClickListener2() {
                @Override
                public void onClick(View v, int position) {
                }
            });
            recycler_medidas.setAdapter(adapterMedida);
            recycler_medidas.setLayoutManager(linearLayout);

            adapterMedida.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    MedidaSeleccionada = adapterMedida.RecuperarTallaSeleccion();
                    Log.i("Inca", "Medida Seleccionada: " + MedidaSeleccionada.getNombreMedida());
                }
            });

        } else {
            tituloTalla.setText("Sin Tallas Disponibles");
            Log.i("Inca", "No se encontraron Medidas");
        }

    }

    private void GenerarCarrusel(Producto productoSeleccionado) {
        /*----------Carrusel----------------*/

        ListaGalerias = productoSeleccionado.getGaleriaProducto();
        if (ListaGalerias == null) {
            Rutas = new String[1];
            Rutas[0] = Constantes.PATH_IMAGEN + productoSeleccionado.getImagenProducto();
            carruselProducto.setImageResources(Rutas);
            carruselProducto.setOnPageClickListener(new CarouselView.OnPageClickListener() {
                @Override
                public void onPageClick(int position) {
                    final LayoutInflater inflater = (LayoutInflater) Item.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View dialoglayout4 = inflater.inflate(R.layout.alert_imagen, null);
                    final ImageView ImagenVista = dialoglayout4.findViewById(R.id.iv_imagen_zoom);


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
                    imagenVista = builder4.show();
                }
            });
        } else {
            Rutas = new String[ListaGalerias.size()];

            for (int i = 0; i < ListaGalerias.size(); i++) {
                Rutas[i] = Constantes.PATH_IMAGEN + ListaGalerias.get(i).getImagenGaleria();
            }

            Collections.reverse(ListaGalerias);

            carruselProducto.setImageResources(Rutas);
            carruselProducto.setOnPageClickListener(new CarouselView.OnPageClickListener() {
                @Override
                public void onPageClick(int position) {

                    final LayoutInflater inflater = (LayoutInflater) Item.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View dialoglayout4 = inflater.inflate(R.layout.alert_imagen, null);
                    final ImageView ImagenVista = dialoglayout4.findViewById(R.id.iv_imagen_zoom);


                    Picasso.get()
                            .load(Constantes.PATH_IMAGEN + ListaGalerias.get(position).getImagenGaleria())
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
                    imagenVista = builder4.show();
                }
            });
        }
    }

    private Producto BuscarProductoSeleccionado(int idProductoRecuperado) {
        Producto temp = new Producto();

        for (int i = 0; i < Constantes.Base_Producto_Todo.size(); i++) {
            if (Constantes.Base_Producto_Todo.get(i).getIdProducto() == idProductoRecuperado) {
                temp = Constantes.Base_Producto_Todo.get(i);
            }
        }
        return temp;
    }

    private void accionesBotones() {

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sesion = new Sesion();
                usuarioRecuperado = sesion.RecuperarSesion(context);
                if (usuarioRecuperado.isSesion()) {
                    /*----------------PROCEDE CON RESERVA----------------------*/
                    int CantidadRecuperada = contadorStock;
                    Medida MedidaRecuperada = MedidaSeleccionada;
                    Producto ProductoRecuperado = ProductoSeleccionado;


                    if(CantidadRecuperada>0){
                        //Agregrando Datos de Items
                        ReservaItem item=new ReservaItem();
                        item.setCantidad(CantidadRecuperada);
                        item.setMedidaReservaItem(MedidaRecuperada);
                        item.setProductoItem(ProductoRecuperado);
                        Constantes.RESERVA_ITEMS.add(item);

                        Toast.makeText(context, "Producto agregado a la Reserva.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Debe indicar una cantidad para continuar.", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    final LayoutInflater inflater = (LayoutInflater) Item.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View dialoglayout4 = inflater.inflate(R.layout.dialog_completar_sesion, null);
                    final Button seguirNavegandoAccion = dialoglayout4.findViewById(R.id.btnSeguirNavegando);
                    final Button completarRegistroAccion = dialoglayout4.findViewById(R.id.btnCompletarRegistro);

                    seguirNavegandoAccion.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            imagenVista.dismiss();
                        }
                    });
                    completarRegistroAccion.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Item.this, ActivityPrincipal.class);
                            intent.putExtra("o", "o5");
                            startActivity(intent);
                        }
                    });

                    androidx.appcompat.app.AlertDialog.Builder builder4 = new androidx.appcompat.app.AlertDialog.Builder(Item.this);
                    builder4.setView(dialoglayout4);
                    imagenVista = builder4.show();
                }
            }
        });

        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sesion = new Sesion();
                usuarioRecuperado = sesion.RecuperarSesion(context);
                if (usuarioRecuperado.isSesion()) {
                    /*----------------PROCEDE CON RESERVA----------------------*/
                    int CantidadRecuperada = contadorStock;
                    Medida MedidaRecuperada = MedidaSeleccionada;
                    Producto ProductoRecuperado = ProductoSeleccionado;

                    if(CantidadRecuperada>0){
                        //Agregrando Datos de Items
                        ReservaItem item=new ReservaItem();
                        item.setCantidad(CantidadRecuperada);
                        item.setMedidaReservaItem(MedidaRecuperada);
                        item.setProductoItem(ProductoRecuperado);
                        Constantes.RESERVA_ITEMS.add(item);
                        Intent intent = new Intent(Item.this, CompraActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        //intent.putExtra("o", "o4");
                        startActivity(intent);
                        Toast.makeText(context, "Producto agregado a la Reserva.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Debe indicar una cantidad para continuar.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    final LayoutInflater inflater = (LayoutInflater) Item.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View dialoglayout4 = inflater.inflate(R.layout.dialog_completar_sesion, null);
                    final Button seguirNavegandoAccion = dialoglayout4.findViewById(R.id.btnSeguirNavegando);
                    final Button completarRegistroAccion = dialoglayout4.findViewById(R.id.btnCompletarRegistro);

                    seguirNavegandoAccion.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            imagenVista.dismiss();
                        }
                    });
                    completarRegistroAccion.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Item.this, ActivityPrincipal.class);
                            intent.putExtra("o", "o5");
                            startActivity(intent);
                        }
                    });

                    androidx.appcompat.app.AlertDialog.Builder builder4 = new androidx.appcompat.app.AlertDialog.Builder(Item.this);
                    builder4.setView(dialoglayout4);
                    imagenVista = builder4.show();
                }
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        scroll.scrollTo(0, 0);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        scroll.scrollTo(0, 0);

    }
}

