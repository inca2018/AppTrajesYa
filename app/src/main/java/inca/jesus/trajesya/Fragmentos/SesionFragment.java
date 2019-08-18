package inca.jesus.trajesya.Fragmentos;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;
import inca.jesus.trajesya.Activities.LoginActivity;
import inca.jesus.trajesya.Data.Modelo.Sesion;
import inca.jesus.trajesya.Data.Modelo.Usuario;
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
    Button op1,op2,op3,op4,op5,op6;
    boolean menu;
    Button regreso;

    TextView correo_user;

    /*-------------------*/
    Sesion sesion;
    Context context;
    Usuario usuarioRecuperado;


    public SesionFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vie =inflater.inflate(R.layout.fragment_sesion, container, false);
        context=getActivity();
        sesion = new Sesion();
        usuarioRecuperado=sesion.RecuperarSesion(context);

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


       if(!usuarioRecuperado.isSesion()){
           nombre.setText("Bienvenido");
           correo_user.setText("Invitado");
           logout.setVisibility(View.GONE);
       }else{

           Picasso.get()
                   .load(usuarioRecuperado.getImagenUsuario())
                   .placeholder(R.drawable.default_imagen)
                   .error(R.drawable.default_imagen)
                   .into(foto);
           nombre.setText(usuarioRecuperado.getNombreUsuario()+usuarioRecuperado.getApellidoUsuario());
           correo_user.setText(usuarioRecuperado.getCorreoUsuario());
           logout.setVisibility(View.VISIBLE);
       }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CerrarSesion();
            }
        });

        return vie;
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




}
