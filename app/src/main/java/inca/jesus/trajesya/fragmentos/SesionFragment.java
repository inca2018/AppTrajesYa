package inca.jesus.trajesya.fragmentos;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import inca.jesus.trajesya.activities.Item;
import inca.jesus.trajesya.activities.LoginActivity;
import inca.jesus.trajesya.adapters.AdapterItemProductos;
import inca.jesus.trajesya.adapters.AdapterUbicaciones;
import inca.jesus.trajesya.adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.clases.Perfil;
import inca.jesus.trajesya.data.conexion.VolleySingleton;
import inca.jesus.trajesya.data.modelo.Categoria;
import inca.jesus.trajesya.data.modelo.Estado;
import inca.jesus.trajesya.data.modelo.Grupo;
import inca.jesus.trajesya.data.modelo.Sesion;
import inca.jesus.trajesya.data.modelo.UbicacionDireccion;
import inca.jesus.trajesya.data.modelo.UnidadTerritorial;
import inca.jesus.trajesya.data.modelo.Usuario;
import inca.jesus.trajesya.data.utils.Constantes;
import inca.jesus.trajesya.R;

import static inca.jesus.trajesya.data.utils.Constantes.SUCCESS;

/**
 * A simple {@link Fragment} subclass.
 */
public class SesionFragment extends Fragment {
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


    AlertDialog nuevaUbicacion;

    public SesionFragment() {
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

        menu = false;

        ListaUbicaciones=new ArrayList<>();
        ListaDistritos=new ArrayList<>();
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
        return vie;
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

                spinnerDistritos=new String[ListaDistritos.size()];
                for (int i = 0; i < ListaDistritos.size(); i++) {
                    spinnerDistritos[i] = ListaDistritos.get(i).getNombreUnidadTerritorial();
                }
                ArrayAdapter<String> adapter_arr = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, spinnerDistritos);
                spDistrito.setAdapter(adapter_arr);


                AlertDialog.Builder builder4 = new AlertDialog.Builder(context);
                builder4.setView(dialoglayout4);
                nuevaUbicacion = builder4.show();
            }
        });
    }

    private void opcionRegresarPerfil() {

        ivRegresarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modulo1.setVisibility(View.VISIBLE);
                SectorUbicaciones.setVisibility(View.GONE);
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
                String imagen = sesion.RecuperarValor(context, "imagen");
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
                    temp.setImagenUsuario(sesion.RecuperarValor(context, "imagen"));

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
        String imagen = sesion.RecuperarValor(context, "imagen");
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
                                Nuevo.setImagenUsuario(jsonResponse.getString("imagen"));
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
                                sesion.RegistrarVariable(editor, context, "imagen", "String", String.valueOf(profile.getProfilePictureUri(100, 100)));
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
                                sesion.RegistrarSesion(SesionFragment.this.context, Nuevo);

                                String Mensaje = jsonResponse.getString("mensaje");
                                progressDialog.dismiss();

                                Toast.makeText(SesionFragment.this.context, Mensaje, Toast.LENGTH_SHORT).show();

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

}