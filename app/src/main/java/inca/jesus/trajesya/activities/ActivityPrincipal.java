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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import inca.jesus.trajesya.clases.CarouselView;


import inca.jesus.trajesya.clases.Perfil;
import inca.jesus.trajesya.data.conexion.VolleySingleton;
import inca.jesus.trajesya.data.modelo.Producto;
import inca.jesus.trajesya.data.modelo.Sesion;
import inca.jesus.trajesya.data.modelo.Usuario;
import inca.jesus.trajesya.data.utils.Constantes;
import inca.jesus.trajesya.fragmentos.fragmentEnvioReserva;
import inca.jesus.trajesya.fragmentos.fragmentInicio;
import inca.jesus.trajesya.fragmentos.fragmentCategorias;
import inca.jesus.trajesya.fragmentos.Fragment3;
import inca.jesus.trajesya.fragmentos.fragmentReserva;
import inca.jesus.trajesya.fragmentos.FragmentBuscando;
import inca.jesus.trajesya.fragmentos.fragmentItem;
import inca.jesus.trajesya.fragmentos.fragmentSesion;
import inca.jesus.trajesya.R;

public class ActivityPrincipal extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private FragmentManager fragmentManager;
    private Fragment fragment = null;
    private SearchView search;
    private CardView linear_search;
    public ProfileTracker profileTracker;
    BottomNavigationView bottomNavigationView;
    Context context;
    Sesion sesion;
    String[] Rutas;
    SharedPreferences.Editor editor;
    public int counter = 8;
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
                                opcionInicio();
                                return true;
                            case R.id.action_2:
                                opcionCategorias();
                                return true;
                            case R.id.action_4:
                                opcionReserva(false);
                                return true;
                            case R.id.action_5:
                                opcionSesion();
                                return true;
                        }
                        return false;
                    }
                });
    }
    public void SelectMenu(int actionId){
        Menu menu=bottomNavigationView.getMenu();
        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            if(item.getItemId() == actionId){
                item.setChecked(true);
            }
        }
    }
    private void verificacionMostrarVista() {
        if (getIntent().getStringExtra("o") == null) {
            fragment = new fragmentInicio();
            fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();
        } else if (getIntent().getStringExtra("o").equalsIgnoreCase("o1")) {
            opcionInicio();
        } else if (getIntent().getStringExtra("o").equalsIgnoreCase("o2")) {
            opcionCategorias();
        } else if (getIntent().getStringExtra("o").equalsIgnoreCase("o3")) {
            opcion3();
        } else if (getIntent().getStringExtra("o").equalsIgnoreCase("o4")) {
            opcionReserva(false);
        } else if (getIntent().getStringExtra("o").equalsIgnoreCase("o5")) {
            opcionSesion();
        }else if(getIntent().getStringExtra("o").equalsIgnoreCase("Item")){
            int idProducto=Integer.parseInt(getIntent().getStringExtra("idProducto"));
            opcionItem(idProducto);
        }
    }

    public void verificacionPublicidad() {
        if (!Constantes.CANTIDAD_PUBLICIDAD) {
            Mostrar_Publicidad();
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

    public void opcionInicio() {
        SelectMenu(R.id.action_1);
        fragment = new fragmentInicio();
        fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.trim().isEmpty()) {
                    fragment = new fragmentInicio();
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

    public void opcionCategorias() {

        SelectMenu(R.id.action_2);
        fragment = new fragmentCategorias();
        fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();

        search.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.trim().isEmpty()) {
                    fragment = new fragmentCategorias();
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

    public void opcionReserva(boolean Limpiar) {
        if(Limpiar){
            fragmentItem fragmentItem = new fragmentItem();
            fragmentItem.LimpiarTallas();
        }

        SelectMenu(R.id.action_4);
        fragment = new fragmentReserva();
        fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.trim().isEmpty()) {
                    fragment = new fragmentReserva();
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

    public void opcionSesion() {
        SelectMenu(R.id.action_5);
        fragment = new fragmentSesion();
        fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.trim().isEmpty()) {
                    fragment = new fragmentSesion();
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

    public void opcionItem(int idProducto) {
        SelectMenu(R.id.action_1);
        fragment = new fragmentItem();
        Bundle bundle=new Bundle();
        bundle.putInt("idProducto",idProducto);
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.trim().isEmpty()) {
                    Producto temporal=sesion.RecuperarProducto(context);
                    if(temporal.getIdProducto()>0){
                        fragment = new fragmentItem();
                        Bundle bundle=new Bundle();
                        bundle.putInt("idProducto",temporal.getIdProducto());
                        fragment.setArguments(bundle);
                    }else{
                        fragment = new fragmentInicio();
                    }

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
    public void opcionEnvioReserva() {
        SelectMenu(R.id.action_4);
        fragment = new fragmentEnvioReserva();
       /* Bundle bundle=new Bundle();
        bundle.putInt("idProducto",idProducto);
        fragment.setArguments(bundle);*/
        fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.trim().isEmpty()) {
                    int tamReserva=Constantes.RESERVA_ITEMS.size();
                    if(tamReserva>0){
                        fragment = new fragmentEnvioReserva();
                      /*  Bundle bundle=new Bundle();
                        bundle.putInt("idProducto",temporal.getIdProducto());
                        fragment.setArguments(bundle);*/
                    }else{
                        fragment = new fragmentReserva();
                    }

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
        fragment = new fragmentInicio();
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
        verificarUsuarioFbLogin(context,profile);
    }

    private void verificarUsuarioFbLogin(final Context context,final Profile profile) {

        final String KEY_FB=profile.getId();

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
                                sesion.RegistrarSesion(context,Nuevo);
                                String Mensaje = jsonResponse.getString("mensaje");
                                Toast.makeText(context, Mensaje, Toast.LENGTH_SHORT).show();

                            } else {
                                sesion.RegistrarVariable(editor, context, "Sesion", "boolean", "false");
                                sesion.RegistrarVariable(editor, context, "SesionFB", "boolean", "true");
                                sesion.RegistrarVariable(editor, context, "KeyFacebook", "String", profile.getId());
                                sesion.RegistrarVariable(editor, context, "nombres", "String", profile.getFirstName());
                                sesion.RegistrarVariable(editor, context, "apellidos ", "String", profile.getLastName());
                                sesion.RegistrarVariable(editor, context, "imagenProducto", "String", String.valueOf(profile.getProfilePictureUri(100, 100)));
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
