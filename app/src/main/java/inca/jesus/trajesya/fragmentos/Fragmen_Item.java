package inca.jesus.trajesya.fragmentos;

import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import inca.jesus.trajesya.R;

public class Fragmen_Item extends Fragment {
    TextView dat;

    public Fragmen_Item() {
        // Required empty public constructor
    }


    public void LLamarMetodo(String dato){
        Toast.makeText(getActivity(), "dato:", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_item, container, false);

        dat=(TextView)view.findViewById(R.id.dato);
        // Inflate the layout for this fragment
        return view;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}