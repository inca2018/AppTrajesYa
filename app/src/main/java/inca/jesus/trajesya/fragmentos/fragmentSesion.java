package inca.jesus.trajesya.fragmentos;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import inca.jesus.trajesya.activities.LoginActivity;
import inca.jesus.trajesya.adapters.AdapterReservas;
import inca.jesus.trajesya.adapters.AdapterUbicaciones;
import inca.jesus.trajesya.adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.clases.Perfil;
import inca.jesus.trajesya.data.conexion.VolleySingleton;
import inca.jesus.trajesya.data.modelo.Estado;
import inca.jesus.trajesya.data.modelo.Galeria;
import inca.jesus.trajesya.data.modelo.Medida;
import inca.jesus.trajesya.data.modelo.Producto;
import inca.jesus.trajesya.data.modelo.Reserva;
import inca.jesus.trajesya.data.modelo.ReservaItem;
import inca.jesus.trajesya.data.modelo.Sesion;
import inca.jesus.trajesya.data.modelo.TipoComprobante;
import inca.jesus.trajesya.data.modelo.TipoPago;
import inca.jesus.trajesya.data.modelo.TipoTarjeta;
import inca.jesus.trajesya.data.modelo.UbicacionDireccion;
import inca.jesus.trajesya.data.modelo.UnidadTerritorial;
import inca.jesus.trajesya.data.modelo.Usuario;
import inca.jesus.trajesya.data.utils.Constantes;
import inca.jesus.trajesya.R;

