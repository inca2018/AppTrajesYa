package inca.jesus.trajesya.fragmentos;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import inca.jesus.trajesya.R;
import inca.jesus.trajesya.activities.ActivityPrincipal;
import inca.jesus.trajesya.adapters.AdapterItemReservaEnvio;
import inca.jesus.trajesya.adapters.AdapterTipoComprobante;
import inca.jesus.trajesya.adapters.AdapterTipoPago;
import inca.jesus.trajesya.adapters.AdapterTipoTarjeta;
import inca.jesus.trajesya.adapters.AdapterUbicacionesEnvio;
import inca.jesus.trajesya.adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.clases.Perfil;
import inca.jesus.trajesya.data.conexion.VolleySingleton;
import inca.jesus.trajesya.data.modelo.ReservaItem;
import inca.jesus.trajesya.data.modelo.Sesion;
import inca.jesus.trajesya.data.modelo.TipoPago;
import inca.jesus.trajesya.data.modelo.UbicacionDireccion;
import inca.jesus.trajesya.data.modelo.UnidadTerritorial;
import inca.jesus.trajesya.data.modelo.Usuario;
import inca.jesus.trajesya.data.utils.Constantes;

import static inca.jesus.trajesya.data.utils.Constantes.SUCCESS;

public class fragmentEnvioReserva extends Fragment {
    RecyclerView recycler;
    LinearLayoutManager linear1;
    AdapterItemReservaEnvio adapterItemReservaEnvio;
    LinearLayout panelPrincipal;
    LinearLayout panelSeleccionUbicacion;
    LinearLayout panelMetodoPago;
    LinearLayout panelBotonesReserva;
    LinearLayout panelTipoComprobante;
    LinearLayout panelFechaContacto;
    ImageView accionSectorUbicacion;
    ImageView accionSectorMetodoPago;
    ImageView accionSectorTipoComprobante;
    ImageView accionSectorFechaContacto;
    CheckBox c_condiciones;
    boolean status=false;
    AlertDialog d,s;
    TextView btn_cupon;
    TextView text_cupon;
    String codigo="";
    Context context;
    RecyclerView recyclerUbicacionesRecuperadas;
    List<UbicacionDireccion> ListaUbicaciones;
    public AdapterUbicacionesEnvio adapterUbicaciones;
    LinearLayoutManager linearLayoutUbicaciones;
    Sesion sesion=new Sesion();
    Button btnVolverReserva;
    Button btnSectorUbicacionVolver;
    Button btnSectorUbicacionNuevaUbicacion;
    Button btnSectorContactoVolver;
    Button btnSectorContactoGuardar;
    Toolbar toolbarReservaEnvio;
    ScrollView scrollEnvioReserva;
    TextView txtPrincipalDireccion;
    TextView txtPrincipalReferencia;
    TextView txtPrincipalUsuario;
    TextView txtPrincipalDistrito;
    ImageView ivAccionFecha;
    TextView txtAccionFecha;
    ImageView ivAccionHora;
    TextView txtAccionHora;
    CheckBox checkUrgencia;
    boolean URGENCIA=false;
    boolean SAVE_CONTACTO=false;
    EditText etContactoTelefono;
    TextView txtPrincipalMetodoPago;
    TextView txtPrincipalTipoComprobante;
    TextView txtCondiciones;
    Button btnEnviarReserva;
    TextView MontoEnvio;
    TextView MontoBase;
    TextView MontoUrgencia;
    TextView MontoDescuento;
    TextView MontoDelivery;
    int DAY;
    int MONTH;
    int YEAR;
    int HOUR;
    int MINUTE;
    String AMPM;
    private static final String CERO = "0";
    private static final String BARRA = "/";
    TextView txtPrincipalFechaEntrega;
    TextView txtPrincipalHoraEntrega;
    TextView txtPrincipalTelefonoEntrega;
    Button btnSectorTipoPagoVolver;
    RecyclerView recyclerTipoPago;
    LinearLayoutManager linearLayoutTipoPago;
    public AdapterTipoPago adapterTipoPago;
    RecyclerView recyclerTipoTarjeta;
    LinearLayoutManager linearLayoutTipoTarjeta;
    public AdapterTipoTarjeta adapterTipoTarjeta;
    public Button btnSectorTipoComprobanteVolver;
    RecyclerView recyclerTipoComprobante;
    LinearLayoutManager linearLayoutTipoComprobante;
    public AdapterTipoComprobante adapterTipoComprobante;
    TextView sectorTituloTipoTarjeta;
    LinearLayout linearMontoUrgencia;
    int TipoUrgencia=0;
    Usuario usuarioTemporal;


