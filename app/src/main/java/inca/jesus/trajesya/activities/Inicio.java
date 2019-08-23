package inca.jesus.trajesya.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import inca.jesus.trajesya.R;

public class Inicio extends AppCompatActivity {

    private ProgressBar p;
    private TextView txtProgress;

    private int pStatus = 0;
    private Handler handler = new Handler();
    ImageView ima;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        p = (ProgressBar) findViewById(R.id.progress);
        txtProgress = (TextView) findViewById(R.id.texxt_progress);
        ima=(ImageView)findViewById(R.id.imagen_logo);
        //SVG svg = SVGParser.getSVGFromResource (getResources (), R.raw.logo2);
        //ima.setImageDrawable (svg.createPictureDrawable ());


        new Thread(new Runnable() {
            @Override
            public void run() {

                while (pStatus <= 100) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            p.setProgress(pStatus);
                            txtProgress.setText("Cargando... "+pStatus+" %");
                        }
                    });
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pStatus++;
                }

                runOnUiThread(new Runnable() {
                    public void run() {
                        mover();
                    }
                });

            }
        }).start();
    }

    void mover(){
        Intent intent = new Intent(Inicio.this,ActivityPrincipal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
