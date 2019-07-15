package inca.jesus.trajesya;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    ImageView ima;
    TextView crear_cuenta;
    TextView sin_cuenta;
    Button loginGoo;
    private CallbackManager callbackManager;
    LoginButton loginButton;
    Button inicio_sesion;
    EditText e1,e2;
    Boolean encontrado;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*crear_cuenta=(TextView)findViewById(R.id.boton_crear_cuenta);
        sin_cuenta=(TextView)findViewById(R.id.ingresar_sin_cuenta);*/
        loginGoo=(Button)findViewById(R.id.loginGoo) ;
        inicio_sesion=(Button)findViewById(R.id.inicio_sesion);
        e1=(EditText)findViewById(R.id.email_login);
        e2=(EditText)findViewById(R.id.pass_login);
        callbackManager = CallbackManager.Factory.create();
        loginButton=(LoginButton)findViewById(R.id.loginFb);
        loginButton.setReadPermissions("email");
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Iniciando Sesión:");
        progressDialog.setMessage("Iniciando.......");
        inicio_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                BuscarUsuario();
                if(encontrado==true){
                    progressDialog.dismiss();
                   /* Toast.makeText(LoginActivity.this, "Bienvenido:"+Sesion.USUARIO.getNombre() , Toast.LENGTH_SHORT).show();*/
                    /*Intent intent=new Intent(LoginActivity.this,Activity_Principal.class);
                    intent.putExtra("o","o1");
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);*/
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Usuario no existe", Toast.LENGTH_SHORT).show();
                    e1.setText("");
                    e2.setText("");
                }

            }
        });
        opciones();
        login_facebook();
        login_google();
    }
    private void login_facebook() {


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                goMainScreen();
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
    private void login_google() {
        loginGoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent=new Intent(LoginActivity.this,SesionGoogleLogin.class);*/
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
               /* startActivity(intent);*/
            }
        });
    }
    private void opciones() {
       /* crear_cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*Intent intent=new Intent(LoginActivity.this,CrearCuentaGeneral.class);
                startActivity(intent);*//*
            }
        });
        sin_cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*Intent intent=new Intent(LoginActivity.this,Activity_Principal.class);
                startActivity(intent);*//*
            }
        });*/
    }
    private void goMainScreen() {
        /*Intent intent = new Intent(LoginActivity.this, Activity_Principal.class);
        startActivity(intent);*/
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    private void BuscarUsuario() {

        Boolean ema=emailValidator(e1.getText().toString());
        if(ema==true){
/*
            for(int i=0;i<Sesion.LISTA_USUARIOS.size();i++){
                if(Sesion.LISTA_USUARIOS.get(i).getCorreo().equalsIgnoreCase(e1.getText().toString()) &&
                        Sesion.LISTA_USUARIOS.get(i).getPass().equalsIgnoreCase(e2.getText().toString())){

                    Sesion.USUARIO.setCorreo(Sesion.LISTA_USUARIOS.get(i).getCorreo());
                    Sesion.USUARIO.setNombre(Sesion.LISTA_USUARIOS.get(i).getNombre());
                    Sesion.USUARIO.setFoto(Sesion.LISTA_USUARIOS.get(i).getFoto());
                    Sesion.USUARIO.setPass(Sesion.LISTA_USUARIOS.get(i).getPass());
                    Sesion.USUARIO.setId(Sesion.LISTA_USUARIOS.get(i).getId());
                    encontrado=true;

                }else{
                    Sesion.USUARIO.setCorreo(null);
                    Sesion.USUARIO.setNombre(null);
                    Sesion.USUARIO.setFoto(null);
                    Sesion.USUARIO.setPass(null);
                    Sesion.USUARIO.setId(null);
                    encontrado=false;

                }
            }*/

        }else{
            Toast.makeText(this, "Formato de Email Incorrecto", Toast.LENGTH_SHORT).show();
            e1.setFocusable(true);
        }

    }

    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
