package inca.jesus.trajesya.Activities;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import inca.jesus.trajesya.Clases.ProductoX;
import inca.jesus.trajesya.R;

public class ItemDetalle extends AppCompatActivity {

    ImageButton o1,o2,o3,o4,o5;
    TextView t1,t2,t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detalle);
        t1=(TextView)findViewById(R.id.item_det_descripcion);
        t2=(TextView)findViewById(R.id.item_det_caracteristicas);
        t3=(TextView)findViewById(R.id.item_det_detalles_producto);

        o1=(ImageButton)findViewById(R.id.ope_1);
        o2=(ImageButton)findViewById(R.id.ope_2);
        o3=(ImageButton)findViewById(R.id.ope_3);
        o4=(ImageButton)findViewById(R.id.ope_4);
        o5=(ImageButton)findViewById(R.id.ope_5);

        Bundle extras=getIntent().getExtras();
        ProductoX prod=extras.getParcelable("Producto");
        t1.setText(prod.getDescripcion());
        t2.setText(prod.getCaracteristicas());
        t3.setText(prod.getDetalle_Producto());



        o1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ItemDetalle.this,ActivityPrincipal.class);
                intent.putExtra("o","o1");
                startActivity(intent);
            }
        });
        o2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ItemDetalle.this,ActivityPrincipal.class);
                intent.putExtra("o","o2");
                startActivity(intent);
            }
        });
        o3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ItemDetalle.this,ActivityPrincipal.class);
                intent.putExtra("o","o3");
                startActivity(intent);
            }
        });
        o4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ItemDetalle.this,ActivityPrincipal.class);
                intent.putExtra("o","o4");
                startActivity(intent);
            }
        });
        o5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ItemDetalle.this,ActivityPrincipal.class);
                intent.putExtra("o","o5");
                startActivity(intent);
            }
        });
    }


}
