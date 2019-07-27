package inca.jesus.trajesya.Activities;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import inca.jesus.trajesya.R;

public class Splash extends AppCompatActivity {

    private ProgressBar p;
    private TextView txtProgress;

    private int pStatus = 0;
    private Handler handler = new Handler();
    ImageView ima;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        p = findViewById(R.id.progress);
        txtProgress =  findViewById(R.id.texxt_progress);

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
                        Thread.sleep(80);
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
        Intent intent = new Intent(Splash.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
