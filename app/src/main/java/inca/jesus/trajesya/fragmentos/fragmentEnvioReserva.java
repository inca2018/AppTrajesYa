package inca.jesus.trajesya.fragmentos;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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

import inca.jesus.trajesya.R;
import inca.jesus.trajesya.activities.ActivityPrincipal;
import inca.jesus.trajesya.adapters.AdapterItemCarrito;
import inca.jesus.trajesya.adapters.AdapterUbicaciones;
import inca.jesus.trajesya.adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.data.conexion.VolleySingleton;
import inca.jesus.trajesya.data.modelo.Sesion;
import inca.jesus.trajesya.data.modelo.UbicacionDireccion;
import inca.jesus.trajesya.data.modelo.UnidadTerritorial;
import inca.jesus.trajesya.data.modelo.Usuario;
import inca.jesus.trajesya.data.utils.Constantes;

import static inca.jesus.trajesya.data.utils.Constantes.SUCCESS;

public class fragmentEnvioReserva extends Fragment {
    RecyclerView recycler;
    LinearLayoutManager linear1;
    AdapterItemCarrito adapterXXX;
    LinearLayout panelPrincipal, panelSeleccionUbicacion, panelMetodoPago, panelBotonesReserva, panelTipoComprobante, panelFechaContacto;
    ImageView accionSectorUbicacion, accionSectorMetodoPago, accionSectorTipoComprobante,b4, accionSectorFechaContacto;
    Button btn_g,btn_tp,btn_g_c,btn_g_r;
    Button op1,op2,op3;
    boolean a1=false,a2=false,a3=false,c1=false,c2=false;
    CardView panel1,panel2;
    Button bole,fac;
    private CardView card_fac;
    CheckBox c_condiciones;
    boolean status=false;
    AlertDialog d,s;
    TextView btn_cupon,text_cupon;
    String codigo="";
    //Nuevas Variables
    Context context;
    RecyclerView recyclerUbicacionesRecuperadas;
    List<UbicacionDireccion> ListaUbicaciones;
    public AdapterUbicaciones adapterUbicaciones;
    LinearLayoutManager linearLayoutUbicaciones;
    Sesion sesion=new Sesion();
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





        c_condiciones=v.findViewById(R.id.check_condiciones);

        btn_g=v.findViewById(R.id.guardar_direc_nueva);

        btn_tp=v.findViewById(R.id.btn_guardar_tipo_pago);

        op1=v.findViewById(R.id.btn_tarjetas);
        op2=v.findViewById(R.id.btn_trasnf);
        op3=v.findViewById(R.id.btn_contraentrega);
        panel1=v.findViewById(R.id.panel_selec_tarjeta);
        panel2=v.findViewById(R.id.panel_selec_tranf);
        panel1.setVisibility(View.GONE);
        panel2.setVisibility(View.GONE);

        btn_g_c=v.findViewById(R.id.btn_guardar_comrp);
        bole=v.findViewById(R.id.btn_boleta2);
        fac=v.findViewById(R.id.btn_factura2);
        card_fac=v.findViewById(R.id.panel_factura);
        card_fac.setVisibility(View.GONE);

        btn_cupon=v. findViewById(R.id.btn_cupon);
        text_cupon=v.findViewById(R.id.text_cupon);

        SegundoModulo();
        PrimerModulo();
        //movimientos_entre_botones();
        recycler_item_compra();

        condiciones();

        if(status==true){
            c_condiciones.setChecked(true);
        }else{
            c_condiciones.setChecked(false);
        }

        adapterXXX.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();

