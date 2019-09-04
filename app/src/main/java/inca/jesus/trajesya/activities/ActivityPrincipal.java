package inca.jesus.trajesya.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import androidx.appcompat.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import inca.jesus.trajesya.clases.CarouselView;


import inca.jesus.trajesya.data.modelo.Producto;
import inca.jesus.trajesya.data.modelo.Sesion;
import inca.jesus.trajesya.data.modelo.Usuario;
import inca.jesus.trajesya.data.utils.Constantes;
import inca.jesus.trajesya.fragmentos.Fragment1;
import inca.jesus.trajesya.fragmentos.Fragment2;
import inca.jesus.trajesya.fragmentos.Fragment3;
import inca.jesus.trajesya.fragmentos.Fragment4;
import inca.jesus.trajesya.fragmentos.FragmentBuscando;
import inca.jesus.trajesya.fragmentos.SesionFragment;
import inca.jesus.trajesya.R;

public class ActivityPrincipal extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private FragmentManager fragmentManager;
    private Fragment fragment = null;
    private SearchView search;
    public Constantes P;
    private CardView linear_search;
    public ProfileTracker profileTracker;
    BottomNavigationView bottomNavigationView;
    Context context;
    Sesion sesion;
    String[] Rutas;
    SharedPreferences.Editor editor;
    public int counter = 18;
    AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        context = getApplicationContext();
        sesion = new Sesion();
        fragmentManager = getSupportFragmentManager();
        linear_search = findViewById(R.id.card_linear_2);
        search = findViewById(R.id.search_datos);
        search.setOnQueryTextListener(this);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        /*---------RECUPERAR DATOS DE SHAREDPREFERENCES--------*/
        SharedPreferences pref = context.getSharedPreferences("Sesion", context.MODE_PRIVATE);
        editor = pref.edit();

        accionNavegador();
        accionClickBuscador();
        verificacionMostrarVista();
        set_Datos_fb();
        verificacionPublicidad();
    }

    private void accionClickBuscador() {
        linear_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(ActivityPrincipal.this, "Buscador por Linear", Toast.LENGTH_SHORT).show();
                search.setIconified(false);
            }
        });
    }

    private void accionNavegador() {
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_1:
                                opcion1();
                                return true;
                            case R.id.action_2:
                                opcion2();
                                return true;
                            case R.id.action_4:
                                opcion4();
                                return true;
                            case R.id.action_5:
                                opcion5();
                                return true;
                        }
                        return false;
                    }
                });
    }

    private void verificacionMostrarVista() {
        if (getIntent().getStringExtra("o") == null) {
            fragment = new Fragment1();
            fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();
        } else if (getIntent().getStringExtra("o").equalsIgnoreCase("o1")) {
            opcion1();
        } else if (getIntent().getStringExtra("o").equalsIgnoreCase("o2")) {
            opcion2();
        } else if (getIntent().getStringExtra("o").equalsIgnoreCase("o3")) {
            opcion3();
        } else if (getIntent().getStringExtra("o").equalsIgnoreCase("o4")) {
            opcion4();
        } else if (getIntent().getStringExtra("o").equalsIgnoreCase("o5")) {
            opcion5();
        }
    }

    public void verificacionPublicidad() {
        if (!Constantes.CANTIDAD_PUBLICIDAD) {
            //Mostrar_Publicidad();
            Constantes.CANTIDAD_PUBLICIDAD = true;
        }
    }

    private void Mostrar_Publicidad() {
        if (Constantes.Base_ListaPublicidad != null) {

            Rutas = new String[Constantes.Base_ListaPublicidad.size()];

            for (int i = 0; i < Constantes.Base_ListaPublicidad.size(); i++) {
                Rutas[i] = Constantes.PATH_IMAGEN + Constantes.Base_ListaPublicidad.get(i).getImagenPublicidad();
            }

            Collections.reverse(Constantes.Base_ListaPublicidad);


            final LayoutInflater inflater = (LayoutInflater) ActivityPrincipal.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View dialoglayout4 = inflater.inflate(R.layout.dialog_publicidad, null);
            final CarouselView carruselPublicidad = dialoglayout4.findViewById(R.id.carruselPublicidad);
            final TextView contador = dialoglayout4.findViewById(R.id.contador);

            carruselPublicidad.setImageResources(Rutas);
            carruselPublicidad.setOnPageClickListener(new CarouselView.OnPageClickListener() {
                @Override
                public void onPageClick(int position) {
                    Log.i("Inca", "Posicion:" + position);
                    Log.i("Inca", "Link Recuperado" + Constantes.Base_ListaPublicidad.get(position).getLinkPublicidad());
                    Uri uri = Uri.parse(Constantes.Base_ListaPublicidad.get(position).getLinkPublicidad());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });

            AlertDialog.Builder builder4 = new AlertDialog.Builder(ActivityPrincipal.this);
            builder4.setView(dialoglayout4);
            alerta = builder4.show();


            new CountDownTimer(Constantes.TIEMPO_PUBLICIDAD, 1000) {
                public void onTick(long millisUntilFinished) {
                    contador.setText(String.valueOf(counter));
                    counter--;
                }

                public void onFinish() {
                    alerta.dismiss();
                }
            }.start();
        }
    }

    private void set_Datos_fb() {

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if (currentProfile != null) {
                    displayProfileInfo(currentProfile);
                }
            }
        };

        if (AccessToken.getCurrentAccessToken() == null) {
            Usuario usuarioRecuperado=sesion.RecuperarSesion(context);
            if(!usuarioRecuperado.isSesion()){
                sesion.RegistrarVariable(editor, context, "Sesion", "boolean", "false");
                sesion.RegistrarVariable(editor, context, "SesionFB", "boolean", "false");
            }

        } else {
            requestEmail(AccessToken.getCurrentAccessToken());

            Profile profile = Profile.getCurrentProfile();
            if (profile != null) {
                displayProfileInfo(profile);
            } else {
                Profile.fetchProfileForCurrentAccessToken();
            }
        }
    }

    public void opcion1() {

        fragment = new Fragment1();
        fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.trim().isEmpty()) {
                    fragment = new Fragment1();
                    fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();
                    return false;
                }

                List<Producto> filteredValues = new ArrayList<>(Constantes.Base_Producto_Todo);
                for (Producto value : Constantes.Base_Producto_Todo) {
                    if (!value.getNombreProducto().toLowerCase().startsWith(newText.toLowerCase())) {
                        filteredValues.remove(value);
                    }
                }
                Constantes.PRODUCTOS_BUSCADOS = filteredValues;

                fragment = new FragmentBuscando();
                fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();

                return false;
            }
        });

    }

    public void opcion2() {


        fragment = new Fragment2();
        fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();

        search.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.trim().isEmpty()) {
                    fragment = new Fragment2();
                    fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();
                    return false;
                }

                List<Producto> filteredValues = new ArrayList<>(Constantes.Base_Producto_Todo);
                for (Producto value : Constantes.Base_Producto_Todo) {
                    if (!value.getNombreProducto().toLowerCase().startsWith(newText.toLowerCase())) {
                        filteredValues.remove(value);
                    }
                }
                Constantes.PRODUCTOS_BUSCADOS = filteredValues;

                fragment = new FragmentBuscando();
                fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();

                return false;
            }
        });


    }

    public void opcion3() {


        fragment = new Fragment3();
        fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.trim().isEmpty()) {
                    fragment = new Fragment3();
                    fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();
                    return false;
                }

                List<Producto> filteredValues = new ArrayList<>(Constantes.Base_Producto_Todo);
                for (Producto value : Constantes.Base_Producto_Todo) {
                    if (!value.getNombreProducto().toLowerCase().startsWith(newText.toLowerCase())) {
                        filteredValues.remove(value);
                    }
                }
                Constantes.PRODUCTOS_BUSCADOS = filteredValues;

                fragment = new FragmentBuscando();
                fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();

                return false;
            }
        });


    }

    public void opcion4() {

        fragment = new Fragment4();
        fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.trim().isEmpty()) {
                    fragment = new Fragment4();
                    fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();
                    return false;
                }

                List<Producto> filteredValues = new ArrayList<>(Constantes.Base_Producto_Todo);
                for (Producto value : Constantes.Base_Producto_Todo) {
                    if (!value.getNombreProducto().toLowerCase().startsWith(newText.toLowerCase())) {
                        filteredValues.remove(value);
                    }
                }
                Constantes.PRODUCTOS_BUSCADOS = filteredValues;

                fragment = new FragmentBuscando();
                fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();

                return false;
            }
        });

    }

    public void opcion5() {

        fragment = new SesionFragment();
        fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.trim().isEmpty()) {
                    fragment = new SesionFragment();
                    fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();
                    return false;
                }
                List<Producto> filteredValues = new ArrayList<>(Constantes.Base_Producto_Todo);
                for (Producto value : Constantes.Base_Producto_Todo) {
                    if (!value.getNombreProducto().toLowerCase().startsWith(newText.toLowerCase())) {
                        filteredValues.remove(value);
                    }
                }
                Constantes.PRODUCTOS_BUSCADOS = filteredValues;

                fragment = new FragmentBuscando();
                fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();
                return false;
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText == null || newText.trim().isEmpty()) {
            resetSearch();
            return false;
        }
        List<Producto> filteredValues = new ArrayList<>(Constantes.Base_Producto_Todo);
        for (Producto value : Constantes.Base_Producto_Todo) {
            if (!value.getNombreProducto().toLowerCase().startsWith(newText.toLowerCase())) {
                filteredValues.remove(value);
            }
        }
        Constantes.PRODUCTOS_BUSCADOS = filteredValues;

        fragment = new FragmentBuscando();
        fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();

        return false;
    }

    private void resetSearch() {
        fragment = new Fragment1();
        fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();
    }

    private void requestEmail(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if (response.getError() != null) {
                    Toast.makeText(getApplicationContext(), response.getError().getErrorMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    String email = object.getString("email");
                    setEmail(email);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        profileTracker.stopTracking();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("SALIR")
                .setMessage("¿Desea Cerrar Sesión?")
                .setPositiveButton("SI",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LoginManager.getInstance().logOut();
                                sesion.EliminarSesion(context);
                                Intent intent = new Intent(ActivityPrincipal.this, LoginActivity.class);
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

    private void displayProfileInfo(Profile profile) {
        sesion.RegistrarVariable(editor, context, "Sesion", "boolean", "false");
        sesion.RegistrarVariable(editor, context, "SesionFB", "boolean", "true");
        sesion.RegistrarVariable(editor, context, "KeyFacebook", "String", profile.getId());
        sesion.RegistrarVariable(editor, context, "nombres", "String", profile.getFirstName());
        sesion.RegistrarVariable(editor, context, "apellidos ", "String", profile.getLastName());
        sesion.RegistrarVariable(editor, context, "imagen", "String", String.valueOf(profile.getProfilePictureUri(100, 100)));

    }
}
