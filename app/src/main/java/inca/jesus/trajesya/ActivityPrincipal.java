package inca.jesus.trajesya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.ProfileTracker;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import inca.jesus.trajesya.Data.Fragmentos.FragmentBuscando;
import inca.jesus.trajesya.Data.Modelo.Producto;

public class ActivityPrincipal extends AppCompatActivity implements SearchView.OnQueryTextListener {
    Producto p;
    List<Producto> Productos;
    Toolbar toolbar = null;
    ImageButton o1, o2, o3, o4, o5;
    TextView opt1, opt2, opt3, opt4, opt5;
    LinearLayout l1, l2, l3, l4, l5;

    CardView boton_video, boton_kids, boton_fotos;
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
        Productos=new ArrayList<>();


    }

    //Accion al Buscar Producto
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    //Accion al Cambiar de Texto en la Busqueda
    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText == null || newText.trim().isEmpty()) {
            resetSearch();
            return false;
        }

        List<Producto> filteredValues = new ArrayList<Producto>(Productos);
        for (Producto value : Productos) {
            if (!value.getNombreProducto().toLowerCase().contains(newText.toLowerCase())) {
                filteredValues.remove(value);
            }
        }
        p.setBUSCADOR(filteredValues);

        fragment = new FragmentBuscando();
        fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();

        return false;

    }

    private void resetSearch() {
        fragment = new Fragment1();
        fragmentManager.beginTransaction().replace(R.id.contenedor, fragment).commit();
    }
}
