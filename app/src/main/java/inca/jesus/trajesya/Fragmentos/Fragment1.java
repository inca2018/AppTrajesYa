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

public class Fragment1 extends Fragment  {
    ViewPager mViewPager;
    public Fragment1() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
            mViewPager = (ViewPager) view.findViewById(R.id.pager);
            poblarViewPager(mViewPager);
            TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);
            tabs.setupWithViewPager(mViewPager);
            tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        return view;
    }
    private void poblarViewPager(ViewPager viewPager) {
        AdaptadorSecciones adapter = new AdaptadorSecciones(getFragmentManager());
        adapter.addFragment(new Promociones(), getString(R.string.lo_mejor));
        adapter.addFragment(new Recomendaciones(), getString(R.string.Recomendaciones));
        adapter.addFragment(new Destacados(), getString(R.string.Destacados));



        //adapter.addFragment(new Categorias(), getString(R.string.Categorias));
        //adapter.addFragment(new LoNuevo(), getString(R.string.Lo_nuevo));
        //adapter.addFragment(new Fragment6(),getString(R.string.videos));
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
