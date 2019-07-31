package inca.jesus.trajesya.Activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import inca.jesus.trajesya.Adapters.AdapterItemCompra;
import inca.jesus.trajesya.Adapters.RecyclerViewOnItemClickListener2;
import inca.jesus.trajesya.Clases.ListCompra;
import inca.jesus.trajesya.R;

public class CompraActivity extends AppCompatActivity {

    RecyclerView recycler;
    LinearLayoutManager linear1;
    AdapterItemCompra adapterXXX;
    ImageView boton_direc;
    LinearLayout l1,l2,l3,l4,l5,l6;
    ImageView b1,b2,b3,b4,b5;
    Button btn_g,btn_tp,btn_g_c,btn_g_r;
    Button op1,op2,op3;
    boolean a1=false,a2=false,a3=false,c1=false,c2=false;
    CardView panel1,panel2;
    Button bole,fac;
    private CardView card_fac;
    CheckBox c_condiciones;
    boolean status=false;
    AlertDialog d,s;
    TextView btn_cupon,text_cupon;
    String codigo="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);
        recycler=(RecyclerView)findViewById(R.id.recycler_compra_pedido);
        boton_direc=(ImageView)findViewById(R.id.boton_direccion);
        c_condiciones=(CheckBox)findViewById(R.id.check_condiciones);
        l1=(LinearLayout)findViewById(R.id.panel_principal);
        l2=(LinearLayout)findViewById(R.id.panel_nueva_direccion);
        l3=(LinearLayout)findViewById(R.id.panel_tipo_pago);
        l4=(LinearLayout)findViewById(R.id.panel_botoness);
        l5=(LinearLayout)findViewById(R.id.panel_Comprobante);
        l6=(LinearLayout)findViewById(R.id.panel_receptor_alterno);
        b1=(ImageView)findViewById(R.id.boton_direccion);
        btn_g=(Button)findViewById(R.id.guardar_direc_nueva);
        b2=(ImageView)findViewById(R.id.btn_tipo_pago);
        btn_tp=(Button)findViewById(R.id.btn_guardar_tipo_pago);
        b3=(ImageView) findViewById(R.id.btn_comprobante);
        op1=(Button)findViewById(R.id.btn_tarjetas);
        op2=(Button)findViewById(R.id.btn_trasnf);
        op3=(Button)findViewById(R.id.btn_contraentrega);
        panel1=(CardView)findViewById(R.id.panel_selec_tarjeta);
        panel2=(CardView)findViewById(R.id.panel_selec_tranf);
        panel1.setVisibility(View.GONE);
        panel2.setVisibility(View.GONE);
        b4=(ImageView)findViewById(R.id.btn_comprobante);
        btn_g_c=(Button)findViewById(R.id.btn_guardar_comrp);
        bole=(Button)findViewById(R.id.btn_boleta2);
        fac=(Button)findViewById(R.id.btn_factura2);
        card_fac=(CardView)findViewById(R.id.panel_factura);
        card_fac.setVisibility(View.GONE);
        b5=(ImageView)findViewById(R.id.btn_receptor);
        btn_g_r=(Button)findViewById(R.id.boton_guardar_recep);
        btn_cupon=(TextView) findViewById(R.id.btn_cupon);
        text_cupon=(TextView)findViewById(R.id.text_cupon);

        SegundoModulo();
        PrimerModulo();
        movimientos_entre_botones();
        recycler_item_compra();

        condiciones();

        if(status==true){
            c_condiciones.setChecked(true);
        }else{
            c_condiciones.setChecked(false);
        }

        adapterXXX.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();

