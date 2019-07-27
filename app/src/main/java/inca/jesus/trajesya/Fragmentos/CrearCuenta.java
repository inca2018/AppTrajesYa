package inca.jesus.trajesya.Fragmentos;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import inca.jesus.trajesya.Activities.ActivityPrincipal;
import inca.jesus.trajesya.Activities.LoginActivity;
import inca.jesus.trajesya.Activities.SesionGoogleLogin;

import inca.jesus.trajesya.Clases.Sesion;
import inca.jesus.trajesya.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CrearCuenta extends Fragment {

    private Button signInButton;
    private Button loginButton;
    EditText nom,ape,email,pass,dni,telf;
    Button guardar;
    Sesion temp;

    public CrearCuenta() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_crear_cuenta, container, false);
        nom=(EditText) view.findViewById(R.id.nueva_nom);
        ape=(EditText) view.findViewById(R.id.nueva_ape);
        email=(EditText) view.findViewById(R.id.nueva_email);
        pass=(EditText) view.findViewById(R.id.nueva_pass);
        dni=(EditText) view.findViewById(R.id.nueva_dni);
        telf=(EditText) view.findViewById(R.id.nueva_telf);
        guardar=(Button)view.findViewById(R.id.boton_crear);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp=new Sesion("11",null,nom.getText().toString()+" "+ape.getText().toString(),email.getText().toString(),pass.getText().toString());
                Sesion.LISTA_USUARIOS.add(temp);

                Toast.makeText(getActivity(), "Usuario Creado con exito", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), ActivityPrincipal.class);
                intent.putExtra("o","o1");
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        loginButton=(Button)view.findViewById(R.id.boton_sesion_Fb2);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });



        signInButton=(Button)view.findViewById(R.id.botonSesion2);


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(),SesionGoogleLogin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return view;
    }

}
