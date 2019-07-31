package inca.jesus.trajesya.Fragmentos;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import inca.jesus.trajesya.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentVideos extends Fragment {


    public FragmentVideos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_fragment_videos, container, false);
    }



}