                if(adapterXXX.getItemCount()==0){
                    Intent intent=new Intent(CompraActivity.this,ActivityPrincipal.class);
                    startActivity(intent);
                }
            }
        });

       btn_cupon.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final LayoutInflater inflater = (LayoutInflater) CompraActivity.this.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
               final View dialoglayout4 = inflater.inflate(R.layout.cupon, null);
               final EditText tex_cupon=(EditText)dialoglayout4.findViewById(R.id.codigo_cupon);
               final Button aceptar_cupon=(Button) dialoglayout4.findViewById(R.id.btn_guardar_cupon);
               aceptar_cupon.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       codigo=tex_cupon.getText().toString();
                       text_cupon.setText(codigo);
                       s.dismiss();
                   }
               });

               AlertDialog.Builder builder4 = new AlertDialog.Builder(CompraActivity.this);
               builder4.setView(dialoglayout4);
               s=builder4.show();
           }
       });

    }

    private void condiciones() {
        c_condiciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(status==false){
                    final LayoutInflater inflater = (LayoutInflater) CompraActivity.this.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                    final View dialoglayout4 = inflater.inflate(R.layout.terminos_condiciones, null);

                    final Button aceptar=(Button) dialoglayout4.findViewById(R.id.btn_acepto);
                    aceptar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            status=true;
                            d.dismiss();
                        }
                    });
                    AlertDialog.Builder builder4 = new AlertDialog.Builder(CompraActivity.this);
                    builder4.setView(dialoglayout4);
                    d=builder4.show();

                }else{
                    c_condiciones.setChecked(false);
                    status=false;
                }
            }
        });


    }
    private void SegundoModulo() {
        bole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("click bole");

                if(c1==false){
                    bole.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    fac.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    card_fac.setVisibility(View.GONE);
                }else if(c1==true){
                    bole.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });

        fac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("click fac");
                if(c2==false){
                    fac.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    bole.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    card_fac.setVisibility(View.VISIBLE);
                }else if(c2==true){
                    fac.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    card_fac.setVisibility(View.GONE);
                }

            }
        });

    }
    private void PrimerModulo() {
        op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a1==false){
                    a1=true;
                    op1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    op2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    op3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    panel2.setVisibility(View.GONE);
                    panel1.setVisibility(View.VISIBLE);
                }else if(a1=true){
                    a1=false;
                    op1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    panel1.setVisibility(View.GONE);
                }
            }
        });
        op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a2==false){
                    a2=true;
                    op2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    op3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    op1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    panel2.setVisibility(View.VISIBLE);
                    panel1.setVisibility(View.GONE);
                }else if(a2=true){
                    a2=false;
                    op2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    panel2.setVisibility(View.GONE);
                }
            }
        });
        op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a3==false){
                    a3=true;
                    op3.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    op1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    op2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    panel2.setVisibility(View.GONE);
                    panel1.setVisibility(View.GONE);
                }else if(a3=true){
                    a3=false;
                    op3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                }
            }
        });


    }
    private void movimientos_entre_botones() {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l2.setVisibility(View.VISIBLE);
                l1.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
            }
        });
        btn_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l2.setVisibility(View.GONE);
                l1.setVisibility(View.VISIBLE);
                l4.setVisibility(View.VISIBLE);
                l3.setVisibility(View.GONE);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l2.setVisibility(View.GONE);
                l1.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);
                l3.setVisibility(View.VISIBLE);
            }
        });


        btn_tp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l2.setVisibility(View.GONE);
                l1.setVisibility(View.VISIBLE);
                l4.setVisibility(View.VISIBLE);
                l3.setVisibility(View.GONE);
            }
        });

        btn_g_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l5.setVisibility(View.GONE);
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.VISIBLE);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l5.setVisibility(View.VISIBLE);
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l6.setVisibility(View.VISIBLE);
                l5.setVisibility(View.GONE);
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);
            }
        });

         btn_g_r.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 l4.setVisibility(View.VISIBLE);
                 l1.setVisibility(View.VISIBLE);
                 l6.setVisibility(View.GONE);
             }
         });
    }
    @SuppressLint("WrongConstant")
    private void recycler_item_compra() {
        linear1 = new LinearLayoutManager(CompraActivity.this, LinearLayoutManager.VERTICAL,false);
        adapterXXX = new AdapterItemCompra(CompraActivity.this, ListCompra.CARRITO_COMPRA, new RecyclerViewOnItemClickListener2() {
            @Override
            public void onClick(View v, int position) {

            }
        });
        recycler.setAdapter(adapterXXX);
        recycler.setLayoutManager(linear1);

    }

}
