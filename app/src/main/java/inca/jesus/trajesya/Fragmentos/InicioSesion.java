package inca.jesus.trajesya.Fragmentos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import inca.jesus.trajesya.Activities.ActivityPrincipal;
import inca.jesus.trajesya.Activities.SesionGoogleLogin;
import inca.jesus.trajesya.Clases.Sesion;
import inca.jesus.trajesya.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class InicioSesion extends Fragment implements GoogleApiClient.OnConnectionFailedListener{
    private Button signInButton;
    private LoginButton loginButton;
    EditText email,pass;
    Button inicio_sesion;

    public CallbackManager callbackManager= CallbackManager.Factory.create();
    public ProfileTracker profileTracker;
    Context c;
    public InicioSesion() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_inicio_sesion, container, false);

        loginButton=(LoginButton)view.findViewById(R.id.loginButton2);
        loginButton.setReadPermissions("email");
        loginButton.setFragment(this);
        FacebookSdk.sdkInitialize(getApplicationContext());

        email=(EditText)view.findViewById(R.id.sesion_email);
        pass=(EditText)view.findViewById(R.id.sesion_pass);
        inicio_sesion=(Button)view.findViewById(R.id.boton_Inicio_Sesion);
        //google sesion
        signInButton=(Button)view.findViewById(R.id.botonSesion);

        inicio_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuscarUsuario();
            }
        });

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                System.out.println("INKA: valido callbackmanager");
                validar();
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

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SesionGoogleLogin.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return view;
    }
    private void validar() {

        System.out.println("INKA: entro validar");

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged (Profile oldProfile, Profile currentProfile) {
                if (currentProfile != null) {
                    System.out.println("INKA: entro if");
                    displayProfileInfo(currentProfile);
                }
            }
        };
        if (AccessToken.getCurrentAccessToken() == null) {
            goLoginScreen();
        } else {
            Profile profile = Profile.getCurrentProfile();
            if (profile != null) {
                displayProfileInfo(profile);
            } else {
                Profile.fetchProfileForCurrentAccessToken();
            }
        }
    }
    private void BuscarUsuario() {

     for(int i=0;i<Sesion.LISTA_USUARIOS.size();i++){
         if(Sesion.LISTA_USUARIOS.get(i).getCorreo().equalsIgnoreCase(email.getText().toString()) &&
            Sesion.LISTA_USUARIOS.get(i).getPass().equalsIgnoreCase(pass.getText().toString())){

             Sesion.USUARIO.setCorreo(Sesion.LISTA_USUARIOS.get(i).getCorreo());
             Sesion.USUARIO.setNombre(Sesion.LISTA_USUARIOS.get(i).getNombre());
             Sesion.USUARIO.setFoto(Sesion.LISTA_USUARIOS.get(i).getFoto());
             Sesion.USUARIO.setPass(Sesion.LISTA_USUARIOS.get(i).getPass());
             Sesion.USUARIO.setId(Sesion.LISTA_USUARIOS.get(i).getId());

             Toast.makeText(getActivity(), "Bienvenido:"+Sesion.LISTA_USUARIOS.get(i).getNombre() , Toast.LENGTH_SHORT).show();
             Intent intent=new Intent(getActivity(), ActivityPrincipal.class);
             intent.putExtra("o","o1");
             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
             startActivity(intent);

              }else{

             Sesion.USUARIO.setCorreo(null);
             Sesion.USUARIO.setNombre(null);
             Sesion.USUARIO.setFoto(null);
             Sesion.USUARIO.setPass(null);
             Sesion.USUARIO.setId(null);

             Toast.makeText(getActivity(), "Usuario no existe", Toast.LENGTH_SHORT).show();
             email.setText("");
             pass.setText("");

              }
         }

     }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c=this.getContext();

        /*LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        validar();
                        Log.i("success", "success");
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });*/
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    private void goLoginScreen() {
        Intent intent = new Intent(getActivity(),ActivityPrincipal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void displayProfileInfo(Profile profile) {
        System.out.println("INKA: entro display");
        Sesion.USUARIO.setId(profile.getId());
        Sesion.USUARIO.setFoto(profile.getProfilePictureUri(100,100));
        Sesion.USUARIO.setCorreo(profile.getLastName());
        Sesion.USUARIO.setNombre(profile.getName());

        System.out.println(Sesion.USUARIO.getNombre());
        System.out.println(Sesion.USUARIO.getCorreo());
        System.out.println( "INKA : Bienvenido: "+profile.getName());
        System.out.println( "INKA : Bienvenido ID: "+profile.getId());
        System.out.println( "INKA : USUARIO ID: "+Sesion.USUARIO.getId());
        System.out.println( "INKA : USUARIO NOMBRE: "+Sesion.USUARIO.getNombre());

        Intent intent = new Intent(getContext(),ActivityPrincipal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }


}
