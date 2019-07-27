package inca.jesus.trajesya.Fragmentos;


import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import inca.jesus.trajesya.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment5 extends Fragment {
    ViewPager mViewPager;


    public Fragment5() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment5, container, false);

        mViewPager = (ViewPager) view.findViewById(R.id.pager2);
        poblarViewPager(mViewPager);
        TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs2);
        tabs.setupWithViewPager(mViewPager);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabs.setTabGravity(TabLayout.GRAVITY_CENTER);


        return view;
    }


    private void poblarViewPager(ViewPager viewPager) {
        Fragment5.AdaptadorSecciones adapter = new Fragment5.AdaptadorSecciones(getFragmentManager());
        adapter.addFragment(new InicioSesion(), getString(R.string.inicio_sesion));
        adapter.addFragment(new CrearCuenta(), getString(R.string.crear_cuenta));

        viewPager.setAdapter(adapter);
    }

    public class AdaptadorSecciones extends FragmentStatePagerAdapter {
        private final List<Fragment> fragmentos = new ArrayList<>();
        private final List<String> titulosFragmentos = new ArrayList<>();

        public AdaptadorSecciones(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentos.get(position);
        }

        @Override
        public int getCount() {
            return fragmentos.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentos.add(fragment);
            titulosFragmentos.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titulosFragmentos.get(position);
        }
    }

}
