package inca.jesus.trajesya.fragmentos;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import inca.jesus.trajesya.R;
import inca.jesus.trajesya.activities.ActivityPrincipal;
import inca.jesus.trajesya.adapters.AdapterGenero;
import inca.jesus.trajesya.adapters.AdapterItemProductosMini;
import inca.jesus.trajesya.adapters.AdapterMedida;
import inca.jesus.trajesya.adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.clases.CarouselView;
import inca.jesus.trajesya.data.modelo.Galeria;
import inca.jesus.trajesya.data.modelo.Genero;
import inca.jesus.trajesya.data.modelo.Medida;
import inca.jesus.trajesya.data.modelo.Producto;
import inca.jesus.trajesya.data.modelo.ReservaItem;
import inca.jesus.trajesya.data.modelo.Sesion;
import inca.jesus.trajesya.data.modelo.Usuario;
import inca.jesus.trajesya.data.utils.Constantes;

public class fragmentItem extends Fragment {
    /*----------Variables Nuevas------------*/
    int idProductoRecuperado = 0;
    Producto ProductoSeleccionado;
    private AdapterItemProductosMini adapterProductosSimilares;
    private AdapterItemProductosMini adapterProductosSimilares2;
    public RecyclerView recycler_similares1;
    public RecyclerView recycler_similares2;
    public RecyclerView recycler_medidas;
    public RecyclerView recycler_generos;
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
    TextView tituloGenero;
    ScrollView scroll;
    private AdapterMedida adapterMedida;
    private AdapterGenero adapterGenero;
    private LinearLayoutManager linearLayout;
    public Button mas, menos;
    public TextView cantidad;
    public int contadorStock = 1;
    Sesion sesion;
    Context context;
    Usuario usuarioRecuperado;
    Medida MedidaSeleccionada;
    Genero GeneroSeleccionada;
    Button btnReservar, btnAgregar;
    AlertDialog alerta;
    List<Medida> Medidas;
    List<Genero> Generos;
    TextView txtDescuento;
    public fragmentItem() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_item_producto, container, false);
        context = getActivity();
        MedidaSeleccionada = new Medida();
        scroll = v.findViewById(R.id.scroll_Item);
        scroll.scrollTo(0, 0);
        nombreProducto = v.findViewById(R.id.txtNombreProducto);
        verificadoProducto = v.findViewById(R.id.txtVerificadoPor);
        carruselProducto = v.findViewById(R.id.pictureplay);
        precioProducto = v.findViewById(R.id.txtPrecioProducto);
        descripcioProducto = v.findViewById(R.id.txtDescripcionProducto);
        recycler_similares1 = v.findViewById(R.id.recycler_similares1);
        recycler_similares2 = v.findViewById(R.id.recycler_similares2);
        recycler_medidas = v.findViewById(R.id.recycler_medidas);
        recycler_generos= v.findViewById(R.id.recycler_generos);
        tituloTalla = v.findViewById(R.id.tituloTalla);
        tituloGenero = v.findViewById(R.id.tituloGenero);
        txtDescuento=v.findViewById(R.id.txtDescuento);
        mas = v.findViewById(R.id.mas);
        menos = v.findViewById(R.id.menos);
        cantidad = v.findViewById(R.id.cantidad);
        btnReservar = v.findViewById(R.id.btnReservar);
        btnAgregar = v.findViewById(R.id.btnAgregarReserva);

        Bundle Recuperado=this.getArguments();
        idProductoRecuperado=Recuperado.getInt("idProducto");
        ProductoSeleccionado = BuscarProductoSeleccionado(idProductoRecuperado);

        GenerarCarrusel(ProductoSeleccionado);


        /*------------- Seteando Datos-----------*/
        nombreProducto.setText(ProductoSeleccionado.getNombreProducto());
        verificadoProducto.setText(ProductoSeleccionado.getVerificadoProducto());

        DecimalFormat formateador = new DecimalFormat("###,###.00");
        double precioAlquiler=ProductoSeleccionado.getPrecioBase();

        if(ProductoSeleccionado.getPorcentajeDescuento()>0){
            txtDescuento.setText("Descuento: -"+(int)ProductoSeleccionado.getPorcentajeDescuento()+"%");
            txtDescuento.setVisibility(View.VISIBLE);
        }else{
            txtDescuento.setVisibility(View.GONE);
        }
        if(precioAlquiler!=0){
            precioProducto.setText("Alquiler: S/ " +formateador.format(precioAlquiler));
        }else{
            precioProducto.setText("Alquiler: S/ 0.00");
        }


        descripcioProducto.setText(ProductoSeleccionado.getDescripcionProducto());

        GenerarTamanos(ProductoSeleccionado);
        GenerarGeneros(ProductoSeleccionado);

        MostrarProductosSimilares(ProductoSeleccionado);
        MostrarProductosSimilares2(ProductoSeleccionado);

        RecuperarCategoria(v,ProductoSeleccionado);
        RecuperarSubCategoria(v,ProductoSeleccionado);

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
                    Toast.makeText(context, "Stock no Disponible.", Toast.LENGTH_SHORT).show();
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

        return v;
    }
    private void RecuperarSubCategoria(View v, Producto productoSeleccionado) {
        subcategoriaSimilar = v.findViewById(R.id.txtNombreSubCategoria);
        String Temp = "";
        for (int i = 0; i < Constantes.Base_SubCategorias_Todo.size(); i++) {
            if (Constantes.Base_SubCategorias_Todo.get(i).getIdSubCategoria() == productoSeleccionado.getSubCategoriaProducto().getIdSubCategoria()) {
                Temp = Constantes.Base_SubCategorias_Todo.get(i).getNombreSubCategoria();
            }
        }
        subcategoriaSimilar.setText("Más de " + Temp);
    }

    private void RecuperarCategoria(View v, Producto productoSeleccionado) {

        categoriaSimiliar = v.findViewById(R.id.txtNombreCategoria);
        String Temp = "";
        for (int i = 0; i < Constantes.Base_Categorias_Todo.size(); i++) {
            if (Constantes.Base_Categorias_Todo.get(i).getIdCategoria() == productoSeleccionado.getCategoriaProducto().getIdCategoria()) {
                Temp = Constantes.Base_Categorias_Todo.get(i).getNombreCategoria();
            }
        }
        categoriaSimiliar.setText("Más de " + Temp);
    }

    private void MostrarProductosSimilares(Producto productoSeleccionado) {

        List<Producto> ProductosSimilares = RecuperarSimilares(productoSeleccionado);

        if (ProductosSimilares != null) {
            linearLayout = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            adapterProductosSimilares = new AdapterItemProductosMini(context, ProductosSimilares, new RecyclerViewOnItemClickListener2() {
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
            linearLayout = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            adapterProductosSimilares2 = new AdapterItemProductosMini(context, ProductosSimilares2, new RecyclerViewOnItemClickListener2() {
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

        Medidas = productoSeleccionado.getMedidaProducto();

        if (Medidas != null) {
            tituloTalla.setText("Tallas Disponibles:");
            //Recylcer de TAMAÑOS
            linearLayout = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            adapterMedida = new AdapterMedida(context, Medidas, new RecyclerViewOnItemClickListener2() {
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

    private void GenerarGeneros(Producto productoSeleccionado){
        Generos = productoSeleccionado.getGeneroProducto();

        if (Generos != null) {
            tituloGenero.setText("Generos Disponibles:");
            //Recylcer de Generos
            linearLayout = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            adapterGenero = new AdapterGenero(context, Generos, new RecyclerViewOnItemClickListener2() {
                @Override
                public void onClick(View v, int position) {
                }
            });
            recycler_generos.setAdapter(adapterGenero);
            recycler_generos.setLayoutManager(linearLayout);

            adapterGenero.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    GeneroSeleccionada = adapterGenero.RecuperarGeneroSeleccion();
                    Log.i("Inca", "Medida Seleccionada: " + GeneroSeleccionada.getNombreGenero());
                }
            });

        } else {
            tituloGenero.setText("Sin Generos Disponibles");
            Log.i("Inca", "No se encontraron Generos");
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
                    final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

                    AlertDialog.Builder builder4 = new AlertDialog.Builder(context);
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

                    final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

                    AlertDialog.Builder builder4 = new AlertDialog.Builder(context);
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
                LanzarAccion(usuarioRecuperado,1);

                /*if (usuarioRecuperado.isSesion()) {

                    String MensajeValidacion=ValidarElementos();

                    if(MensajeValidacion.length()>0){
                        Toast.makeText(context, MensajeValidacion, Toast.LENGTH_SHORT).show();
                    }else{
                        ReservaItem item=new ReservaItem();
                        item.setCantidad(contadorStock);
                        item.setMedidaReservaItem(MedidaSeleccionada);
                        item.setProductoItem(ProductoSeleccionado);
                        item.setGeneroReservaitem(GeneroSeleccionada);
                        Constantes.RESERVA_ITEMS.add(item);
                        //((ActivityPrincipal)context).opcionReserva();
                        Toast.makeText(context, "Producto agregado a la Reserva.", Toast.LENGTH_SHORT).show();

                    }

                   int CantidadRecuperada = contadorStock;
                    Medida MedidaRecuperada = MedidaSeleccionada;
                    Producto ProductoRecuperado = ProductoSeleccionado;
                    Genero GeneroRecuperado= GeneroSeleccionada;

                     if(Medidas!=null){
                        if(MedidaRecuperada.getIdMedida()>0){
                            if(CantidadRecuperada>0){
                                //Agregrando Datos de Items
                                ReservaItem item=new ReservaItem();
                                item.setCantidad(CantidadRecuperada);
                                item.setMedidaReservaItem(MedidaRecuperada);
                                item.setProductoItem(ProductoRecuperado);
                                Constantes.RESERVA_ITEMS.add(item);
                                //((ActivityPrincipal)context).opcionReserva();
                                Toast.makeText(context, "Producto agregado a la Reserva.", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Debe indicar una cantidad para continuar.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(context, "Seleccione una Talla Disponible para continuar.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        if(CantidadRecuperada>0){
                            //Agregrando Datos de Items
                            ReservaItem item=new ReservaItem();
                            item.setCantidad(CantidadRecuperada);
                            item.setMedidaReservaItem(MedidaRecuperada);
                            item.setProductoItem(ProductoRecuperado);
                            Constantes.RESERVA_ITEMS.add(item);
                            ((ActivityPrincipal)context).opcionReserva(false);
                            Toast.makeText(context, "Producto agregado a la Reserva.", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Debe indicar una cantidad para continuar.", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else {
                    final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                            ((ActivityPrincipal)context).opcionSesion();
                        }
                    });

                    androidx.appcompat.app.AlertDialog.Builder builder4 = new androidx.appcompat.app.AlertDialog.Builder(context);
                    builder4.setView(dialoglayout4);
                    imagenVista = builder4.show();
                } */
            }
        });

        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sesion = new Sesion();
                usuarioRecuperado = sesion.RecuperarSesion(context);
                LanzarAccion(usuarioRecuperado,2);

               /* if (usuarioRecuperado.isSesion()) {

                    int CantidadRecuperada = contadorStock;
                    Medida MedidaRecuperada = MedidaSeleccionada;
                    Producto ProductoRecuperado = ProductoSeleccionado;
                    if(Medidas!=null){
                        if(MedidaRecuperada.getIdMedida()>0){
                            if(CantidadRecuperada>0){
                                //Agregrando Datos de Items
                                ReservaItem item=new ReservaItem();
                                item.setCantidad(CantidadRecuperada);
                                item.setMedidaReservaItem(MedidaRecuperada);
                                item.setProductoItem(ProductoRecuperado);
                                Constantes.RESERVA_ITEMS.add(item);
                                ((ActivityPrincipal)context).opcionReserva(false);
                                Toast.makeText(context, "Producto agregado a la Reserva.", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Debe indicar una cantidad para continuar.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(context, "Seleccione una Talla Disponible para continuar.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        if(CantidadRecuperada>0){
                            //Agregrando Datos de Items
                            ReservaItem item=new ReservaItem();
                            item.setCantidad(CantidadRecuperada);
                            item.setMedidaReservaItem(MedidaRecuperada);
                            item.setProductoItem(ProductoRecuperado);
                            Constantes.RESERVA_ITEMS.add(item);
                            ((ActivityPrincipal)context).opcionReserva(false);
                            Toast.makeText(context, "Producto agregado a la Reserva.", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Debe indicar una cantidad para continuar.", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else {
                    final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                            ((ActivityPrincipal)context).opcionSesion();
                        }
                    });

                    androidx.appcompat.app.AlertDialog.Builder builder4 = new androidx.appcompat.app.AlertDialog.Builder(context);
                    builder4.setView(dialoglayout4);
                    imagenVista = builder4.show();
                }*/
            }
        });
    }

    private void LanzarAccion(Usuario usuarioRecuperado, int Accion) {

        if (usuarioRecuperado.isSesion()) {

            String MensajeValidacion=ValidarElementos();

            if(MensajeValidacion.length()>0){
                Toast.makeText(context, MensajeValidacion, Toast.LENGTH_SHORT).show();
            }else{
                ReservaItem item=new ReservaItem();
                item.setCantidad(contadorStock);
                item.setMedidaReservaItem(MedidaSeleccionada);
                item.setProductoItem(ProductoSeleccionado);
                item.setGeneroReservaitem(GeneroSeleccionada);
                Constantes.RESERVA_ITEMS.add(item);

                if(Accion==1){
                    //((ActivityPrincipal)context).opcionReserva();
                    Toast.makeText(context, "Producto agregado a la Reserva.", Toast.LENGTH_SHORT).show();
                }else{
                    ((ActivityPrincipal)context).opcionReserva(false);
                    Toast.makeText(context, "Producto agregado a la Reserva.", Toast.LENGTH_SHORT).show();
                }
            }

        } else {
            final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                    ((ActivityPrincipal)context).opcionSesion();
                }
            });

            androidx.appcompat.app.AlertDialog.Builder builder4 = new androidx.appcompat.app.AlertDialog.Builder(context);
            builder4.setView(dialoglayout4);
            imagenVista = builder4.show();
        }
    }

    private String ValidarElementos() {
        String mensaje="";

        int CantidadRecuperada = contadorStock;
        Medida MedidaRecuperada = MedidaSeleccionada;
        Producto ProductoRecuperado = ProductoSeleccionado;
        Genero GeneroRecuperado= GeneroSeleccionada;

        if(CantidadRecuperada==0){
            mensaje=mensaje+"- Ingrese Cantidad de Reserva.\n";
        }

        if(GeneroRecuperado==null){
            mensaje=mensaje+"- Seleccione Genero de Reserva.\n";
        }else{
            if(GeneroRecuperado.getIdGenero()<=0){
                mensaje=mensaje+"- Seleccione Genero de Reserva.\n";
            }
        }

        if(MedidaRecuperada==null){
            mensaje=mensaje+"- Seleccione Medida de Reserva.\n";
        }else{
            if(MedidaRecuperada.getIdMedida()<=0){
                mensaje=mensaje+"- Seleccione Medida de Reserva.\n";
            }
        }

        return mensaje;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        scroll.scrollTo(0, 0);
    }

    public void LimpiarTallas(){
        if(adapterMedida!=null){
            adapterMedida.QuitarSeleccion();
        }
    }
}
