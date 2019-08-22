package inca.jesus.trajesya.Fragmentos;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import inca.jesus.trajesya.Activities.LoginActivity;
import inca.jesus.trajesya.Clases.Perfil;
import inca.jesus.trajesya.Data.Conexion.VolleySingleton;
import inca.jesus.trajesya.Data.Modelo.Sesion;
import inca.jesus.trajesya.Data.Modelo.Usuario;
import inca.jesus.trajesya.Data.Utils.Constantes;
import inca.jesus.trajesya.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SesionFragment extends Fragment {
    ImageView foto;
    TextView nombre;
    Button logout;
    LinearLayout modulo1;
    LinearLayout modulo2;
    Button op1,op2,op3,op4;
    boolean menu;
    Button regreso;
    TextView correo_user;
    /*-------------------*/
    Sesion sesion;
    Context context;
    Usuario usuarioRecuperado;
    TextView tituloOpcion,tituloDescripcion;
    LinearLayout SectorLogo,SectorOpcionesUsuario,SectorOpcionesUsuarioExtras,SectorFbRegistro;
    EditText etRegistroNombre,etRegistroApellido,etRegistroDni,etRegistroCorreo,etRegistroUsuario,etRegistroPassword;
    Button btnRegistrarUsuario;
    ProgressDialog progressDialog;


    private CallbackManager callbackManager;
    LoginButton loginButton;
    public ProfileTracker profileTracker;


    SharedPreferences.Editor editor;

    public SesionFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vie =inflater.inflate(R.layout.fragment_sesion, container, false);
        context=getActivity();
        sesion = new Sesion();

        tituloOpcion=vie.findViewById(R.id.tituloOpcion);
        tituloDescripcion=vie.findViewById(R.id.tituloDescripcion);
        modulo1=vie.findViewById(R.id.modulo1);
        modulo2=vie.findViewById(R.id.modulo2);
        foto=vie.findViewById(R.id.sesion_imagen);
        nombre=vie.findViewById(R.id.sesion_nombre);
        logout=vie.findViewById(R.id.boton_cerrar_sesion);
        op1=vie.findViewById(R.id.perfil_op1);
        op2=vie.findViewById(R.id.perfil_op2);
        op3=vie.findViewById(R.id.perfil_op3);
        op4=vie.findViewById(R.id.perfil_op4);
        SectorLogo=vie.findViewById(R.id.SectorLogo);
        SectorOpcionesUsuario=vie.findViewById(R.id.OpcionesUsuario);
        SectorOpcionesUsuarioExtras=vie.findViewById(R.id.OpcionesUsuarioExtras);
        etRegistroNombre=vie.findViewById(R.id.etRegistroNombre);
        etRegistroApellido=vie.findViewById(R.id.etRegistroApellido);
        etRegistroDni=vie.findViewById(R.id.etRegistroDni);
        etRegistroCorreo=vie.findViewById(R.id.etRegistroCorreo);
        etRegistroUsuario=vie.findViewById(R.id.etRegistroUsuario);
        etRegistroPassword=vie.findViewById(R.id.etRegistroPassword);
        btnRegistrarUsuario=vie.findViewById(R.id.btnRegistrarUsuario);
        correo_user=vie.findViewById(R.id.correo_user);
        SectorFbRegistro=vie.findViewById(R.id.SectorLoginFB);

        loginButton=vie.findViewById(R.id.loginFbSesion);
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions("email");

        SharedPreferences pref = context.getSharedPreferences("Sesion", context.MODE_PRIVATE);
        editor = pref.edit();

        /*******Recuperar Usuario*******/
        usuarioRecuperado=sesion.RecuperarSesion(context);
        RecuperarInformacionSesion(usuarioRecuperado);

        regreso=(Button)vie.findViewById(R.id.regresar);
        regreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu=false;
                modulo1.setVisibility(View.VISIBLE);
                modulo2.setVisibility(View.GONE);
            }
        });
        menu=false;

        OpcionesUsuario();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CerrarSesion();
            }
        });

        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Mensaje=VerificarCampos();
               if(Mensaje.length()==0){
                   Usuario temp=new Usuario();
                   temp.setNombreUsuario(etRegistroNombre.getText().toString());
                   temp.setApellidoUsuario(etRegistroApellido.getText().toString());
                   temp.setDniUsuario(etRegistroDni.getText().toString());
                   temp.setCorreoUsuario(etRegistroCorreo.getText().toString());
                   temp.setUsuario(etRegistroUsuario.getText().toString());
                   temp.setPassword(etRegistroPassword.getText().toString());

                   RegistrarUsuarioServidor(context,temp);
               }else{
                   Toast.makeText(context, Mensaje, Toast.LENGTH_SHORT).show();
               }
            }
        });

        SesionFacebook(context);
        return vie;
    }

    private void OpcionesUsuario() {
        op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tituloOpcion.setText("TÉRMINOS Y CONDICIONES");
                tituloDescripcion.setText(Constantes.TERMINOS_CONDICIONES);
                menu=true;
                modulo1.setVisibility(View.GONE);
                modulo2.setVisibility(View.VISIBLE);
            }
        });
        op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tituloOpcion.setText("POLITICA DE ALQUILER");
                tituloDescripcion.setText(Constantes.POLITICA_ALQUILER);
                menu=true;
                modulo1.setVisibility(View.GONE);
                modulo2.setVisibility(View.VISIBLE);
            }
        });

        op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tituloOpcion.setText("PRIVACIDAD DE CONFIDENCIALIDAD");
                tituloDescripcion.setText(Constantes.PRIVACIDAD_CONFIDENCIALIDAD);
                menu=true;
                modulo1.setVisibility(View.GONE);
                modulo2.setVisibility(View.VISIBLE);
            }
        });
        op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tituloOpcion.setText("PREGUNTAS FRECUENTES");
                tituloDescripcion.setText(Constantes.PREGUNTAS_PRECUENTES);
                menu=true;
                modulo1.setVisibility(View.GONE);
                modulo2.setVisibility(View.VISIBLE);
            }
        });
    }

    private void RecuperarInformacionSesion(Usuario usuarioRecuperado) {

        /*Verificacion de Vistas Segun Sesion*/

        if(!usuarioRecuperado.isSesion() && !usuarioRecuperado.isSesionFB()){
            SesionFiltro(1);
        }else{
            if(!usuarioRecuperado.isSesion()){
                SesionFiltro(2);
            }else{
                SesionFiltro(3);
            }
        }
    }

    public void SesionFiltro(int dato){

        switch (dato){
            case 1:
                /*Ingreso  sin usuario*/
                SectorFbRegistro.setVisibility(View.VISIBLE);
                SectorLogo.setVisibility(View.GONE);
                SectorOpcionesUsuario.setVisibility(View.GONE);
                SectorOpcionesUsuarioExtras.setVisibility(View.GONE);
                modulo1.setVisibility(View.GONE);
                modulo2.setVisibility(View.GONE);
                /*mostrar informacion*/
                nombre.setText("Bienvenido");
                correo_user.setText("Invitado");
                logout.setVisibility(View.GONE);
                break;
            case 2:
                /*Ingreso con Cuenta de FB pero no esta registrado*/
                SectorFbRegistro.setVisibility(View.GONE);
                SectorLogo.setVisibility(View.VISIBLE);
                SectorOpcionesUsuario.setVisibility(View.GONE);
                SectorOpcionesUsuarioExtras.setVisibility(View.GONE);
                modulo1.setVisibility(View.VISIBLE);
                modulo2.setVisibility(View.GONE);

                Picasso.get()
                        .load(usuarioRecuperado.getImagenUsuario())
                        .placeholder(R.drawable.default_imagen)
                        .error(R.drawable.default_imagen)
                        .into(foto);
                nombre.setText(usuarioRecuperado.getNombreUsuario()+usuarioRecuperado.getApellidoUsuario());
                correo_user.setText(usuarioRecuperado.getCorreoUsuario());

                logout.setVisibility(View.VISIBLE);
                break;
            case 3:
                /*Ingreso con cuenta ya registrada para compras*/
                SectorFbRegistro.setVisibility(View.GONE);
                SectorLogo.setVisibility(View.VISIBLE);
                SectorOpcionesUsuario.setVisibility(View.VISIBLE);
                SectorOpcionesUsuarioExtras.setVisibility(View.VISIBLE);
                modulo1.setVisibility(View.VISIBLE);
                modulo2.setVisibility(View.GONE);

                Picasso.get()
                        .load(usuarioRecuperado.getImagenUsuario())
                        .placeholder(R.drawable.default_imagen)
                        .error(R.drawable.default_imagen)
                        .into(foto);
                nombre.setText(usuarioRecuperado.getNombreUsuario()+usuarioRecuperado.getApellidoUsuario());
                correo_user.setText(usuarioRecuperado.getCorreoUsuario());
                logout.setVisibility(View.VISIBLE);
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
                                Intent intent=new Intent(context, LoginActivity.class);
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

    public String VerificarCampos(){
        String mensaje="";

        // Recuperar Link de Imagen

        String etNombre = etRegistroNombre.getText().toString().trim();
        String etApellido = etRegistroApellido.getText().toString().trim();
        String etDni = etRegistroDni.getText().toString().trim();
        String etCorreo = etRegistroCorreo.getText().toString().trim();
        String etUsuario = etRegistroUsuario.getText().toString().trim();
        String etPassword = etRegistroPassword.getText().toString().trim();


        mensaje=(etNombre.length()==0)?(mensaje+"- Ingrese Nombre de Usuario.\n"):(mensaje+"");
        mensaje=(etApellido.length()==0)?(mensaje+"- Ingrese Apellido de Usuario.\n"):(mensaje+"");
        mensaje=(etDni.length()==0)?(mensaje+"- Ingrese DNI de Usuario.\n"):(mensaje+"");

        mensaje=(etUsuario.length()==0)?(mensaje+"- Ingrese Usuario.\n"):(mensaje+"");
        mensaje=(etPassword.length()==0)?(mensaje+"- Ingrese Contraseña.\n"):(mensaje+"");

        mensaje=(etDni.length()!=8)?(mensaje+"- Dni Ingresado debe ser de 8 digitos.\n"):(mensaje+"");
        mensaje=(etUsuario.length()<6)?(mensaje+"- Ingrese Usuario mayor o igual a 6 caracteres.\n"):(mensaje+"");
        mensaje=(etPassword.length()<6)?(mensaje+"- Ingrese Contraseña mayor o igual a a 6 caracteres.\n"):(mensaje+"");

        if(!emailValidator(etCorreo)){
            mensaje=mensaje+"- Correo no tiene el Formato Correcto( Example@dominio.com).\n";
        }

        return  mensaje;
    }

    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void RegistrarUsuarioServidor(final Context context,final Usuario usuario){
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
                               /* Usuario Nuevo=new Usuario();
                                Nuevo.setIdUsuario(jsonResponse.getInt("idUsuario"));
                                Nuevo.setUsuario(jsonResponse.getString("usuario"));
                                Nuevo.setNombreUsuario(jsonResponse.getString("nombres"));
                                Nuevo.setApellidoUsuario(jsonResponse.getString("apellidos"));
                                Nuevo.setImagenUsuario(jsonResponse.getString("imagen"));
                                Nuevo.setCorreoUsuario(jsonResponse.getString("correo"));
                                Nuevo.setSesion(true);
                                Perfil perfil=new Perfil();
                                perfil.setIdPerfil(jsonResponse.getInt("idPerfil"));
                                perfil.setNombrePrefil(jsonResponse.getString("perfil"));
                                Nuevo.setPerfilUsuario(perfil);
                                Nuevo.setSesion(true);
                                Nuevo.setSesionFB(false);
                                sesion.RegistrarSesion(context,Nuevo);*/

                                String Mensaje=jsonResponse.getString("mensaje");
                                progressDialog.dismiss();
                                Toast.makeText(context, Mensaje, Toast.LENGTH_SHORT).show();

                            }else{

                                progressDialog.dismiss();
                                String Mensaje=jsonResponse.getString("mensaje");
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
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    private void SesionFacebook(final Context context) {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
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
                    displayProfileInfo(currentProfile);
                }
            }
        };

        if (AccessToken.getCurrentAccessToken() == null) {
            sesion.RegistrarVariable(editor, this.context,"Sesion","boolean","false");
            sesion.RegistrarVariable(editor, this.context,"SesionFB","boolean","false");

        } else {
            requestEmail(context,AccessToken.getCurrentAccessToken());

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
        sesion.RegistrarVariable(editor,context,"Correo","String",email);
    }
    private void displayProfileInfo(Profile profile) {
        sesion.RegistrarVariable(editor,context,"SesionFB","boolean","true");
        sesion.RegistrarVariable(editor,context,"KeyFacebook","String",profile.getId());
        sesion.RegistrarVariable(editor,context,"nombres","String",profile.getName());
        sesion.RegistrarVariable(editor,context,"apellidos ","String",profile.getLastName());
        sesion.RegistrarVariable(editor,context,"imagen","String", String.valueOf(profile.getProfilePictureUri(100,100)));

        SesionFiltro(2); 

    }
}
