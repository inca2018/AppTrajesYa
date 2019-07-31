package inca.jesus.trajesya.Activities;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import inca.jesus.trajesya.Clases.Sesion;
import inca.jesus.trajesya.R;

public class CrearCuentaGeneral extends AppCompatActivity {

    EditText o1,o2,o3,o4,o5;
    Button b1;
    Sesion temp;
    Boolean op1,op2,op3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        o1=(EditText)findViewById(R.id.nom_usuario_reg);
        o2=(EditText)findViewById(R.id.pass_usuario_reg);
        o3=(EditText)findViewById(R.id.correo_usuario_reg);
        o4=(EditText)findViewById(R.id.dni_usuario_reg);
        o5=(EditText)findViewById(R.id.telefono_usuario_reg);
        b1=(Button)findViewById(R.id.boton_guardar_registro);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(o1.getText().length()==0){
                    op1=false;
                }else{
                    op1=true;
                }
                if(o2.getText().length()==0){
                    op2=false;
                }else{
                    op2=true;
                }
                if(o3.getText().length()==0){
                    op3=false;
                }else{
                    op3=true;
                }
                Boolean tt=emailValidator(o3.getText().toString());


                if(op1==true&&op2==true&&op3==true){
                    if(tt==true){
                    System.out.println("Inka: op1:"+op1+" op2: "+op2+" op3: "+op3);
                    temp=new Sesion("111",null,o1.getText().toString(),o3.getText().toString(),o2.getText().toString());
                    Sesion.LISTA_USUARIOS.add(temp);
                    Toast.makeText(CrearCuentaGeneral.this, "Usuario Creado con exito!", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(CrearCuentaGeneral.this,LoginActivity.class);
                    startActivity(intent);
                    }else{
                        Toast.makeText(CrearCuentaGeneral.this,"Formato de Email incorrecto!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    String d="Completar los Campos:";
                    List<String> opciones=new ArrayList<String>();
                    if(op1==false){
                        String d1="Nombres";
                        opciones.add(d1);
                    }
                    if(op2==false){
                        String d1="Contraseña";
                        opciones.add(d1);
                    }
                    if(op3==false){
                        String d1="Correo";
                        opciones.add(d1);
                    }
                       for(int i=0;i<opciones.size();i++){
                         d=d.concat("\n - "+opciones.get(i)+" ");
                       }
                       Toast.makeText(CrearCuentaGeneral.this,d, Toast.LENGTH_SHORT).show();
                    }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent=new Intent(CrearCuentaGeneral.this,LoginActivity.class);
        startActivity(intent);

    }

    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
