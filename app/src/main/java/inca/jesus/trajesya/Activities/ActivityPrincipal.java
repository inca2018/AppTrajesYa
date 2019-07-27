package inca.jesus.trajesya.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import inca.jesus.trajesya.Clases.ProductoX;
import inca.jesus.trajesya.Clases.Sesion;

import inca.jesus.trajesya.Fragmentos.Fragment1;
import inca.jesus.trajesya.Fragmentos.Fragment2;
import inca.jesus.trajesya.Fragmentos.Fragment3;
import inca.jesus.trajesya.Fragmentos.Fragment4;
import inca.jesus.trajesya.Fragmentos.FragmentBuscando;
import inca.jesus.trajesya.Fragmentos.SesionFragment;
import inca.jesus.trajesya.R;

public class ActivityPrincipal extends AppCompatActivity implements SearchView.OnQueryTextListener {
    Toolbar toolbar = null;
    ImageButton o1,o2,o3,o4,o5;
    TextView opt1,opt2,opt3,opt4,opt5;
    LinearLayout l1,l2,l3,l4,l5;
    ProductoX p;
    CardView boton_video,boton_kids,boton_fotos;
    private FragmentManager fragmentManager;
    private Fragment fragment = null;
    private SearchView search;

    private CardView linear_search;
    public ProfileTracker profileTracker;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        fragmentManager = getSupportFragmentManager();
        toolbar = (Toolbar) findViewById(R.id.toolbar_base);
        setSupportActionBar(toolbar);
        linear_search=(CardView) findViewById(R.id.card_linear_2);
        //boton_kids=(CardView)findViewById(R.id.panel_boton_cumpleKids);
        //boton_fotos=(CardView)findViewById(R.id.panel_boton_cumplefotos);

       /* boton_kids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityPrincipal.this,ListaProductos.class);
                startActivity(intent);
            }
        });
        boton_fotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityPrincipal.this,CumpleFotos.class);
                startActivity(intent);
            }
        });*/

