package inca.jesus.trajesya.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import inca.jesus.trajesya.Data.Conexion.Sesion;
import inca.jesus.trajesya.Data.Conexion.VolleySingleton;
import inca.jesus.trajesya.Clases.Perfil;
import inca.jesus.trajesya.Data.Modelo.Usuario;
import inca.jesus.trajesya.R;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    TextView sin_cuenta;
    private CallbackManager callbackManager;
    LoginButton loginButton;
    Button inicio_sesion;
    EditText usuario, password;
    ProgressDialog progressDialog;
    Context context;
    Sesion sesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context=this;
        sesion=new Sesion();
        inicio_sesion = findViewById(R.id.inicio_sesion);
        usuario = findViewById(R.id.etUsuario);
        password = findViewById(R.id.etPassword);
        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.loginFb);
        sin_cuenta=findViewById(R.id.btnSinCuenta);
        loginButton.setReadPermissions("email");

        TextView sin_cuenta =findViewById(R.id.btnSinCuenta);
        SpannableString mitextoU = new SpannableString("SIN USUARIO");
        mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
        sin_cuenta.setText(mitextoU);


        opciones();
        SesionFacebook();
    }

    private void SesionFacebook() {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                IrMenuPrincipal();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), R.string.cancel_login, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void opciones() {
        sin_cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IrMenuPrincipal();
            }
        });
    }

    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void IniciarSesion(View view) {
        String mensaje=VerificarCampos();
        if(mensaje.length()==0){
            Usuario Temporal=new Usuario();
            Temporal.setUsuario(usuario.getText().toString());
            Temporal.setPassword(usuario.getText().toString());
            VerificarUsuarioServidor(context,Temporal);
        }else{
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }
    }

    private void VerificarUsuarioServidor(final Context context,final Usuario usuario){
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Login:");
        progressDialog.setMessage("Iniciando.......");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Sesion.LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Usuario Nuevo=new Usuario();
                                Nuevo.setIdUsuario(jsonResponse.getInt("idUsuario"));
                                Nuevo.setUsuario(jsonResponse.getString("usuario"));
                                Nuevo.setNombreUsuario(jsonResponse.getString("nombres"));
                                Nuevo.setApellidoUsuario(jsonResponse.getString("apellidos"));
                                Nuevo.setImagenUsuario(jsonResponse.getString("imagen"));
                                Perfil perfil=new Perfil();
                                perfil.setIdPerfil(jsonResponse.getInt("idPerfil"));
                                perfil.setNombrePrefil(jsonResponse.getString("perfil"));
                                Nuevo.setPerfilUsuario(perfil);
                                sesion.RegistrarSesion(context,Nuevo);

                                String Mensaje=jsonResponse.getString("mensaje");
                                progressDialog.dismiss();
                                Toast.makeText(context, Mensaje, Toast.LENGTH_SHORT).show();
                                IrMenuPrincipal();
                            }else{
                                LimpiarCampos();
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
                params.put("operacion", "VerificarLogin");
                params.put("usuario", usuario.getUsuario());
                params.put("password", usuario.getPassword());
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public String VerificarCampos(){
        String mensaje="";
        String etUsuario = usuario.getText().toString().trim();
        String etPassword = password.getText().toString().trim();

        mensaje=(etUsuario.length()==0)?(mensaje+"- Ingrese Usuario.\n"):(mensaje+"");
        mensaje=(etPassword.length()==0)?(mensaje+"- Ingrese Contraseña.\n"):(mensaje+"");
        mensaje=(etUsuario.length()<6)?(mensaje+"- Ingrese Usuario mayor o igual a 6 caracteres.\n"):(mensaje+"");
        mensaje=(etPassword.length()<6)?(mensaje+"- Ingrese Contraseña mayor o igual a a 6 caracteres.\n"):(mensaje+"");
        return  mensaje;
    }

    public void LimpiarCampos(){
        usuario.setText("");
        password.setText("");
    }

    public void IrMenuPrincipal(){
        Intent intent=new Intent(LoginActivity.this, ActivityPrincipal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
