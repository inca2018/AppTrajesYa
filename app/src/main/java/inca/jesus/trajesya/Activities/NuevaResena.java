package inca.jesus.trajesya.Activities;

import android.content.Intent;
import android.os.Bundle;

import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import inca.jesus.trajesya.Clases.ListResenas;
import inca.jesus.trajesya.Clases.ProductoX;
import inca.jesus.trajesya.Clases.Resenas;
import inca.jesus.trajesya.Clases.Sesion;
import inca.jesus.trajesya.R;

public class NuevaResena extends AppCompatActivity {

    RatingBar rat;
    EditText titulo,desc;
    Button boton;
    Resenas re;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_resena);
        rat=(RatingBar)findViewById(R.id.nueva_resena_rating);
        titulo=(EditText) findViewById(R.id.nueva_resena_titulo);
        desc=(EditText) findViewById(R.id.nueva_resena_desc);
        boton=(Button) findViewById(R.id.nueva_resena_boton);



        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Time today = new Time(Time.getCurrentTimezone());
                today.setToNow();
                int monthDay = today.monthDay;
                int month = today.month+1;
                int year=today.year;
                String fecha=monthDay+"/"+month+"/"+year;

                Bundle extras=getIntent().getExtras();
                ProductoX prod=extras.getParcelable("Producto");

                 re=new Resenas(1,prod, Sesion.USUARIO.getNombre(),fecha, Float.valueOf(rat.getRating()),
                         titulo.getText().toString(),desc.getText().toString());
                 ListResenas.ALMACEN_RESENAS.add(re);

                Toast.makeText(NuevaResena.this, "Reseña Registrado con Exito!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(NuevaResena.this,Item.class);
                intent.putExtra("Producto",extras.getParcelable("Producto"));
                startActivity(intent);
                finish();

            }
        });


    }
}