import static inca.jesus.trajesya.data.utils.Constantes.SUCCESS;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentSesion extends Fragment {
    public ImageView foto;
    public TextView nombre;
    public Button logout;
    LinearLayout modulo1;
    LinearLayout modulo2;
    LinearLayout SectorUbicaciones;
    TextView op1, op2, op3, op4;
    boolean menu;
    Button regreso;
    TextView correo_user;
    /*-------------------*/
    Sesion sesion;
    Context context;
    Usuario usuarioRecuperado;
    TextView tituloOpcion, tituloDescripcion;
    LinearLayout SectorLogo, SectorOpcionesUsuario, SectorOpcionesUsuarioExtras, SectorFbRegistro, SectorRegistro;
    EditText etRegistroNombre, etRegistroApellido, etRegistroDni, etRegistroCorreo, etRegistroUsuario, etRegistroPassword;
    Button btnRegistrarUsuario;
    ProgressDialog progressDialog;
    TextView OpcionCompletarInformacion;
    TextView txtUbicaciones;
    private CallbackManager callbackManager;
    LoginButton loginButton;
    public ProfileTracker profileTracker;
    ImageView ivRegistroImagen;
    String KeyFB = "";
    SharedPreferences.Editor editor;
    ImageView ivRegresarPerfil;
    Button btnAgregarUbicacion;
    public List<UnidadTerritorial> ListaDistritos;
    String[] spinnerDistritos;
    public List<UbicacionDireccion> ListaUbicaciones;
    public RecyclerView recyclerMisUbicaciones;
    public LinearLayoutManager linearLayoutUbicaciones;
    public AdapterUbicaciones adapterUbicaciones;
    RelativeLayout MensajeUbicacionesVacias;
    AlertDialog nuevaUbicacion;
    AlertDialog detalleReserva;
    TextView opcionReservas;
    LinearLayout SectorReservasDisponibles;
    ImageView ivRegresarPerfil2;
    RecyclerView recyclerReservasDisponibles;
    RelativeLayout MensajeReservassVacias;
    List<Reserva> ListaReservas;
    public LinearLayoutManager linearLayoutReservas;
    public AdapterReservas adapterReservas;
    TextView tituloMisReservas;
    TextView tituloMisUbicaciones;

    public fragmentSesion() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vie = inflater.inflate(R.layout.fragment_sesion, container, false);
        context = getActivity();
        sesion = new Sesion();
        tituloOpcion = vie.findViewById(R.id.tituloOpcion);
        tituloDescripcion = vie.findViewById(R.id.tituloDescripcion);
        modulo1 = vie.findViewById(R.id.modulo1);
        modulo2 = vie.findViewById(R.id.modulo2);
        SectorRegistro = vie.findViewById(R.id.SectorRegistro);
        SectorFbRegistro = vie.findViewById(R.id.SectorLoginFB);
        SectorUbicaciones=vie.findViewById(R.id.SectorUbicaciones);

        foto = vie.findViewById(R.id.sesion_imagen);
        nombre = vie.findViewById(R.id.sesion_nombre);
        logout = vie.findViewById(R.id.boton_cerrar_sesion);
        op1 = vie.findViewById(R.id.perfil_op1);
        op2 = vie.findViewById(R.id.perfil_op2);
        op3 = vie.findViewById(R.id.perfil_op3);
        op4 = vie.findViewById(R.id.perfil_op4);
        SectorLogo = vie.findViewById(R.id.SectorLogo);
        SectorOpcionesUsuario = vie.findViewById(R.id.OpcionesUsuario);
        SectorOpcionesUsuarioExtras = vie.findViewById(R.id.OpcionesUsuarioExtras);
        etRegistroNombre = vie.findViewById(R.id.etRegistroNombre);
        etRegistroApellido = vie.findViewById(R.id.etRegistroApellido);
        etRegistroDni = vie.findViewById(R.id.etRegistroDni);
        etRegistroCorreo = vie.findViewById(R.id.etRegistroCorreo);
        etRegistroUsuario = vie.findViewById(R.id.etRegistroUsuario);
        etRegistroPassword = vie.findViewById(R.id.etRegistroPassword);
        btnRegistrarUsuario = vie.findViewById(R.id.btnRegistrarUsuario);
        correo_user = vie.findViewById(R.id.correo_user);

        ivRegistroImagen = vie.findViewById(R.id.ivRegistroImagen);
        OpcionCompletarInformacion = vie.findViewById(R.id.OpcionCompletarInformacion);
        loginButton = vie.findViewById(R.id.loginFbSesion);
        regreso = vie.findViewById(R.id.regresar);
        txtUbicaciones=vie.findViewById(R.id.txtUbicaciones);
        ivRegresarPerfil=vie.findViewById(R.id.ivRegresarPerfil);
        btnAgregarUbicacion=vie.findViewById(R.id.btnAgregarUbicacion);
        recyclerMisUbicaciones=vie.findViewById(R.id.recyclerMisUbicaciones);

        SectorUbicaciones.setVisibility(View.GONE);
        MensajeUbicacionesVacias=vie.findViewById(R.id.MensajeUbicacionesVacias);
        opcionReservas=vie.findViewById(R.id.opcionReservas);
        SectorReservasDisponibles=vie.findViewById(R.id.SectorReservasDisponibles);
        ivRegresarPerfil2=vie.findViewById(R.id.ivRegresarPerfil2);
        recyclerReservasDisponibles=vie.findViewById(R.id.recyclerReservasDisponibles);
        MensajeReservassVacias=vie.findViewById(R.id.MensajeReservassVacias);
        tituloMisReservas=vie.findViewById(R.id.tituloMisReservas);
        tituloMisUbicaciones=vie.findViewById(R.id.tituloMisUbicaciones);
        menu = false;

        ListaUbicaciones=new ArrayList<>();
        ListaDistritos=new ArrayList<>();
        ListaReservas=new ArrayList<>();
        /*---------------------SETEAR TEXTO SUBRAYADO--------------------------*/
        SpannableString mitextoU = new SpannableString("COMPLETAR INFORMACIÓN");
        mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
        OpcionCompletarInformacion.setText(mitextoU);
        /*--------------------CREAR LLAMADO--------------------------*/
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions("email");
        loginButton.setFragment(this);
        /*-----------RECUPERAR INFORMACION DE SHAREDPREFERENCES------------*/
        SharedPreferences pref = context.getSharedPreferences("Sesion", context.MODE_PRIVATE);
        editor = pref.edit();
        /*-----------Recuperar Usuario------*/
        recuperarInformacionSesion();
        opcionRegresar();
        opcionesUsuario();
        opcionCerrarSesion();
        opcionRegistrarUsuario();
        sesionFacebook(context);
        opcionCompletarInformacion();
        opcionUbicaciones();
        opcionRegresarPerfil();
        opcionAgregarUbicacion();
        GenerarAdapterListadoUbicaciones();
        RecuperarDistritos();
        opcionReservas();
        GenerarAdapterListadoReservas();


        SpannableString mitextoReserva = new SpannableString("MIS RESERVAS");
        mitextoReserva.setSpan(new UnderlineSpan(), 0, mitextoReserva.length(), 0);
        tituloMisReservas.setText(mitextoReserva);
        SpannableString mitextoUbicaciones = new SpannableString("MIS UBICACIONES GUARDADAS");
        mitextoUbicaciones.setSpan(new UnderlineSpan(), 0, mitextoUbicaciones.length(), 0);
        tituloMisUbicaciones.setText(mitextoUbicaciones);



        return vie;
    }

    @SuppressLint("WrongConstant")
    private void GenerarAdapterListadoReservas() {
        linearLayoutReservas= new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        adapterReservas = new AdapterReservas(context, ListaReservas, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
                Constantes.RESERVA_DETALLE=(ListaReservas.get(position));
                MostrarDetalleReserva(ListaReservas.get(position));
            }
        });
        recyclerReservasDisponibles.setAdapter(adapterReservas);
        recyclerReservasDisponibles.setLayoutManager(linearLayoutReservas);

        adapterReservas.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                int size=adapterReservas.getItemCount();
                if(size<0){
                    recyclerReservasDisponibles.setVisibility(View.GONE);
                    MensajeUbicacionesVacias.setVisibility(View.VISIBLE);
                }else{
                    recyclerReservasDisponibles.setVisibility(View.VISIBLE);
                    MensajeUbicacionesVacias.setVisibility(View.GONE);
                }
            }
        });
    }

    private void MostrarDetalleReserva(Reserva reserva) {

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogDetalleReserva = inflater.inflate(R.layout.dialog_info_reserva, null);
        final TextView dialogCodigoReserva = dialogDetalleReserva.findViewById(R.id.txtDialogCodigoReserva);
        final TextView dialogContactoReserva=dialogDetalleReserva.findViewById(R.id.txtDialogContactoReserva);
        final TextView dialogDireccionReserva=dialogDetalleReserva.findViewById(R.id.txtDialogDireccionReserva);
        final TextView dialogDistritoReserva=dialogDetalleReserva.findViewById(R.id.txtDialogDistritoReserva);
        final TextView dialogEstadoReserva=dialogDetalleReserva.findViewById(R.id.txtDialogEstadoReserva);
        final TextView dialogFechaReserva=dialogDetalleReserva.findViewById(R.id.txtDialogFechaReserva);
        final TextView dialogHoraReserva=dialogDetalleReserva.findViewById(R.id.txtDialogHoraReserva);
        final TextView dialogPrecioBase=dialogDetalleReserva.findViewById(R.id.txtDialogPrecioBase);
        final TextView dialogPrecioDelivery=dialogDetalleReserva.findViewById(R.id.txtDialogPrecioDelivery);
        final TextView dialogPrecioDescuento=dialogDetalleReserva.findViewById(R.id.txtDialogPrecioDescuento);
        final TextView dialogPrecioTotal=dialogDetalleReserva.findViewById(R.id.txtDialogPrecioTotal);
        final TextView dialogReferenciaReserva=dialogDetalleReserva.findViewById(R.id.txtDialogReferenciaReserva);
        final TextView dialogTipoComprobante=dialogDetalleReserva.findViewById(R.id.txtDialogTipoComprobante);
        final TextView dialogTipoPago=dialogDetalleReserva.findViewById(R.id.txtDialogTipoPago);
        final ImageView eliminarDialogReservaDetalles=dialogDetalleReserva.findViewById(R.id.eliminarDialogReservaDetalles);
        final LinearLayout contenedorReservaItem=dialogDetalleReserva.findViewById(R.id.contenedorReservaItem);

        dialogCodigoReserva.setText(""+Correlativo(reserva.getIdReserva()));
        dialogContactoReserva.setText(""+reserva.getTelefono());
        dialogDireccionReserva.setText(""+reserva.getUbicacionDireccionReserva().getDireccionEntrega());
        dialogDistritoReserva.setText(""+reserva.getUbicacionDireccionReserva().getDistrito().getNombreUnidadTerritorial());
        dialogReferenciaReserva.setText(""+reserva.getUbicacionDireccionReserva().getReferenciaDireccion());
        if(reserva.getTipoTarjetaReserva().getNombreTarjeta()==null || reserva.getTipoTarjetaReserva().getNombreTarjeta()=="null"){
            dialogTipoPago.setText(""+reserva.getTipoPagoReserva().getNombreTipoPago());
        }else{
            dialogTipoPago.setText(""+reserva.getTipoPagoReserva().getNombreTipoPago()+" - "+reserva.getTipoTarjetaReserva().getNombreTarjeta());
        }

        dialogTipoComprobante.setText(""+reserva.getTipoComprobante().getNombreTipoComprobante());
        String estado=reserva.getEstadoReserva().getNombreEstado();
        dialogEstadoReserva.setText(estado);
        switch (estado){
            case "Nuevo":
                dialogEstadoReserva.setTextColor(context.getResources().getColor( R.color.nueva));
                break;
            case "Atendido":
                dialogEstadoReserva.setTextColor(context.getResources().getColor( R.color.asignada));
                break;
            case "Cerrado":
                dialogEstadoReserva.setTextColor(context.getResources().getColor( R.color.cerrada));
                break;
            case "Anulado":
                dialogEstadoReserva.setTextColor(context.getResources().getColor( R.color.anulada));
                break;
        }

        dialogFechaReserva.setText(reserva.getFechaEntrega());
        dialogHoraReserva.setText(reserva.getHoraReserva()+" "+reserva.getTiempo());

        DecimalFormat formateador = new DecimalFormat("###,###.00");
        int tipoReserva=reserva.getTipoReserva();
        double total=0;
        if(tipoReserva==1){
            dialogPrecioBase.setText(formateador.format(reserva.getTotalBase()));

            total=((reserva.getTotalBase()+
                            reserva.getTotalDelivery())
                            -reserva.getTotalDescuento());
        }else{
            dialogPrecioBase.setText(formateador.format(reserva.getTotalUrgencia()));

            total=((reserva.getTotalUrgencia()+
                    reserva.getTotalDelivery())
                    -reserva.getTotalDescuento());
        }

        dialogPrecioDelivery.setText(formateador.format(reserva.getTotalDelivery()));
        dialogPrecioDescuento.setText(formateador.format(reserva.getTotalDescuento()));


        if(total==0){
            dialogPrecioTotal.setText("S/ 0.00");
        }else{

            String valor=formateador.format(total);
            dialogPrecioTotal.setText("S/ "+valor);
        }


        RrecuperarListaDetalle(contenedorReservaItem,reserva);


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogDetalleReserva);
        detalleReserva = builder.show();

        eliminarDialogReservaDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detalleReserva.dismiss();
            }
        });

    }

    private void RrecuperarListaDetalle(LinearLayout contenedorReservaItem,Reserva reserva) {

        if (reserva.getListaItems().size()>0){
            for(int i=0;i<reserva.getListaItems().size();i++){
                contenedorReservaItem.addView(RecuperarElemento(reserva.getTipoReserva(),(i+1),reserva.getListaItems().get(i)));
            }
        }else{
            Toast.makeText(context, "No tiene Elementos Registrados", Toast.LENGTH_SHORT).show();
        }


    }

    private View RecuperarElemento(int tipoReserva, int i, ReservaItem reservaItem) {

        LinearLayout contenedor = new LinearLayout(context);
        contenedor.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        contenedor.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout contenedorA = new LinearLayout(context);
        contenedorA.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1f));
        contenedorA.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout contenedorB = new LinearLayout(context);
        contenedorB.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1f));
        contenedorB.setOrientation(LinearLayout.HORIZONTAL);

        TextView  num= new TextView(context);
        num.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,6f));
        num.setGravity(Gravity.CENTER_HORIZONTAL);
        num.setTextColor(context.getResources().getColor(R.color.SinCheck));
        num.setTextSize(10f);
        num.setTypeface(Typeface.MONOSPACE);
        num.setText(String.valueOf(i)+".- ");

        TextView NombreProducto=new TextView(context);
        NombreProducto.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1f));
        NombreProducto.setGravity(Gravity.START);
        NombreProducto.setTextColor(context.getResources().getColor(R.color.SinCheck));
        NombreProducto.setTextSize(10f);
        NombreProducto.setTypeface(Typeface.MONOSPACE);
        NombreProducto.setText(reservaItem.getProductoItem().getNombreProducto());



        contenedorA.addView(num);
        contenedorA.addView(NombreProducto);

        TextView CantidadProducto=new TextView(context);
        CantidadProducto.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1.2f));
        CantidadProducto.setGravity(Gravity.CENTER_HORIZONTAL);
        CantidadProducto.setTextColor(context.getResources().getColor(R.color.SinCheck));
        CantidadProducto.setTextSize(10f);
        CantidadProducto.setTypeface(Typeface.MONOSPACE);
        CantidadProducto.setText(String.valueOf(reservaItem.getCantidad()+" u."));

        TextView MedidaProducto=new TextView(context);
        MedidaProducto.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1.2f));
        MedidaProducto.setGravity(Gravity.CENTER_HORIZONTAL);
        MedidaProducto.setTextColor(context.getResources().getColor(R.color.SinCheck));
        MedidaProducto.setTextSize(10f);
        MedidaProducto.setTypeface(Typeface.MONOSPACE);
        MedidaProducto.setText(reservaItem.getMedidaReservaItem().getNombreMedida());

        TextView PrecioBase=new TextView(context);
        PrecioBase.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1f));
        PrecioBase.setGravity(Gravity.CENTER_HORIZONTAL);
        PrecioBase.setTextColor(context.getResources().getColor(R.color.SinCheck));
        PrecioBase.setTextSize(10f);
        PrecioBase.setTypeface(Typeface.MONOSPACE);

        DecimalFormat formateador = new DecimalFormat("###,###.00");
        // MONTO PERFIL
        if(tipoReserva==1){
            PrecioBase.setText("S/ "+formateador.format((reservaItem.getProductoItem().getPrecioBase()*reservaItem.getCantidad())));
        }else{
            PrecioBase.setText("S/ "+formateador.format((reservaItem.getProductoItem().getPrecioUrgencia()*reservaItem.getCantidad())));
        }
        contenedorB.addView(CantidadProducto);
        contenedorB.addView(MedidaProducto);
        contenedorB.addView(PrecioBase);
        contenedor.addView(contenedorA);
        contenedor.addView(contenedorB);

       return contenedor;
    }

    private void opcionReservas() {
        opcionReservas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modulo1.setVisibility(View.GONE);
                SectorReservasDisponibles.setVisibility(View.VISIBLE);
                recyclerReservasDisponibles.setVisibility(View.VISIBLE);
                Usuario usuario=sesion.RecuperarSesion(context);
                RecuperarReservasDisponibles(context,usuario.getIdUsuario());
            }
        });
    }

    private void RecuperarReservasDisponibles(final Context context,int idUser) {
        final String idUsuario=String.valueOf(idUser);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.GESTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean(SUCCESS);
                            ListaReservas.clear();
                            if (success) {
                                JSONArray reservas = jsonResponse.getJSONArray("reservas");
                                for (int i = 0; i < reservas.length(); i++) {
                                    JSONObject objeto = reservas.getJSONObject(i);
                                    Reserva reserva=new Reserva();
                                    reserva.setIdReserva(objeto.getInt("idReserva"));
                                    reserva.setTipoReserva(objeto.getInt("TipoReserva"));
                                    reserva.setFechaEntrega(objeto.getString("fechaReserva"));
                                    reserva.setHoraReserva(objeto.getString("horaReserva"));
                                    TipoTarjeta tipoTarjeta=new TipoTarjeta();
                                    tipoTarjeta.setNombreTarjeta(objeto.getString("NombreTarjeta"));
                                    reserva.setTipoTarjetaReserva(tipoTarjeta);
                                    TipoPago tipoPago=new TipoPago();
                                    tipoPago.setNombreTipoPago(objeto.getString("TipoPago"));
                                    reserva.setTipoPagoReserva(tipoPago);
                                    TipoComprobante tipoComprobante=new TipoComprobante();
                                    tipoComprobante.setNombreTipoComprobante(objeto.getString("NombreComprobante"));
                                    reserva.setTipoComprobante(tipoComprobante);
                                    Estado estado =new Estado();
                                    estado.setNombreEstado(objeto.getString("DescripcionEstado"));
                                    reserva.setEstadoReserva(estado);
                                    UbicacionDireccion ubicacionDireccion=new UbicacionDireccion();
                                    UnidadTerritorial distrito = new UnidadTerritorial();
                                    distrito.setNombreUnidadTerritorial(objeto.getString("distrito"));
                                    ubicacionDireccion.setDistrito(distrito);
                                    ubicacionDireccion.setDireccionEntrega(objeto.getString("UbicacionDireccion"));
                                    ubicacionDireccion.setReferenciaDireccion(objeto.getString("UbicacionReferencia"));
                                    reserva.setUbicacionDireccionReserva(ubicacionDireccion);
                                    reserva.setTelefono(objeto.getString("TelefonoContacto"));
                                    reserva.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    reserva.setTotalBase(objeto.getDouble("TotalBase"));
                                    reserva.setTotalDelivery(objeto.getDouble("TotalDelivery"));
                                    reserva.setTotalDescuento(objeto.getDouble("TotalDescuento"));
                                    reserva.setTotalUrgencia(objeto.getDouble("TotalUrgencia"));
                                    reserva.setTiempo(objeto.getString("tiempo"));

                                    if (!objeto.isNull("ItemProducto")) {
                                        JSONArray itemProducto = new JSONArray(objeto.getString("ItemProducto"));

                                        List<ReservaItem> ListareservaItems = new ArrayList<>();
                                        for (int u = 0; u < itemProducto.length(); u++) {
                                            JSONObject objetoItem = itemProducto.getJSONObject(u);
                                            ReservaItem reservaItem=new ReservaItem();
                                            reservaItem.setCantidad(objetoItem.getInt("Cantidad"));
                                            Producto producto = new Producto();
                                            producto.setNombreProducto(objetoItem.getString("NombreProducto"));
                                            producto.setPrecioBase(objetoItem.getDouble("PrecioAlquiler"));
                                            producto.setPrecioUrgencia(objetoItem.getDouble("Precioventa"));
                                            producto.setPorcentajeDescuento(objetoItem.getDouble("PrecioDescuento"));
                                            reservaItem.setProductoItem(producto);
                                            Medida medida=new Medida();
                                            medida.setNombreMedida(objetoItem.getString("NombreMedida"));
                                            reservaItem.setMedidaReservaItem(medida);
                                            ListareservaItems.add(reservaItem);
                                        }
                                        reserva.setListaItems(ListareservaItems);
                                    }else{
                                        Log.e("Inca", "Error de encontrar Items");
                                    }

                                    ListaReservas.add(reserva);
                                    adapterReservas.notifyDataSetChanged();
                                }

                            } else {
                                //Toast.makeText(context, "No se encuentran Reservas Registradas.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Inca", "Error JSON EN Reservas:" + e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(Constantes.OPERACION, "RecuperarReservas");
                params.put("idUsuario",idUsuario);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    private void RecuperarDistritos() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.GESTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean(SUCCESS);

                            if (success) {
                                JSONArray categorias = jsonResponse.getJSONArray("distritos");
                                for (int i = 0; i < categorias.length(); i++) {
                                    JSONObject objeto = categorias.getJSONObject(i);
                                    UnidadTerritorial distrito=new UnidadTerritorial();

                                    distrito.setIdUnidadTerritorial(objeto.getInt("idDistrito"));
                                    distrito.setNombreUnidadTerritorial(objeto.getString("distrito"));
                                    ListaDistritos.add(distrito);
                                }

                            } else {
                                Toast.makeText(context, "No se encuentran Ubicaciones Registradas.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Inca", "Error JSON EN Ubicaciones:" + e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(Constantes.OPERACION, "ListarDistritosLima");
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    private void opcionAgregarUbicacion() {
        btnAgregarUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View dialoglayout4 = inflater.inflate(R.layout.dialog_nueva_ubicacion, null);
                final Spinner spDistrito = dialoglayout4.findViewById(R.id.spDistrito);
                final Button btnRegistrarUbicacion=dialoglayout4.findViewById(R.id.btnRegistrarUbicacion);
                final EditText etDireccionUbicacion=dialoglayout4.findViewById(R.id.etDireccionUbicacion);
                final EditText etReferenciaUbicacion=dialoglayout4.findViewById(R.id.etReferenciaUbicacion);

                spinnerDistritos=new String[(ListaDistritos.size())];
                for (int i = 0; i < ListaDistritos.size(); i++) {
                    spinnerDistritos[i] = ListaDistritos.get(i).getNombreUnidadTerritorial();
                }
                ArrayAdapter<String> adapter_arr = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, spinnerDistritos);
                spDistrito.setAdapter(adapter_arr);

                AlertDialog.Builder builder4 = new AlertDialog.Builder(context);
                builder4.setView(dialoglayout4);
                nuevaUbicacion = builder4.show();

                btnRegistrarUbicacion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Recuperando Valores

                        String selectDistrito   = (String) spDistrito.getSelectedItem();
                        String selectDireccion  =  etDireccionUbicacion.getText().toString().trim().toUpperCase();
                        String selectReferencia = etReferenciaUbicacion.getText().toString().trim().toUpperCase();
                        int idDistrito=0;
                        for(int i=1;i<ListaDistritos.size();i++){
                            if(ListaDistritos.get(i-1).getNombreUnidadTerritorial().equalsIgnoreCase(selectDistrito)){
                                idDistrito=ListaDistritos.get(i-1).getIdUnidadTerritorial();
                            }
                        }

                        String mensaje=VerificarCamposUbicacion(selectDireccion,selectReferencia,idDistrito);
                        if(mensaje.length()==0){
                           RegistrarUbicacionNueva(context,idDistrito,selectDireccion,selectReferencia,nuevaUbicacion);
                        }else{
                            Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void RegistrarUbicacionNueva(final Context context, final int idDistritoRe, final String selectDireccion, final String selectReferencia, final AlertDialog nuevaUbicacion) {
        Usuario usuarioTemp=sesion.RecuperarSesion(context);
        final String idUsuario=String.valueOf(usuarioTemp.getIdUsuario());
        final String idDistrito=String.valueOf(idDistritoRe);

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Registro:");
        progressDialog.setMessage("Registrando Ubicación.......");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                nuevaUbicacion.dismiss();
                                ListaUbicaciones.clear();
                                Usuario usuarioRecu=sesion.RecuperarSesion(context);
                                Log.e("Inca","ID USUARIO:"+usuarioRecu.getIdUsuario());
                                RecuperarUbicacionesUsuario(context,usuarioRecu.getIdUsuario());
                            }
                            progressDialog.dismiss();
                            String Mensaje = jsonResponse.getString("mensaje");
                            Toast.makeText(context, Mensaje, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "RegistrarUbicacion");
                params.put("idUsuario",idUsuario);
                params.put("direccion", selectDireccion);
                params.put("referencia", selectReferencia);
                params.put("idDistrito", idDistrito);

                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }

    private void opcionRegresarPerfil() {

        ivRegresarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modulo1.setVisibility(View.VISIBLE);
                SectorUbicaciones.setVisibility(View.GONE);
            }
        });
        ivRegresarPerfil2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modulo1.setVisibility(View.VISIBLE);
                SectorReservasDisponibles.setVisibility(View.GONE);
            }
        });
    }

    @SuppressLint("WrongConstant")
    private void GenerarAdapterListadoUbicaciones() {

        linearLayoutUbicaciones= new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        adapterUbicaciones = new AdapterUbicaciones(context, ListaUbicaciones, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
            }
        });
        recyclerMisUbicaciones.setAdapter(adapterUbicaciones);
        recyclerMisUbicaciones.setLayoutManager(linearLayoutUbicaciones);

        adapterUbicaciones.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                int size=adapterUbicaciones.getItemCount();
                if(size<0){
                    recyclerMisUbicaciones.setVisibility(View.GONE);
                    MensajeReservassVacias.setVisibility(View.VISIBLE);
                    }else{
                    recyclerMisUbicaciones.setVisibility(View.VISIBLE);
                    MensajeReservassVacias.setVisibility(View.GONE);
                }
            }
        });
    }

    private void opcionUbicaciones() {
        txtUbicaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modulo1.setVisibility(View.GONE);
                SectorUbicaciones.setVisibility(View.VISIBLE);

                ListaUbicaciones.clear();
                Usuario usuarioRecu=sesion.RecuperarSesion(context);
                Log.e("Inca","ID USUARIO:"+usuarioRecu.getIdUsuario());
                RecuperarUbicacionesUsuario(context,usuarioRecu.getIdUsuario());
            }
        });
    }

    private void RecuperarUbicacionesUsuario(final Context context, final int idUsu) {
        final String idUsuario=String.valueOf(idUsu);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean(SUCCESS);

                            if (success) {
                                if (!jsonResponse.isNull("ubicaciones")) {
                                    JSONArray categorias = jsonResponse.getJSONArray("ubicaciones");

                                    for (int i = 0; i < categorias.length(); i++) {
                                        JSONObject objeto = categorias.getJSONObject(i);
                                        UbicacionDireccion ubicacion=new UbicacionDireccion();
                                        ubicacion.setIdUbicacionDireccion(objeto.getInt("idReservaUbicaciones"));
                                        ubicacion.setDireccionEntrega(objeto.getString("DireccionEntrega"));
                                        ubicacion.setReferenciaDireccion(objeto.getString("ReferenciaDireccion"));
                                        UnidadTerritorial distrito=new UnidadTerritorial();
                                        distrito.setIdUnidadTerritorial(objeto.getInt("Distrito_idDistrito"));
                                        distrito.setNombreUnidadTerritorial(objeto.getString("distrito"));
                                        ubicacion.setDistrito(distrito);
                                        ubicacion.setFechaRegistro(objeto.getString("fechaRegistro"));
                                        ubicacion.setPrecioDelivery(objeto.getDouble("precioDelivery"));
                                        ListaUbicaciones.add(ubicacion);
                                        adapterUbicaciones.notifyDataSetChanged();
                                    }
                                }else{
                                    Log.e("Inca", "No se encuentran Ubicaciones Registradas.");
                                }

                            } else {
                                Toast.makeText(context, "No se encuentran Ubicaciones Registradas.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Inca", "Error JSON EN Ubicaciones:" + e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(Constantes.OPERACION, "RecuperarUbicaciones");
                params.put("idUsuario", idUsuario);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    private void opcionCompletarInformacion() {
        OpcionCompletarInformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SectorFbRegistro.setVisibility(View.GONE);
                SectorLogo.setVisibility(View.VISIBLE);
                SectorOpcionesUsuario.setVisibility(View.GONE);
                SectorOpcionesUsuarioExtras.setVisibility(View.GONE);
                modulo1.setVisibility(View.GONE);
                modulo2.setVisibility(View.GONE);
                SectorRegistro.setVisibility(View.VISIBLE);
                String Nombres = sesion.RecuperarValor(context, "nombres");
                String Apellidos = sesion.RecuperarValor(context, "apellidos");
                String imagen = sesion.RecuperarValor(context, "imagenProducto");
                String correo = sesion.RecuperarValor(context, "Correo");
                KeyFB = sesion.RecuperarValor(context, "KeyFacebook");
                Picasso.get()
                        .load(imagen)
                        .placeholder(R.drawable.default_imagen)
                        .error(R.drawable.default_imagen)
                        .into(ivRegistroImagen);
                etRegistroNombre.setText(Nombres);
                etRegistroApellido.setText(Apellidos);
                etRegistroCorreo.setText(correo);
            }
        });
    }

    private void opcionRegistrarUsuario() {
        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Mensaje = VerificarCampos();
                if (Mensaje.length() == 0) {
                    Usuario temp = new Usuario();
                    temp.setNombreUsuario(etRegistroNombre.getText().toString());
                    temp.setApellidoUsuario(etRegistroApellido.getText().toString());
                    temp.setDniUsuario(etRegistroDni.getText().toString());
                    temp.setCorreoUsuario(etRegistroCorreo.getText().toString());
                    temp.setUsuario(etRegistroUsuario.getText().toString());
                    temp.setPassword(etRegistroPassword.getText().toString());
                    temp.setImagenUsuario(sesion.RecuperarValor(context, "imagenProducto"));

                    RegistrarUsuarioServidor(context, temp);
                } else {
                    Toast.makeText(context, Mensaje, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void opcionRegresar() {
        regreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu = false;
                modulo1.setVisibility(View.VISIBLE);
                modulo2.setVisibility(View.GONE);
            }
        });
    }

    private void opcionCerrarSesion() {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CerrarSesion();
            }
        });
    }

    private void opcionesUsuario() {
        op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tituloOpcion.setText("TÉRMINOS Y CONDICIONES");
                tituloDescripcion.setText(Constantes.TERMINOS_CONDICIONES);
                menu = true;
                modulo1.setVisibility(View.GONE);
                modulo2.setVisibility(View.VISIBLE);
            }
        });
        op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tituloOpcion.setText("POLITICA DE ALQUILER");
                tituloDescripcion.setText(Constantes.POLITICA_ALQUILER);
                menu = true;
                modulo1.setVisibility(View.GONE);
                modulo2.setVisibility(View.VISIBLE);
            }
        });

        op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tituloOpcion.setText("PRIVACIDAD DE CONFIDENCIALIDAD");
                tituloDescripcion.setText(Constantes.PRIVACIDAD_CONFIDENCIALIDAD);
                menu = true;
                modulo1.setVisibility(View.GONE);
                modulo2.setVisibility(View.VISIBLE);
            }
        });
        op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tituloOpcion.setText("PREGUNTAS FRECUENTES");
                tituloDescripcion.setText(Constantes.PREGUNTAS_PRECUENTES);
                menu = true;
                modulo1.setVisibility(View.GONE);
                modulo2.setVisibility(View.VISIBLE);
            }
        });
    }

    private void recuperarInformacionSesion() {

        /*Verificacion de Vistas Segun Sesion*/
        usuarioRecuperado = sesion.RecuperarSesion(context);

        if (!usuarioRecuperado.isSesion() && !usuarioRecuperado.isSesionFB()) {
            SesionFiltro(1);
        } else {
            if (!usuarioRecuperado.isSesion()) {
                verificarUsuarioFbLogin(context);
            } else {
                SesionFiltro(3);
            }
        }
    }

    public void SesionFiltro(int dato) {

        String Nombres = sesion.RecuperarValor(context, "nombres");
        String Apellido = sesion.RecuperarValor(context, "apellidos");
        String imagen = sesion.RecuperarValor(context, "imagenProducto");
        String correo = sesion.RecuperarValor(context, "Correo");

        String keyFB=sesion.RecuperarValor(context,"KeyFacebook");
        if(keyFB.length()==0){
            imagen=Constantes.PATH_IMAGEN+imagen;
        }
        switch (dato) {
            case 1:
                /*Ingreso  sin usuario*/
                SectorFbRegistro.setVisibility(View.VISIBLE);
                SectorLogo.setVisibility(View.GONE);
                SectorOpcionesUsuario.setVisibility(View.GONE);
                SectorOpcionesUsuarioExtras.setVisibility(View.GONE);
                modulo1.setVisibility(View.GONE);
                modulo2.setVisibility(View.GONE);
                SectorRegistro.setVisibility(View.GONE);
                /*mostrar informacion*/
                nombre.setText("Bienvenido");
                correo_user.setText("Invitado");
                logout.setVisibility(View.GONE);
                OpcionCompletarInformacion.setVisibility(View.GONE);
                break;
            case 2:
                /*Ingreso con Cuenta de FB pero no esta registrado*/
                SectorFbRegistro.setVisibility(View.GONE);
                SectorLogo.setVisibility(View.VISIBLE);
                SectorOpcionesUsuario.setVisibility(View.GONE);
                SectorOpcionesUsuarioExtras.setVisibility(View.GONE);
                modulo1.setVisibility(View.VISIBLE);
                modulo2.setVisibility(View.GONE);
                SectorRegistro.setVisibility(View.GONE);

                Picasso.get()
                        .load(imagen)
                        .placeholder(R.drawable.default_imagen)
                        .error(R.drawable.default_imagen)
                        .into(foto);
                nombre.setText(Nombres + " " + Apellido);
                correo_user.setText(correo);

                logout.setVisibility(View.VISIBLE);
                OpcionCompletarInformacion.setVisibility(View.VISIBLE);
                break;
            case 3:
                /*Ingreso con cuenta ya registrada para compras*/
                SectorFbRegistro.setVisibility(View.GONE);
                SectorLogo.setVisibility(View.VISIBLE);
                SectorOpcionesUsuario.setVisibility(View.VISIBLE);
                SectorOpcionesUsuarioExtras.setVisibility(View.VISIBLE);
                modulo1.setVisibility(View.VISIBLE);
                modulo2.setVisibility(View.GONE);
                SectorRegistro.setVisibility(View.GONE);

                Picasso.get()
                        .load(imagen)
                        .placeholder(R.drawable.default_imagen)
                        .error(R.drawable.default_imagen)
                        .into(foto);
                nombre.setText(Nombres + " " + Apellido);
                correo_user.setText(correo);
                logout.setVisibility(View.VISIBLE);
                OpcionCompletarInformacion.setVisibility(View.GONE);
                break;
        }

    }

    private void CerrarSesion() {
        final androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("SALIR")
                .setMessage("¿Desea Cerrar Sesión?")
                .setPositiveButton("SI",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LoginManager.getInstance().logOut();
                                sesion.EliminarSesion(context);
                                Intent intent = new Intent(context, LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                .setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
        builder.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public String VerificarCampos() {
        String mensaje = "";

        // Recuperar Link de Imagen

        String etNombre = etRegistroNombre.getText().toString().trim();
        String etApellido = etRegistroApellido.getText().toString().trim();
        String etDni = etRegistroDni.getText().toString().trim();
        String etCorreo = etRegistroCorreo.getText().toString().trim();
        String etUsuario = etRegistroUsuario.getText().toString().trim();
        String etPassword = etRegistroPassword.getText().toString().trim();


        mensaje = (etNombre.length() == 0) ? (mensaje + "- Ingrese Nombre de Usuario.\n") : (mensaje + "");
        mensaje = (etApellido.length() == 0) ? (mensaje + "- Ingrese Apellido de Usuario.\n") : (mensaje + "");
        mensaje = (etDni.length() == 0) ? (mensaje + "- Ingrese DNI de Usuario.\n") : (mensaje + "");

        mensaje = (etUsuario.length() == 0) ? (mensaje + "- Ingrese Usuario.\n") : (mensaje + "");
        mensaje = (etPassword.length() == 0) ? (mensaje + "- Ingrese Contraseña.\n") : (mensaje + "");

        mensaje = (etDni.length() != 8) ? (mensaje + "- Dni Ingresado debe ser de 8 digitos.\n") : (mensaje + "");
        mensaje = (etUsuario.length() < 6) ? (mensaje + "- Ingrese Usuario mayor o igual a 6 caracteres.\n") : (mensaje + "");
        mensaje = (etPassword.length() < 6) ? (mensaje + "- Ingrese Contraseña mayor o igual a a 6 caracteres.\n") : (mensaje + "");

        if (!emailValidator(etCorreo)) {
            mensaje = mensaje + "- Correo no tiene el Formato Correcto( Example@dominio.com).\n";
        }

        return mensaje;
    }

    public String VerificarCamposUbicacion(String selectDireccion, String selectReferencia, int idDistrito) {
        String mensaje = "";
        mensaje = (idDistrito == 0) ? (mensaje + "- Seleccione Distrito.\n") : (mensaje + "");
        mensaje = (selectDireccion.length() == 0) ? (mensaje + "- Ingrese Dirección.\n") : (mensaje + "");
        mensaje = (selectReferencia.length() == 0) ? (mensaje + "- Ingrese Referencia.\n") : (mensaje + "");


        return mensaje;
    }
    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void RegistrarUsuarioServidor(final Context context, final Usuario usuario) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Registro:");
        progressDialog.setMessage("Registrando Usuario.......");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Usuario Nuevo = new Usuario();
                                Nuevo.setIdUsuario(jsonResponse.getInt("idUsuario"));
                                Nuevo.setUsuario(jsonResponse.getString("usuario"));
                                Nuevo.setNombreUsuario(jsonResponse.getString("nombres"));
                                Nuevo.setApellidoUsuario(jsonResponse.getString("apellidos"));
                                Nuevo.setImagenUsuario(jsonResponse.getString("imagenProducto"));
                                Nuevo.setCorreoUsuario(jsonResponse.getString("correo"));
                                Nuevo.setSesion(true);
                                Perfil perfil = new Perfil();
                                perfil.setIdPerfil(jsonResponse.getInt("idPerfil"));
                                perfil.setNombrePrefil(jsonResponse.getString("perfil"));
                                Nuevo.setPerfilUsuario(perfil);
                                Nuevo.setSesion(true);
                                Nuevo.setSesionFB(false);
                                sesion.RegistrarSesion(context, Nuevo);

                                String Mensaje = jsonResponse.getString("mensaje");
                                progressDialog.dismiss();

                                Toast.makeText(context, Mensaje, Toast.LENGTH_SHORT).show();

                                SesionFiltro(3);
                            } else {

                                progressDialog.dismiss();
                                String Mensaje = jsonResponse.getString("mensaje");
                                Toast.makeText(context, Mensaje, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "RegistrarUsuario");
                params.put("RegistroUsuario", usuario.getUsuario());
                params.put("RegistroPassword", usuario.getPassword());
                params.put("RegistroNombre", usuario.getNombreUsuario());
                params.put("RegistroApellido", usuario.getApellidoUsuario());
                params.put("RegistroDni", usuario.getDniUsuario());
                params.put("RegistroCorreo", usuario.getCorreoUsuario());
                params.put("ImagenUsuario", usuario.getImagenUsuario());
                params.put("KeyFb", KeyFB);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void sesionFacebook(final Context context) {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i("Inca", "Ingreso OnSUcces");
                InicioDesdeFBSesion(context);
            }

            @Override
            public void onCancel() {
                Toast.makeText(context, R.string.cancel_login, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(context, R.string.error_login, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void InicioDesdeFBSesion(Context context) {
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if (currentProfile != null) {
                    Log.i("Inca", "Ingreso a Recuperar Informacion de Sesion FB");
                    displayProfileInfo(currentProfile);
                }
            }
        };

        if (AccessToken.getCurrentAccessToken() == null) {
            sesion.RegistrarVariable(editor, this.context, "Sesion", "boolean", "false");
            sesion.RegistrarVariable(editor, this.context, "SesionFB", "boolean", "false");

        } else {
            requestEmail(context, AccessToken.getCurrentAccessToken());

            Profile profile = Profile.getCurrentProfile();
            if (profile != null) {
                displayProfileInfo(profile);
            } else {
                Profile.fetchProfileForCurrentAccessToken();
            }
        }
    }

    private void requestEmail(final Context context, AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if (response.getError() != null) {
                    Toast.makeText(context, response.getError().getErrorMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    String email = object.getString("email");
                    setEmail(email);
                } catch (JSONException e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email, gender, birthday, location");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void setEmail(String email) {
        sesion.RegistrarVariable(editor, context, "Correo", "String", email);
        correo_user.setText(email);
    }

    private void displayProfileInfo(Profile profile) {
        verificarUsuarioFbButton(profile);
    }

    private void verificarUsuarioFbButton(final Profile profile) {
        final String KEY_FB=profile.getId();

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Registro:");
        progressDialog.setMessage("Buscando Usuario.......");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Usuario Nuevo = new Usuario();
                                Nuevo.setIdUsuario(jsonResponse.getInt("idUsuario"));
                                Nuevo.setUsuario(jsonResponse.getString("usuario"));
                                Nuevo.setNombreUsuario(jsonResponse.getString("nombres"));
                                Nuevo.setApellidoUsuario(jsonResponse.getString("apellidos"));
                                Nuevo.setImagenUsuario(jsonResponse.getString("imagenProducto"));
                                Nuevo.setCorreoUsuario(jsonResponse.getString("correo"));
                                Nuevo.setSesion(true);
                                Perfil perfil = new Perfil();
                                perfil.setIdPerfil(jsonResponse.getInt("idPerfil"));
                                perfil.setNombrePrefil(jsonResponse.getString("perfil"));
                                Nuevo.setPerfilUsuario(perfil);
                                Nuevo.setKeyFacebook(jsonResponse.getString("keyFb"));
                                Nuevo.setSesion(true);
                                Nuevo.setSesionFB(true);
                                sesion.RegistrarSesion(context, Nuevo);

                                String Mensaje = jsonResponse.getString("mensaje");
                                progressDialog.dismiss();

                                Toast.makeText(context, Mensaje, Toast.LENGTH_SHORT).show();

                                SesionFiltro(3);
                            } else {
                                sesion.RegistrarVariable(editor, context, "SesionFB", "boolean", "true");
                                sesion.RegistrarVariable(editor, context, "KeyFacebook", "String", profile.getId());
                                sesion.RegistrarVariable(editor, context, "nombres", "String", profile.getFirstName());
                                sesion.RegistrarVariable(editor, context, "apellidos", "String", profile.getLastName());
                                sesion.RegistrarVariable(editor, context, "imagenProducto", "String", String.valueOf(profile.getProfilePictureUri(100, 100)));
                                SesionFiltro(2);

                                progressDialog.dismiss();
                                //String Mensaje = jsonResponse.getString("mensaje");
                                //Toast.makeText(context, Mensaje, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "BuscarUsuario");
                params.put("KeyFb", KEY_FB);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }

    private void verificarUsuarioFbLogin(Context context) {

        final String KEY_FB=sesion.RecuperarValor(context,"KeyFacebook");

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Registro:");
        progressDialog.setMessage("Buscando Usuario.......");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Usuario Nuevo = new Usuario();
                                Nuevo.setIdUsuario(jsonResponse.getInt("idUsuario"));
                                Nuevo.setUsuario(jsonResponse.getString("usuario"));
                                Nuevo.setNombreUsuario(jsonResponse.getString("nombres"));
                                Nuevo.setApellidoUsuario(jsonResponse.getString("apellidos"));
                                Nuevo.setImagenUsuario(jsonResponse.getString("imagen"));
                                Nuevo.setCorreoUsuario(jsonResponse.getString("correo"));
                                Nuevo.setSesion(true);
                                Perfil perfil = new Perfil();
                                perfil.setIdPerfil(jsonResponse.getInt("idPerfil"));
                                perfil.setNombrePrefil(jsonResponse.getString("perfil"));
                                Nuevo.setPerfilUsuario(perfil);
                                Nuevo.setKeyFacebook(jsonResponse.getString("keyFb"));
                                Nuevo.setSesion(true);
                                Nuevo.setSesionFB(true);
                                sesion.RegistrarSesion(fragmentSesion.this.context, Nuevo);

                                String Mensaje = jsonResponse.getString("mensaje");
                                progressDialog.dismiss();

                                Toast.makeText(fragmentSesion.this.context, Mensaje, Toast.LENGTH_SHORT).show();

                                SesionFiltro(3);
                            } else {

                                SesionFiltro(2);
                                progressDialog.dismiss();
                                //String Mensaje = jsonResponse.getString("mensaje");
                                //Toast.makeText(context, Mensaje, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "BuscarUsuario");
                params.put("KeyFb", KEY_FB);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public String Correlativo(int idReserva){
        String correlativo="";
        int len=String.valueOf(idReserva).length();
        int numCeros=5-len;
        String cero="0";
        String ceros=  repeat(cero,numCeros);
        correlativo="R-"+ceros+idReserva;
        return correlativo;
    }
    public static String repeat(String val, int count){
        StringBuilder buf = new StringBuilder(val.length() * count);
        while (count-- > 0) {
            buf.append(val);
        }
        return buf.toString();
    }

}
