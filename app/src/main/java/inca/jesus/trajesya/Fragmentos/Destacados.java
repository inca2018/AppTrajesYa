package inca.jesus.trajesya.Fragmentos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import inca.jesus.trajesya.Activities.ActivityPrincipal;
import inca.jesus.trajesya.Adapters.Adapter1;
import inca.jesus.trajesya.Adapters.Adapter3;
import inca.jesus.trajesya.Adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.Clases.ProductoX;
import inca.jesus.trajesya.R;

public class Destacados extends Fragment {
    private RecyclerView recycler1,recycler2,recycler3,recycler4;
    private LinearLayoutManager linearLayout1,linearLayout2,linearLayout3,linearLayout4;
    private Adapter1 adapter1,adapter2,adapter3,adapter4;
    private Adapter3 adapterT;
    CardView card1,card2,card3,card4;
    public Destacados() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_destacados, container, false);
        recycler1=(RecyclerView)view.findViewById(R.id.recycler1);
        recycler2=(RecyclerView)view.findViewById(R.id.recycler2);
        recycler3=(RecyclerView)view.findViewById(R.id.recycler3);
        recycler4=(RecyclerView)view.findViewById(R.id.recycler4);

        linearLayout1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        adapterT = new Adapter3(getActivity(), ProductoX.LCDx5, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {

            }
        });
        recycler1.setAdapter(adapterT);
        recycler1.setLayoutManager(linearLayout1);

        linearLayout2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        adapterT = new Adapter3(getActivity(),ProductoX.CELULARx5, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {

            }
        });
        recycler2.setAdapter(adapterT);
        recycler2.setLayoutManager(linearLayout2);

        linearLayout3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        adapterT = new Adapter3(getActivity(),ProductoX.CASACASx5, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {

            }
        });
        recycler3.setAdapter(adapterT);
        recycler3.setLayoutManager(linearLayout3);

        linearLayout4 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        adapterT = new Adapter3(getActivity(),ProductoX.BLUSAx5, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {

            }
        });
        recycler4.setAdapter(adapterT);
        recycler4.setLayoutManager(linearLayout4);


        card1=(CardView)view.findViewById(R.id.card1);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Promo1", Toast.LENGTH_SHORT).show();
            }
        });
        card2=(CardView)view.findViewById(R.id.card2);
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Promo2", Toast.LENGTH_SHORT).show();
            }
        });
        card3=(CardView)view.findViewById(R.id.card3);
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Promo3", Toast.LENGTH_SHORT).show();
            }
        });
        card4=(CardView)view.findViewById(R.id.card4);
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Promo4", Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void onBackPressed() {
        Intent intent =new Intent(getActivity(),ActivityPrincipal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK  | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        return;
    }
}