                if(adapterXXX.getItemCount()==0){
                    Intent intent=new Intent(context, ActivityPrincipal.class);
                    startActivity(intent);
                }
            }
        });

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
        accionesMovimientoPaneles();
        return v;
    }

    private void listadoUbicaciones() {
        linearLayoutUbicaciones= new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        adapterUbicaciones = new AdapterUbicaciones(context, ListaUbicaciones, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
            }
        });
        recyclerUbicacionesRecuperadas.setAdapter(adapterUbicaciones);
        recyclerUbicacionesRecuperadas.setLayoutManager(linearLayoutUbicaciones);
    }

    private void accionesMovimientoPaneles() {
        accionSectorUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuarioTemporal=sesion.RecuperarSesion(context);
                RecuperarUbicacionesUsuario(context,usuarioTemporal.getIdUsuario());
                AccionesSectores(panelSeleccionUbicacion);
            }
        });
        accionSectorFechaContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccionesSectores(panelFechaContacto);
            }
        });
        accionSectorMetodoPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccionesSectores(panelMetodoPago);
            }
        });
        accionSectorTipoComprobante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccionesSectores(panelTipoComprobante);
            }
        });

    }


    public void AccionesSectores(LinearLayout areaSeleccion){
        panelSeleccionUbicacion.setVisibility(View.GONE);
        panelPrincipal.setVisibility(View.GONE);
        panelBotonesReserva.setVisibility(View.GONE);
        panelMetodoPago.setVisibility(View.GONE);
        areaSeleccion.setVisibility(View.VISIBLE);
    }
    private void condiciones() {
        c_condiciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(status==false){
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
    private void SegundoModulo() {
        bole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("click bole");

                if(c1==false){
                    bole.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    fac.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    card_fac.setVisibility(View.GONE);
                }else if(c1==true){
                    bole.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });

        fac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("click fac");
                if(c2==false){
                    fac.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    bole.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    card_fac.setVisibility(View.VISIBLE);
                }else if(c2==true){
                    fac.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    card_fac.setVisibility(View.GONE);
                }

            }
        });

    }
    private void PrimerModulo() {
        op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a1==false){
                    a1=true;
                    op1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    op2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    op3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    panel2.setVisibility(View.GONE);
                    panel1.setVisibility(View.VISIBLE);
                }else if(a1=true){
                    a1=false;
                    op1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    panel1.setVisibility(View.GONE);
                }
            }
        });
        op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a2==false){
                    a2=true;
                    op2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    op3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    op1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    panel2.setVisibility(View.VISIBLE);
                    panel1.setVisibility(View.GONE);
                }else if(a2=true){
                    a2=false;
                    op2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    panel2.setVisibility(View.GONE);
                }
            }
        });
        op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a3==false){
                    a3=true;
                    op3.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    op1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    op2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    panel2.setVisibility(View.GONE);
                    panel1.setVisibility(View.GONE);
                }else if(a3=true){
                    a3=false;
                    op3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                }
            }
        });


    }
    private void movimientos_entre_botones() {

        btn_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panelSeleccionUbicacion.setVisibility(View.GONE);
                panelPrincipal.setVisibility(View.VISIBLE);
                panelBotonesReserva.setVisibility(View.VISIBLE);
                panelMetodoPago.setVisibility(View.GONE);
            }
        });

        btn_tp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panelSeleccionUbicacion.setVisibility(View.GONE);
                panelPrincipal.setVisibility(View.VISIBLE);
                panelBotonesReserva.setVisibility(View.VISIBLE);
                panelMetodoPago.setVisibility(View.GONE);
            }
        });

        btn_g_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panelTipoComprobante.setVisibility(View.GONE);
                panelPrincipal.setVisibility(View.VISIBLE);
                panelSeleccionUbicacion.setVisibility(View.GONE);
                panelMetodoPago.setVisibility(View.GONE);
                panelBotonesReserva.setVisibility(View.VISIBLE);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panelTipoComprobante.setVisibility(View.VISIBLE);
                panelPrincipal.setVisibility(View.GONE);
                panelSeleccionUbicacion.setVisibility(View.GONE);
                panelMetodoPago.setVisibility(View.GONE);
                panelBotonesReserva.setVisibility(View.GONE);
            }
        });
        accionSectorFechaContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                panelTipoComprobante.setVisibility(View.GONE);
                panelPrincipal.setVisibility(View.GONE);
                panelSeleccionUbicacion.setVisibility(View.GONE);
                panelMetodoPago.setVisibility(View.GONE);
                panelBotonesReserva.setVisibility(View.GONE);
            }
        });

        btn_g_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panelBotonesReserva.setVisibility(View.VISIBLE);
                panelPrincipal.setVisibility(View.VISIBLE);

            }
        });
    }
    @SuppressLint("WrongConstant")
    private void recycler_item_compra() {
        linear1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
        adapterXXX = new AdapterItemCarrito(context, Constantes.RESERVA_ITEMS, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {
                //not required
            }
        });
        recycler.setAdapter(adapterXXX);
        recycler.setLayoutManager(linear1);

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
