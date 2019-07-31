package inca.jesus.trajesya.Fragmentos;

import android.content.Intent;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;


import inca.jesus.trajesya.Activities.LoginActivity;
import inca.jesus.trajesya.Clases.Sesion;
import inca.jesus.trajesya.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SesionFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {
    ImageView foto;
    TextView nombre;
    Button logout;
    LinearLayout modulo1;
    LinearLayout modulo2;
    Button op1,op2,op3,op4,op5,op6;
    boolean menu;
    Button regreso;

    TextView correo_user;

    private GoogleApiClient googleApliClient;

    public SesionFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vie =inflater.inflate(R.layout.fragment_sesion, container, false);
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
        modulo1=(LinearLayout)vie.findViewById(R.id.modulo1);
        modulo2=(LinearLayout)vie.findViewById(R.id.modulo2);
        foto=(ImageView)vie.findViewById(R.id.sesion_imagen);
        nombre=(TextView)vie.findViewById(R.id.sesion_nombre);
        logout=(Button)vie.findViewById(R.id.boton_cerrar_sesion);
        op1=(Button)vie.findViewById(R.id.perfil_op1);
        op2=(Button)vie.findViewById(R.id.perfil_op2);
        op3=(Button)vie.findViewById(R.id.perfil_op3);
        op4=(Button)vie.findViewById(R.id.perfil_op4);
        op5=(Button)vie.findViewById(R.id.perfil_op5);
        op6=(Button)vie.findViewById(R.id.perfil_op6);
        correo_user=(TextView)vie.findViewById(R.id.correo_user);

        modulo1.setVisibility(View.VISIBLE);
        modulo2.setVisibility(View.GONE);

        op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu=true;
                modulo1.setVisibility(View.GONE);
                modulo2.setVisibility(View.VISIBLE);
            }
        });
        op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu=true;
                modulo1.setVisibility(View.GONE);
                modulo2.setVisibility(View.VISIBLE);
            }
        });

        op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu=true;
                modulo1.setVisibility(View.GONE);
                modulo2.setVisibility(View.VISIBLE);
            }
        });
        op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu=true;
                modulo1.setVisibility(View.GONE);
                modulo2.setVisibility(View.VISIBLE);
            }
        });

        op5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu=true;
                modulo1.setVisibility(View.GONE);
                modulo2.setVisibility(View.VISIBLE);
            }
        });
        op6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu=true;
                modulo1.setVisibility(View.GONE);
                modulo2.setVisibility(View.VISIBLE);
            }
        });

       if(Sesion.USUARIO.getId()==null){
           nombre.setText("Bienvenido");
           correo_user.setText("Invitado");
           logout.setVisibility(View.GONE);
       }else{
           Glide.with(this).load(Sesion.USUARIO.getFoto()).into(foto);
           nombre.setText(Sesion.USUARIO.getNombre());
           correo_user.setText(Sesion.USUARIO.getCorreo());
           logout.setVisibility(View.VISIBLE);
       }



        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApliClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Auth.GoogleSignInApi.signOut(googleApliClient).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        if(status.isSuccess()){
                            System.out.println( "INKA: Si cerro sesion");
                            inicieSesion();
                        }else{
                            System.out.println( "INKA: NO cerro sesion");
                            Toast.makeText(getActivity(), "Error al cerrar sesión", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        return vie;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        googleApliClient.stopAutoManage(getActivity());
        googleApliClient.disconnect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void inicieSesion() {

        LoginManager.getInstance().logOut();

        System.out.println("INKA: CERRO_SESION"+Sesion.USUARIO.getNombre());
        Sesion.USUARIO.setId(null);
        Sesion.USUARIO.setNombre(null);
        Sesion.USUARIO.setCorreo(null);
        Sesion.USUARIO.setFoto(null);
        System.out.println("INKA: CERRO_SESION"+Sesion.USUARIO.getNombre());

        Intent intent=new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }





}