    public fragmentEnvioReserva() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_envio_reserva, container, false);
        context=getActivity();
        recycler=v.findViewById(R.id.recycler_compra_pedido);
        accionSectorUbicacion =v.findViewById(R.id.accionSectorUbicacion);
        accionSectorFechaContacto =v.findViewById(R.id.accionSectorFechaContacto);
        accionSectorMetodoPago =v.findViewById(R.id.accionSectorMetodoPago);
        accionSectorTipoComprobante =v.findViewById(R.id.accionSectorTipoComprobante);

        panelPrincipal =v.findViewById(R.id.panelPrincipal);
        panelSeleccionUbicacion =v.findViewById(R.id.panelSeleccionUbicacion);
        panelFechaContacto =v.findViewById(R.id.panelFechaContacto);
        panelMetodoPago =v.findViewById(R.id.panelMetodoPago);
        panelTipoComprobante =v.findViewById(R.id.panelTipoComprobante);
        panelBotonesReserva =v.findViewById(R.id.panelBotonesReserva);

        recyclerUbicacionesRecuperadas=v.findViewById(R.id.recyclerUbicacionesRecuperadas);
        ListaUbicaciones=new ArrayList<>();


        btnVolverReserva=v.findViewById(R.id.btnVolverReserva);
        btnSectorUbicacionVolver=v.findViewById(R.id.btnSectorUbicacionVolver);
        btnSectorUbicacionNuevaUbicacion=v.findViewById(R.id.btnSectorUbicacionNuevaUbicacion);
        toolbarReservaEnvio=v.findViewById(R.id.toolbarReservaEnvio);
        scrollEnvioReserva=v.findViewById(R.id.scrollEnvioReserva);

        txtPrincipalUsuario=v.findViewById(R.id.txtPrincipalUsuario);
        txtPrincipalDireccion=v.findViewById(R.id.txtPrincipalDireccion);
        txtPrincipalReferencia=v.findViewById(R.id.txtPrincipalReferencia);
        txtPrincipalDistrito=v.findViewById(R.id.txtPrincipalDistrito);

        btnSectorContactoVolver=v.findViewById(R.id.btnSectorContactoVolver);
        btnSectorContactoGuardar=v.findViewById(R.id.btnSectorContactoGuardar);

        /*--------------VARIABLES FECHA Y HORA----------------*/
        ivAccionFecha=v.findViewById(R.id.ivAccionFecha);
        txtAccionFecha=v.findViewById(R.id.txtAccionFecha);
        ivAccionHora=v.findViewById(R.id.ivAccionHora);
        txtAccionHora=v.findViewById(R.id.txtAccionHora);

        etContactoTelefono=v.findViewById(R.id.etContactoTelefono);

        checkUrgencia=v.findViewById(R.id.checkUrgencia);
        MontoBase=v.findViewById(R.id.MontoBase);
        MontoUrgencia=v.findViewById(R.id.MontoUrgencia);
        MontoDescuento=v.findViewById(R.id.MontoDescuento);
        linearMontoUrgencia=v.findViewById(R.id.linearMontoUrgencia);
        linearMontoUrgencia.setVisibility(View.GONE);
        if(URGENCIA){
            checkUrgencia.setChecked(true);
        }else{
            checkUrgencia.setChecked(false);
        }

        txtPrincipalFechaEntrega=v.findViewById(R.id.txtPrincipalFechaEntrega);
        txtPrincipalHoraEntrega=v.findViewById(R.id.txtPrincipalHoraEntrega);
        txtPrincipalTelefonoEntrega=v.findViewById(R.id.txtPrincipalTelefonoEntrega);

        btnSectorTipoPagoVolver=v.findViewById(R.id.btnSectorTipoPagoVolver);


        txtPrincipalMetodoPago=v.findViewById(R.id.txtPrincipalMetodoPago);

        recyclerTipoPago=v.findViewById(R.id.recyclerTipoPago);
        recyclerTipoTarjeta=v.findViewById(R.id.recyclerTipoTarjeta);
        recyclerTipoComprobante=v.findViewById(R.id.recyclerTipoComprobante);
        btnSectorTipoComprobanteVolver=v.findViewById(R.id.btnSectorTipoComprobanteVolver);
        txtPrincipalTipoComprobante=v.findViewById(R.id.txtPrincipalTipoComprobante);
        sectorTituloTipoTarjeta=v.findViewById(R.id.sectorTituloTipoTarjeta);

        btnEnviarReserva=v.findViewById(R.id.btnEnviarReserva);

        MontoEnvio=v.findViewById(R.id.MontoEnvio);
        MontoDelivery=v.findViewById(R.id.MontoDelivery);


        /************************************/

        c_condiciones=v.findViewById(R.id.check_condiciones);


        btn_cupon=v. findViewById(R.id.btn_cupon);
        text_cupon=v.findViewById(R.id.text_cupon);

        txtCondiciones=v.findViewById(R.id.txtCondiciones);

        listadoItemReservaEnvio();

        condiciones();

        if(status==true){
            c_condiciones.setChecked(true);
        }else{
            c_condiciones.setChecked(false);
        }

        btn_cupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                final View dialoglayout4 = inflater.inflate(R.layout.cupon, null);
                final EditText tex_cupon=(EditText)dialoglayout4.findViewById(R.id.codigo_cupon);
                final Button aceptar_cupon=(Button) dialoglayout4.findViewById(R.id.btn_guardar_cupon);
                aceptar_cupon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        codigo=tex_cupon.getText().toString();
                        text_cupon.setText(codigo);
                        s.dismiss();
                    }
                });

                AlertDialog.Builder builder4 = new AlertDialog.Builder(context);
                builder4.setView(dialoglayout4);
                s=builder4.show();
            }
        });
        listadoUbicaciones();
        listadoTipoPago();
        listadoTipoTarjeta();
        listadoTipoComprobante();
        accionesMovimientoPaneles();
        accionBotones();
        /*------------Recuperar Ubicaciones----------------------*/
        usuarioTemporal=sesion.RecuperarSesion(context);
        RecuperarUbicacionesUsuario(context,usuarioTemporal.getIdUsuario());
        MostrarValoresPrincipal();
        AccionBotonesContacto();
        VerificacionUrgencia();
        AccionEnviarReserva();


        txtCondiciones.setText(Constantes.TEXT_CONDICION_RESERVA);
        return v;
    }
    public void AccionEnviarReserva() {
        btnEnviarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerificacionInformacionEnvio();
            }
        });
    }

    public void VerificacionInformacionEnvio() {
        String error="";
        if(Constantes.UBICACION_SELECT.getIdUbicacionDireccion()==0){
           error=error+"- Seleccione Ubicación de entrega. \n";
        }

        if(!SAVE_CONTACTO){
            error=error+"- Ingrese el número Contacto.\n";
        }

        if(Constantes.TIPO_COMPROBANTE_SELECT.getIdTipoComprobante()==0){
            error=error+"- Seleccione Tipo de Comprobante. \n";
        }

        if(Constantes.TIPO_PAGO_SELECT.getIdTipoPago()==0){
            error=error+"- Seleccione Método de Pago. \n";
        }
        if(Constantes.TIPO_PAGO_SELECT.getIdTipoPago()==2){
            if(Constantes.TIPO_TARJETA_SELECT.getIdTipoTarjeta()==0){
                error=error+"- Seleccione Tipo de Tarjeta. \n";
            }
        }

        if(URGENCIA){
            TipoUrgencia=2;
        }else{
            TipoUrgencia=1;
        }
        if(!status){
            error=error+"- Acepte Condiciones de Reserva. \n";
        }

        if(error.length()==0){
            //Toast.makeText(context, "Envio Reserva", Toast.LENGTH_SHORT).show();
            EnviarInformacionReservaService(context);
        }else{
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
        }

    }

    private void EnviarInformacionReservaService(final Context context) {

        final String TipoReserva=String.valueOf(TipoUrgencia);
        final String FechaReserva=Constantes.FECHA_RESERVA+" "+Constantes.HORA_RESERVA;
        final String TipoPago=String.valueOf(Constantes.TIPO_PAGO_SELECT.getIdTipoPago());
        final String TipoDocumento=String.valueOf(Constantes.TIPO_COMPROBANTE_SELECT.getIdTipoComprobante());
        final String TipoTarjeta=String.valueOf(Constantes.TIPO_TARJETA_SELECT.getIdTipoTarjeta());
        final String Usuario=String.valueOf(usuarioTemporal.getIdUsuario());
        final String Ubicacion=Constantes.UBICACION_SELECT.getDireccionEntrega();
        final String Referencia=Constantes.UBICACION_SELECT.getReferenciaDireccion();
        final String Telefono=etContactoTelefono.getText().toString();
        final String Distrito=String.valueOf(Constantes.UBICACION_SELECT.getDistrito().getIdUnidadTerritorial());
        final String Estado="3";
        final String Condiciones="1";
        final String Tiempo=Constantes.TIEMPO;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.GESTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                int idReserva = jsonResponse.getInt("idReserva");
                                 EnviarItemReservaService(idReserva);
                            } else {
                                Toast.makeText(context, "Error al Enviar Reserva", Toast.LENGTH_SHORT).show();
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
                params.put("operacion", "RegistrarReserva");
                params.put("TipoReserva", TipoReserva);
                params.put("FechaReserva",FechaReserva);
                params.put("TipoPago",TipoPago);
                params.put("TipoDocumento",TipoDocumento);
                params.put("TipoTarjeta",TipoTarjeta);
                params.put("Usuario",Usuario);
                params.put("Ubicacion",Ubicacion);
                params.put("Referencia",Referencia);
                params.put("Telefono",Telefono);
                params.put("Distrito",Distrito);
                params.put("Estado",Estado);
                params.put("Condiciones",Condiciones);
                params.put("Tiempo",Tiempo);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    private void EnviarItemReservaService(final int idReservaRecuperado) {
        final String idReserva=String.valueOf(idReservaRecuperado);
        String listaProductos="";
        List<ReservaItem> listaProductosReserva = Constantes.RESERVA_ITEMS;

        for(int i=0;i<listaProductosReserva.size();i++){
            if(i==listaProductosReserva.size()-1){
                listaProductos=listaProductos+
                        listaProductosReserva.get(i).getProductoItem().getIdProducto()+"-"+
                        listaProductosReserva.get(i).getCantidad()+"-"+
                        listaProductosReserva.get(i).getMedidaReservaItem().getIdMedida();
            }else{
                listaProductos=listaProductos+
                        listaProductosReserva.get(i).getProductoItem().getIdProducto()+"-"+
                        listaProductosReserva.get(i).getCantidad()+"-"+
                        listaProductosReserva.get(i).getMedidaReservaItem().getIdMedida()+"|";
            }
        }
        final String Lista=listaProductos;
        Log.i("Inca","Listado Productos: " +Lista);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.GESTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Toast.makeText(context, "Reserva Enviada Correctamente", Toast.LENGTH_SHORT).show();
                                LimpiarVariables();
                            } else {
                                Toast.makeText(context, "Error al Enviar Reserva", Toast.LENGTH_SHORT).show();
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
                params.put("operacion", "RegistrarListaproductos");
                params.put("idReserva", idReserva);
                params.put("ListaProductos",Lista);

                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    private void LimpiarVariables() {
     Constantes.TIPO_TARJETA_SELECT=null;
     Constantes.TIPO_COMPROBANTE_SELECT=null;
     Constantes.TIPO_PAGO_SELECT=null;
     Constantes.FECHA_RESERVA=null;
     Constantes.HORA_RESERVA=null;
     Constantes.RESERVA_ITEMS=null;
        ((ActivityPrincipal)context).opcionReserva();
    }

    public void AccionBotonesContacto() {


        ivAccionFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog recogerFecha = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                        final int mesActual = month + 1;
                        //Formateo el día obtenido: antepone el 0 si son menores de 10
                        String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                        //Formateo el mes obtenido: antepone el 0 si son menores de 10
                        String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                        //Muestro la fecha con el formato deseado
                        txtAccionFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
                        Constantes.FECHA_RESERVA=year+"-"+mesFormateado+"-"+diaFormateado;
                    }
                    //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
                    /**
                     *También puede cargar los valores que usted desee
                     */
                },YEAR, MONTH,DAY);


                if(URGENCIA){
                    Calendar c = Calendar.getInstance();
                    c.set(YEAR, MONTH, DAY);
                    recogerFecha.getDatePicker().setMinDate(c.getTimeInMillis());
                }else{
                    Calendar c = Calendar.getInstance();
                    c.set(YEAR, MONTH, DAY+1);
                    recogerFecha.getDatePicker().setMinDate(c.getTimeInMillis());
                }
                recogerFecha.show();
            }
        });
        ivAccionHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog recogerHora = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        //Formateo el hora obtenido: antepone el 0 si son menores de 10
                        String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                        //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                        String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
                        //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                        String AM_PM;
                        if(hourOfDay < 12) {
                            AM_PM = "a.m.";
                        } else {
                            AM_PM = "p.m.";
                        }
                        Constantes.TIEMPO=AM_PM;
                        if(hourOfDay>=Constantes.INICIO_HORARIO && hourOfDay<=Constantes.FIN_HORARIO){
                            //Muestro la hora con el formato deseado
                            txtAccionHora.setText(horaFormateada + ":" + minutoFormateado + " " + AM_PM);
                            Constantes.HORA_RESERVA=horaFormateada+":"+minutoFormateado;
                        }else{
                            Toast.makeText(context, "El Horario de atención para las reservas es de "+Constantes.INICIO_HORARIO+" a.m. a "+Constantes.FIN_HORARIO+" p.m.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    //Estos valores deben ir en ese orden
                    //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
                    //Pero el sistema devuelve la hora en formato 24 horas
                }, HOUR, MINUTE, true);
                recogerHora.show();
            }
        });
    }
    public void CalcularFechaHoraActual() {

        Calendar calendarNow = new GregorianCalendar(TimeZone.getTimeZone("America/Lima"));
        DAY =calendarNow.get(Calendar.DAY_OF_MONTH);
        MONTH = calendarNow.get(Calendar.MONTH);
        YEAR = calendarNow.get(Calendar.YEAR);
        HOUR = calendarNow.get(Calendar.HOUR);
        MINUTE= calendarNow.get(Calendar.MINUTE);
        int ampm=calendarNow.get(Calendar.AM_PM);
        AMPM=VerificarTiempo(ampm);

        Log.i("Inca","Fecha y Hora:"+
                VerificarDigitos(DAY)+"/"+
                VerificarDigitos(MONTH+1)+"/"+YEAR+" "+
                VerificarDigitos(HOUR)+":"+
                VerificarDigitos(MINUTE)+" "+
                AMPM);
        Constantes.TIEMPO=AMPM;
        txtAccionFecha.setText(VerificarDigitos(DAY+1)+BARRA+VerificarDigitos(MONTH+1)+BARRA+YEAR);
        Constantes.FECHA_RESERVA=YEAR+"-"+VerificarDigitos(MONTH+1)+"-"+VerificarDigitos(DAY+1);
        txtAccionHora.setText( VerificarDigitos(HOUR)+":"+VerificarDigitos(MINUTE)+" "+AMPM);
        Constantes.HORA_RESERVA=VerificarDigitos(HOUR)+":"+VerificarDigitos(MINUTE);
        etContactoTelefono.setText("");
    }
    public String VerificarDigitos(int valor){
        String re="";
        if(String.valueOf(valor).length()==1){
            re="0"+valor;
        }else{
            re=String.valueOf(valor);
        }
        return re;
    }
    public String VerificarTiempo(int valor){
        String re="";
        if(valor==1){
            re="p.m.";
        }else{
            re="a.m.";
        }
        return re;
    }
    public void MostrarValoresPrincipal() {
        Usuario usuarioTemporal=sesion.RecuperarSesion(context);

        UbicacionDireccion ubicacionTemporal=Constantes.UBICACION_SELECT;
        txtPrincipalUsuario.setText(usuarioTemporal.getNombreUsuario()+" "+usuarioTemporal.getApellidoUsuario());

        if(ubicacionTemporal.getDistrito()!=null){
            if(ubicacionTemporal.getDistrito().getNombreUnidadTerritorial().length()>0){
                txtPrincipalDistrito.setText(ubicacionTemporal.getDistrito().getNombreUnidadTerritorial());
            }else{
                txtPrincipalDistrito.setText("Sin Distrito Seleccionado");
            }
            //txtPrincipalDistrito.setText(ubicacionTemporal.getDistrito().getNombreUnidadTerritorial());
        }else{
            txtPrincipalDistrito.setText("Sin Distrito Seleccionado");
        }

        if(ubicacionTemporal.getDireccionEntrega()!=null){
            if(ubicacionTemporal.getDireccionEntrega().length()>0){
                txtPrincipalDireccion.setText(ubicacionTemporal.getDireccionEntrega());
            }else{
                txtPrincipalDireccion.setText("Sin Dirección Seleccionada");
            }
        }else{
            txtPrincipalDireccion.setText("Sin Dirección Seleccionada");
        }
        if(ubicacionTemporal.getReferenciaDireccion()!=null){
            if(ubicacionTemporal.getDireccionEntrega().length()>0){
                txtPrincipalReferencia.setText(ubicacionTemporal.getDireccionEntrega());
            }else{
                txtPrincipalReferencia.setText("");
            }
        }else{
            txtPrincipalReferencia.setText("");
        }
        CalcularTotalGeneral();
    }
    public void accionBotones() {
        btnVolverReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityPrincipal)context).opcionReserva();
            }
        });
        btnSectorUbicacionVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccionMostrarPrincipal();
                scrollEnvioReserva.scrollTo(0,0);
            }
        });
        btnSectorUbicacionNuevaUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityPrincipal)context).opcionSesion();
            }
        });
        btnSectorContactoVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccionMostrarPrincipal();
                scrollEnvioReserva.scrollTo(0,0);
            }
        });
        btnSectorContactoGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etContactoTelefono.getText().length()<9){
                    Toast.makeText(context, "Telefono Celular no es correcto!", Toast.LENGTH_SHORT).show();
                }else{
                    AccionMostrarPrincipal();
                    SAVE_CONTACTO=true;
                    txtPrincipalFechaEntrega.setText(VerificarDigitos(DAY)+BARRA+VerificarDigitos((MONTH+1))+BARRA+YEAR);
                    txtPrincipalHoraEntrega.setText(VerificarDigitos(HOUR)+":"+VerificarDigitos(MINUTE)+" "+AMPM);
                    txtPrincipalTelefonoEntrega.setText(etContactoTelefono.getText().toString());
                }
            }
        });
        btnSectorTipoPagoVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccionMostrarPrincipal();
                scrollEnvioReserva.scrollTo(0,0);
            }
        });
        btnSectorTipoComprobanteVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccionMostrarPrincipal();
                scrollEnvioReserva.scrollTo(0,0);
            }
        });
    }
    @SuppressLint("WrongConstant")
    public void listadoUbicaciones() {
        linearLayoutUbicaciones= new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        adapterUbicaciones = new AdapterUbicacionesEnvio(context, ListaUbicaciones, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
            }
        });
        recyclerUbicacionesRecuperadas.setAdapter(adapterUbicaciones);
        recyclerUbicacionesRecuperadas.setLayoutManager(linearLayoutUbicaciones);
        adapterUbicaciones.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                MostrarValoresPrincipal();
            }
        });
    }
    @SuppressLint("WrongConstant")
    public void listadoTipoPago() {
        linearLayoutTipoPago= new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        adapterTipoPago = new AdapterTipoPago(context, Constantes.Base_ListaTipoPago, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
                //note required
            }
        });
        recyclerTipoPago.setAdapter(adapterTipoPago);
        recyclerTipoPago.setLayoutManager(linearLayoutTipoPago);
        adapterTipoPago.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                MostrarMetodoPago();
                TipoPago tipoPagoSeleccion=adapterTipoPago.RecuperarTipoPagoSeleccionada();
                if(tipoPagoSeleccion.getIdTipoPago()==2){
                    sectorTituloTipoTarjeta.setVisibility(View.VISIBLE);
                    recyclerTipoTarjeta.setVisibility(View.VISIBLE);
                }else{
                    sectorTituloTipoTarjeta.setVisibility(View.GONE);
                    recyclerTipoTarjeta.setVisibility(View.GONE);
                    adapterTipoTarjeta.QuitarSeleccion();
                    adapterTipoTarjeta.notifyDataSetChanged();
                    Constantes.TIPO_TARJETA_SELECT.setIdTipoTarjeta(0);
                    MostrarMetodoPago();
                }
            }
        });
    }
    public void MostrarMetodoPago() {
        String tipoPagoTemporal="";
        String tipoTarjetaTemporal="";


        if(Constantes.TIPO_PAGO_SELECT.getIdTipoPago()>0){
            if(Constantes.TIPO_PAGO_SELECT.getNombreTipoPago().length()>0){
                tipoPagoTemporal=Constantes.TIPO_PAGO_SELECT.getNombreTipoPago();
            }
        }
        if(Constantes.TIPO_TARJETA_SELECT.getIdTipoTarjeta()>0){
            if(Constantes.TIPO_TARJETA_SELECT.getNombreTarjeta().length()>0){
                tipoTarjetaTemporal=Constantes.TIPO_TARJETA_SELECT.getNombreTarjeta();
            }
        }
        if(tipoPagoTemporal.length()==0 && tipoTarjetaTemporal.length()==0){
            txtPrincipalMetodoPago.setText("Sin Metodo de Pago seleccionado.");
        }else{
            if(tipoTarjetaTemporal.length()==0){
                txtPrincipalMetodoPago.setText(tipoPagoTemporal);
            }else{
                txtPrincipalMetodoPago.setText(tipoPagoTemporal+" - "+tipoTarjetaTemporal);
            }
        }
    }
    @SuppressLint("WrongConstant")
    public void listadoTipoTarjeta() {
        linearLayoutTipoTarjeta= new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        adapterTipoTarjeta = new AdapterTipoTarjeta(context, Constantes.Base_ListaTipoTarjeta, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
                //note required
            }
        });
        recyclerTipoTarjeta.setAdapter(adapterTipoTarjeta);
        recyclerTipoTarjeta.setLayoutManager(linearLayoutTipoTarjeta);
        adapterTipoTarjeta.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                MostrarMetodoPago();
            }
        });
    }
    @SuppressLint("WrongConstant")
    public void listadoTipoComprobante() {
        linearLayoutTipoComprobante= new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        adapterTipoComprobante = new AdapterTipoComprobante(context, Constantes.Base_ListaTipoComprobante, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
                //note required
            }
        });
        recyclerTipoComprobante.setAdapter(adapterTipoComprobante);
        recyclerTipoComprobante.setLayoutManager(linearLayoutTipoComprobante);
        adapterTipoComprobante.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                MostrarTipoComprobante();
            }
        });
    }
    public void MostrarTipoComprobante() {
        String tipoComprobanteTemporal="";
        if(Constantes.TIPO_COMPROBANTE_SELECT.getIdTipoComprobante()>0){
            if(Constantes.TIPO_COMPROBANTE_SELECT.getNombreTipoComprobante().length()>0){
                tipoComprobanteTemporal=Constantes.TIPO_COMPROBANTE_SELECT.getNombreTipoComprobante();
            }
        }
        txtPrincipalTipoComprobante.setText(tipoComprobanteTemporal);
    }
    public void accionesMovimientoPaneles() {
        accionSectorUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccionesSectores(panelSeleccionUbicacion);
                MostrarToolbarAccion(false);
            }
        });
        accionSectorFechaContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccionesSectores(panelFechaContacto);
                MostrarToolbarAccion(false);
                if(!SAVE_CONTACTO){
                    CalcularFechaHoraActual();
                }
            }
        });
        accionSectorMetodoPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccionesSectores(panelMetodoPago);
                MostrarToolbarAccion(false);
            }
        });
        accionSectorTipoComprobante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccionesSectores(panelTipoComprobante);
                MostrarToolbarAccion(false);
            }
        });

    }
    public void MostrarToolbarAccion(boolean re){
        if(re){
            panelBotonesReserva.setVisibility(View.VISIBLE);
            toolbarReservaEnvio.setVisibility(View.VISIBLE);
        }else{
            panelBotonesReserva.setVisibility(View.GONE);
            toolbarReservaEnvio.setVisibility(View.GONE);
        }
    }
    public void AccionMostrarPrincipal(){
        AccionesSectores(panelPrincipal);
        MostrarToolbarAccion(true);
    }
    public void AccionesSectores(LinearLayout areaSeleccion){
        panelSeleccionUbicacion.setVisibility(View.GONE);
        panelFechaContacto.setVisibility(View.GONE);
        panelPrincipal.setVisibility(View.GONE);
        panelMetodoPago.setVisibility(View.GONE);
        panelTipoComprobante.setVisibility(View.GONE);
        areaSeleccion.setVisibility(View.VISIBLE);
    }
    public void condiciones() {
        c_condiciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!status){
                    final LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                    final View dialoglayout4 = inflater.inflate(R.layout.terminos_condiciones, null);

                    final Button aceptar=(Button) dialoglayout4.findViewById(R.id.btn_acepto);
                    aceptar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            status=true;
                            d.dismiss();
                        }
                    });
                    AlertDialog.Builder builder4 = new AlertDialog.Builder(context);
                    builder4.setView(dialoglayout4);
                    d=builder4.show();

                }else{
                    c_condiciones.setChecked(false);
                    status=false;
                }
            }
        });


    }
    @SuppressLint("WrongConstant")
    public void listadoItemReservaEnvio() {
        linear1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
        adapterItemReservaEnvio = new AdapterItemReservaEnvio(context, Constantes.RESERVA_ITEMS, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
                //not required
            }
        });
        recycler.setAdapter(adapterItemReservaEnvio);
        recycler.setLayoutManager(linear1);
        CalcularTotalGeneral();
        adapterItemReservaEnvio.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();

                if(adapterItemReservaEnvio.getItemCount()==0){
                    Intent intent=new Intent(context, ActivityPrincipal.class);
                    startActivity(intent);
                }else{
                    CalcularTotalGeneral();
                }
            }
        });

    }
    public void VerificacionUrgencia() {

        checkUrgencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(URGENCIA){
                    checkUrgencia.setChecked(false);
                    URGENCIA=false;
                    txtAccionFecha.setText(VerificarDigitos(DAY+1)+BARRA+VerificarDigitos(MONTH+1)+BARRA+YEAR);
                    txtAccionHora.setText( VerificarDigitos(HOUR)+":"+VerificarDigitos(MINUTE)+" "+AMPM);
                    CalcularTotalGeneral();
                }else{
                    checkUrgencia.setChecked(true);
                    URGENCIA=true;
                    txtAccionFecha.setText(VerificarDigitos(DAY)+BARRA+VerificarDigitos(MONTH+1)+BARRA+YEAR);
                    txtAccionHora.setText( VerificarDigitos(HOUR)+":"+VerificarDigitos(MINUTE)+" "+AMPM);
                    CalcularTotalGeneral();
                }
            }
        });
    }
    private void CalcularTotalGeneral() {

        DecimalFormat formateador = new DecimalFormat("###,###.00");
        double totalUrgencia=0;
        double totalBase=0;
        double totalDescuentos=0;
        double totalDelivery=0;
        if(URGENCIA){
            linearMontoUrgencia.setVisibility(View.VISIBLE);
            totalUrgencia=adapterItemReservaEnvio.RecuperarTotalesBase(true)-adapterItemReservaEnvio.RecuperarTotalesBase(false);
            totalBase=adapterItemReservaEnvio.RecuperarTotalesBase(false);
            if(totalBase==0){
                MontoBase.setText("S/ 0,00");
            }else{
                String valorBase=formateador.format(totalBase);
                MontoBase.setText("S/ "+valorBase);
            }

            if(totalUrgencia==0){
                MontoUrgencia.setText("S/ 0,00");
            }else{
                String valorUrgencia=formateador.format(totalUrgencia);
                MontoUrgencia.setText("S/ "+valorUrgencia);
            }

        }else{
            linearMontoUrgencia.setVisibility(View.GONE);
            totalBase=adapterItemReservaEnvio.RecuperarTotalesBase(false);
            if(totalBase==0){
                MontoBase.setText("S/ 0,00");
            }else{
                String valorBase=formateador.format(totalBase);
                MontoBase.setText("S/ "+valorBase);
            }
        }

        totalDescuentos=adapterItemReservaEnvio.RecuperarDescuentos();
        String valorDescuento=formateador.format(totalDescuentos);
        MontoDescuento.setText("S/ "+valorDescuento);

        if(Constantes.UBICACION_SELECT.getIdUbicacionDireccion()!=0){
             totalDelivery=Constantes.UBICACION_SELECT.getPrecioDelivery();
             String valorDelivery=formateador.format(totalDelivery);
            MontoDelivery.setText("S/ "+valorDelivery);
        }else{
            MontoDelivery.setText("S/ 0.00");
        }

        double totalReserva=((totalBase+totalUrgencia)-totalDescuentos)+totalDelivery;
        if(totalReserva==0){
           MontoEnvio.setText("S/ 0.00");
        }else{
            String valorReserva=formateador.format(totalReserva);
            MontoEnvio.setText("S/ "+valorReserva);
        }
    }
    public void RecuperarUbicacionesUsuario(final Context context, final int idUsu) {
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
                                    JSONArray ubicaciones = jsonResponse.getJSONArray("ubicaciones");

                                    for (int i = 0; i < ubicaciones.length(); i++) {
                                        JSONObject objeto = ubicaciones.getJSONObject(i);
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
}