        if(Sesion.USUARIO.getId()==null){
            System.out.println("INKA: USUARIO VACIO");
        }else{
            System.out.println("INKA: USUARIO"+Sesion.USUARIO.getNombre());
        }

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_1:
                                Opcion1();
                                return true;
                            case R.id.action_2:
                                Opcion2();
                                return true;
                            case R.id.action_3:
                                Opcion3();
                                return true;
                            case R.id.action_4:
                                Opcion4();
                                return true;
                            case R.id.action_5:
                                Opcion5();
                                return true;
                        }
                        return false;
                    }
                });

        linear_search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(ActivityPrincipal.this, "Buscador por Linear", Toast.LENGTH_SHORT).show();
                search.setIconified(false);
            }
        });
        //boton_video=(CardView)findViewById(R.id.panel_boton_videos);
        /*boton_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Activity_Principal.this,Videos.class);
                startActivity(intent);
            }
        });*/
        search=(SearchView)findViewById(R.id.search_datos);
        search.setOnQueryTextListener(this);


        l1=(LinearLayout)findViewById(R.id.linear1);
        l2=(LinearLayout)findViewById(R.id.linear2);
        l3=(LinearLayout)findViewById(R.id.linear3);
        l4=(LinearLayout)findViewById(R.id.linear4);
        l5=(LinearLayout)findViewById(R.id.linear5);

        o1=(ImageButton)findViewById(R.id.ope_1);
        o2=(ImageButton)findViewById(R.id.ope_2);
        o3=(ImageButton)findViewById(R.id.ope_3);
        o4=(ImageButton)findViewById(R.id.ope_4);
        o5=(ImageButton)findViewById(R.id.ope_5);
        opt1=(TextView)findViewById(R.id.opt1);
        opt2=(TextView)findViewById(R.id.opt2);
        opt3=(TextView)findViewById(R.id.opt3);
        opt4=(TextView)findViewById(R.id.opt4);
        opt5=(TextView)findViewById(R.id.opt5);

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opcion1();

            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opcion2();
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opcion3();
            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opcion4();
            }
        });
        l5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opcion5();
            }
        });

        o1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opcion1();

            }
        });
        o2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opcion2();
            }
        });
        o3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opcion3();
            }
        });
        o4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opcion4();
            }
        });
        o5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opcion5();
            }
        });

        opt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opcion1();

            }
        });
        opt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opcion2();
            }
        });
        opt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opcion3();
            }
        });
        opt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opcion4();
            }
        });
        opt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opcion5();
            }
        });

        if(getIntent().getStringExtra("o")==null){
            fragment = new Fragment1();
            fragmentManager.beginTransaction().replace(R.id.contenedor,fragment).commit();

        }else  if(getIntent().getStringExtra("o").equalsIgnoreCase("o1")){
            Opcion1();
        }else  if(getIntent().getStringExtra("o").equalsIgnoreCase("o2")){
            Opcion2();
        }else  if(getIntent().getStringExtra("o").equalsIgnoreCase("o3")){
            Opcion3();
        }else  if(getIntent().getStringExtra("o").equalsIgnoreCase("o4")){
            Opcion4();
        }else  if(getIntent().getStringExtra("o").equalsIgnoreCase("o5")){
            Opcion5();
        }
        set_Datos_fb();
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
            //Intent intent = new Intent(Activity_Principal.this, LoginGeneral.class);
            //startActivity(intent);
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
    public void Opcion1() {
        o1.setImageResource(R.drawable.ic_inicio_menu);
        o2.setImageResource(R.drawable.ic_categoria3);
        o3.setImageResource(R.drawable.ic_favoritos3);
        o4.setImageResource(R.drawable.ic_carrito3);
        o5.setImageResource(R.drawable.ic_perfil3);

        opt1.setTextColor(getResources().getColor(R.color.blanco));
        opt2.setTextColor(getResources().getColor(R.color.noFocus));
        opt3.setTextColor(getResources().getColor(R.color.noFocus));
        opt4.setTextColor(getResources().getColor(R.color.noFocus));
        opt5.setTextColor(getResources().getColor(R.color.noFocus));

        fragment = new Fragment1();
        fragmentManager.beginTransaction().replace(R.id.contenedor,fragment).commit();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.trim().isEmpty()) {
                    fragment = new Fragment1();
                    fragmentManager.beginTransaction().replace(R.id.contenedor,fragment).commit();
                    return false;
                }

                List<ProductoX> filteredValues = new ArrayList<ProductoX>(ProductoX.TODO);
                for (ProductoX value : ProductoX.TODO) {
                    if (!value.getNom_producto().toLowerCase().contains(newText.toLowerCase())) {
                        filteredValues.remove(value);
                    }
                }
                p.setBUSCADOR(filteredValues);

                fragment = new FragmentBuscando();
                fragmentManager.beginTransaction().replace(R.id.contenedor,fragment).commit();



                return false;
            }
        });

    }
    public void Opcion2() {
        o1.setImageResource(R.drawable.ic_inicio3);
        o2.setImageResource(R.drawable.ic_categoria_blanco_ofii);
        o3.setImageResource(R.drawable.ic_favoritos3);
        o4.setImageResource(R.drawable.ic_carrito3);
        o5.setImageResource(R.drawable.ic_perfil3);

        opt1.setTextColor(getResources().getColor(R.color.noFocus));
        opt2.setTextColor(getResources().getColor(R.color.blanco));
        opt3.setTextColor(getResources().getColor(R.color.noFocus));
        opt4.setTextColor(getResources().getColor(R.color.noFocus));
        opt5.setTextColor(getResources().getColor(R.color.noFocus));


        fragment = new Fragment2();
        fragmentManager.beginTransaction().replace(R.id.contenedor,fragment).commit();

        search.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.trim().isEmpty()) {
                    fragment = new Fragment2();
                    fragmentManager.beginTransaction().replace(R.id.contenedor,fragment).commit();
                    return false;
                }

                List<ProductoX> filteredValues = new ArrayList<ProductoX>(ProductoX.TODO);
                for (ProductoX value : ProductoX.TODO) {
                    if (!value.getNom_producto().toLowerCase().contains(newText.toLowerCase())) {
                        filteredValues.remove(value);
                    }
                }
                p.setBUSCADOR(filteredValues);

                fragment = new FragmentBuscando();
                fragmentManager.beginTransaction().replace(R.id.contenedor,fragment).commit();



                return false;
            }
        });


    }
    public void Opcion3() {
        o1.setImageResource(R.drawable.ic_inicio3);
        o2.setImageResource(R.drawable.ic_categoria3);
        o3.setImageResource(R.drawable.ic_favorito_blanco_of);
        o4.setImageResource(R.drawable.ic_carrito3);
        o5.setImageResource(R.drawable.ic_perfil3);

        opt1.setTextColor(getResources().getColor(R.color.noFocus));
        opt2.setTextColor(getResources().getColor(R.color.noFocus));
        opt3.setTextColor(getResources().getColor(R.color.blanco));
        opt4.setTextColor(getResources().getColor(R.color.noFocus));
        opt5.setTextColor(getResources().getColor(R.color.noFocus));


        fragment = new Fragment3();
        fragmentManager.beginTransaction().replace(R.id.contenedor,fragment).commit();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.trim().isEmpty()) {
                    fragment = new Fragment3();
                    fragmentManager.beginTransaction().replace(R.id.contenedor,fragment).commit();
                    return false;
                }

                List<ProductoX> filteredValues = new ArrayList<ProductoX>(ProductoX.TODO);
                for (ProductoX value : ProductoX.TODO) {
                    if (!value.getNom_producto().toLowerCase().contains(newText.toLowerCase())) {
                        filteredValues.remove(value);
                    }
                }
                p.setBUSCADOR(filteredValues);

                fragment = new FragmentBuscando();
                fragmentManager.beginTransaction().replace(R.id.contenedor,fragment).commit();




                return false;
            }
        });


    }
    public void Opcion4() {
        o1.setImageResource(R.drawable.ic_inicio3);
        o2.setImageResource(R.drawable.ic_categoria3);
        o3.setImageResource(R.drawable.ic_favoritos3);
        o4.setImageResource(R.drawable.ic_carrito_blanco_ofi);
        o5.setImageResource(R.drawable.ic_perfil3);

        opt1.setTextColor(getResources().getColor(R.color.noFocus));
        opt2.setTextColor(getResources().getColor(R.color.noFocus));
        opt3.setTextColor(getResources().getColor(R.color.noFocus));
        opt4.setTextColor(getResources().getColor(R.color.blanco));
        opt5.setTextColor(getResources().getColor(R.color.noFocus));



        fragment = new Fragment4();
        fragmentManager.beginTransaction().replace(R.id.contenedor,fragment).commit();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.trim().isEmpty()) {
                    fragment = new Fragment4();
                    fragmentManager.beginTransaction().replace(R.id.contenedor,fragment).commit();
                    return false;
                }

                List<ProductoX> filteredValues = new ArrayList<ProductoX>(ProductoX.TODO);
                for (ProductoX value : ProductoX.TODO) {
                    if (!value.getNom_producto().toLowerCase().contains(newText.toLowerCase())) {
                        filteredValues.remove(value);
                    }
                }
                p.setBUSCADOR(filteredValues);

                fragment = new FragmentBuscando();
                fragmentManager.beginTransaction().replace(R.id.contenedor,fragment).commit();




                return false;
            }
        });

    }
    public void Opcion5() {

        o1.setImageResource(R.drawable.ic_inicio3);
        o2.setImageResource(R.drawable.ic_categoria3);
        o3.setImageResource(R.drawable.ic_favoritos3);
        o4.setImageResource(R.drawable.ic_carrito3);
        o5.setImageResource(R.drawable.ic_perfil_blanco_ofii);

        opt1.setTextColor(getResources().getColor(R.color.noFocus));
        opt2.setTextColor(getResources().getColor(R.color.noFocus));
        opt3.setTextColor(getResources().getColor(R.color.noFocus));
        opt4.setTextColor(getResources().getColor(R.color.noFocus));
        opt5.setTextColor(getResources().getColor(R.color.blanco));


        /*if(Sesion.USUARIO.getId()==null){
            fragment = new Fragment5();
            fragmentManager.beginTransaction().replace(R.id.contenedor,fragment).commit();
        }else{*/
        fragment = new SesionFragment();
        fragmentManager.beginTransaction().replace(R.id.contenedor,fragment).commit();
        /*}*/

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.trim().isEmpty()) {
                    fragment = new SesionFragment();
                    fragmentManager.beginTransaction().replace(R.id.contenedor,fragment).commit();
                    return false;
                }
                List<ProductoX> filteredValues = new ArrayList<ProductoX>(ProductoX.TODO);
                for (ProductoX value : ProductoX.TODO) {
                    if (!value.getNom_producto().toLowerCase().contains(newText.toLowerCase())) {
                        filteredValues.remove(value);
                    }
                }
                p.setBUSCADOR(filteredValues);
                fragment = new FragmentBuscando();
                fragmentManager.beginTransaction().replace(R.id.contenedor,fragment).commit();
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

        List<ProductoX> filteredValues = new ArrayList<ProductoX>(ProductoX.TODO);
        for (ProductoX value : ProductoX.TODO) {
            if (!value.getNom_producto().toLowerCase().contains(newText.toLowerCase())) {
                filteredValues.remove(value);
            }
        }
        p.setBUSCADOR(filteredValues);

        fragment = new FragmentBuscando();
        fragmentManager.beginTransaction().replace(R.id.contenedor,fragment).commit();

        return false;
    }
    private void resetSearch() {
        fragment = new Fragment1();
        fragmentManager.beginTransaction().replace(R.id.contenedor,fragment).commit();
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
        Sesion.USUARIO.setCorreo(email);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        profileTracker.stopTracking();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(ActivityPrincipal.this,LoginActivity.class);
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

    }
}
